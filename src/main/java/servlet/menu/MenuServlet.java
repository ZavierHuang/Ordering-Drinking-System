package servlet.menu;

import bean.menuItem.MenuItem;
import bean.orders.Orders;
import bean.product.Product;
import bean.user.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import service.adminService.AdminService;
import service.adminService.AdminServiceImpl;
import service.cartService.CartService;
import service.cartService.CartServiceImpl;
import service.ordersService.OrdersService;
import service.ordersService.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "MenuServlet",
        urlPatterns = {"/CustomerMenu", "/updateMenuItem" , "/addNewMenuItem", "/searchMenuItem", "/deleteMenuItem"}
)
public class MenuServlet extends HttpServlet {
    private static final String MENU_URL = "WEB-INF/jsp/customer/Menu.jsp";
    private static final String MENU_URL_EMPLOYEE = "WEB-INF/jsp/admin/employee.jsp";
    private static final String MENU_URL_ADMIN = "WEB-INF/jsp/admin/admin.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        int level = (int) session.getAttribute("levels");
        //System.out.println("level:"+level);
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }

        if(level<=9 &&level>=0){
            CartService cartService = new CartServiceImpl();
            List<Product> customerCart = cartService.getCustomerCart(account);
            System.out.println("customerCart:"+customerCart);
            Integer totalPrice = 0;
            for (Product product : customerCart) {
                totalPrice += product.getPrice();
            }
            //System.out.println("a customer login");
            String customerCartJson = new Gson().toJson(customerCart);
            session.setAttribute("customerCart", customerCartJson);
            session.setAttribute("totalPrice", totalPrice);
            request.getRequestDispatcher(MENU_URL).forward(request, response);
        }else if(level >= 10){
            String FORWARD_URL = MENU_URL_EMPLOYEE;
            if (level == 99){
                FORWARD_URL = MENU_URL_ADMIN;
                AdminService adminService = new AdminServiceImpl();
                List<User> allEmp = adminService.getAllEmp();
                String allEmpJson = new Gson().toJson(allEmp);
                session.setAttribute("allEmpJson", allEmpJson);
                session.setAttribute("empSum", allEmp.size());
            }
            OrdersService ordersService = new OrdersServiceImpl();
            List<Orders> allOrders = ordersService.getAllOrdersDetail();

            String allOrderJson = new Gson().toJson(allOrders);
            session.setAttribute("allOrderJson", allOrderJson);
            session.setAttribute("ordersSum",  allOrders.size());
            request.getRequestDispatcher(FORWARD_URL).forward(request, response);

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String path = request.getServletPath();  // 獲取當前請求的URL模式
        System.out.println("PATH:" + path);

        if("/addNewMenuItem".equals(path)) {
            Gson gson = new Gson();
            String newDataJson = request.getParameter("newData");
            MenuItem newMenuItem = gson.fromJson(newDataJson, MenuItem.class);

            AdminService adminService = new AdminServiceImpl();
            adminService.addNewMenuItem(newMenuItem);
        }
        else if("/updateMenuItem".equals(path)) {
            Gson gson = new Gson();
            String oldDataJson = request.getParameter("oldData");
            String newDataJson = request.getParameter("newData");

            MenuItem oldMenuItem = gson.fromJson(oldDataJson, MenuItem.class);
            MenuItem newMenuItem = gson.fromJson(newDataJson, MenuItem.class);

            AdminService adminService = new AdminServiceImpl();
            adminService.updateMenu(oldMenuItem, newMenuItem);

            List<MenuItem> allMenu = adminService.getAllMenuItem();
            String allMenuJson = new Gson().toJson(allMenu);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(allMenuJson);

        }
        else if("/searchMenuItem".equals(path)) {
            AdminService adminService = new AdminServiceImpl();
            Gson gson = new Gson();
            String searchSymbol = request.getParameter("searchSymbol");
            String searchDataJson = request.getParameter("searchData");
            MenuItem searchMenuItem = gson.fromJson(searchDataJson, MenuItem.class);
//            System.out.println("searchMenuItem:"+searchMenuItem);

            List<MenuItem> matchSearchMenuItems = adminService.searchMenu(searchMenuItem, searchSymbol);
            String searchMenuJson = new Gson().toJson(matchSearchMenuItems);
            int totalSum = matchSearchMenuItems.size();

            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("menuItems", searchMenuJson);
            responseJson.addProperty("totalItems", totalSum);
            String responseData = new Gson().toJson(responseJson);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(responseData);
        }
        else if("/deleteMenuItem".equals(path)){
            AdminService adminService = new AdminServiceImpl();
            String deleteMenuItemName = request.getParameter("deletData");
            adminService.deleteMenu(deleteMenuItemName);
        }
        else {
            String account = (String) session.getAttribute("account");
            String ud = request.getParameter("ud");
            switch (ud) {
                case "sendOrder":
                    Integer amount = Integer.parseInt(request.getParameter("amount"));
                    OrdersService ordersService = new OrdersServiceImpl();
                    ordersService.buildOrder(account, amount);
                    break;
                case "delete":
                    Integer productID = Integer.parseInt(request.getParameter("productID"));
                    CartService cartService = new CartServiceImpl();
                    cartService.deleteProduct(account, productID);
                    break;
                default:
                    break;
            }
        }
    }
}

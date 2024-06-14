package servlet.admin;

import bean.menuItem.MenuItem;
import bean.product.Product;
import bean.user.User;
import com.google.gson.Gson;
import service.adminService.AdminService;
import service.adminService.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "AdminServlet",
        urlPatterns = {"/admin", "/addEmploee", "/manageMenu", "/showAllEmp", "/delEmp", "/updateEmp"}
)
public class AdminServlet extends HttpServlet {
//    private static final String CUSTOMER_URL = "WEB-INF/jsp/products/Tea.jsp";
//    private static final String ADD_EMP_URL = "WEB-INF/jsp/products/addEmploee.jsp";
    private static final String ADD_EMP_URL = "WEB-INF/jsp/register/Register.jsp";
    private static final String MANAGE_MENU_URL = "WEB-INF/jsp/admin/manageMenu.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }


        String path = request.getServletPath();  // 獲取當前請求的URL模式

        System.out.println("path:"+path);

        if ("/manageMenu".equals(path)) {
            AdminService adminService = new AdminServiceImpl();
            List<Product> alltype = adminService.getAllType();
            List<MenuItem> allMenu = adminService.getAllMenuItem();
            String allMenuJson = new Gson().toJson(allMenu);
            String allTypeJson = new Gson().toJson(alltype);

//            System.out.println("allMenu:"+allMenu);
//            System.out.println("allMenuJson:"+allMenuJson);

            session.setAttribute("allTypeJson", allTypeJson);
            session.setAttribute("allMenuJson", allMenuJson);
            session.setAttribute("totalItem", allMenu.size());
            request.getRequestDispatcher(MANAGE_MENU_URL).forward(request, response);
        }else if ("/addEmploee".equals(path)) {
            request.setAttribute("isEmployee", "Y");
            request.getRequestDispatcher(ADD_EMP_URL).forward(request, response);
        } else if ("/showAllEmp".equals(path)) {


        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        String account = (String) session.getAttribute("account");

        String ud = request.getParameter("ud");
        String empAccount = request.getParameter("empAccount");
        //System.out.println("empAccount:"+empAccount);
        AdminService adminService = new AdminServiceImpl();
        switch (ud) {//updateEmp
            case "updateEmp":

//                account: account,
//                password: password,
//                level: level,
//                phone: phone

                String account = request.getParameter("account");
                String password = request.getParameter("password");
                String level = request.getParameter("level");
                String phone = request.getParameter("phone");

                adminService.updateEmployeebyAcc(new User(account,password,Integer.parseInt(level),phone));
                break;

            case "delEmp":

                adminService.deleteEmployeebyAcc(empAccount);
                break;
            default:
                break;
        }
    }







}

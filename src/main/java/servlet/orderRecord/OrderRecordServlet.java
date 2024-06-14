package servlet.orderRecord;

import bean.product.Product;
import com.google.gson.Gson;
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
        name = "OrderRecordServlet",
        urlPatterns = {"/OrderRecord"}
)
public class OrderRecordServlet extends HttpServlet {
    private static final String ORDERRECORD_URL = "WEB-INF/jsp/orderRecord/OrderRecord.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }
        request.getRequestDispatcher(ORDERRECORD_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();



        OrdersService ordersService = new OrdersServiceImpl();
        String account = (String) session.getAttribute("account");

        String ud = request.getParameter("ud");
        switch (ud) {
            case "query":
                String date = request.getParameter("date");
                session.setAttribute("date", date);

                List<Product> productList =  ordersService.getOrderProductsByDate(account, date);
                Integer status = ordersService.getOrderStatusByAcc(account,date);

                switch (status){
                    case 0:
                        session.setAttribute("status", "訂單已送出，請等待店員確認，謝謝!");
                        break;
                    case 1:
                        session.setAttribute("status", "訂單製作中...");
                        break;
                    case 2:
                        session.setAttribute("status", "訂單已完成！");
                        break;
                    case -1:
                        session.setAttribute("status", "訂單已取消！");
                        break;
                }



                String productInorderJson = new Gson().toJson(productList);
                session.setAttribute("productInorderJson", productInorderJson);
                break;

            case "queryCustomer":

                String customer_account = request.getParameter("account");
                String order_date = request.getParameter("date");

//                OrdersService ordersService = new OrdersServiceImpl();
                List<Product> order_productList =  ordersService.getOrderProductsByDate(customer_account, order_date);

                System.out.println("date:"+order_date);
                System.out.println("customer:"+customer_account);


                String customerProductInOrderJson = new Gson().toJson(order_productList);
                System.out.println("customerProductInOrderJson:"+customerProductInOrderJson);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().print(customerProductInOrderJson);
                break;


            case "cancelOrders":

                String orderDate = (String) session.getAttribute("date");

                Integer result = ordersService.cancelOrderByAcc(account,orderDate);
                response.setContentType("text/plain;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                String statusString = "";

                if(result.equals(0)){
                    statusString = "訂單取消成功！";
                }else if(result.equals(-1)){
                    statusString = "訂單取消失敗！";
                }else if(result.equals(-2)){
                    statusString = "訂單不可取消！";
                }
                response.getWriter().print(statusString);

                break;

            default:
                break;
        }







    }
}

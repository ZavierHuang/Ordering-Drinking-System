package servlet.employee;

import service.ordersService.OrdersService;
import service.ordersService.OrdersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "EmployeeServlet",
        urlPatterns = {"/updateOrder"}
)
public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }

        String path = request.getServletPath();  // 獲取當前請求的URL模式

        System.out.println("path:"+path);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String ud=request.getParameter("ud");
        int orderID=Integer.parseInt(request.getParameter("orderID"));

//        System.out.println("orderID:"+orderID);
//        System.out.println("ud:"+ud);
        OrdersService ordersService = new OrdersServiceImpl();
        switch (ud){
            case "updateOrder":
                int status=Integer.parseInt(request.getParameter("status"));
                System.out.println("status:"+status);
                ordersService.updateOrderStatus(orderID,status);
                break;
        }
    }
}

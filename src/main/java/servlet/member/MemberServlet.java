package servlet.member;

import bean.menuItem.MenuItem;
import bean.orders.Orders;
import bean.user.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import service.ordersService.OrdersService;
import service.ordersService.OrdersServiceImpl;
import service.userService.UserService;
import service.userService.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "MemberServlet",
        urlPatterns = {"/Member", "/updateUserInfo"}
)
public class MemberServlet extends HttpServlet {
    private static final String MEMBER_URL = "WEB-INF/jsp/member/Member.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String account = (String) session.getAttribute("account");
        if (account == null) {
            response.sendRedirect("Login");
            return;
        }
        OrdersService ordersService = new OrdersServiceImpl();
        List<Orders> ordersList = ordersService.getOrder(account);

        UserService userService = new UserServiceImpl();
        List<User> userList = userService.gerAllUserInfo();
        System.out.println("userList:"+userList);

        String ordersJson = new Gson().toJson(ordersList);
        String userInfoJson = new Gson().toJson(userList);
        session.setAttribute("ordersJson", ordersJson);
        session.setAttribute("userInfoJson", userInfoJson);
        request.getRequestDispatcher(MEMBER_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        HttpSession session = request.getSession();
        System.out.println("PATH:"+ path);

        if("/updateUserInfo".equals(path)){
            Gson gson = new Gson();
            UserService userService = new UserServiceImpl();
            String newUserInfo = request.getParameter("newUserInfoData");
            User user = gson.fromJson(newUserInfo, User.class);
            userService.updateUserInfo(user);
        }
    }
}

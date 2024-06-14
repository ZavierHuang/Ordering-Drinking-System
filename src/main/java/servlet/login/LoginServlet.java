package servlet.login;

import bean.user.User;
import service.customerService.CustomerService;
import service.customerService.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "LoginServlet",
        urlPatterns = {"/Login"}
)
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_URL = "WEB-INF/jsp/login/Login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(LOGIN_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String account = request.getParameter("Account");
        String password = request.getParameter("Password");
        CustomerService customerService = new CustomerServiceImpl();
        User customer = customerService.customerLogin(account, password);
        System.out.println(customer);
        if(customer != null && customer.getAccount() != null){
            session.setAttribute("account", customer.getAccount());
            session.setAttribute("levels", customer.getLevel());
            response.getWriter().print("0");
        }
        else{
            response.getWriter().print("1");
        }

    }
}

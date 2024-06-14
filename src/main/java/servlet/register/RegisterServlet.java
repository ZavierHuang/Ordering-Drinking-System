package servlet.register;

import service.customerService.CustomerService;
import service.customerService.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "RegisterServlet",
        urlPatterns = {"/Register"}
)
public class RegisterServlet extends HttpServlet {
    private static final String REGISTER_URL = "WEB-INF/jsp/register/Register.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("isEmployee", "N");
        //System.out.println("==RegisterServlet doGet==");
        request.getRequestDispatcher(REGISTER_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String r_account = request.getParameter("R_Account");
        String r_password = request.getParameter("R_Password");
        String r_tel = request.getParameter("R_Tel");
        String r_lev = request.getParameter("R_Level");

        CustomerService customerService = new CustomerServiceImpl();

        if (customerService.register(r_account,r_password,r_tel,Integer.parseInt(r_lev))){
            response.getWriter().print("0");
        }
        else {
            response.getWriter().print("1");
        }

    }
}
package servlet.sugarice;

import bean.product.Product;
import service.cartService.CartService;
import service.cartService.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "SugariceServlet",
        urlPatterns = {"/Sugarice"}
)
public class SugariceServlet extends HttpServlet {
    private static final String CUSTOMER_URL = "WEB-INF/jsp/sugarice/Sugarice.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productName = (String) session.getAttribute("ProductName");
        String account = (String) session.getAttribute("account");
        if (productName == null || account == null) {
            response.sendRedirect("Login");
            return;
        }
        request.setAttribute("productName", productName);

        request.getRequestDispatcher(CUSTOMER_URL).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getSession().getAttribute("account").toString();
        String productName = request.getParameter("productName");
        String size = request.getParameter("size");
        String sugar = request.getParameter("sugar");
        String ice = request.getParameter("ice");
        String quantity = request.getParameter("quantity");

        Product product = new Product();
        product.setName(productName);
        if (size.equals("中杯")) {
            product.setSize("M");
        } else if (size.equals("大杯")) {
            product.setSize("L");
        }
        product.setSugar(sugar);
        product.setIce(ice);
        product.setQuantity(Integer.parseInt(quantity));

        CartService cartService = new CartServiceImpl();
        cartService.addProduct(account, product);
    }
}

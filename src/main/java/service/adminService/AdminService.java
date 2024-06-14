package service.adminService;

import bean.menuItem.MenuItem;
import bean.product.Product;
import bean.user.User;

import java.util.List;

public interface AdminService {

    List<User> getAllEmp();

    int deleteEmployeebyAcc(String account);

    int updateEmployeebyAcc(User user);

    List<MenuItem> getAllMenuItem();

    List<Product> getAllType();

    void updateMenu(MenuItem oldMenuItem, MenuItem newMenuItem);
    void addNewMenuItem(MenuItem newMenuItem);
    List<MenuItem> searchMenu(MenuItem searchMenuItem, String symbol);
    void deleteMenu(String menuItemName);

}

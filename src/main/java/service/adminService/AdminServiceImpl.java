package service.adminService;

import bean.menuItem.MenuItem;
import bean.product.Product;
import bean.user.User;
import dao.admin.AdminDAO;
import dao.admin.AdminDaoImpl;
import dao.menuItem.MenuItemDAO;
import dao.menuItem.MenuItemDAOImpl;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;

import java.util.List;

public class AdminServiceImpl  implements AdminService {
    private final AdminDAO adminDAO = new AdminDaoImpl();
    private final MenuItemDAO menuDAO = new MenuItemDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<User> getAllEmp(){
        return adminDAO.getAllUsers();
    }

    @Override
    public int deleteEmployeebyAcc(String account){
        return adminDAO.deleteEmployeebyAcc(account);
    }

    @Override
    public int updateEmployeebyAcc(User user) {
        return adminDAO.updateEmployeebyAcc(user);
    }

    @Override
    public List<MenuItem>  getAllMenuItem(){
        return menuDAO.getAllMenuItem();
    }

    public List<Product> getAllType(){ return productDAO.getType();}

    @Override
    public void updateMenu(MenuItem oldMenuItem, MenuItem newMenuItem) {
        menuDAO.updateMenuItem(oldMenuItem, newMenuItem);
    }

    @Override
    public void addNewMenuItem(MenuItem newMenuItem) {
        menuDAO.addNewMenuItem(newMenuItem);
    }

    @Override
    public List<MenuItem> searchMenu(MenuItem searchMenuItem, String symbol) {
        return menuDAO.searchMenuItem(searchMenuItem, symbol);
    }

    @Override
    public void deleteMenu(String menuItemName) {
        menuDAO.deleteMenuItem(menuItemName);
    }

}

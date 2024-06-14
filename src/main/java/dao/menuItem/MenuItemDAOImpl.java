package dao.menuItem;

import bean.menuItem.MenuItem;
import bean.product.Product;
import dao.product.ProductDAO;
import java.util.ArrayList;
import java.util.List;

public class MenuItemDAOImpl implements MenuItemDAO {

    private final ProductDAO productDAO = new dao.product.ProductDAOImpl();
    @Override
    public List<MenuItem> getAllMenuItem() {
        List<MenuItem> allMenuList = new ArrayList<>();
        List<Product> allProducts = productDAO.getAllProducts();

        for(int i=0;i<allProducts.size();i+=2)
        {
            MenuItem menu = new MenuItem();
            Product product1 = allProducts.get(i);      //M
            Product product2 = allProducts.get(i+1);    //L

            menu.setName(product1.getName());
            menu.setType(product1.getType());
            menu.setSize_M(product1.getSize());
            menu.setPrice_M(product1.getPrice());
            menu.setSize_L(product2.getSize());
            menu.setPrice_L(product2.getPrice());
            menu.setStack(product1.getStack());

            if(product1.getState() == 1)
                menu.setState("Open");
            else
                menu.setState("Close");
            
            allMenuList.add(menu);
        }

        return allMenuList;
    }

    @Override
    public void updateMenuItem(MenuItem oldMenuItem, MenuItem newMenuItem) {
        productDAO.updateMenuItem(oldMenuItem, newMenuItem);
    }

    @Override
    public void addNewMenuItem(MenuItem newMenuItem) {
        productDAO.addNewMenuItem(newMenuItem);
    }

    @Override
    public List<MenuItem> searchMenuItem(MenuItem searchMenuItem, String symbol) {
        return productDAO.searchMenuItem(searchMenuItem, symbol);
    }

    @Override
    public void deleteMenuItem(String menuItemName) {
        productDAO.deleteMenuItemByName(menuItemName);
    }
}

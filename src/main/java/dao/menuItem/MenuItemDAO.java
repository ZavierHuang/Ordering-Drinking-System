package dao.menuItem;

import bean.menuItem.MenuItem;

import java.util.List;

public interface MenuItemDAO {
    List<MenuItem> getAllMenuItem();

    void updateMenuItem(MenuItem oldMenuItem, MenuItem newMenuItem);

    void addNewMenuItem(MenuItem newMenuItem);

    List<MenuItem> searchMenuItem(MenuItem searchMenuItem, String symbol);

    void deleteMenuItem(String menuItemName);
}

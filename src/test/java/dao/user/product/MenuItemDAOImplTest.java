package dao.user.product;

import bean.menuItem.MenuItem;
import dao.menuItem.MenuItemDAO;
import dao.menuItem.MenuItemDAOImpl;
import dao.product.ProductDAO;
import dao.product.ProductDAOImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MenuItemDAOImplTest {
    private MenuItemDAO menuItemDAO;
    private ProductDAO productDAO;
    @Before
    public void setUp() throws Exception {
        menuItemDAO = new MenuItemDAOImpl();
        productDAO = new ProductDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @After
    public void tearDown(){
//        DatabaseUtil.initDatabase();
    }

    @Test
    public void testSearchMenuItem(){
        List<MenuItem> testSearchItem = new ArrayList<>();
        testSearchItem.add(new MenuItem(null, null, 90, null, null, null));
        testSearchItem.add(new MenuItem(null, 50, null, null, null, null));
        testSearchItem.add(new MenuItem(null, 75, 95, null, null, null));
        testSearchItem.add(new MenuItem("曼巴", null, null, null, null, null));
        testSearchItem.add(new MenuItem(null, null, null, "Latte", null, null));
        testSearchItem.add(new MenuItem(null, null, null, null, "Open", null));
        testSearchItem.add(new MenuItem(null, null, null, null, null, 20));
        testSearchItem.add(new MenuItem(null, null, null, null, null, null));

        assertEquals(13, productDAO.searchMenuItem(testSearchItem.get(0),null).size());
        assertEquals(2, productDAO.searchMenuItem(testSearchItem.get(1),null).size());
        assertEquals(7, productDAO.searchMenuItem(testSearchItem.get(2),null).size());
        assertEquals(1, productDAO.searchMenuItem(testSearchItem.get(3),null).size());
        assertEquals(7, productDAO.searchMenuItem(testSearchItem.get(4),null).size());
        assertEquals(36, productDAO.searchMenuItem(testSearchItem.get(5),null).size());
        assertEquals(0, productDAO.searchMenuItem(testSearchItem.get(6),"<=").size());
        assertEquals(36, productDAO.searchMenuItem(testSearchItem.get(7),null).size());
    }

    @Test
    public void testAddAndDeleteNewMenuItem(){
        MenuItem newMenuItem = new MenuItem("測試用", 170, 190, "Boutique hand coffee", "Close", 30);
        menuItemDAO.addNewMenuItem(newMenuItem);
        List<MenuItem> searchmenuItem = menuItemDAO.searchMenuItem(newMenuItem, null);
        assertEquals("測試用", searchmenuItem.get(0).getName());
        assertEquals(170, searchmenuItem.get(0).getPrice_M().intValue());
        assertEquals(190, searchmenuItem.get(0).getPrice_L().intValue());
        assertEquals("Boutique hand coffee", searchmenuItem.get(0).getType());
        assertEquals("Close", searchmenuItem.get(0).getState());
        List<MenuItem> allmenu = menuItemDAO.getAllMenuItem();
        assertEquals(37, allmenu.size());

        String deleteItemName = "測試用";
        menuItemDAO.deleteMenuItem(deleteItemName);
        List<MenuItem> allMenuItem = menuItemDAO.getAllMenuItem();
        assertEquals(36, allMenuItem.size());

    }



    @Test
    public void testUpdateMenuItem(){
        MenuItem oldMenuItem = new MenuItem("耶加雪非", 70, 90, "Boutique hand coffee", "Open", 30);
        MenuItem newMenuItem = new MenuItem("耶加雪非", 170, 190, "Boutique hand coffee", "Close", 30);
        menuItemDAO.updateMenuItem(oldMenuItem, newMenuItem);
        List<MenuItem> searchmenuItem = menuItemDAO.searchMenuItem(newMenuItem, null);
        assertEquals("耶加雪非", searchmenuItem.get(0).getName());
        assertEquals(170, searchmenuItem.get(0).getPrice_M().intValue());
        assertEquals(190, searchmenuItem.get(0).getPrice_L().intValue());
        assertEquals("Boutique hand coffee", searchmenuItem.get(0).getType());
        assertEquals("Close", searchmenuItem.get(0).getState());
        menuItemDAO.updateMenuItem(newMenuItem, oldMenuItem);
    }

    @Test
    public void testGetAllMenuTest() {
        List<MenuItem> allmenu = menuItemDAO.getAllMenuItem();
        assertEquals(36, allmenu.size());
    }


}
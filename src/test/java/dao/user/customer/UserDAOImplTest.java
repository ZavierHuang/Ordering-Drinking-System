package dao.user.customer;

import bean.user.User;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import static org.junit.Assert.*;

public class UserDAOImplTest {

    private UserDAO userDAO;
    @Before
    public void setUp() throws Exception {
        userDAO = new UserDAOImpl();
        DatabaseUtil.initDatabase();
    }
    @Test
    public void test_getAccount(){
        assertNotNull(userDAO.getAccount("111"));
        assertNull(userDAO.getAccount("123"));
    }

    @Test
    public void test_insert() {
        userDAO.insert("444","123456","0912345678",0);
        assertNotNull(userDAO.getAccount("444"));
    }

    @Test
    public void test_getLogin() {
        User user = userDAO.getLogin("111","111");
        assertNotNull(user);
        user = userDAO.getLogin("123","123");
        assertNull(user);
    }


    @Test
    public void test_getCustomerID() {
        Integer id = userDAO.getCustomerID("111");
        assertEquals( 1,id.intValue());
        id = userDAO.getCustomerID("123");
        assertEquals(Integer.valueOf(0),id);
    }
}
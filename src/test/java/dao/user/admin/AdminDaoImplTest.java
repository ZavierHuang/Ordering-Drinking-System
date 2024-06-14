package dao.user.admin;

import bean.user.User;
import dao.admin.AdminDAO;
import dao.admin.AdminDaoImpl;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.List;

import static org.junit.Assert.*;

public class AdminDaoImplTest {

    private AdminDAO adminDAO;
    @Before
    public void setUp() throws Exception {
        adminDAO = new AdminDaoImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void getEmployeebyId() {
        User user = adminDAO.getUserbyId(2);
        assertEquals(10,user.getLevel());
        assertEquals("222",user.getAccount());
    }

    @Test
    public void insertEmployee() {
        adminDAO.insertEmployee(new User("a","a",11,"0987654321"));
        User user = adminDAO.getUserbyId(4);
        assertEquals(11,user.getLevel());
        assertEquals("a",user.getAccount());
    }

    @Test
    public void updateEmployee() {
        adminDAO.updateEmployee(new User(2,"a","a",11,"0987654321"));
        User user = adminDAO.getUserbyId(2);
        assertEquals(11,user.getLevel());
        assertEquals("a",user.getAccount());
        assertEquals("0987654321",user.getPhone());
    }

    @Test
    public void deleteAllEmployee() {
        adminDAO.deleteAllEmployee();
        assertTrue(adminDAO.getAllUsers().isEmpty());
    }

    @Test
    public void deleteEmployeebyAcc() {
        adminDAO.deleteEmployeebyAcc("222");
        User user = adminDAO.getUserbyId(2);
        assertNull(user);
    }

    @Test
    public void getAllEmployeeList() {
        List<User> userList = adminDAO.getAllUsers();
        assertEquals(1,userList.size());
        for (User user : userList) {
            assertEquals("222",user.getAccount());
            assertEquals(10,user.getLevel());
            assertEquals("0930333339",user.getPhone());
        }
    }


}

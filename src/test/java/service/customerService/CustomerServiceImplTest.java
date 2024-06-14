package service.customerService;

import bean.user.User;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CustomerServiceImplTest {
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        customerService = new CustomerServiceImpl();
        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_login() {
        User user = customerService.customerLogin("111","111");
        assertNotNull(user);
    }

    @Test
    public void test_register() {
        Boolean register = customerService.register("444","444","0912345678",0);
        assertTrue(register);
    }
}
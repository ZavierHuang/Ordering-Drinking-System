package dao.build;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BuildDAOImplTest {
    private BuildDAO buildDAO;
    @Before
    public void setUp() throws Exception {
        buildDAO = new BuildDAOImpl();
        DatabaseUtil.initDatabase();
    }

    @After
    public void tearDown(){
//        DatabaseUtil.initDatabase();
    }

    @Test
    public void test_getOrderId(){
        List<Integer> exceptOrderIds=new ArrayList<>();
        exceptOrderIds.add(2);
        assertEquals( buildDAO.getOrderID(1,1),exceptOrderIds);
    }

    @Test
    public void test_getCustomerCartID(){
        Integer customerCartId = buildDAO.getCustomerCartID(1);
        assertEquals(Integer.valueOf(2),customerCartId);
    }

}
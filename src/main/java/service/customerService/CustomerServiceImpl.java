package service.customerService;

import bean.user.User;
import dao.build.BuildDAO;
import dao.build.BuildDAOImpl;
import dao.orders.OrdersDAO;
import dao.orders.OrdersDAOImpl;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;

public class CustomerServiceImpl implements CustomerService{
    private final UserDAO userDAO = new UserDAOImpl();
    private final BuildDAO buildDAO = new BuildDAOImpl();
    private final OrdersDAO ordersDAO = new OrdersDAOImpl();

    @Override
    public User customerLogin(String account, String password){
        return userDAO.getLogin(account, password);
    }

    @Override
    public Boolean register(String newAccount,String pwd,String phone,int level){
        String account = userDAO.getAccount(newAccount);
        if(account == null){
            userDAO.insert(newAccount,pwd,phone,level);
            Integer orderID = ordersDAO.initial();
            Integer customerID = userDAO.getCustomerID(userDAO.getAccount(newAccount));
            buildDAO.insert(customerID, orderID);
            return true;
        }
        return false;
    }

}

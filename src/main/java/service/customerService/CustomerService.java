package service.customerService;

import bean.user.User;

public interface CustomerService {
    public User customerLogin(String account, String password);
    public Boolean register(String account,String pwd,String phone,int level);

}

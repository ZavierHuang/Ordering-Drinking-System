package dao.user;

import bean.user.User;

import java.util.List;

public interface UserDAO {
    public User getLogin(String account, String password);
    public String getAccount(String inAccount);
    public void insert(String newAccount,String pwd,String phone,int level);
    public Integer getCustomerID(String account);
    List<User> getAllInfo();
    void updateUserInfo(User user);
}

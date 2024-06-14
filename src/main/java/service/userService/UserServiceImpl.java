package service.userService;

import bean.user.User;
import dao.user.UserDAO;
import dao.user.UserDAOImpl;

import java.util.List;

public class UserServiceImpl implements UserService{

    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public List<User> gerAllUserInfo() {
        return userDAO.getAllInfo();
    }

    @Override
    public void updateUserInfo(User user) {
        userDAO.updateUserInfo(user);
    }
}

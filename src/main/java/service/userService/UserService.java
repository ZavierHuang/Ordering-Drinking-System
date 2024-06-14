package service.userService;

import bean.user.User;

import java.util.List;

public interface UserService {
    public List<User> gerAllUserInfo();

    void updateUserInfo(User user);
}

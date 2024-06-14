package dao.admin;

import bean.user.User;

import java.util.List;

public interface AdminDAO {


    public int insertEmployee(User user);
    int updateEmployee(User user);
    List<User> getAllUsers();
    User getUserbyId(int userId);

    int deleteAllEmployee();


    int deleteEmployeebyAcc(String account);


    int updateEmployeebyAcc(User user);
}

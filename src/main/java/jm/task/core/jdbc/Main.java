package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Name_1", "Last_Name_1", (byte) 20);
        userService.saveUser("Name_2", "Last_Name_2", (byte) 25);
        userService.saveUser("Name_3", "Last_Name_3", (byte) 31);
        userService.saveUser("Name_4", "Last_Name_4", (byte) 38);
        List<User> allUsers = userService.getAllUsers();
        for (User i : allUsers) {
            System.out.println(i);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

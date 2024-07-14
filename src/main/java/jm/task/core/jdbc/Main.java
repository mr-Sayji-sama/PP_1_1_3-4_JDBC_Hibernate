package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Name_1", "Last_Name_1", (byte) 20);
        userDao.saveUser("Name_2", "Last_Name_2", (byte) 25);
        userDao.saveUser("Name_3", "Last_Name_3", (byte) 31);
        userDao.saveUser("Name_4", "Last_Name_4", (byte) 38);

        userDao.removeUserById(1);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}

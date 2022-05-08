package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Иван", "Иванов", (byte) 25);
        System.out.println("User с именем – " + userService.getAllUsers().get(0) + " добавлен в базу данных ");
        userService.saveUser("Петр", "Петров", (byte) 26);
        System.out.println("User с именем – " + userService.getAllUsers().get(1) + " добавлен в базу данных ");
        userService.saveUser("Александр", "Александров", (byte) 27);
        System.out.println("User с именем – " + userService.getAllUsers().get(2) + " добавлен в базу данных ");
        userService.saveUser("Евдоким", "Евдокимов", (byte) 28);
        System.out.println("User с именем – " + userService.getAllUsers().get(3) + " добавлен в базу данных ");
        List<User> list = userService.getAllUsers();
        for (User user: list) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

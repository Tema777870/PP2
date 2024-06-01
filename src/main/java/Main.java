import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
//      userService.createUsersTable();
        userService.saveUser("Tanjiro", "Kamado", (byte) 17);
        userService.saveUser("Sasuke", "Uchiha", (byte) 17);
        userService.saveUser("Kakashi", "Hatake", (byte) 28);
        userService.saveUser("Hiruzen", "Sarutobi", (byte) 72);
        userService.removeUserById(2);
//        userService.cleanUsersTable();
   //    userService.dropUsersTable();
        List<User> users = userService.getAllUsers();
        for (User u: users)
            System.out.println(u);

    }
}
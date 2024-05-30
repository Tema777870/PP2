import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Naruto", "Uzumaki", (byte) 17);
        userService.saveUser("Sasuke", "Uchiha", (byte) 17);
        userService.saveUser("Kakashi", "Hatake", (byte) 28);
        userService.saveUser("Hiruzen", "Sarutobi", (byte) 72);
        userService.removeUserById(4);
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
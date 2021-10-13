package menus;

import daos.PrescriptionDao;
import daos.UserDao;
import entities.Item;
import entities.Prescription;
import entities.User;
import services.PrescriptionService;
import services.UserService;
import utils.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {
    Auth authMenu = new Auth();
    Patient patientMenu = new Patient();
    Admin adminMenu = new Admin();
    public void run(){
        while (true) {
            Optional<User> userOptional = authMenu.run();
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (user.getRole() == User.Role.ADMIN) {
                    adminMenu.run(user);
                } else {
                    patientMenu.run(user);
                }
            } else {
                return;
            }
        }
    }
}

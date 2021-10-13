package menus;

import entities.Patient;
import entities.User;
import services.UserService;
import utils.Scanner;

import java.util.Optional;

public class Auth {
    Scanner sc = new Scanner();
    UserService userService = new UserService();
    Optional<User> userOptional;
    String userName;
    String password;
    public Optional<User> run(){
        int command;
        do {
            userOptional=Optional.empty();
            System.out.println("1)login 2)register as patient 3)exit");
            command = sc.getInt();
            User user;
            switch (command){
                case 1:
                    if(login().isPresent()) {
                        return userOptional;
                    }
                    break;
                case 2:
                    if(register().isPresent()) {
                        return userOptional;
                    }
                    break;
                case 3:
                    System.out.println("bye!");
                    break;
                default :
                    System.out.println("invalid command.");
                    break;
            }
        }while(command!=3);
        return userOptional;
    }

    private Optional<User> register() {
        userOptional=Optional.empty();
        System.out.println("enter username:");
        userName = sc.getString();
        while(userService.getByUsername(userName).isPresent() ){
            System.out.println("username already exits, try another one:");
            userName = sc.getString();
        }
        System.out.println("enter password:");
        password = sc.getString();
        User user = new Patient(0,userName,password , User.Role.valueOf("patient".toUpperCase()));
        int id = userService.save(user);
        user.setId(id);
        userOptional=Optional.of(user);
        return userOptional;
    }

    private Optional<User> login() {
        userOptional=Optional.empty();
        System.out.println("enter username:");
        userName = sc.getString();
        System.out.println("enter password:");
        password = sc.getString();
        userOptional= userService.getByUsername(userName);
        if(userOptional.isPresent()){
            User user = userOptional.get();
            if (user.getPassword().equals( password)){
                return userOptional;
            }else{
                System.out.println("wrong password!");
                userOptional=Optional.empty();
            }
        }else{
            System.out.println("username doesn't exist!");
        }
        return userOptional;
    }
}

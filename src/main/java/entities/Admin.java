package entities;

public class Admin extends User{

    public Admin(String userName, String password) {
        super(userName, password, Role.ADMIN);

    }
}

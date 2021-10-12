package entities;

public class Admin extends User{

    public Admin(String userName, String password) {
        super(userName, password);
        super.setRole(Role.ADMIN);
    }
}

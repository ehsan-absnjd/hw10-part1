package entities;

public class Admin extends User{

    public Admin(String userName, String password) {
        super(userName, password, Role.ADMIN);
    }

    public Admin(int id, String userName, String password, Role role) {
        super(id, userName, password, role);
    }
}

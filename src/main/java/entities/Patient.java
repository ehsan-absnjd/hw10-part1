package entities;

public class Patient extends User{

    public Patient(String userName, String password) {
        super(userName, password, Role.PATIENT);
    }

    public Patient(int id, String userName, String password, Role role) {
        super(id, userName, password, role);
    }
}


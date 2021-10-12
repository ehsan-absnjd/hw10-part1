package entities;

public class Patient extends User{

    public Patient(String userName, String password) {
        super(userName, password);
        super.setRole(Role.PATIENT);
    }
}


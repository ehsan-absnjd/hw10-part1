package entities;


import java.util.List;

public class Prescription {
    private int id;
    private int patientId;
    private boolean isConfirmed;
    private List<Item> itemList;

    public Prescription(int id, int patientId, boolean isConfirmed, List<Item> itemList) {
        this.id = id;
        this.patientId = patientId;
        this.isConfirmed = isConfirmed;
        this.itemList = itemList;
    }

    public Prescription(int patientId, List<Item> itemList) {
        this.patientId = patientId;
        this.itemList = itemList;
    }

    public int getPatientId() {
        return patientId;
    }

    public List<Item> getItemList() {
        return itemList;
    }
}

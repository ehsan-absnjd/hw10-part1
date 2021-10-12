package entities;

public class Item {
    private int id;
    private int prescriptionId;
    private String name;
    private int quantity;
    private Boolean exists;
    private Double unitPrice;

    public Item(int id, int prescriptionId, String name, int quantity, Boolean exists, Double unitPrice) {
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.name = name;
        this.quantity = quantity;
        this.exists = exists;
        this.unitPrice = unitPrice;
    }

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
}

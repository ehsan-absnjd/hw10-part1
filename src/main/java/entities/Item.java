package entities;

public class Item {
    private int id;
    private int prescriptionId;
    private String name;
    private int quantity;
    private Boolean exists;
    private Double unitPrice;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public Item(int id, int prescriptionId, String name, int quantity, Boolean exists, Double unitPrice) {
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.name = name;
        this.quantity = quantity;
        this.exists = exists;
        this.unitPrice = unitPrice;
    }

    public Boolean getExists() {
        return exists;
    }

    public Double getUnitPrice() {
        return unitPrice;
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

    public int getId() {
        return id;
    }
    
    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", prescriptionId=" + prescriptionId +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", exists=" + exists +
                ", unitPrice=" + unitPrice +
                '}';
    }
}

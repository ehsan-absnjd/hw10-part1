package menus;

import entities.Item;
import entities.Prescription;
import entities.User;
import services.PrescriptionService;
import utils.Scanner;

import java.util.List;
import java.util.Optional;

public class Admin {
    Scanner sc = new Scanner();
    PrescriptionService prescriptionService = new PrescriptionService();
    public void run(User user){
        int command;
        do {
            System.out.println("1) show all prescriptions 2) confirm prescription 3) exit");
            command= sc.getInt();
            switch (command){
                case 1:
                    showAll();
                    break;
                case 2:
                    confirmPrescription();
                    break;
                case 3:
                    System.out.println("logged out.");
                    break;
                default:
                    System.out.println("invalid command.");
            }
        }while(command!=3);
    }

    private void confirmPrescription() {
        System.out.println("enter prescription id:");
        int id = sc.getInt();
        Optional<Prescription> prescriptionOptional = prescriptionService.getById(id);
        if (!prescriptionOptional.isPresent()){
            System.out.println("prescription doesn't exit");
            return;
        }else if (prescriptionOptional.get().isConfirmed()){
            System.out.println("prescription is already confirmed.");
            return;
        }
        Prescription prescription = prescriptionOptional.get();
        List<Item> items = prescription.getItemList();
        for (Item item : items) {
            System.out.println("does " +item.getName()+" exist in stock? y/n");
            char c = sc.getString().toLowerCase().charAt(0);
            if (c == 'y') {
                item.setExists(true);
                System.out.println("how much is the unit price?");
                double price = sc.getDouble();
                item.setUnitPrice(price);
            }else{
                item.setExists(false);
            }
        }
        prescriptionService.confirm(prescription);
    }

    private void showAll() {
        List<Prescription> prescriptionList = prescriptionService.getAll();
        for (Prescription prescription : prescriptionList){
            System.out.println( prescription.isConfirmed() ? "confirmed " : "unconfirmed" + " prescription for user with ID : " + prescription.getPatientId() + " with prescrption ID : " +prescription.getId() );
            for(Item item : prescription.getItemList()){
                String itemString="";
                if(prescription.isConfirmed()&& item.getExists()){
                    itemString = " with unit price " + item.getUnitPrice();
                }
                System.out.println("\t" + item.getName() + " * " + item.getQuantity() + itemString);
            }

        }

    }
}

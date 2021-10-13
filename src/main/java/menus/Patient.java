package menus;

import entities.Item;
import entities.Prescription;
import entities.User;
import services.PrescriptionService;
import utils.Scanner;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Patient {
    int prescriptionCount;
    Scanner sc = new Scanner();
    PrescriptionService prescriptionService = new PrescriptionService();
    User user;
    public void run(User user){
        this.user = user;
        int command;
        do {
            System.out.println("1) add prescription 2) see confirmed prescriptions 3) edit prescription 4) delete prescription 5) exit");
            command = sc.getInt();
            switch (command) {
                case 1:
                    addPrescription();
                    break;
                case 2:
                    showConfirmedPrescriptions();
                    break;
                case 3:
                    editPrescription();

                    break;
                case 4:
                    deletePrescription();
                    break;
                case 5:
                    prescriptionCount=0;
                    break;
                default:
                    System.out.println("invalid command.");
            }
        }while(command!= 5);

    }

    private void deletePrescription() {
        System.out.println("enter prescription id to delete");
        int id = sc.getInt();
        Optional<Prescription> prescriptionOptional= prescriptionService.getById(id);
        if (prescriptionOptional.isPresent()) {
            Prescription prescription = prescriptionOptional.get();
            if (prescription.getPatientId() == user.getId()) {
                prescriptionService.delete(id);
            }else{
                System.out.println("this prescription doesn't belong to current user.");
            }
        }else{
            System.out.println("prescription with this id doesn't exist.");
        }
    }

    private void editPrescription() {
        int subCommand;
        List<Item> itemList;
        System.out.println("enter prescription id to edit");
        int id = sc.getInt();
        Optional<Prescription> prescriptionOptional= prescriptionService.getById(id);
        if (prescriptionOptional.isPresent()){
            Prescription prescription = prescriptionOptional.get();
            if(prescription.getPatientId()== user.getId()){
                itemList = new ArrayList<>();
                do{
                    System.out.println("1) add item 2)save");
                    int counter=1;
                    if (counter==11){
                        subCommand=2;
                    }else{
                        subCommand = sc.getInt();
                    }
                    switch (subCommand){
                        case 1:
                            System.out.println("item name:");
                            String name= sc.getString();
                            System.out.println("quantity:");
                            int quantity=sc.getInt();
                            itemList.add(new Item( name , quantity));
                            break;
                        case 2:
                            prescriptionService.update(id, itemList);
                            break;
                        default:
                            System.out.println("invalid command.");
                    }
                    counter++;
                }while(subCommand!= 2 );
            }else{
                System.out.println("this prescription doesn't belong to current user.");
            }
        }else{
            System.out.println("confirmed prescription with this id doesn't exist.");
        }
    }

    private void showConfirmedPrescriptions() {
        List<Prescription> prescriptionList = prescriptionService.getConfirmedByPatientId(user.getId());
        for (Prescription prescription : prescriptionList) {
            System.out.println("prescription with ID : " + prescription.getId());
            double total = 0;
            for (Item item : prescription.getItemList()) {
                String itemString = "";
                if (item.getExists()) {
                    itemString = " with unit price " + item.getUnitPrice();
                    total += item.getUnitPrice() * item.getQuantity();
                }else{
                    itemString= " out of stock";
                }
                System.out.println("\t" + item.getName() + " * " + item.getQuantity() + itemString);
            }
            System.out.println("\twith total fee of " + total);
        }
    }

    private void addPrescription() {
        if(prescriptionCount==3) {
            return;
        }
        int subCommand;
        List<Item> itemList = new ArrayList<>();
        do{
            System.out.println("1) add item 2)save");
            int counter=1;
            if (counter==11){
                subCommand=2;
            }else{
                subCommand = sc.getInt();
            }
            switch (subCommand){
                case 1:
                    System.out.println("item name:");
                    String name= sc.getString();
                    System.out.println("quantity:");
                    int quantity=sc.getInt();
                    itemList.add(new Item( name , quantity));
                    break;
                case 2:
                    Prescription prescription = new Prescription(user.getId() , itemList);
                    prescriptionService.save(prescription);
                    prescriptionCount++;
                    break;
                default:
                    System.out.println("invalid command.");
            }
            counter++;
        }while(subCommand!= 2 );
    }
}

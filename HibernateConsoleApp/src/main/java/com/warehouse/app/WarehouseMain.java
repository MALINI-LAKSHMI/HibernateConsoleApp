package com.warehouse.app;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.warehouse.bean.Shipment;
import com.warehouse.service.DispatchService;

public class WarehouseMain {

    public static void main(String[] args) {

        DispatchService service = new DispatchService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n---- WAREHOUSE DISPATCH SYSTEM ----");
            System.out.println("1. Add Shipment");
            System.out.println("2. View Shipment");
            System.out.println("3. View All Shipments");
            System.out.println("4. Dispatch Shipment");
            System.out.println("5. Cancel Dispatch");
            System.out.println("6. Remove Shipment");
            System.out.println("0. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            try {
                switch (choice) {

                case 1:
                    Shipment s = new Shipment();

                    System.out.print("Shipment ID: ");
                    s.setShipmentID(sc.next());

                    sc.nextLine();
                    System.out.print("Item Description: ");
                    s.setItemDescription(sc.nextLine());

                    System.out.print("Total Quantity: ");
                    int total = sc.nextInt();
                    s.setAvailableQuantity(total);       
                    s.setAvailableQuantity(total);    

                    s.setReceivedDate(new Date());

                    System.out.println(
                        service.addNewShipment(s) ? "ADDED" : "FAILED");
                    break;

                case 3:
                    List<Shipment> list = service.viewAllShipments();
                    list.forEach(System.out::println);
                    break;

                case 4:
                    System.out.print("Shipment ID: ");
                    String sid = sc.next();

                    sc.nextLine();
                    System.out.print("Destination: ");
                    String dest = sc.nextLine();

                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();

                    System.out.println(
                        service.dispatchShipment(sid, dest, qty)
                        ? "DISPATCHED" : "FAILED");
                    break;

                case 5:
                    System.out.print("Dispatch ID: ");
                    System.out.println(
                        service.cancelDispatch(sc.nextInt())
                        ? "CANCELLED" : "FAILED");
                    break;

                case 6:
                    System.out.print("Shipment ID: ");
                    System.out.println(
                        service.removeShipment(sc.next())
                        ? "REMOVED" : "FAILED");
                    break;

                case 0:
                    System.out.println("Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e);
            }
        }
    }
}

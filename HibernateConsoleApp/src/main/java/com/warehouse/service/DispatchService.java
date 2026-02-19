package com.warehouse.service;

import java.util.Date;
import java.util.List;

import com.warehouse.bean.Dispatch;
import com.warehouse.bean.Shipment;
import com.warehouse.dao.DispatchDAO;
import com.warehouse.dao.ShipmentDAO;

public class DispatchService {

    private ShipmentDAO shipmentDAO = new ShipmentDAO();
    private DispatchDAO dispatchDAO = new DispatchDAO();

    // CASE 1 - Add Shipment
    public boolean addNewShipment(Shipment s) {
        return shipmentDAO.insertShipment(s);
    }

    // CASE 2 - View Shipment
    public Shipment viewShipmentDetails(String id) {
        return shipmentDAO.findShipment(id);
    }

    // CASE 3 - View All Shipments
    public List<Shipment> viewAllShipments() {
        return shipmentDAO.viewAllShipments();
    }

    // CASE 4 - Dispatch Shipment
    public boolean dispatchShipment(String id, String dest, int qty) {

        Shipment shipment = shipmentDAO.findShipment(id);


        if (shipment == null)
            return false;

        if (shipment.getAvailableQuantity() < qty)
            return false;

        // Reduce available quantity
        shipment.setAvailableQuantity(
                shipment.getAvailableQuantity() - qty);

        shipmentDAO.updateShipment(shipment);

        // Create dispatch record
        Dispatch d = new Dispatch();

        d.setDispatchID(dispatchDAO.generateDispatchID()); // int
        d.setShipmentID(id);
        d.setDestination(dest);
        d.setQuantityDispatched(qty);
        d.setDispatchDate(new Date()); // ✅ correct method
        d.setStatus("ACTIVE");

        dispatchDAO.recordDispatch(d);

        return true;
    }


    // CASE 5 - Cancel Dispatch
    public boolean cancelDispatch(int dispatchID) {

        return dispatchDAO.removeDispatch(dispatchID);
    }

    // CASE 6 - Remove Shipment
    public boolean removeShipment(String id) {

        return shipmentDAO.removeShipment(id);
    }
}

package com.warehouse.bean;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="DISPATCH_TBL")
public class Dispatch {

    @Id
    @Column(name="dispatch_id")
    private int dispatchID;

    @Column(name="shipment_id")
    private String shipmentID;

    @Column(name="destination")
    private String destination;

    @Column(name="quantity_dispatched")
    private int quantityDispatched;

    @Temporal(TemporalType.DATE)
    @Column(name="dispatch_date")
    private Date dispatchDate;

    @Column(name="status")
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDispatchID(int dispatchID) {
        this.dispatchID = dispatchID;
    }

    public void setShipmentID(String shipmentID) {
        this.shipmentID = shipmentID;
    }

    public void setQuantityDispatched(int qty) {
        this.quantityDispatched = qty;
    }

    public void setDestination(String dest) {
        this.destination = dest;
    }

    public void setDispatchDate(Date date) {
        this.dispatchDate = date;
    }
}

package com.warehouse.bean;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name="SHIPMENT_TBL")
public class Shipment {

    @Id
    @Column(name="shipment_id")
    private String shipmentID;

    @Column(name="item_description")
    private String itemDescription;

    @Column(name="total_quantity")
    private int totalQuantity;

    @Column(name="available_quantity")
    private int availableQuantity;

    @Temporal(TemporalType.DATE)
    @Column(name="received_date")
    private Date receivedDate;

    public int getAvailableQuantity() {
        return this.availableQuantity;
    }

    public void setAvailableQuantity(int qty) {
        this.availableQuantity = qty;
    }

    public void setItemDescription(String desc) {
        this.itemDescription = desc;
    }

    public void setShipmentID(String id) {
        this.shipmentID = id;
    }

    public void setReceivedDate(Date date) {
        this.receivedDate = date;
    }
}
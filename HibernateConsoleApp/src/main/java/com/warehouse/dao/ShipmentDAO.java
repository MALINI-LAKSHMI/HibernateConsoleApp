package com.warehouse.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.warehouse.bean.Shipment;
import com.warehouse.util.HibernateUtil.HibernateUtil;

public class ShipmentDAO {

    // 1️⃣ Insert Shipment
    public boolean insertShipment(Shipment s) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(s);

        tx.commit();
        session.close();

        return true;
    }

    // 2️⃣ Get Shipment By ID
    public Shipment findShipment(String id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Shipment s = session.get(Shipment.class, id);
        session.close();

        return s;
    }

    // 3️⃣ View All
    public List<Shipment> viewAllShipments() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Shipment> list =
                session.createQuery("from Shipment", Shipment.class).list();
        session.close();

        return list;
    }

    // 4️⃣ Update Shipment
    public boolean updateShipment(Shipment s) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.update(s);

        tx.commit();
        session.close();

        return true;
    }

    // 5️⃣ Remove Shipment
    public boolean removeShipment(String id) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Shipment s = session.get(Shipment.class, id);

        if (s != null) {
            session.delete(s);
            tx.commit();
            session.close();
            return true;
        }

        tx.rollback();
        session.close();
        return false;
    }
}

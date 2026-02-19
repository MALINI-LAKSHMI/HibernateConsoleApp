package com.warehouse.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.warehouse.bean.Dispatch;
import com.warehouse.util.HibernateUtil.HibernateUtil;

public class DispatchDAO {

    // 1️⃣ Generate Dispatch ID
    public int generateDispatchID() {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Integer maxId = (Integer) session
                .createQuery("select max(d.dispatchID) from Dispatch d")
                .uniqueResult();

        session.close();

        if (maxId == null)
            return 80001;
        else
            return maxId + 1;
    }

    // 2️⃣ Record Dispatch
    public boolean recordDispatch(Dispatch d) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        session.save(d);

        tx.commit();
        session.close();

        return true;
    }

    // 3️⃣ Cancel Dispatch
    public boolean removeDispatch(int dispatchID) {

        Session session =
                HibernateUtil.getSessionFactory().openSession();

        Transaction tx = session.beginTransaction();

        Dispatch dispatch =
                session.get(Dispatch.class, dispatchID);

        if (dispatch != null) {
            ((Dispatch) dispatch).setStatus("CANCELLED");
            session.update(dispatch);
            tx.commit();
            session.close();
            return true;
        }

        tx.rollback();
        session.close();
        return false;
    }
}

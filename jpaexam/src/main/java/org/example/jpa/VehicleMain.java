package org.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class VehicleMain {
    public static void main(String[] args) {
        create();
    }

    public static void create() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Car car = new Car();
            car.setManufacturer("W");
            car.setSeatCount(5);

            em.persist(car);

            tx.commit();
        } finally {
            em.close();
        }
    }
}

package org.example.jpa;

import jakarta.persistence.EntityManager;

public class Vehicle2Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Car2 car = new Car2();
            car.setManufacturer("lion");
            car.setSeatCount(3);

            Truck2 t = new Truck2();
            t.setManufacturer("like");
            t.setPayloadCapacity(200);

            em.persist(car);
            em.persist(t);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}

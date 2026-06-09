package org.example.jpa;

import jakarta.persistence.EntityManager;

public class Vehicle3Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Car3 car = new Car3();
            car.setManufacturer("lion");
            car.setSeatCount(3);

            Truck3 t = new Truck3();
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

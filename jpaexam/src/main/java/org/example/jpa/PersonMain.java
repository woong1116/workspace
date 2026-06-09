package org.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PersonMain {
    public static void main(String[] args) {
        create();
//        find();
//        update();
//        delete();
    }


    public static void create() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            Person person1 = new Person("kkk");
            Passport passport1 = new Passport("12345678");

            passport1.setPerson(person1);
            person1.setPassport(passport1);

            em.persist(person1);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    public static void find() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Person person = em.find(Person.class, 1L);
        System.out.println("Person Name: " + person.getName());
        System.out.println("Passport Number: " + person.getPassport().getPassportNumber());

        em.getTransaction().commit();
        em.close();
    }

    public static void update() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Person person = em.find(Person.class, 1L);
        person.setName("Updated Person Name");  // 변경 감지로 자동 UPDATE

        em.getTransaction().commit();
        em.close();
    }

    public static void delete() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();

        Person person = em.find(Person.class, 1L);
        em.remove(person);  // cascade로 여권도 함께 삭제됨

        em.getTransaction().commit();
        em.close();
    }
}

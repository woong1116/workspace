package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StudentMain {
    public static void find() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            School school = entityManager.find(School.class, 1L);
            log.info("School Name : {}", school.getName());
            log.info("1");

            Student student = entityManager.find(Student.class, 1L);
            log.info("Student name : {}", student.getName());
            log.info("School Name : {}", student.getSchool().getName());
        } finally {
            entityManager.close();
        }
    }

    //    생성
    public static void create() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            School school = new School("lion school");
            entityManager.persist(school);

            entityManager.getTransaction().commit();


        } finally {
            entityManager.close();
        }
    }

    //    수정
    public static void update() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try {

        } finally {
            entityManager.close();
        }
    }


    //    삭제
    public static void delete() {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        try {
            Student student = entityManager.find(Student.class, 4L);
            entityManager.remove(student);

            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public static void main(String[] args) {
//          조회
        find();
        create();
    }
}

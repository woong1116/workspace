package org.example.jpa;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmployeeMain {
    public static void main(String[] args) {
//        create();
//        find();
//        update();
        delete();
    }

    public static void find() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            Employee employee = em.find(Employee.class, 1L);
            log.info("사원 이름 : {}", employee.getName());
            Project project = em.find(Project.class, 1L);
            log.info("프로젝트명 : {}", project.getTitle());

            for(Employee e : project.getEmployees()) {
                log.info("사원 이름 : {}", e.getName());
            }
        } finally {
            em.close();
        }
    }

    public static void create() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try{
            Employee employee = new Employee();
            employee.setName("김철수");

            Project project = new Project();
            project.setTitle("프로젝트");

//            Project project = em.find(Project.class, 1L);

//            employee.getProjects().add(project);
//            project.getEmployees().add(employee);

            employee.addProject(project);

            em.persist(employee);
            em.persist(project);

            em.getTransaction().commit();

        }finally {
            em.close();
        }
    }

    public static void update() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Employee employee = em.find(Employee.class, 3L);
            employee.setName("kkk");

            employee.getProjects().add(em.find(Project.class, 2L));

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }

    public static void delete() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        try {
            Employee employee = em.find(Employee.class, 1L);
            em.remove(employee);

            em.getTransaction().commit();

        } finally {
            em.close();
        }
    }
}

package org.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class UserJpaTest {
    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;

    @BeforeAll
    public static void setup(){
//        System.out.println("BeforeAll 실행");
        emf = Persistence.createEntityManagerFactory("lionPU");
    }
    @AfterAll
    public static void tearDown(){

//        System.out.println("AfterAll 실행");
        if(emf != null){
            emf.close();
        }
    }

    @BeforeEach
    public void before(){
//        System.out.println("BeforeEach 실행");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
    }
    @AfterEach
    public void after(){
//        System.out.println("AfterEach 실행");
        if(tx != null && tx.isActive()){
            tx.rollback();
        }
        if(em != null){
            em.close();
        }
    }

    @Test
    void test1(){
        System.out.println("test1실행!!");
    }

    @Test
    void test2(){
        System.out.println("test2실행!!");
    }

    @Test
    @DisplayName("insert Test :: 성공하면 id 가 존재")
    void insertUser(){
        User user = new User("admin","admin@admin.com");
        em.persist(user);
        tx.commit();

        assertNotNull(user.getId(),"입력이 잘 되면 id 값이 자동으로 생성됩니다.");
    }
}

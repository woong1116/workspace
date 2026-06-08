package org.example.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaRun {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lionPU");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

//        트랜잭션 시작!!
        entityManager.getTransaction().begin();
//
//        User user = new User("carami_11","carami@carami.com");  // 비영속
//        user.setId(1L);

//        User user = entityManager.find(User.class, 1L);
//        user.setName("강경미");
//
//        user.setName("new_carami");
//
//
//        User user2 = new User("carami_33","carami@carami.com");  // 비영속
//        user2.setId(4L);
//
//        User user3 = new User("홍길동","hong@hong.com");
//
//
//        System.out.println("persist 실행전!!");
////        entityManager.persist(user);  // 영속상태
//        entityManager.persist(user3);
//        System.out.println("persist 실행후!!");

        User user1 = new User("Alice", "alice@example.com");  // id = null
        User user2 = new User("Alice", "alice@example.com");






//        User user = entityManager.find(User.class, 1L);
//        User user2 = entityManager.find(User.class, 1L);
//
//        if(user == user2)
//            System.out.println("같은 인스턴스!!");
//
//
//        User newUser = new User("둘리","22@gmail.com");
//        newUser.setId(6L);
//
//        entityManager.persist(newUser);
//
//        User findUser = entityManager.find(User.class,6L);
//
//        System.out.println(findUser);

        //조회 - 영속성컨텍스트에서 찾고,  없으면 DB에서 찾는다.
        User fUser = entityManager.find(User.class, 3L);
        User fUser2 = entityManager.find(User.class, 3L);

        if(fUser == fUser2)
            System.out.println("같은인스턴스");

//        수정 - 트랜잭션이 종료되는 시점에 영속성 컨텍스트를 분석해서 결정함!!
        fUser.setName("aaaa");
        fUser.setEmail("aaa@aaa.com");

//        삭제
        User delUser = entityManager.find(User.class, 6L);
        entityManager.remove(delUser);


//        트랜잭션 종료!!
        System.out.println("commit 실행 전!!");
        entityManager.getTransaction().commit();
        System.out.println("commit 실행 후!!");


        entityManager.close();
        entityManagerFactory.close();


    }

}

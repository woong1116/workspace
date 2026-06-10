package org.example.springdatajpa3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private final EntityManager entityManager;

    @Override
    public void caramiCustom() {
        System.out.println("!");
    }

    @Override
    public List<User> findUsersByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> user = query.from(User.class);
        query.select(user).where(criteriaBuilder.like(user.get("name"), "%" + name + "%"));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<User> findUsersDynamically(String name, String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> user = query.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null) {
            predicates.add(criteriaBuilder.equal(user.get("name"), name));
        }

        if (email != null) {
            predicates.add(criteriaBuilder.equal(user.get("email"), email));
        }

        query.select(user).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
}

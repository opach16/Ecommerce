package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CartDao extends CrudRepository<Cart, Long> {
    @Override
    List<Cart> findAll();

    Optional<Cart> findById(Long id);

    Cart save(Cart order);

    void deleteById(Long id);
}

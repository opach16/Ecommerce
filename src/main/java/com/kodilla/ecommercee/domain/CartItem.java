package com.kodilla.ecommercee.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name="CART_ITEMS")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",unique = true, nullable = false, updatable = false)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name="CART_ID")
    private Cart cart;

    @NotNull
    @Column(name="QUANTITY")
    private Integer quantity;

    @NotNull
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

    public CartItem(Cart cart, Integer quantity, Product product) {
        this.cart = cart;
        this.quantity = quantity;
        this.product = product;
    }

    public void setQuantity(@NotNull Integer quantity) {
        this.quantity = quantity;
    }
}

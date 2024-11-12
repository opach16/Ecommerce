package com.kodilla.ecommercee.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
   public void removeFromCart(){
        this.cart = null;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CartItem cartItem = (CartItem) o;
        return id.equals(cartItem.id) && Objects.equals(cart, cartItem.cart) && Objects.equals(quantity, cartItem.quantity) && Objects.equals(product, cartItem.product);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + Objects.hashCode(cart);
        result = 31 * result + Objects.hashCode(quantity);
        result = 31 * result + Objects.hashCode(product);
        return result;
    }
}

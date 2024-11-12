package com.kodilla.ecommercee.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CARTS")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique = true, nullable = false, updatable = false)
    private Long id;

    @OneToMany(
            targetEntity = CartItem.class,
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<CartItem> cartItems;

    @NotNull
    @Column(name="ORDERED")
    private boolean ordered;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    private BigDecimal total;

    public Cart(User user) {
        this.user = user;
        this.cartItems  = new ArrayList<CartItem>();
        this.ordered = false;
    }

    public Cart(List<CartItem> cartItems, User user) {
        this.cartItems = cartItems;
        this.user = user;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setOrdered(@NotNull boolean ordered) {
        this.ordered = ordered;
    }
    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }
}

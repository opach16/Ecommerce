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
@Entity
@Table(name="CARTS")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID",unique=true,nullable=false,updatable=false)
    private Long id;

    @OneToMany(
            targetEntity = CartItem.class,
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
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
        this.total = BigDecimal.ZERO;
        this.ordered = false;
        this.cartItems = new ArrayList<>();
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }
    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }
}

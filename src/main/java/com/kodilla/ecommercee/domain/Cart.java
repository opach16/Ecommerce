package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="CARTS")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false, updatable=false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @OneToMany(
            targetEntity = CartItem.class,
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<CartItem> cartItems = new ArrayList<>();

    @NotNull
    @Column(name="ORDERED")
    private boolean ordered;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @Column(name="TOTAL")
    private BigDecimal total;

    public Cart(User user) {
        this.user = user;
        this.ordered = false;
    }

    @PrePersist
    protected void onCreate() {
        ordered = false;
    }

    public void addCartItem(CartItem cartItem) {
        this.cartItems.add(cartItem);
    }
    public void removeCartItem(CartItem cartItem) {
        this.cartItems.remove(cartItem);
    }
}

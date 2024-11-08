package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", unique=true, nullable=false, updatable=false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NotNull
    @Column(name="STATUS")
    private String status;

    @NotNull
    @Column(name="ORDER_DATE")
    private LocalDate orderDate;

    @OneToOne
    @JoinColumn(name="CART_ID")
    private Cart cart;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;

    public Order(Cart cart, User user) {
        this.cart = cart;
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        this.status = "Ready to order";
        this.orderDate = LocalDate.now();
    }
}

package com.kodilla.ecommercee.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name="ID")
    private Long id;

    @NotNull
    @Column(name="STATUS")
    private String status;

    @NotNull
    @Column(name="ORDER_DATE")
    private LocalDate orderDate;

    @NotNull
    @OneToOne
    @JoinColumn(name="CART_ID")
    private Cart cart;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="USER_ID")
    private User user;



}

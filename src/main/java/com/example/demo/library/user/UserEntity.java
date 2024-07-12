package com.example.demo.library.user;

import org.springframework.beans.factory.annotation.Value;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, length = 20, name = "name", updatable = true)
    private String name;
    @Column(nullable = false, length = 50, name = "email", unique = true, updatable = true)
    private String email;
    // positive or zero
    @Column(nullable = false, name = "age", updatable = true, columnDefinition = " INT CHECK (age >= 0)")
    private int age;

    @Transient
    @Value(" #{this.name} + ' ' + #{this.email}")
    private String namePlusEmail;

    @Embedded
    private UserAddress address;
}

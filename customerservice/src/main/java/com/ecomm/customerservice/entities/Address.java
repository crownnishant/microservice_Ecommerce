package com.ecomm.customerservice.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Entity
@Table(name="address")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Validated
@Builder
public class Address {

    @Id
    private String streetId = UUID.randomUUID().toString();

    private String street;
    private String houseNumber;
    private String zipCode;

}

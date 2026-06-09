package org.example.jpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("TRUCK")
@Getter@Setter
public class Truck extends Vehicle{
    private double payloadCapacity;
}
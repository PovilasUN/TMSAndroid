package com.tmsapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Cargo {
    private int id;
    private CargoType cargoType;
    private String numberOfProducts;
    private String cargoWeight;
    private DeliveryStatus deliveryStatus;

    public Cargo(CargoType cargoType, String numberOfProducts, String cargoWeight, DeliveryStatus deliveryStatus) {
        this.cargoType = cargoType;
        this.numberOfProducts = numberOfProducts;
        this.cargoWeight = cargoWeight;
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return getCargoType() + " " + getNumberOfProducts() + "qty. " + getCargoWeight() + "kg. " + getDeliveryStatus();
    }
}

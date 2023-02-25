package com.tmsapp.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Truck {
    private int id;
    private TruckModel truckModel;
    private LocalDate makeDate;
    private LocalDate technicalCheckExpirationDate;
    private double truckWeight;
    private int horsePower;
    private double fuelTankCapacity;

    public Truck(TruckModel truckModel, LocalDate makeDate, LocalDate technicalCheckExpirationDate, double truckWeight, int horsePower, double fuelTankCapacity) {
        this.truckModel = truckModel;
        this.makeDate = makeDate;
        this.technicalCheckExpirationDate = technicalCheckExpirationDate;
        this.truckWeight = truckWeight;
        this.horsePower = horsePower;
        this.fuelTankCapacity = fuelTankCapacity;
    }

    @Override
    public String toString() {
        return getTruckModel() + " " + getMakeDate() + " " + getTruckWeight() + "kg " + getHorsePower() + "HP " + getFuelTankCapacity() + "l.";
    }
}

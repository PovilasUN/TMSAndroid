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

public class Destination {
    private int id;
    private String startLocation;
    private String endLocation;
    private LocalDate startDate;
    private LocalDate endDate;

    public Destination(String startLocation, String endLocation, LocalDate startDate, LocalDate endDate) {
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return getStartLocation() + " " + getEndLocation() + " " + getStartDate() + " " + getEndDate();
    }
}

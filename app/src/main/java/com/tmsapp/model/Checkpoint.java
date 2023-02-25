package com.tmsapp.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Checkpoint {
    private int id;
    private String checkpointLocation;
    private String usedAmountOfFuel;
    private LocalDateTime checkpointTime = LocalDateTime.now();

    public Checkpoint(String checkpointLocation, String usedAmountOfFuel, LocalDateTime checkpointTime) {
        this.checkpointLocation = checkpointLocation;
        this.usedAmountOfFuel = usedAmountOfFuel;
        this.checkpointTime = checkpointTime;
    }

    @Override
    public String toString() {
        return getCheckpointLocation() + " " + getUsedAmountOfFuel() + "l. " + getCheckpointTime();
    }
}

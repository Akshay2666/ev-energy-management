
package com.evenergy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "charging_simulations")
public class ChargingSimulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleId;
    private int priority;
    private double allocatedPowerKW;
    private double buildingLoadKW;
    private double maxGridCapacityKW;

    public ChargingSimulation() {
    }

    public ChargingSimulation(
            String vehicleId,
            int priority,
            double allocatedPowerKW,
            double buildingLoadKW,
            double maxGridCapacityKW) {
        this.vehicleId = vehicleId;
        this.priority = priority;
        this.allocatedPowerKW = allocatedPowerKW;
        this.buildingLoadKW = buildingLoadKW;
        this.maxGridCapacityKW = maxGridCapacityKW;
    }

    public Long getId() {
        return id;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public int getPriority() {
        return priority;
    }

    public double getAllocatedPowerKW() {
        return allocatedPowerKW;
    }

    public double getBuildingLoadKW() {
        return buildingLoadKW;
    }

    public double getMaxGridCapacityKW() {
        return maxGridCapacityKW;
    }
}



package com.evenergy.model;

public class ChargingAllocationResponse {

    private String vehicleId;
    private int priority;
    private double allocatedPowerKW;

    public ChargingAllocationResponse(
            String vehicleId,
            int priority,
            double allocatedPowerKW) {
        this.vehicleId = vehicleId;
        this.priority = priority;
        this.allocatedPowerKW = allocatedPowerKW;
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
}


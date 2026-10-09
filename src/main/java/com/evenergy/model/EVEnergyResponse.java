
package com.evenergy.model;

public class EVEnergyResponse {

    private String vehicleId;
    private double requiredEnergyKWh;

    public EVEnergyResponse(String vehicleId, double requiredEnergyKWh) {
        this.vehicleId = vehicleId;
        this.requiredEnergyKWh = requiredEnergyKWh;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public double getRequiredEnergyKWh() {
        return requiredEnergyKWh;
    }
}

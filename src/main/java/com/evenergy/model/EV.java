
package com.evenergy.model;

public class EV {

    private String vehicleId;
    private double batteryCapacityKWh;
    private double currentSOC;
    private double targetSOC;
    private double maxChargingPowerKW;


    public EV(String vehicleId,
              double batteryCapacityKWh,
              double currentSOC,
              double targetSOC,
              double maxChargingPowerKW) {

        this.vehicleId = vehicleId;
        this.batteryCapacityKWh = batteryCapacityKWh;
        this.currentSOC = currentSOC;
        this.targetSOC = targetSOC;
        this.maxChargingPowerKW = maxChargingPowerKW;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public double getBatteryCapacityKWh() {
        return batteryCapacityKWh;
    }

    public double getCurrentSOC() {
        return currentSOC;
    }

    public double getTargetSOC() {
        return targetSOC;
    }

    public double getMaxChargingPowerKW() {
        return maxChargingPowerKW;
    }

    public double getRequiredEnergyKWh() {
        return batteryCapacityKWh * (targetSOC - currentSOC) / 100.0;
    }

}


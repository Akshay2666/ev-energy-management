
package com.evenergy.model;

public class EV {

    private String vehicleId;
    private double batteryCapacityKWh;
    private double currentSOC;
    private double targetSOC;
    private double maxChargingPowerKW;
    private int priority;



    public EV(String vehicleId,
              double batteryCapacityKWh,
              double currentSOC,
              double targetSOC,
              double maxChargingPowerKW,
              int priority) {


        this.vehicleId = vehicleId;
        this.batteryCapacityKWh = batteryCapacityKWh;
        this.currentSOC = currentSOC;
        this.targetSOC = targetSOC;
        this.maxChargingPowerKW = maxChargingPowerKW;
        this.priority = priority;

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
    public int getPriority() {
        return priority;
    }


}


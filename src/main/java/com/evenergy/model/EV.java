
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

        if (vehicleId == null || vehicleId.isBlank()) {
            throw new IllegalArgumentException(
                    "Vehicle ID must not be null or blank."
            );
        }

        if (!Double.isFinite(batteryCapacityKWh)
                || batteryCapacityKWh <= 0) {
            throw new IllegalArgumentException(
                    "Battery capacity must be a positive, finite number."
            );
        }

        if (!Double.isFinite(currentSOC)
                || currentSOC < 0
                || currentSOC > 100) {
            throw new IllegalArgumentException(
                    "Current SOC must be between 0 and 100."
            );
        }

        if (!Double.isFinite(targetSOC)
                || targetSOC < currentSOC
                || targetSOC > 100) {
            throw new IllegalArgumentException(
                    "Target SOC must be between current SOC and 100."
            );
        }

        if (!Double.isFinite(maxChargingPowerKW)
                || maxChargingPowerKW <= 0) {
            throw new IllegalArgumentException(
                    "Maximum charging power must be a positive, finite number."
            );
        }

        if (priority < 1 || priority > 3) {
            throw new IllegalArgumentException(
                    "Priority must be 1, 2, or 3."
            );
        }

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
        return batteryCapacityKWh
                * (targetSOC - currentSOC) / 100.0;
    }

    public int getPriority() {
        return priority;
    }
}

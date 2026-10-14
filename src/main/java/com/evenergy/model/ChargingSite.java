
package com.evenergy.model;

public class ChargingSite {

    private double maxGridCapacityKW;
    private double currentBuildingLoadKW;



    public ChargingSite(double maxGridCapacityKW,
                        double currentBuildingLoadKW) {

        if (!Double.isFinite(maxGridCapacityKW)
                || maxGridCapacityKW <= 0) {
            throw new IllegalArgumentException(
                    "Grid capacity must be a positive, finite number."
            );
        }

        if (!Double.isFinite(currentBuildingLoadKW)
                || currentBuildingLoadKW < 0) {
            throw new IllegalArgumentException(
                    "Building load must be a non-negative, finite number."
            );
        }

        this.maxGridCapacityKW = maxGridCapacityKW;
        this.currentBuildingLoadKW = currentBuildingLoadKW;
    }


    public double getAvailableChargingPowerKW() {
        return maxGridCapacityKW - currentBuildingLoadKW;
    }

    public double getMaxGridCapacityKW() {
        return maxGridCapacityKW;
    }

    public double getCurrentBuildingLoadKW() {
        return currentBuildingLoadKW;
    }

}


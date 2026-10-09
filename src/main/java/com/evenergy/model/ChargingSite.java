
package com.evenergy.model;

public class ChargingSite {

    private double maxGridCapacityKW;
    private double currentBuildingLoadKW;


    public ChargingSite(double maxGridCapacityKW,
                        double currentBuildingLoadKW) {

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


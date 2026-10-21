
package com.evenergy.model;

import java.util.List;

public class ChargingSimulationRequest {

    private double buildingLoadKW;
    private double maxGridCapacityKW = 500.0;
    private List<EV> evList;


    public ChargingSimulationRequest() {
    }

    public double getBuildingLoadKW() {
        return buildingLoadKW;
    }

    public void setBuildingLoadKW(double buildingLoadKW) {
        this.buildingLoadKW = buildingLoadKW;
    }
    public double getMaxGridCapacityKW() {
        return maxGridCapacityKW;
    }

    public void setMaxGridCapacityKW(double maxGridCapacityKW) {
        this.maxGridCapacityKW = maxGridCapacityKW;
    }
    public List<EV> getEvList() {
        return evList;
    }

    public void setEvList(List<EV> evList) {
        this.evList = evList;
    }
}

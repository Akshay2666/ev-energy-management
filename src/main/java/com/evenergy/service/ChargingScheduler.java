
package com.evenergy.service;

import com.evenergy.model.ChargingSite;
import com.evenergy.model.EV;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChargingScheduler {

    public double calculateAvailablePower(ChargingSite site) {
        return site.getAvailableChargingPowerKW();
    }

    public Map<String, Double> allocateChargingPower(
            ChargingSite site, List<EV> evList) {

        Map<String, Double> allocations = new LinkedHashMap<>();


        double remainingPower = Math.max(
                0.0,
                calculateAvailablePower(site)
        );


        for (EV ev : evList) {
            double allocatedPower = Math.min(
                    ev.getMaxChargingPowerKW(),
                    remainingPower
            );

            allocations.put(ev.getVehicleId(), allocatedPower);

            remainingPower -= allocatedPower;
        }

        return allocations;
    }
}

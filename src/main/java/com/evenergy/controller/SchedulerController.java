package com.evenergy.controller;
import com.evenergy.model.ChargingAllocationResponse;
import com.evenergy.model.ChargingSite;
import com.evenergy.model.EV;
import com.evenergy.service.ChargingScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SchedulerController {

    @GetMapping("/api/scheduler/test")
    public Map<String, Double> testScheduler() {

        ChargingSite site = new ChargingSite(500.0, 480.0);

        List<EV> evList = List.of(
                new EV("EV001", 80.0, 30.0, 90.0, 11.0,1),
                new EV("EV002", 60.0, 50.0, 80.0, 7.0,2),
                new EV("EV003", 100.0, 20.0, 100.0, 22.0,3)
        );

        ChargingScheduler scheduler = new ChargingScheduler();

        return scheduler.allocateChargingPower(site, evList);
    }


    @GetMapping("/api/scheduler/simulate")
    public List<ChargingAllocationResponse> simulateScheduler(
            @org.springframework.web.bind.annotation.RequestParam(
                    defaultValue = "300.0") double buildingLoadKW) {

        ChargingSite site = new ChargingSite(500.0, buildingLoadKW);

        List<EV> evList = List.of(
                new EV("EV001", 80.0, 30.0, 90.0, 11.0, 1),
                new EV("EV002", 60.0, 50.0, 80.0, 7.0, 2),
                new EV("EV003", 100.0, 20.0, 100.0, 22.0, 3)
        );

        ChargingScheduler scheduler = new ChargingScheduler();

        Map<String, Double> allocations =
                scheduler.allocateChargingPower(site, evList);

        List<ChargingAllocationResponse> response = new ArrayList<>();

        for (EV ev : evList) {
            double allocatedPower =
                    allocations.getOrDefault(ev.getVehicleId(), 0.0);

            response.add(new ChargingAllocationResponse(
                    ev.getVehicleId(),
                    ev.getPriority(),
                    allocatedPower
            ));
        }

        return response;
    }


}



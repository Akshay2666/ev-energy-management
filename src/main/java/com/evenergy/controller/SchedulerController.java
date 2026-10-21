
package com.evenergy.controller;

import com.evenergy.model.ChargingAllocationResponse;
import com.evenergy.model.ChargingSimulation;
import com.evenergy.model.ChargingSimulationRequest;
import com.evenergy.model.ChargingSite;
import com.evenergy.model.EV;
import com.evenergy.service.ChargingScheduler;
import com.evenergy.service.ChargingSimulationService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SchedulerController {

    private final ChargingSimulationService simulationService;

    public SchedulerController(
            ChargingSimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @GetMapping("/api/scheduler/test")
    public Map<String, Double> testScheduler() {

        ChargingSite site = new ChargingSite(500.0, 480.0);

        List<EV> evList = List.of(
                new EV("EV001", 80.0, 30.0, 90.0, 11.0, 1),
                new EV("EV002", 60.0, 50.0, 80.0, 7.0, 2),
                new EV("EV003", 100.0, 20.0, 100.0, 22.0, 3)
        );

        ChargingScheduler scheduler = new ChargingScheduler();

        return scheduler.allocateChargingPower(site, evList);
    }

    @GetMapping("/api/scheduler/simulate")
    public List<ChargingAllocationResponse> simulateScheduler(
            @RequestParam(defaultValue = "300.0")
            double buildingLoadKW) {

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

    @PostMapping("/api/scheduler/simulate/custom")
    public List<ChargingAllocationResponse> simulateCustom(
            @RequestBody ChargingSimulationRequest request) {

        ChargingSite site = new ChargingSite(
                request.getMaxGridCapacityKW(),
                request.getBuildingLoadKW()
        );

        List<EV> evList = request.getEvList();

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

    @PostMapping("/api/scheduler/simulate/custom/save")
    public List<ChargingSimulation> simulateAndSave(
            @RequestBody ChargingSimulationRequest request) {

        ChargingSite site = new ChargingSite(
                request.getMaxGridCapacityKW(),
                request.getBuildingLoadKW()
        );

        List<EV> evList = request.getEvList();

        ChargingScheduler scheduler = new ChargingScheduler();

        Map<String, Double> allocations =
                scheduler.allocateChargingPower(site, evList);

        List<ChargingSimulation> records = new ArrayList<>();

        for (EV ev : evList) {
            double allocatedPower =
                    allocations.getOrDefault(ev.getVehicleId(), 0.0);

            records.add(new ChargingSimulation(
                    ev.getVehicleId(),
                    ev.getPriority(),
                    allocatedPower,
                    request.getBuildingLoadKW(),
                    request.getMaxGridCapacityKW()
            ));
        }

        return simulationService.saveAll(records);
    }

    @GetMapping("/api/scheduler/history")
    public List<ChargingSimulation> getSimulationHistory() {
        return simulationService.getAll();
    }
}




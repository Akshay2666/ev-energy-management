
package com.evenergy.controller;
import com.evenergy.model.EVEnergyResponse;
import java.util.ArrayList;
import com.evenergy.model.EV;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EVController {

    @GetMapping("/api/ev/test")
    public String test() {
        return "EV Energy Management API is working!";
    }

    @GetMapping("/api/ev/sample")
    public EV getSampleEV() {
        return new EV("EV001", 80.0, 30.0, 90.0, 11.0);
    }

    @GetMapping("/api/ev/energy")
    public double getRequiredEnergy() {
        EV ev = new EV("EV001", 80.0, 30.0, 90.0, 11.0);

        return ev.getRequiredEnergyKWh();
    }


    @GetMapping("/api/ev/all")
    public List<EV> getAllEVs() {

        EV ev1 = new EV("EV001", 80.0, 30.0, 90.0, 11.0);
        EV ev2 = new EV("EV002", 60.0, 50.0, 80.0, 7.0);
        EV ev3 = new EV("EV003", 100.0, 20.0, 100.0, 22.0);

        return List.of(ev1, ev2, ev3);
    }

    @GetMapping("/api/ev/energy/all")
    public List<EVEnergyResponse> getAllEVEnergy() {

        EV ev1 = new EV("EV001", 80.0, 30.0, 90.0, 11.0);
        EV ev2 = new EV("EV002", 60.0, 50.0, 80.0, 7.0);
        EV ev3 = new EV("EV003", 100.0, 20.0, 100.0, 22.0);

        List<EV> vehicles = List.of(ev1, ev2, ev3);
        List<EVEnergyResponse> results = new ArrayList<>();

        for (EV ev : vehicles) {
            EVEnergyResponse response = new EVEnergyResponse(
                    ev.getVehicleId(),
                    ev.getRequiredEnergyKWh()
            );

            results.add(response);
        }

        return results;
    }

}

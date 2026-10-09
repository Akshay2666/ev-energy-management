
package com.evenergy.controller;

import com.evenergy.model.ChargingSite;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChargingSiteController {

    @GetMapping("/api/site")
    public ChargingSite getChargingSite() {
        return new ChargingSite(500.0, 300.0);
    }

    @GetMapping("/api/site/simulate")
    public ChargingSite simulateBuildingLoad(
            @RequestParam double buildingLoadKW) {

        return new ChargingSite(500.0, buildingLoadKW);
    }

}



package com.evenergy.service;

import com.evenergy.model.ChargingSimulation;
import com.evenergy.repository.ChargingSimulationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargingSimulationService {

    private final ChargingSimulationRepository repository;

    public ChargingSimulationService(
            ChargingSimulationRepository repository) {
        this.repository = repository;
    }

    public List<ChargingSimulation> saveAll(
            List<ChargingSimulation> simulations) {
        return repository.saveAll(simulations);
    }

    public List<ChargingSimulation> getAll() {
        return repository.findAll();
    }
}


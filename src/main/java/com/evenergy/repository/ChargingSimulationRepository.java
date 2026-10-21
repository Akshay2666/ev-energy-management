
package com.evenergy.repository;

import com.evenergy.model.ChargingSimulation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChargingSimulationRepository
        extends JpaRepository<ChargingSimulation, Long> {
}

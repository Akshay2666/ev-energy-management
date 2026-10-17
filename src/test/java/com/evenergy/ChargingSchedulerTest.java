
package com.evenergy;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.evenergy.model.ChargingSite;
import com.evenergy.model.EV;
import com.evenergy.service.ChargingScheduler;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChargingSchedulerTest {

    @Test
    void shouldRespectAvailableChargingPower() {

        ChargingSite site = new ChargingSite(500.0, 480.0);

        List<EV> evList = List.of(
                new EV("EV001", 80.0, 30.0, 90.0, 11.0, 1),
                new EV("EV002", 60.0, 50.0, 80.0, 7.0, 2),
                new EV("EV003", 100.0, 20.0, 100.0, 22.0, 3)
        );

        ChargingScheduler scheduler = new ChargingScheduler();

        Map<String, Double> allocations =
                scheduler.allocateChargingPower(site, evList);

        assertEquals(11.0, allocations.get("EV001"), 0.001);
        assertEquals(7.0, allocations.get("EV002"), 0.001);
        assertEquals(2.0, allocations.get("EV003"), 0.001);

        double totalAllocated = allocations.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        assertEquals(20.0, totalAllocated, 0.001);
    }

    @Test
    void shouldAllocateZeroPowerWhenBuildingExceedsCapacity() {

        ChargingSite site = new ChargingSite(500.0, 520.0);

        List<EV> evList = List.of(
                new EV("EV001", 80.0, 30.0, 90.0, 11.0, 1),
                new EV("EV002", 60.0, 50.0, 80.0, 7.0, 2),
                new EV("EV003", 100.0, 20.0, 100.0, 22.0, 3)
        );

        ChargingScheduler scheduler = new ChargingScheduler();

        Map<String, Double> allocations =
                scheduler.allocateChargingPower(site, evList);

        assertEquals(0.0, allocations.get("EV001"), 0.001);
        assertEquals(0.0, allocations.get("EV002"), 0.001);
        assertEquals(0.0, allocations.get("EV003"), 0.001);
    }

    @Test
    void shouldAllocatePowerAccordingToPriority() {

        ChargingSite site = new ChargingSite(500.0, 480.0);

        // Deliberately put the lowest-priority EV first.
        List<EV> evList = List.of(
                new EV("EV003", 100.0, 20.0, 100.0, 22.0, 3),
                new EV("EV002", 60.0, 50.0, 80.0, 7.0, 2),
                new EV("EV001", 80.0, 30.0, 90.0, 11.0, 1)
        );

        ChargingScheduler scheduler = new ChargingScheduler();

        Map<String, Double> allocations =
                scheduler.allocateChargingPower(site, evList);

        assertEquals(11.0, allocations.get("EV001"), 0.001);
        assertEquals(7.0, allocations.get("EV002"), 0.001);
        assertEquals(2.0, allocations.get("EV003"), 0.001);
    }

    @Test
    void shouldRejectNegativeGridCapacity() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new ChargingSite(-500.0, 300.0)
        );
    }

    @Test
    void shouldRejectNegativeBuildingLoad() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new ChargingSite(500.0, -10.0)
        );
    }

    @Test
    void shouldNeverExceedEVMaximumChargingPower() {

        ChargingSite site = new ChargingSite(500.0, 300.0);

        List<EV> evList = List.of(
                new EV("EV001", 80.0, 30.0, 90.0, 11.0, 1),
                new EV("EV002", 60.0, 50.0, 80.0, 7.0, 2),
                new EV("EV003", 100.0, 20.0, 100.0, 22.0, 3)
        );

        ChargingScheduler scheduler = new ChargingScheduler();

        Map<String, Double> allocations =
                scheduler.allocateChargingPower(site, evList);

        for (EV ev : evList) {
            double allocatedPower = allocations.get(ev.getVehicleId());

            assertTrue(
                    allocatedPower <= ev.getMaxChargingPowerKW(),
                    ev.getVehicleId() + " exceeded its maximum charging power"
            );
        }
    }

    @Test
    void shouldReturnEmptyAllocationsWhenNoEVsArePresent() {

        ChargingSite site = new ChargingSite(500.0, 300.0);

        List<EV> evList = List.of();

        ChargingScheduler scheduler = new ChargingScheduler();

        Map<String, Double> allocations =
                scheduler.allocateChargingPower(site, evList);

        assertTrue(allocations.isEmpty());
    }

    @Test
    void shouldAllocateZeroPowerWhenNoPowerIsAvailable() {

        ChargingSite site = new ChargingSite(500.0, 500.0);

        List<EV> evList = List.of(
                new EV("EV001", 80.0, 30.0, 90.0, 11.0, 1),
                new EV("EV002", 60.0, 50.0, 80.0, 7.0, 2),
                new EV("EV003", 100.0, 20.0, 100.0, 22.0, 3)
        );

        ChargingScheduler scheduler = new ChargingScheduler();

        Map<String, Double> allocations =
                scheduler.allocateChargingPower(site, evList);

        assertEquals(0.0, allocations.get("EV001"), 0.001);
        assertEquals(0.0, allocations.get("EV002"), 0.001);
        assertEquals(0.0, allocations.get("EV003"), 0.001);
    }

    @Test
    void shouldNeverAllocateNegativePower() {

        ChargingSite site = new ChargingSite(500.0, 495.0);

        List<EV> evList = List.of(
                new EV("EV001", 80.0, 30.0, 90.0, 11.0, 1),
                new EV("EV002", 60.0, 50.0, 80.0, 7.0, 2),
                new EV("EV003", 100.0, 20.0, 100.0, 22.0, 3)
        );

        ChargingScheduler scheduler = new ChargingScheduler();

        Map<String, Double> allocations =
                scheduler.allocateChargingPower(site, evList);

        for (double allocatedPower : allocations.values()) {
            assertTrue(allocatedPower >= 0.0);
        }
    }

    @Test
    void shouldNeverExceedAvailableSitePower() {

        ChargingSite site = new ChargingSite(500.0, 470.0);

        List<EV> evList = List.of(
                new EV("EV001", 80.0, 30.0, 90.0, 11.0, 1),
                new EV("EV002", 60.0, 50.0, 80.0, 7.0, 2),
                new EV("EV003", 100.0, 20.0, 100.0, 22.0, 3)
        );

        ChargingScheduler scheduler = new ChargingScheduler();

        Map<String, Double> allocations =
                scheduler.allocateChargingPower(site, evList);

        double totalAllocated = allocations.values()
                .stream()
                .mapToDouble(Double::doubleValue)
                .sum();

        assertTrue(
                totalAllocated <= site.getAvailableChargingPowerKW()
        );
    }

}


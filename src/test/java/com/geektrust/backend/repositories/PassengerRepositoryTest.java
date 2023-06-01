package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.CheckIn;
import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationName;
import com.geektrust.backend.services.IMetroCardService;
import com.geektrust.backend.services.MetroCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PassengerRepositoryTest {

    private IPassengerRepository passengerRepository;
    private IMetroCardService metroCardService;

    @BeforeEach
    void setUp() {
        metroCardService = new MetroCardService();
        passengerRepository = new PassengerRepository(metroCardService);
    }

    @Test
    void checkInPassenger_ShouldUpdateStationAmountMapAndPassengerMap_WhenPassengerChecksIn() {
        // Arrange
        CheckIn checkIn = new CheckIn("card123", StationName.A, PassengerType.REGULAR);

        // Act
        passengerRepository.checkInPassenger(checkIn);

        // Assert
        Map<String, Integer> stationAmountMap = passengerRepository.getStationAmountMap();
        Map<String, StationName> passengerMap = passengerRepository.getPassengerMap();
        assertEquals(1, stationAmountMap.get(StationName.A.name()));
        assertTrue(passengerMap.containsKey("card123"));
        assertEquals(StationName.A, passengerMap.get("card123"));
    }

    @Test
    void checkInPassenger_ShouldUpdateStationAmountMapAndRemoveCardFromPassengerMap_WhenPassengerChecksOut() {
        // Arrange
        CheckIn checkIn = new CheckIn("card123", StationName.A, PassengerType.REGULAR);
        passengerRepository.checkInPassenger(checkIn);
        CheckIn checkOut = new CheckIn("card123", StationName.B, PassengerType.REGULAR);

        // Act
        passengerRepository.checkInPassenger(checkOut);

        // Assert
        Map<String, Integer> stationAmountMap = passengerRepository.getStationAmountMap();
        Map<String, StationName> passengerMap = passengerRepository.getPassengerMap();
        assertEquals(2, stationAmountMap.get(StationName.A.name()));
        assertFalse(passengerMap.containsKey("card123"));
    }

    @Test
    void checkInPassenger_ShouldUpdateStationDiscountMap_WhenPassengerChecksIn() {
        // Arrange
        CheckIn checkIn = new CheckIn("card123", StationName.A, PassengerType.REGULAR);

        // Act
        passengerRepository.checkInPassenger(checkIn);

        // Assert
        Map<String, Integer> stationDiscountMap = passengerRepository.getStationDiscountMap();
        assertEquals(1, stationDiscountMap.get(StationName.A.name()));
    }

    @Test
    void checkInPassenger_ShouldUpdateStationTypeCountMap_WhenPassengerChecksIn() {
        // Arrange
        CheckIn checkIn = new CheckIn("card123", StationName.A, PassengerType.REGULAR);

        // Act
        passengerRepository.checkInPassenger(checkIn);

        // Assert
        Map<StationName, Map<PassengerType, Integer>> stationTypeCountMap = passengerRepository.getStationTypeCountMap();
        Map<PassengerType, Integer> typeCountMap = stationTypeCountMap.get(StationName.A);
        assertEquals(1, typeCountMap.get(PassengerType.REGULAR));
    }
}

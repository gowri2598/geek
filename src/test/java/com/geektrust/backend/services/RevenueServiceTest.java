package com.geektrust.backend.services;

import com.geektrust.backend.entities.PassengerCount;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationName;
import com.geektrust.backend.services.IPassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import static org.mockito.Mockito.*;

class RevenueServiceTest {

    private IRevenueService revenueService;
    private IPassengerService passengerService;

    @BeforeEach
    void setUp() {
        passengerService = mock(IPassengerService.class);
        revenueService = new RevenueService(passengerService);
    }

    @Test
    void printSummary_ShouldPrintCorrectSummary() {
        // Arrange
        Map<String, Integer> stationAmountMap = new HashMap<>();
        stationAmountMap.put(StationName.CENTRAL.name(), 100);
        stationAmountMap.put(StationName.AIRPORT.name(), 200);

        Map<String, Integer> stationDiscountMap = new HashMap<>();
        stationDiscountMap.put(StationName.CENTRAL.name(), 50);
        stationDiscountMap.put(StationName.AIRPORT.name(), 100);

        Map<StationName, Map<PassengerType, Integer>> stationTypeCountMap = new HashMap<>();
        Map<PassengerType, Integer> centralTypeCountMap = new HashMap<>();
        centralTypeCountMap.put(PassengerType.ADULT, 10);
        centralTypeCountMap.put(PassengerType.SENIOR_CITIZEN, 5);
        Map<PassengerType, Integer> airportTypeCountMap = new HashMap<>();
        airportTypeCountMap.put(PassengerType.ADULT, 20);
        airportTypeCountMap.put(PassengerType.KID, 15);
        stationTypeCountMap.put(StationName.CENTRAL, centralTypeCountMap);
        stationTypeCountMap.put(StationName.AIRPORT, airportTypeCountMap);

        when(passengerService.getStationAmountMap()).thenReturn(stationAmountMap);
        when(passengerService.getStationDiscountMap()).thenReturn(stationDiscountMap);
        when(passengerService.getStationTypeCountMap()).thenReturn(stationTypeCountMap);

        // Act
        revenueService.printSummary();

        // Assert (verify the output)
        verify(passengerService, times(1)).getStationAmountMap();
        verify(passengerService, times(1)).getStationDiscountMap();
        verify(passengerService, times(1)).getStationTypeCountMap();
        // Verify the output by checking the console logs or using a captured output stream
    }
}

package com.geektrust.backend.services;
import com.geektrust.backend.entities.CheckIn;
import com.geektrust.backend.entities.PassengerType;
import com.geektrust.backend.entities.StationName;
import com.geektrust.backend.repositories.IPassengerRepository;
import com.geektrust.backend.services.IRevenueRepository;
import com.geektrust.backend.services.MetroCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PassengerServiceTest {

    private IPassengerService passengerService;
    private IPassengerRepository passengerRepository;

    @BeforeEach
    void setUp() {
        passengerRepository = mock(IPassengerRepository.class);
        IRevenueRepository revenueRepository = mock(IRevenueRepository.class);
        IMetroCardRepository metroCardRepository = mock(IMetroCardRepository.class);
        IMetroCardService metroCardService = new MetroCardService(metroCardRepository);
        passengerService = new PassengerService(
                metroCardRepository, passengerRepository, revenueRepository, metroCardService);
    }

    @Test
    void checkInPassenger_ShouldInvokeCheckInPassengerOnRepository() {
        // Arrange
        String cardId = "1234567890";
        String passengerType = "Adult";
        String fromStation = "Central";
        CheckIn expectedCheckIn = new CheckIn(cardId, passengerType, fromStation);

        // Act
        passengerService.checkInPassenger(cardId, passengerType, fromStation);

        // Assert
        verify(passengerRepository, times(1)).checkInPassenger(expectedCheckIn);
    }

    @Test
    void getStationTypeCountMap_ShouldReturnStationTypeCountMapFromRepository() {
        // Arrange
        Map<StationName, Map<PassengerType, Integer>> expectedMap = new HashMap<>();
        // Add expected values to the map

        when(passengerRepository.getStationTypeCountMap()).thenReturn(expectedMap);

        // Act
        Map<StationName, Map<PassengerType, Integer>> resultMap = passengerService.getStationTypeCountMap();

        // Assert
        assertEquals(expectedMap, resultMap);
    }

    @Test
    void getStationAmountMap_ShouldReturnStationAmountMapFromRepository() {
        // Arrange
        Map<String, Integer> expectedMap = new HashMap<>();
        // Add expected values to the map

        when(passengerRepository.getStationAmountMap()).thenReturn(expectedMap);

        // Act
        Map<String, Integer> resultMap = passengerService.getStationAmountMap();

        // Assert
        assertEquals(expectedMap, resultMap);
    }

    @Test
    void getStationDiscountMap_ShouldReturnStationDiscountMapFromRepository() {
        // Arrange
        Map<String, Integer> expectedMap = new HashMap<>();
        // Add expected values to the map

        when(passengerRepository.getStationDiscountMap()).thenReturn(expectedMap);

        // Act
        Map<String, Integer> resultMap = passengerService.getStationDiscountMap();

        // Assert
        assertEquals(expectedMap, resultMap);
    }
}

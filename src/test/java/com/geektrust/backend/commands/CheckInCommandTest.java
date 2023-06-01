package com.geektrust.backend.commands;

import com.geektrust.backend.commands.CheckInCommand;
import com.geektrust.backend.exceptions.CardNumberNotFoundException;
import com.geektrust.backend.services.IMetroCardService;
import com.geektrust.backend.services.IPassengerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

class CheckInCommandTest {

    @Mock
    private IMetroCardService metroCardService;
    
    @Mock
    private IPassengerService passengerService;

    private CheckInCommand checkInCommand;

    
    @ExtendWith(MockitoExtension.class)
    @BeforeEach
    void setUp() {
     
        checkInCommand = new CheckInCommand(metroCardService, passengerService);
    }

    @Test
    void execute_ShouldCheckInPassenger_WhenValidTokensProvided() {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("command");  // Assuming there is a specific command token
        tokens.add("cardNumber");
        tokens.add("passengerName");
        tokens.add("destination");

        // Act
        checkInCommand.execute(tokens);

        // Assert
        Mockito.verify(passengerService).checkInPassenger("cardNumber", "passengerName", "destination");
        // Here, you would need to replace "checkInPassenger" with the actual method name in IPassengerService
        // and provide the appropriate arguments based on your implementation.

        // Additionally, you can assert any other expected behavior or conditions based on your implementation.
    }
}

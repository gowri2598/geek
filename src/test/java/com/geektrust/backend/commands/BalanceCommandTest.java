package com.geektrust.backend.commands;

import com.geektrust.backend.commands.BalanceCommand;
import com.geektrust.backend.exceptions.CardNumberNotFoundException;
import com.geektrust.backend.services.IMetroCardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class BalanceCommandTest {

    @Mock
    private IMetroCardService metroCardService;

    private BalanceCommand balanceCommand;

    @BeforeEach
    void setUp() {
        
        balanceCommand = new BalanceCommand(metroCardService);
    }

    @Test
    void execute_ShouldAddCard_WhenValidTokensProvided() {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("command");  // Assuming there is a specific command token
        tokens.add("cardNumber");
        tokens.add("balance");

        // Act
        balanceCommand.execute(tokens);

        // Assert
        Mockito.verify(metroCardService).addCard("cardNumber", Integer.parseInt("balance"));
        // Here, you would need to replace "addCard" with the actual method name in IMetroCardService
        // and provide the appropriate arguments based on your implementation.

        // Additionally, you can assert any other expected behavior or conditions based on your implementation.
    }
}
package com.geektrust.backend.commands;

import com.geektrust.backend.commands.ICommand;
import com.geektrust.backend.commands.PrintSummaryCommand;
import com.geektrust.backend.services.IMetroCardService;
import com.geektrust.backend.services.IRevenueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class PrintSummaryCommandTest {

    @Mock
    private IMetroCardService metroCardService;
    
    @Mock
    private IRevenueService revenueService;

    private PrintSummaryCommand printSummaryCommand;

    @BeforeEach
    void setUp() {
        
        printSummaryCommand = new PrintSummaryCommand(metroCardService, revenueService);
    }

    @Test
    void execute_ShouldCallPrintSummary_WhenValidTokensProvided() {
        // Arrange
        List<String> tokens = new ArrayList<>();
        tokens.add("command");  // Assuming there is a specific command token

        // Act
        printSummaryCommand.execute(tokens);

        // Assert
        Mockito.verify(revenueService).printSummary();
        // Here, you would need to replace "printSummary" with the actual method name in IRevenueService.

        // Additionally, you can assert any other expected behavior or conditions based on your implementation.
    }
}

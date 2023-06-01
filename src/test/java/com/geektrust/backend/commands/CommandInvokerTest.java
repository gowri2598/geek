package com.geektrust.backend.commands;

import com.geektrust.backend.commands.CommandInvoker;
import com.geektrust.backend.commands.ICommand;
import com.geektrust.backend.exceptions.NoSuchCommandException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommandInvokerTest {

    private CommandInvoker commandInvoker;

    @BeforeEach
    void setUp() {
        commandInvoker = new CommandInvoker();
    }

    @Test
    void register_ShouldRegisterCommand_WhenValidCommandNameAndCommandProvided() {
        // Arrange
        String commandName = "balance";
        ICommand command = mock(ICommand.class);

        // Act
        commandInvoker.register(commandName, command);

        // Assert
        assertSame(command, commandInvoker.get(commandName));
    }

    @Test
    void executeCommand_ShouldExecuteRegisteredCommand_WhenValidCommandNameAndTokensProvided() throws NoSuchCommandException {
        // Arrange
        String commandName = "balance";
        List<String> tokens = new ArrayList<>();
        ICommand command = mock(ICommand.class);
        commandInvoker.register(commandName, command);

        // Act
        commandInvoker.executeCommand(commandName, tokens);

        // Assert
        verify(command).execute(tokens);
    }

    @Test
    void executeCommand_ShouldThrowNoSuchCommandException_WhenCommandNameNotRegistered() {
        // Arrange
        String commandName = "invalidCommand";
        List<String> tokens = new ArrayList<>();

        // Act & Assert
        assertThrows(NoSuchCommandException.class, () -> commandInvoker.executeCommand(commandName, tokens));
    }
}
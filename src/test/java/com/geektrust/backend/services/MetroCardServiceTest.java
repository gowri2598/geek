package com.geektrust.backend.services;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.repositories.IMetroCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MetroCardServiceTest {

    private IMetroCardService metroCardService;
    private IMetroCardRepository metroCardRepository;

    @BeforeEach
    void setUp() {
        metroCardRepository = mock(IMetroCardRepository.class);
        metroCardService = new MetroCardService(metroCardRepository);
    }

    @Test
    void addCard_ShouldSaveCardToRepository() {
        // Arrange
        String cardId = "1234567890";
        int balance = 100;
        MetroCard metroCard = new MetroCard(cardId, balance);

        // Act
        metroCardService.addCard(cardId, balance);

        // Assert
        verify(metroCardRepository, times(1)).save(metroCard);
    }

    @Test
    void getCards_ShouldReturnListOfCardsFromRepository() {
        // Arrange
        List<MetroCard> expectedCards = new ArrayList<>();
        expectedCards.add(new MetroCard("1234567890", 100));
        expectedCards.add(new MetroCard("9876543210", 200));
        when(metroCardRepository.findAll()).thenReturn(expectedCards);

        // Act
        List<MetroCard> fetchedCards = metroCardService.getCards();

        // Assert
        assertEquals(expectedCards, fetchedCards);
    }

    @Test
    void transactCard_ShouldUpdateCardBalanceAndReturnRemainingAmount() {
        // Arrange
        String cardId = "1234567890";
        int initialBalance = 200;
        int transactionAmount = 150;
        int expectedRemainingBalance = 50;

        MetroCard metroCard = new MetroCard(cardId, initialBalance);
        when(metroCardRepository.getCard(cardId)).thenReturn(metroCard);

        // Act
        int remainingBalance = metroCardService.transactCard(cardId, transactionAmount);

        // Assert
        assertEquals(expectedRemainingBalance, remainingBalance);
        assertEquals(expectedRemainingBalance, metroCard.getBalance());
        verify(metroCardRepository, times(1)).save(metroCard);
    }
}

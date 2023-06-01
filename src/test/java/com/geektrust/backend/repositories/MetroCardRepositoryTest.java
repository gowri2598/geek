package com.geektrust.backend.repositories;

import com.geektrust.backend.entities.MetroCard;
import com.geektrust.backend.repositories.IMetroCardRepository;
import com.geektrust.backend.repositories.MetroCardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class MetroCardRepositoryTest {

    private IMetroCardRepository metroCardRepository;

    @BeforeEach
    void setUp() {
        metroCardRepository = new MetroCardRepository();
    }

    @Test
    void save_ShouldAddCardToRepository_WhenValidCardEntityProvided() {
        // Arrange
        MetroCard card = new MetroCard("card123", 100);
        
        // Act
        metroCardRepository.save(card);

        // Assert
        MetroCard retrievedCard = metroCardRepository.getCard("card123");
        assertNotNull(retrievedCard);
        assertEquals("card123", retrievedCard.getCardId());
        assertEquals(100, retrievedCard.getBalance());
    }

    @Test
    void findAll_ShouldReturnAllCardsInRepository_WhenCardsExist() {
        // Arrange
        MetroCard card1 = new MetroCard("card1", 50);
        MetroCard card2 = new MetroCard("card2", 75);
        metroCardRepository.save(card1);
        metroCardRepository.save(card2);

        // Act
        List<MetroCard> cards = metroCardRepository.findAll();

        // Assert
        assertEquals(2, cards.size());
        assertTrue(cards.contains(card1));
        assertTrue(cards.contains(card2));
    }

    @Test
    void getCard_ShouldReturnCorrectCard_WhenCardExistsInRepository() {
        // Arrange
        MetroCard card = new MetroCard("card123", 100);
        metroCardRepository.save(card);

        // Act
        MetroCard retrievedCard = metroCardRepository.getCard("card123");

        // Assert
        assertNotNull(retrievedCard);
        assertEquals("card123", retrievedCard.getCardId());
        assertEquals(100, retrievedCard.getBalance());
    }

    @Test
    void getCard_ShouldReturnNull_WhenCardDoesNotExistInRepository() {
        // Act
        MetroCard retrievedCard = metroCardRepository.getCard("nonExistentCard");

        // Assert
        assertNull(retrievedCard);
    }
}

package com.geektrust.backend.repositories;

import com.geektrust.backend.dtos.TotalCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

class RevenueRepositoryTest {

    private IRevenueRepository revenueRepository;

    @BeforeEach
    void setUp() {
        revenueRepository = new RevenueRepository();
    }

    @Test
    void save_ShouldAddOrUpdateTotalCollection_WhenEntityIsSaved() {
        // Arrange
        TotalCollection entity = new TotalCollection("StationA", 100, 10, "Regular", 1);

        // Act
        TotalCollection savedEntity = revenueRepository.save(entity);

        // Assert
        Optional<TotalCollection> fetchedEntity = revenueRepository.findById("StationA_Regular");
        assertTrue(fetchedEntity.isPresent());
        assertEquals(entity, fetchedEntity.get());

        // Update existing entity
        entity.setAmountCollected(200);
        entity.setDiscountGiven(20);
        entity.setCount(2);

        // Save updated entity
        revenueRepository.save(entity);

        // Assert updated entity
        fetchedEntity = revenueRepository.findById("StationA_Regular");
        assertTrue(fetchedEntity.isPresent());
        assertEquals(entity, fetchedEntity.get());
    }

    @Test
    void findById_ShouldReturnOptionalEmpty_WhenEntityWithIdDoesNotExist() {
        // Arrange

        // Act
        Optional<TotalCollection> fetchedEntity = revenueRepository.findById("NonExistingId");

        // Assert
        assertTrue(fetchedEntity.isEmpty());
    }

    @Test
    void findAll_ShouldReturnAllTotalCollections() {
        // Arrange
        TotalCollection entity1 = new TotalCollection("StationA", 100, 10, "Regular", 1);
        TotalCollection entity2 = new TotalCollection("StationB", 200, 20, "Premium", 2);
        revenueRepository.save(entity1);
        revenueRepository.save(entity2);

        // Act
        Optional<List<TotalCollection>> fetchedEntities = revenueRepository.findAll();

        // Assert
        assertTrue(fetchedEntities.isPresent());
        List<TotalCollection> expectedEntities = new ArrayList<>();
        expectedEntities.add(entity1);
        expectedEntities.add(entity2);
        assertEquals(expectedEntities, fetchedEntities.get());
    }
}

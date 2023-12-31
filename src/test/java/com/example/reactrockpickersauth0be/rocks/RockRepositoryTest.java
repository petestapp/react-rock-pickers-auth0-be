package com.example.reactrockpickersauth0be.rocks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = RockRepository.class)
@EnableAutoConfiguration
class RockRepositoryTest {
    @Autowired
    private RockRepository rockRepository;

    @Test
    public void saveTest_ShouldSaveRock() {
        // arrange
        String type = "Obsidian";
        Rock rock = new Rock(type, false);

        // act
        Rock result = rockRepository.save(rock);

        // assert
        assertNotNull(result);
    }

    @Test
    public void saveAllTest_ShouldSaveMultipleRocks() {
        // arrange
        String type1 = "Obsidian";
        Rock rock1 = new Rock(type1, false);

        String type2 = "Basalt";
        Rock rock2 = new Rock(type2, false);

        String type3 = "Quartz";
        Rock rock3 = new Rock(type3, false);

        List<Rock> rocks = new ArrayList<>();
        rocks.add(rock1);
        rocks.add(rock2);
        rocks.add(rock3);

        // act
        Iterable<Rock> result = rockRepository.saveAll(rocks);
        List<Rock> resultAsList = new ArrayList<>();
        result.forEach(resultAsList::add);

        // assert
        assertNotNull(result);
        assertEquals(rock1, resultAsList.get(0));
        assertEquals(rock2, resultAsList.get(1));
        assertEquals(rock3, resultAsList.get(2));
    }

    @Test
    public void getAllTest_ShouldReturnIterableOfRocks() {
        // arrange
        String type1 = "Obsidian";
        Rock rock1 = new Rock(type1, false);
        rockRepository.save(rock1);

        String type2 = "Basalt";
        Rock rock2 = new Rock(type2, false);
        rockRepository.save(rock2);

        String type3 = "Gneiss";
        Rock rock3 = new Rock(type3, true);
        rockRepository.save(rock3);

        String type4 = "Quartz";
        Rock rock4 = new Rock(type4, false);
        rockRepository.save(rock4);

        String type5 = "Marble";
        Rock rock5 = new Rock(type5, true);
        rockRepository.save(rock5);

        // act
        Iterable<Rock> result = rockRepository.findAll();
        List<Rock> resultAsList = new ArrayList<Rock>();
        result.forEach(resultAsList::add);

        // assert
        assertEquals(5, resultAsList.size());

        assertEquals(type1, resultAsList.get(0).type);
        assertEquals(type2, resultAsList.get(1).type);
        assertEquals(type3, resultAsList.get(2).type);
        assertEquals(type4, resultAsList.get(3).type);
        assertEquals(type5, resultAsList.get(4).type);

        assertEquals(false, resultAsList.get(0).secret);
        assertEquals(false, resultAsList.get(1).secret);
        assertEquals(true, resultAsList.get(2).secret);
        assertEquals(false, resultAsList.get(3).secret);
        assertEquals(true, resultAsList.get(4).secret);
    }

    @Test
    public void findBySecretFalse_ShouldReturnAllRocksWhereSecretIsFalse() {
        // arrange
        String type1 = "Obsidian";
        Rock rock1 = new Rock(type1, false);
        rockRepository.save(rock1);

        String type2 = "Basalt";
        Rock rock2 = new Rock(type2, false);
        rockRepository.save(rock2);

        String type3 = "Gneiss";
        Rock rock3 = new Rock(type3, true);
        rockRepository.save(rock3);

        String type4 = "Quartz";
        Rock rock4 = new Rock(type4, false);
        rockRepository.save(rock4);

        String type5 = "Marble";
        Rock rock5 = new Rock(type5, true);
        rockRepository.save(rock5);

        // act
        Iterable<Rock> result = rockRepository.findBySecretFalse();
        List<Rock> resultAsList = new ArrayList<Rock>();
        result.forEach(resultAsList::add);

        // assert
        assertEquals(3, resultAsList.size());
        assertEquals(type1, resultAsList.get(0).type);
        assertEquals(type2, resultAsList.get(1).type);
        assertEquals(type4, resultAsList.get(2).type);
    }
}
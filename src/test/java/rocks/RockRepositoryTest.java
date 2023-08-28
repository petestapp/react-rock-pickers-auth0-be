package rocks;

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
    public void saveRockTest_ShouldSaveRock() {
        // arrange
        String type = "Obsidian";
        Rock rock = new Rock(type);

        // act
        Rock result = rockRepository.save(rock);

        // assert
        assertNotNull(result);
    }

    @Test
    public void getAllRocksTest_ShouldReturnIterableOfRocks() {
        // arrange
        String type1 = "Obsidian";
        Rock rock1 = new Rock(type1);
        rockRepository.save(rock1);

        String type2 = "Basalt";
        Rock rock2 = new Rock(type2);
        rockRepository.save(rock2);

        String type3 = "Quartz";
        Rock rock3 = new Rock(type3);
        rockRepository.save(rock3);

        // act
        Iterable<Rock> result = rockRepository.findAll();
        List<Rock> resultAsList = new ArrayList<Rock>();
        result.forEach(resultAsList::add);
        Object[] resultsAsArray = resultAsList.toArray();

        // assert
        assertEquals(3, resultsAsArray.length);
        assertEquals(type1, ((Rock) resultsAsArray[0]).type);
        assertEquals(type2, ((Rock) resultsAsArray[1]).type);
        assertEquals(type3, ((Rock) resultsAsArray[2]).type);
    }
}
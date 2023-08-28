package rocks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

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

        //
        assertNotNull(result);
    }
}
package rocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RockServiceTest {
    private RockService rockService;
    @Mock
    private RockRepository rockRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rockService = new RockService(rockRepository);
    }

    @Test
    public void rockServiceConstructor_ShouldCreateRockService() {
        // arrange and act
        RockService rockService = new RockService(rockRepository);

        // assert
        assertNotNull(rockService);
    }

    @Test
    public void defaultItems_ShouldCreateListOfRocks() {
        // arrange
        List<Rock> expected = List.of(
                new Rock("Obsidian"),
                new Rock("Basalt"),
                new Rock("Quartz")
        );

        // act
        List<Rock> result = RockService.defaultItems();

        // assert
        assertEquals(expected.get(0).type, result.get(0).type);
        assertEquals(expected.get(1).type, result.get(1).type);
        assertEquals(expected.get(2).type, result.get(2).type);
    }

    @Test
    public void rockServiceConstructor_ShouldAddDefaultRocks() {
        // arrange and act
        RockService rockService = new RockService(rockRepository);

        // assert
        verify(rockRepository).saveAll(eq(defaultItems()));
    }

    @Test
    public void saveRock_ShouldSaveRock() throws Exception {
        // arrange
        String type = "Obsidian";
        Rock rock = new Rock(type);

        when(rockRepository.save(rock)).thenReturn(rock);

        // act
        Rock result = rockService.saveRock(rock);

        // assert
        verify(rockRepository, times(1)).save(rock);
        assertEquals(rock, result);
    }
}
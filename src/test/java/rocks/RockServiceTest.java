package rocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Test
    public void getAllRocks_ShouldGetAllRocks() throws Exception {
        // arrange
        String type1 = "Obsidian";
        String type2 = "Basalt";
        String type3 = "Quartz";

        Iterable<Rock> expectedAsIterable = Arrays.asList(
                new Rock(type1),
                new Rock(type2),
                new Rock(type3)
        );

        List<Rock> expectedAsList = new ArrayList<>();
        expectedAsIterable.forEach(expectedAsList::add);

        when(rockRepository.findAll()).thenReturn(expectedAsList);

        // act
        List<Rock> result = rockService.getAllRocks();

        // assert
        assertEquals(expectedAsList.size(), result.size());
        assertEquals(type1, result.get(0).type);
        assertEquals(type2, result.get(1).type);
        assertEquals(type3, result.get(2).type);
    }
}
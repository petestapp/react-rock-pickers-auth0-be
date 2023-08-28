package rocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
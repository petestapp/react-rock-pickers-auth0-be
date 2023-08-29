package rocks;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes= {RockService.class, RockController.class})
@AutoConfigureMockMvc
@EnableWebMvc
class RockControllerTest {
    private RockController rockController;
    @MockBean
    RockService rockService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rockController = new RockController(rockService);
    }

    @Test
    public void rockControllerConstructor_ShouldCreateRockController() {
        // arrange
        RockController rockController = new RockController(rockService);

        // act and assert
        assertNotNull(rockController);
    }
}
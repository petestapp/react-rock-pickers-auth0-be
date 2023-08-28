package rocks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RockTest {
    @Test
    public void rockNoArgConstructorTest_ShouldCreateRock() {
        // arrange and act
        Rock rock = new Rock();

        // assert
        assertNotNull(rock);
    }
}
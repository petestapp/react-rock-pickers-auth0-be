package com.example.reactrockpickersauth0be.rocks;

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

    @Test
    public void rockConstructorTest_ShouldCreateRock() {
        // arrange
        String type = "Obsidian";
        boolean secret = false;

        // act
        Rock rock = new Rock(type, false);

        // assert
        assertEquals(type, rock.type);
        assertEquals(secret, rock.secret);
    }
}
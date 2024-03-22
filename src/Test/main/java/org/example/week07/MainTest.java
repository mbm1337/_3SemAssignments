package org.example.week07;

import static org.junit.jupiter.api.Assertions.*;

import org.example.week07.TDD.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {
    Main main;

    @BeforeEach
    void setUp() {
        main = new Main();
    }


    @Test
    void reqOne() {
        String expected = "Hello, Tim.";
        String actual = main.greet("Tim");

        assertEquals(expected, actual);

    }

    @Test
    void reqTwo() {
        String expected = "Hello, my friend.";
        String actual = main.greet(null);

        assertEquals(expected, actual);
    }

    @Test
    void reqThree() {
        String expected = "HELLO, TIM!";
        String actual = main.greet("TIM");

        assertEquals(expected, actual);
    }

    @Test
    void reqFour() {
        String expected = "Hello, Tim and Bob.";
        String actual = main.greet(new String[]{"Tim", "Bob"});

        assertEquals(expected, actual);
    }

    @Test
    void reqFive() {
        String expected = "Hello, Amy, Brian and Charlotte.";
        String actual = main.greet(new String[]{"Amy", "Brian", "Charlotte"});

        assertEquals(expected, actual);
    }

    @Test
    void reqSix() {
        String expected = "Hello, Amy and Charlotte. AND HELLO, BRIAN!";
        String actual = main.greet(new String[]{"Amy", "BRIAN", "Charlotte"});

        assertEquals(expected, actual);
    }

    @Test
    void reqSeven() {

        String actual = main.greet(new String[]{"Bob", "Charlie, Dianne"});
        String expected = "Hello, Bob, Charlie and Dianne.";

        assertEquals(expected, actual);
    }

    @Test
    void reqEight() {

        String actual = main.greet(new String[]{"Bob", "\"Charlie, Dianne\""});
        String expected = "Hello, Bob and Charlie, Dianne.";

        assertEquals(expected, actual);
    }

}
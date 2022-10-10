package com.example.ravendecoder;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HelloApplicationTest {

    @Test
    void testing5() throws IOException {
        ArrayList<String> testFive = new ArrayList<>();

        testFive.add("the : 57");
        testFive.add("and : 38");
        testFive.add("i : 32");
        testFive.add("my : 24");
        testFive.add("of : 22");

        assertEquals(testFive, HelloApplication.ravenDecode(5));
    }

    @Test
    void testing10() throws IOException {
        ArrayList<String> testFive = new ArrayList<>();

        testFive.add("the : 57");
        testFive.add("and : 38");
        testFive.add("i : 32");
        testFive.add("my : 24");
        testFive.add("of : 22");
        testFive.add("that : 18");
        testFive.add("this : 16");
        testFive.add("a : 15");
        testFive.add("door : 14");
        testFive.add("raven : 11");

        assertEquals(testFive, HelloApplication.ravenDecode(10));
    }

    @Test
    void testing15() throws IOException {
        ArrayList<String> testFive = new ArrayList<>();

        testFive.add("the : 57");
        testFive.add("and : 38");
        testFive.add("i : 32");
        testFive.add("my : 24");
        testFive.add("of : 22");
        testFive.add("that : 18");
        testFive.add("this : 16");
        testFive.add("a : 15");
        testFive.add("door : 14");
        testFive.add("raven : 11");
        testFive.add("chamber : 11");
        testFive.add("bird : 10");
        testFive.add("is : 10");
        testFive.add("on : 10");
        testFive.add("nevermore : 9");

        assertEquals(testFive, HelloApplication.ravenDecode(15));
    }

    @Test
    void testing20() throws IOException {
        ArrayList<String> testFive = new ArrayList<>();

        testFive.add("the : 57");
        testFive.add("and : 38");
        testFive.add("i : 32");
        testFive.add("my : 24");
        testFive.add("of : 22");
        testFive.add("that : 18");
        testFive.add("this : 16");
        testFive.add("a : 15");
        testFive.add("door : 14");
        testFive.add("raven : 11");
        testFive.add("chamber : 11");
        testFive.add("bird : 10");
        testFive.add("is : 10");
        testFive.add("on : 10");
        testFive.add("nevermore : 9");
        testFive.add("at : 8");
        testFive.add("with : 8");
        testFive.add("or : 8");
        testFive.add("then : 8");
        testFive.add("lenore : 8");

        assertEquals(testFive, HelloApplication.ravenDecode(20));
    }
}
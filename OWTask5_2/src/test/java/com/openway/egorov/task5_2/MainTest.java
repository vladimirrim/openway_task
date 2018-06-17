package com.openway.egorov.task5_2;

import org.hamcrest.number.IsCloseTo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private ByteArrayOutputStream outContent;
    private final String sep = System.getProperty("line.separator");

    @BeforeEach
    void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    void consoleInputLessArguments() {
        Main.main(new String[]{"2.4", "4.6", "-7.8"});
        assertThat(outContent.toString(), is("Invalid number of arguments.Expected: 4 real numbers." + sep));
    }

    @Test
    void consoleInputMoreArguments() {
        Main.main(new String[]{"2.4", "4.6", "-7.8", "9,6", "3.4"});
        assertThat(outContent.toString(), is("Invalid number of arguments.Expected: 4 real numbers." + sep));
    }

    @Test
    void fourIntegers() {
        Main.main(new String[]{"100", "300", "200", "200"});
        assertThat(Double.parseDouble(outContent.toString()), new IsCloseTo(1, 0.000001));
    }

    @Test
    void fourDoubles() {
        Main.main(new String[]{"2.4", "4.6", "-7.8", "5.8"});
        assertThat(Double.parseDouble(outContent.toString()), new IsCloseTo(-3.5, 0.000001));
    }

    @Test
    void zeroDivisionPlusInfinity() {
        Main.main(new String[]{"2.78", "1", "1", "-1"});
        assertThat(outContent.toString(), is("Infinity" + sep));
    }

    @Test
    void zeroDivisionMinusInfinity() {
        Main.main(new String[]{"-2.78", "1", "0", "0"});
        assertThat(outContent.toString(), is("-Infinity" + sep));
    }


    @Test
    void consoleInputFirstIncorrectNumber() {
        Main.main(new String[]{"jojo", "2", "3", "4"});
        assertThat(outContent.toString(), is("Sorry! Looks like error occurred during parsing your numbers.Error:For input string: \"jojo\"" + sep));
    }

    @Test
    void consoleInputSecondIncorrectNumber() {
        Main.main(new String[]{"1", "jojo", "3", "4"});
        assertThat(outContent.toString(), is("Sorry! Looks like error occurred during parsing your numbers.Error:For input string: \"jojo\"" + sep));
    }

    @Test
    void consoleInputThirdIncorrectNumber() {
        Main.main(new String[]{"1", "2", "jojo", "4"});
        assertThat(outContent.toString(), is("Sorry! Looks like error occurred during parsing your numbers.Error:For input string: \"jojo\"" + sep));
    }

    @Test
    void consoleInputFourthIncorrectNumber() {
        Main.main(new String[]{"1", "2", "3", "jojo"});
        assertThat(outContent.toString(), is("Sorry! Looks like error occurred during parsing your numbers.Error:For input string: \"jojo\"" + sep));
    }

    @Test
    void consoleInputTwoDots() {
        Main.main(new String[]{"2.2.2", "2", "3", "4"});
        assertThat(outContent.toString(), is("Sorry! Looks like error occurred during parsing your numbers.Error:multiple points" + sep));
    }

    @Test
    void consoleInputWithSpace() {
        Main.main(new String[]{"2.2", "2", "3", "4 jojo"});
        assertThat(outContent.toString(), is("Sorry! Looks like error occurred during parsing your numbers.Error:For input string: \"4 jojo\"" + sep));
    }
}
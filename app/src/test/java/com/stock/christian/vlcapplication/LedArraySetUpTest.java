package com.stock.christian.vlcapplication;

import com.stock.christian.vlcapplication.calculation.LedArrayException;
import com.stock.christian.vlcapplication.calculation.LedArraySetUp;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Test;

/**
 * Testing on Behaviour of LedArrayTest
 */
public class LedArraySetUpTest {

    LedArraySetUp array;

    @After
    public void tearDown() {
        array = null;
    }

    /**
     * Tests behaviour on valid String on 4x4 matrix
     * @throws LedArrayException if wrong setup is choosen
     */
    @Test
    public void validBitStringFourLines() throws LedArrayException {
        array = new LedArraySetUp("1111000000001111");
        assertThat(array.getUseBits(0, 16, 2, 4), is("11000011"));
    }

    /**
     * Tests behaviour on valid String on 10x12 matrix
     * @throws LedArrayException if wrong setup is choosen
     */
    @Test
    public void validBitStringTwelveLines() throws LedArrayException {
        array = new LedArraySetUp("000000000000" +
                "000100100000" +
                "000100000100" +
                "000100110000" +
                "000100110000" +
                "000100111100" +
                "000000000000" +
                "000000000000" +
                "000000000000" +
                "000000000000");
        assertThat(array.getUseBits(14, 79, 8, 12), is("0100100001000001010011000100110001001111"));
    }
    /**
     * Tests behaviour on valid String on 10x12 matrix, but only first character on 4 lines
     * @throws LedArrayException if wrong setup is choosen
     */
    @Test
    public void validBitStringOnePerLine() throws LedArrayException {
        array = new LedArraySetUp("000000000000" +
                "000100100000" +
                "000100000100" +
                "100100110000" +
                "000100110000" +
                "100100111100" +
                "000000000000" +
                "000000000000" +
                "000000000000" +
                "000000000000");
        assertThat(array.getUseBits(36, 72, 1, 12), is("1010"));
    }
    /**
     * Tests behaviour on valid String with setup all 1
     * @throws LedArrayException if wrong setup is choosen
     */
    @Test
    public void validStringWithAllValues1() throws LedArrayException {
        array = new LedArraySetUp("000000000000" +
                "000100100000" +
                "000100000100");

        assertThat(array.getUseBits(1, 1, 1, 1), is("0"));

    }

    /**
     * Tests behaviour on valid String with not valid Firstbit and checks message on custom Exception
     */
    @Test
    public void validStringWithWrongFirstBit() {
        array = new LedArraySetUp("000000000000" +
                "000100100000" +
                "000100000100");
        try {
            assertThat(array.getUseBits(5000, 72, 1, 12), is("1010"));
        } catch (LedArrayException ex) {
            assertThat(ex.getMessage(), is("At least, FirstBit is not valid"));
        }
    }

    /**
     * Tests behaviour on valid String with not valid Firstbit and checks message on custom Exception
     */
    @Test
    public void validStringWithNegativeFirstBit() throws LedArrayException {
        array = new LedArraySetUp("000000000000" +
                "000100100000" +
                "000100000100");
        try {
            assertThat(array.getUseBits(-12, 72, 1, 12), is("1010"));
        } catch (LedArrayException ex) {
            assertThat(ex.getMessage(), is("At least, FirstBit is not valid"));
        }
    }

    /**
     * Tests behaviour on valid String with not valid LastBit and checks message on custom Exception
     */
    @Test
    public void validStringWithWrongLastBit() throws LedArrayException {
        array = new LedArraySetUp("000000000000" +
                "000100100000" +
                "000100000100");
        try {
            assertThat(array.getUseBits(0, 500, 1, 12), is("1010"));
        } catch (LedArrayException ex) {
            assertThat(ex.getMessage(), is("At least, LastBit is not valid"));
        }
    }

    /**
     * Tests behaviour on valid String with not valid BitsPerLine and checks message on custom Exception
     */
    @Test
    public void validStringWithLongerBitLengthThenBitsPerLine() {
        array = new LedArraySetUp("000000000000" +
                "000100100000" +
                "000100000100");
        try {
            assertThat(array.getUseBits(0, 20, 11, 10), is("1010"));
        } catch (LedArrayException ex) {
            assertThat(ex.getMessage(), is("At least, BitLength is not valid"));
        }
    }

    /**
     * Tests behaviour on valid String with not valid Firstbit and checks message on custom Exception
     */
    @Test
    public void validStringWithInvalidBitsPerLine() {
        array = new LedArraySetUp("000000000000" +
                "000100100000" +
                "000100000100");
        try {
            assertThat(array.getUseBits(0, 20, 0, 0), is("1010"));
        } catch (LedArrayException ex) {
            assertThat(ex.getMessage(), is("At least, BitLength is not valid"));
        }
    }

    //TODO: Lot of different setuos aren't tested




}

package com.stock.christian.vlcapplication;

import com.stock.christian.vlcapplication.calculation.AsciiConverter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Testing on Behaviour for different BitStrings
 */
public class AsciiConverterTest {

    private AsciiConverter converter;

    @Before
    public void setUp(){
        converter = new AsciiConverter();
    }

    @After
    public void tearDown(){
        converter = null;
    }

    /**
     * Test with valid char Best Case
     */
    @Test
    public void testToAsciiWithValidString(){

        assertThat(converter.toAscii("0100100001000001010011000100110001001111"),is("HALLO"));
    }

    /**
     * Test with valid char and special Character
     */
    @Test
    public void testToAsciiWithSpecialChar(){

        assertThat(converter.toAscii("0100100100100111011011010110000101010100011001010111001101110100"),is("I'maTest"));
    }

    /**
     * Test with valid char and special Character ' '
     */
    @Test
    public void testToAsciiWithSPACE(){

        assertThat(converter.toAscii("01001001001001110110110100100000011000010010000001010100011001010111001101110100"),is("I'm a Test"));
    }

    /**
     * Test with not valid Input
     * @throws NumberFormatException if String is malformed and not convertable
     */
    @Test(expected = NumberFormatException.class)
    public void testToAsciiWithNoValidString(){
        assertThat(converter.toAscii("abcdefgh"),is(""));
    }

    /**
     * Test with not valid Input and SPACE
     * @throws NumberFormatException if String is malformed and not convertable
     */
    @Test(expected = NumberFormatException.class)
    public void testToAsciiWithBinaryAndSpecialCharacter(){
        assertThat(converter.toAscii("00110011 11001100"),is(""));
    }

    /**
     * Test with short Binary ( String < 8 )
     */
    @Test()
    public void testToAsciiWithShortBinary(){
        assertThat(converter.toAscii("001100"),is(""));
    }

    /**
     * Test with valid String and short Binary at the Ende
     */
    @Test()
    public void testToAsciiWithValidStringAndShortBinary(){
        assertThat(converter.toAscii("0100100001000001010011000100110011100"),is("HALL"));
    }

    /**
     * Test with Characters Out of Scope \n\tHA\b
     */
    @Test()
    public void testToAsciiWithAsciiControlCharacters(){
        assertThat(converter.toAscii("0000101000001001010010000100000100001000 "),is("HA"));
    }
}

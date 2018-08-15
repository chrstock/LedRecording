package com.stock.christian.vlcapplication;

import android.graphics.Bitmap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.stock.christian.vlcapplication.calculation.CalculationREFACTOR;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit Test for simple cases for different methods
 */
public class CalculationUnitTest {

    private CalculationREFACTOR calculation;

    private List<Bitmap> bitmapList;

    @Before
    public void setUp() {
        bitmapList = new ArrayList<>();
        calculation = new CalculationREFACTOR();
    }


    @After
    public void tearDown() {
        bitmapList = null;
        calculation = null;
    }

    @Test
    public void checkIfEmptyListBringsEmptyString() {
        assertThat(calculation.calculateTextMessage(bitmapList), is(""));
    }
}

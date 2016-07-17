package za.co.entelect.test.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.co.entelect.test.Material;
import za.co.entelect.test.Node;
import za.co.entelect.test.Road;

import static org.mockito.Mockito.mock;

public class TollNodeImplTest {

    private double TOLL_AMOUNT = 500.0;

    private TollNodeImpl sut;

    @Before
    public void setUp() {
        sut = new TollNodeImpl(500.0);
    }

    @Test
    public void testCreation() {
        Assert.assertNotNull(sut);
        Assert.assertEquals(TOLL_AMOUNT, sut.getTollAmount(), 0.1);
    }

}

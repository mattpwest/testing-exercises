package za.co.entelect.test.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import za.co.entelect.test.Node;
import za.co.entelect.test.Road;

import static org.mockito.Mockito.mock;

public class AbstractNodeImplTest {

    private AbstractNodeImpl sut;

    @Before
    public void setUp() {
        sut = new AbstractNodeImpl() {};
    }

    @Test
    public void testCreation() {
        Assert.assertTrue(sut.getNeighbours().isEmpty());
    }

    @Test
    public void testAddNeighbour() {
        Node mockNeighbour = mock(Node.class);
        Road mockRoad = mock(Road.class);

        sut.addNeighbour(mockNeighbour, mockRoad);

        Assert.assertFalse(sut.getNeighbours().isEmpty());
        Assert.assertEquals(mockRoad, sut.getRoadToNeighbour(mockNeighbour));
    }
}

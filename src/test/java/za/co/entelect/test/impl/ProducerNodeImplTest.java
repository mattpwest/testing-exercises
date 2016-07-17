package za.co.entelect.test.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.co.entelect.test.Material;
import za.co.entelect.test.Node;
import za.co.entelect.test.Road;

import static org.mockito.Mockito.*;

public class ProducerNodeImplTest {

    private Material PRODUCTION_MATERIAL = Material.Sand;
    private double PRODUCTION_PER_DAY = 0.5;
    private double MAX_STORAGE = 10.0;

    private ProducerNodeImpl sut;

    @Before
    public void setUp() {
        sut = new ProducerNodeImpl(PRODUCTION_MATERIAL, PRODUCTION_PER_DAY, MAX_STORAGE);
    }

    @Test
    public void testCreation() {
        Assert.assertNotNull(sut);
        Assert.assertEquals(PRODUCTION_MATERIAL, sut.getProducedMaterial());
        Assert.assertEquals(PRODUCTION_PER_DAY, sut.getProductionPerDay(), 0.1);
        Assert.assertEquals(MAX_STORAGE, sut.getMaxStorage(), 0.1);
        Assert.assertEquals(0.0, sut.getStoredAmount(), 0.1);
        Assert.assertTrue(sut.getNeighbours().isEmpty());
    }

    @Test
    public void testProduction() {
        double currentAmount = sut.getStoredAmount();
        sut.produce();
        double newAmount = sut.getStoredAmount();

        Assert.assertEquals(sut.getProductionPerDay(), newAmount - currentAmount, 0.1);
    }

    @Test
    public void testOverProductionImpossible() {
        int timesToProduceMax = (int) (sut.getMaxStorage() / sut.getProductionPerDay()) + 2;
        for (int i = 0; i < timesToProduceMax; i++) {
            sut.produce();
        }

        Assert.assertEquals(sut.getMaxStorage(), sut.getStoredAmount(), 0.1);
    }
}

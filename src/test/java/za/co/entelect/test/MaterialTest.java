package za.co.entelect.test;

import org.junit.Assert;
import org.junit.Test;

public class MaterialTest {

    @Test
    public void hasBricks() {
        Material sut = Material.Bricks;
        Assert.assertNotNull(sut);
    }

    @Test
    public void hasConcrete() {
        Material sut = Material.Concrete;
        Assert.assertNotNull(sut);
    }

    @Test
    public void hasSand() {
        Material sut = Material.Sand;
        Assert.assertNotNull(sut);
    }

    @Test
    public void hasWater() {
        Material sut = Material.Water;
        Assert.assertNotNull(sut);
    }

    @Test
    public void brickDensityIsCorrect() {
        Material sut = Material.Bricks;

        Assert.assertEquals(1.89, sut.getDensity(), 0.01);
    }

    @Test
    public void concreteDensityIsCorrect() {
        Material sut = Material.Concrete;

        Assert.assertEquals(2.4, sut.getDensity(), 0.01);
    }

    @Test
    public void sandDensityIsCorrect() {
        Material sut = Material.Sand;

        Assert.assertEquals(1.6, sut.getDensity(), 0.01);
    }

    @Test
    public void waterDensityIsCorrect() {
        Material sut = Material.Water;

        Assert.assertEquals(1.0, sut.getDensity(), 0.01);
    }
}

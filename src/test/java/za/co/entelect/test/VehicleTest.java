package za.co.entelect.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.co.entelect.test.exceptions.AlreadyLoadedException;
import za.co.entelect.test.exceptions.NotLoadedException;
import za.co.entelect.test.exceptions.OverVolumeException;
import za.co.entelect.test.exceptions.OverWeightException;
import za.co.entelect.test.exceptions.UnsupportedException;
import za.co.entelect.test.exceptions.VehicleException;

public class VehicleTest {

    final String NAME = "LDV";
    final double WEIGHT_LIMIT = 1.5;
    final double VOLUME_LIMIT = 2.0;
    final double MAX_SPEED = 100.0;
    final double MAX_SPEED_LOADED = 60.0;
    final Material[] ALLOWED_MATERIALS = {Material.Bricks, Material.Concrete, Material.Sand};

    Vehicle sut;

    @Before
    public void setUp() {
        sut = new Vehicle(NAME, WEIGHT_LIMIT, VOLUME_LIMIT, MAX_SPEED, MAX_SPEED_LOADED, ALLOWED_MATERIALS);
    }

    @Test
    public void testVehicleCreation() {
        Assert.assertEquals(NAME, sut.getName());
        Assert.assertEquals(WEIGHT_LIMIT, sut.getWeightLimit(), 0.1);
        Assert.assertEquals(VOLUME_LIMIT, sut.getVolumeLimit(), 0.1);
        Assert.assertEquals(MAX_SPEED, sut.getMaxSpeed(), 0.1);
        Assert.assertEquals(MAX_SPEED_LOADED, sut.getMaxSpeedLoaded(), 0.1);
        Assert.assertArrayEquals(ALLOWED_MATERIALS, sut.getTypeLimits().toArray());
    }

    @Test
    public void testLoadVehicle() throws VehicleException {
        sut.load(Material.Sand, 0.5);
    }

    @Test(expected = OverWeightException.class)
    public void testOverloadVehicle() throws VehicleException {
        sut.load(Material.Sand, 10.0);
    }

    @Test(expected = UnsupportedException.class)
    public void testLoadUnsupportedMaterial() throws VehicleException {
        sut.load(Material.Water, 1.0);
    }

    @Test(expected = OverVolumeException.class)
    public void testLoadOverVolumeException() throws VehicleException {
        sut.load(Material.Sand, 1.3);
    }

    @Test(expected = AlreadyLoadedException.class)
    public void testAlreadyLoadedException() throws VehicleException {
        sut.load(Material.Sand, 0.1);
        sut.load(Material.Bricks, 0.1);
    }

    @Test
    public void testUnload() throws VehicleException {
        sut.load(Material.Sand, 0.5);
        sut.unload();

        Assert.assertNull(sut.getCurrentCargo());
        Assert.assertEquals(0.0, sut.getCurrentCargoWeight(), 0.1);
    }

    @Test(expected = NotLoadedException.class)
    public void testUnloadIfEmpty() throws VehicleException {
        sut.unload();
    }

    @Test
    public void testCurrentSpeedEqualsMaxSpeedIfUnloaded() {
        Assert.assertEquals(sut.getMaxSpeed(), sut.getCurrentSpeed(), 0.1);
    }

    @Test
    public void testCurrentSpeedReducedIfLoaded() throws VehicleException {
        sut.load(Material.Sand, 1.0);
        double currentSpeed = sut.getCurrentSpeed();

        Assert.assertTrue(currentSpeed >= sut.getMaxSpeedLoaded());
        Assert.assertTrue(currentSpeed < sut.getMaxSpeed());
    }
}

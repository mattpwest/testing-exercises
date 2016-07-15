package za.co.entelect.test;

import za.co.entelect.test.exceptions.AlreadyLoadedException;
import za.co.entelect.test.exceptions.NotLoadedException;
import za.co.entelect.test.exceptions.OverVolumeException;
import za.co.entelect.test.exceptions.OverWeightException;
import za.co.entelect.test.exceptions.UnsupportedException;
import za.co.entelect.test.exceptions.VehicleException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vehicle {
    private String name = "";
    private double weightLimit = 0.0;
    private double volumeLimit = 0.0;
    private double maxSpeed = 0.0;
    private double maxSpeedLoaded = 0.0;

    private List<Material> typeLimits;
    private Material currentCargo = null;
    private double currentCargoWeight = 0.0;

    public Vehicle(String name, double weightLimit, double volumeLimit, double maxSpeed, double maxSpeedLoaded,
                   Material[] allowedTypes) {
        this.name = name;
        this.weightLimit = weightLimit;
        this.volumeLimit = volumeLimit;
        this.maxSpeed = maxSpeed;
        this.maxSpeedLoaded = maxSpeedLoaded;
        this.typeLimits = new ArrayList<>(Arrays.asList(allowedTypes));
    }

    public String getName() {
        return name;
    }

    public double getWeightLimit() {
        return weightLimit;
    }

    public double getVolumeLimit() {
        return volumeLimit;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getMaxSpeedLoaded() {
        return maxSpeedLoaded;
    }

    public List<Material> getTypeLimits() {
        return new ArrayList<>(typeLimits);
    }

    public Material getCurrentCargo() {
        return currentCargo;
    }

    public double getCurrentCargoWeight() {
        return currentCargoWeight;
    }

    public void load(Material material, double weight) throws VehicleException {
        if (currentCargo != null || currentCargoWeight > 0.0) {
            throw new AlreadyLoadedException();
        }

        if (!typeLimits.contains(material)) {
            throw new UnsupportedException(typeLimits, material);
        }

        if (weight > weightLimit) {
            throw new OverWeightException(weight, weight);
        }

        double actualVolume = weight * material.getDensity();
        if (actualVolume > volumeLimit) {
            throw new OverVolumeException(volumeLimit, actualVolume);
        }

        this.currentCargo = material;
        this.currentCargoWeight = weight;
    }

    public void unload() throws VehicleException {
        if (currentCargo == null || currentCargoWeight == 0.0) {
            throw new NotLoadedException();
        }

        currentCargo = null;
        currentCargoWeight = 0.0;

        // TODO: Put the cargo somewhere instead of offloading into the void...
    }

    public double getCurrentSpeed() {
        double percentageLoadUnused = ((weightLimit - currentCargoWeight) / weightLimit);
        double speedDifference = maxSpeed - maxSpeedLoaded;
        return maxSpeedLoaded + speedDifference * percentageLoadUnused;
    }
}

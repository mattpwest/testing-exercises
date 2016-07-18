package za.co.entelect.test.impl;

import za.co.entelect.test.LabourProvider;

import java.util.Date;
import java.util.Random;

public class LabourProviderImpl implements LabourProvider {

    private Random rng;

    public LabourProviderImpl() {
        rng = new Random(new Date().getTime());
    }

    @Override
    public int provideDayLabourers(int numRequired) {
        return rng.nextInt(numRequired + 1);
    }
}

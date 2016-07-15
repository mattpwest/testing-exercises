package za.co.entelect.test.exceptions;

import za.co.entelect.test.Material;

public class OverVolumeException extends VehicleException {
    public OverVolumeException(double volumeLimit, double actualVolume) {
        super(String.format(
                "Load volume of %.2f is more than the allowed limit of %.2f - loading aborted",
                actualVolume,
                volumeLimit
        ));
    }
}

package za.co.entelect.test.exceptions;

public class OverWeightException extends VehicleException {
    public OverWeightException(double weightLimit, double actualWeight) {
        super(String.format(
                "Load of %.2f is more than the allowed limit of %.2f - loading aborted",
                actualWeight,
                weightLimit
        ));
    }
}

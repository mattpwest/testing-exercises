package za.co.entelect.test.exceptions;

public class NotLoadedException extends VehicleException {
    public NotLoadedException() {
        super("nothing loaded - unloading aborted");
    }
}

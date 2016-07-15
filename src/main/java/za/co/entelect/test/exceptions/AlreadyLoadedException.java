package za.co.entelect.test.exceptions;

public class AlreadyLoadedException extends VehicleException {
    public AlreadyLoadedException() {
        super("already loaded, unload first - loading aborted");
    }
}

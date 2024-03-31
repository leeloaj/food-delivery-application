package fujitsutrialtask.fooddeliveryapp.Exception;

public class DeliveryException extends RuntimeException {
    public enum Reason {
        UNSUITABLE_CONDITIONS_FOR_THIS_VEHICLE_TYPE
    }

    private final String message;
    private final Reason reason;

    public DeliveryException(String message, Reason reason) {
        super(message);
        this.message = message;
        this.reason = reason;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Reason getReason() {
        return reason;
    }
}

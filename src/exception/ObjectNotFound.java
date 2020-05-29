package exception;

public class ObjectNotFound extends RuntimeException {
    String message;

    @Override
    public String getMessage() {
        return message;
    }

    public ObjectNotFound(String message) {
        super(message);
        this.message = message;
    }
}

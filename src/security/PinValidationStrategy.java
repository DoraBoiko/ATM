package security;

public interface PinValidationStrategy {
    boolean validate(String inputPin, String realPin);
}

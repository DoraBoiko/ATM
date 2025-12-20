package security;

public class SimplePinValidation implements PinValidationStrategy {
    @Override
    public boolean validate(String inputPin, String realPin) {
        return inputPin.equals(realPin);
    }
}
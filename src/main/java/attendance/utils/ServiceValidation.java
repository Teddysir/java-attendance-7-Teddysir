package attendance.utils;

import attendance.constants.ErrorMessageType;

public class ServiceValidation {

    public static void validateNull(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessageType.ERROR_NULL.getMessage());
        }
    }

}

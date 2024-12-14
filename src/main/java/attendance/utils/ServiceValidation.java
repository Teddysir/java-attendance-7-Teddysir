package attendance.utils;

import attendance.constants.ErrorMessageType;
import camp.nextstep.edu.missionutils.DateTimes;

import java.util.List;

public class ServiceValidation {

    public static void validateNull(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessageType.ERROR_NULL.getMessage());
        }
    }

    public static void validateExistsUser(List<String> users, String userName) {
        if(!users.contains(userName)) {
            throw new IllegalArgumentException(ErrorMessageType.ERROR_INVALID_USER.getMessage());
        }
    }

    public static void validateCheckAndFix() {
    }

}

package attendance.utils;

import attendance.constants.ErrorMessageType;
import attendance.controller.Controller;
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

    public static void validateCheckAndFix(String input, String day) {
        if(input.equals("1") || input.equals("2")) {
            if(day.equals("토") || day.equals("일") || day.equals("크리스마스")) {
                String output = String.format("[ERROR] %s %s요일은 등교일이 아닙니다.", Controller.formatDate, day);
                throw new IllegalArgumentException(output);
            }
        }
    }

    public static void validateTimeFormat(String attendanceTime) {
        int hour = Integer.parseInt(attendanceTime.substring(0,2));
        int minute = Integer.parseInt(attendanceTime.substring(3,5));
        if (hour < 0 || hour > 24 || minute < 0 || minute >60 ){
            throw new IllegalArgumentException(ErrorMessageType.ERROR_TIME_FORMAT.getMessage());
        }
    }

    public static void validateFutureDay(int num) {
        if(Controller.numDay < num) {
            throw new IllegalArgumentException(ErrorMessageType.ERROR_FUTURE_FIXED.getMessage());
        }
    }

}

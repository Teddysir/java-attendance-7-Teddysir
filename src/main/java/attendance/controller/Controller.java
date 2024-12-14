package attendance.controller;

import attendance.constants.DayType;
import attendance.utils.ServiceValidation;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {

    public static final LocalDateTime date = DateTimes.now();
    public static final String formatDate = date.format(DateTimeFormatter.ofPattern("MM월 dd일"));
    public static final int numDay = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("dd")));

    public void start() {
        clientInput_function();
    }

    private void clientInput_function() {
        String day = getDay();
        OutputView.messageToday(formatDate, day);
    }

    private String getDay() {
        return DayType.checkedDay(numDay % 7);
    }

}

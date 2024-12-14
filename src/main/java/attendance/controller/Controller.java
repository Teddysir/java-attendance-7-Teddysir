package attendance.controller;

import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {

    public static final LocalDateTime date = DateTimes.now();
    private static final String formatDate = date.format(DateTimeFormatter.ofPattern("MM월 dd일"));

    public void start() {
        clientInput_function();
    }

    private void clientInput_function() {
        OutputView.messageToday(formatDate);
    }

}

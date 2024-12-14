package attendance.controller;

import attendance.constants.DayType;
import attendance.model.Student;
import attendance.model.Students;
import attendance.utils.ServiceValidation;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {

    public static final LocalDateTime date = DateTimes.now();
    public static final String formatDate = date.format(DateTimeFormatter.ofPattern("MM월 dd일"));
    public static final int numDay = Integer.parseInt(date.format(DateTimeFormatter.ofPattern("dd")));

    private final Students students = new Students();

    public void start() {
        students.initStudentAttendanceInfo();
        clientInput_Function();
    }

    private void clientInput_Function() {
        String day = getDay(numDay);
        OutputView.messageToday(formatDate, day);
        OutputView.messageFunction();
        String clientInput = Console.readLine().trim();
        System.out.println();
        Service_Function(clientInput);
    }

    private void Service_Function(String clientInput){
        if (clientInput.equals("1")) {
            client_Service_Attendance_Checked();
            clientInput_Function();
        }
        if (clientInput.equals("2"))  {
            client_Service_Attendance_Fixed();
            clientInput_Function();
        }
        if (clientInput.equals("3"))  {
            client_Service_Attendance_Crew_Info();
            clientInput_Function();
        }
        if (clientInput.equals("4"))  {
            client_Service_Attendance_Crew_Danger();
            clientInput_Function();
        }
        if (clientInput.equals("Q"))  {
            client_Service_Attendance_Exit();
        }
    }

    private void client_Service_Attendance_Checked() {
        String nickname = InputView.inputNickName();
        ServiceValidation.validateExistsUser(students.getStudentName(), nickname);
        String attendanceTime = InputView.inputAttendanceTime();

        LocalDateTime nowDate = DateTimes.now();
        String nowFormatDate = nowDate.format(DateTimeFormatter.ofPattern("MM월 dd일"));
        int nowNumDay = Integer.parseInt(nowDate.format(DateTimeFormatter.ofPattern("dd")));
        String nowFormatYMD = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd "));

        String yearAttendanceTime = nowFormatYMD + attendanceTime;

        Student student = new Student(nickname, yearAttendanceTime);

        System.out.printf("\n%s %s요일 %s %s\n\n",nowFormatDate, getDay(nowNumDay), attendanceTime, "출석"); // 출석 입력받기
    }

    private void client_Service_Attendance_Fixed() {

    }

    private void client_Service_Attendance_Crew_Info() {

    }


    private void client_Service_Attendance_Crew_Danger() {

    }

    private void client_Service_Attendance_Exit() {

    }

    private String getDay(int num) {
        return DayType.checkedDay(num % 7);
    }

}

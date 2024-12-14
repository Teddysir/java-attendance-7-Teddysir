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
import java.util.List;

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
        String day = students.getDay(numDay);
        OutputView.messageToday(formatDate, day);
        OutputView.messageFunction();
        String clientInput = Console.readLine().trim();
        System.out.println();
        Service_Function(clientInput);
    }

    private void Service_Function(String clientInput){
        if (clientInput.equals("1")) {

            LocalDateTime nowDate = DateTimes.now();
            String nowFormatDate = nowDate.format(DateTimeFormatter.ofPattern("MM월 dd일"));
            int nowNumDay = Integer.parseInt(nowDate.format(DateTimeFormatter.ofPattern("dd")));
            ServiceValidation.validateCheckAndFix(clientInput, students.getDay(nowNumDay), nowFormatDate);

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
        ServiceValidation.validateTimeFormat(attendanceTime);

        LocalDateTime nowDate = DateTimes.now();
        String nowFormatDate = nowDate.format(DateTimeFormatter.ofPattern("MM월 dd일"));
        int nowNumDay = Integer.parseInt(nowDate.format(DateTimeFormatter.ofPattern("dd")));
        String nowFormatYMD = nowDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        int hour = Integer.parseInt(attendanceTime.substring(0,2));
        int minute = Integer.parseInt(attendanceTime.substring(3, 5));

        String attendanceType = students.getAttendanceTypeByStrDay(hour, minute, students.getDay(nowNumDay));

        String formatAttendanceTime = String.format("%s %s",nowFormatYMD, attendanceTime);
        Student student = new Student(nickname, formatAttendanceTime);
        students.addStudentInfo(student);

        System.out.printf("\n%s %s요일 %s %s\n\n",nowFormatDate, students.getDay(nowNumDay), attendanceTime, attendanceType); // 출석 입력받기
    }

    private void client_Service_Attendance_Fixed() {

    }

    private void client_Service_Attendance_Crew_Info() {
        String nickName = InputView.inputNickName();
        OutputView.messageAttendanceInfo(nickName);
        ServiceValidation.validateExistsUser(students.getStudentName(), nickName);

        List<String> studentAttendanceTime = students.getStudentAttendanceInfo(nickName);


        for (int i = studentAttendanceTime.size()-1; i >= 0; i--) {
            String userMonth = studentAttendanceTime.get(i).substring(5,7); // 12
            String userDay = studentAttendanceTime.get(i).substring(8,10); // 09
            String strDay = students.getDay(Integer.parseInt(userDay)); // 09 -> 9 -> ~요일
            String userTime = studentAttendanceTime.get(i).substring(11, 16); // 09:58
            String attendanceType = students.getUserAttendanceType(studentAttendanceTime.get(i), userDay);
            String output = String.format("%s월 %s일 %s요일 %s %s",userMonth, userDay, strDay, userTime, attendanceType);
            System.out.println(output);
        }
        System.out.println();
    }


    private void client_Service_Attendance_Crew_Danger() {

    }

    private void client_Service_Attendance_Exit() {

    }



}

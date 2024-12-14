package attendance.controller;

import attendance.constants.DayType;
import attendance.model.Student;
import attendance.model.Students;
import attendance.utils.ServiceValidation;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.DateTimes;

import java.io.File;
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

    private void Service_Function(String clientInput) {
        if (clientInput.equals("1")) {

            LocalDateTime nowDate = DateTimes.now();
            String nowFormatDate = nowDate.format(DateTimeFormatter.ofPattern("MM월 dd일"));
            int nowNumDay = Integer.parseInt(nowDate.format(DateTimeFormatter.ofPattern("dd")));
            ServiceValidation.validateCheckAndFix(clientInput, students.getDay(nowNumDay), nowFormatDate);

            client_Service_Attendance_Checked();
            clientInput_Function();
        }
        if (clientInput.equals("2")) {
            client_Service_Attendance_Fixed();
            clientInput_Function();
        }
        if (clientInput.equals("3")) {
            client_Service_Attendance_Crew_Info();
            clientInput_Function();
        }
        if (clientInput.equals("4")) {
            client_Service_Attendance_Crew_Danger();
            clientInput_Function();
        }
        if (clientInput.equals("Q")) {
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

        int hour = Integer.parseInt(attendanceTime.substring(0, 2));
        int minute = Integer.parseInt(attendanceTime.substring(3, 5));

        String attendanceType = students.getAttendanceTypeByStrDay(hour, minute, students.getDay(nowNumDay));

        String formatAttendanceTime = String.format("%s %s", nowFormatYMD, attendanceTime);
        Student student = new Student(nickname, formatAttendanceTime);
        students.addStudentInfo(student);

        System.out.printf("\n%s %s요일 %s %s\n\n", nowFormatDate, students.getDay(nowNumDay), attendanceTime, attendanceType); // 출석 입력받기
    }

    private void client_Service_Attendance_Fixed() {
        String nickName = InputView.inputFixedUserName();
        int fixedDate = Integer.parseInt(InputView.inputFixedDate());
        String fixedDateTime = InputView.inputAttendanceTime();

        Student student = students.getStudent(nickName, fixedDate); // 학생 객체 찾기

        String originMonth = student.getAttendanceTime().substring(5, 7);
        String originDay = student.getAttendanceTime().substring(8, 10);
        int originNumDay = Integer.parseInt(originDay);
        String strDay = students.getDay(originNumDay);
        String strTime = student.getAttendanceTime().substring(11, 16);
        int hour = Integer.parseInt(strTime.substring(0, 2));
        int minute = Integer.parseInt(strTime.substring(3, 5));
        String attendanceType = students.getAttendanceTypeByStrDay(hour, minute, strDay);

        student.setAttendanceTime(fixedDateTime);

        int fixedHour = Integer.parseInt(fixedDateTime.substring(0, 2));
        int fixedMinute = Integer.parseInt(fixedDateTime.substring(3, 5));
        String fixedAttendanceType = students.getAttendanceTypeByStrDay(fixedHour, fixedMinute, strDay);

        String output = String.format("\n%s월 %s일 %s요일 %s %s -> %s %s 수정 완료!\n", originMonth, originDay, strDay, strTime, attendanceType, fixedDateTime, fixedAttendanceType);
        System.out.println(output);


    }

    private void client_Service_Attendance_Crew_Info() {
        String nickName = InputView.inputNickName();
        OutputView.messageAttendanceInfo(nickName);
        ServiceValidation.validateExistsUser(students.getStudentName(), nickName);

        List<String> studentAttendanceTime = students.getStudentAttendanceInfo(nickName);


        for (int i = studentAttendanceTime.size() - 1; i >= 0; i--) {
            String userMonth = studentAttendanceTime.get(i).substring(5, 7); // 12
            String userDay = studentAttendanceTime.get(i).substring(8, 10); // 09
            String strDay = students.getDay(Integer.parseInt(userDay)); // 09 -> 9 -> ~요일
            String userTime = studentAttendanceTime.get(i).substring(11, 16); // 09:58
            String attendanceType = students.getUserAttendanceType(studentAttendanceTime.get(i), userDay);

            if (attendanceType.equals("지각")) {
                Student student = students.getStudentByAttendanceTime(studentAttendanceTime.get(i));
                student.setLateCount();
            }

            if (attendanceType.equals("결석")) {
                Student student = students.getStudentByAttendanceTime(studentAttendanceTime.get(i));
                student.setAbsenceCount();
            }

            String output = String.format("%s월 %s일 %s요일 %s %s", userMonth, userDay, strDay, userTime, attendanceType);
            System.out.println(output);
        }
        System.out.println();
    }


    private void client_Service_Attendance_Crew_Danger() {
        OutputView.messageDangerStudents();

        List<Student> dangerStudent = students.getDangerStudent();

        for (int i = 0; i < dangerStudent.size(); i++) {
            int point = 0;
            String type = "";
            point += dangerStudent.get(i).getLateCount();
            point += (dangerStudent.get(i).getAbsenceCount() * 3);
            if (point >= 6) {
                type = "(경고)";
            }
            if (point >= 9) {
                type = "(면담)";
            }
            if (point > 15) {
                type = "(제적)";
            }
            OutputView.messageDangerStudent(dangerStudent.get(i).getName(), dangerStudent.get(i).getAbsenceCount(), dangerStudent.get(i).getLateCount(), type);
        }

    }

    private void client_Service_Attendance_Exit() {

    }


}

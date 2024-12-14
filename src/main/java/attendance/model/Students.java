package attendance.model;

import attendance.constants.DayType;
import com.sun.source.tree.IfTree;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Students {

    List<Student> students = new ArrayList<>();

    public void initStudentAttendanceInfo() {
        students.add(new Student("쿠기", "2024-12-13 10:08"));
        students.add(new Student("빙봉", "2024-12-13 10:07"));
        students.add(new Student("빙티", "2024-12-13 10:07"));
        students.add(new Student("이든", "2024-12-13 10:07"));

        students.add(new Student("빙봉", "2024-12-12 11:11"));
        students.add(new Student("이든", "2024-12-12 10:06"));
        students.add(new Student("짱수", "2024-12-12 10:00"));

        students.add(new Student("빙봉", "2024-12-11 10:02"));
        students.add(new Student("쿠키", "2024-12-11 10:02"));

        students.add(new Student("빙티", "2024-12-10 10:08"));
        students.add(new Student("빙봉", "2024-12-10 10:06"));
        students.add(new Student("이든", "2024-12-10 10:02"));
        students.add(new Student("쿠키", "2024-12-10 10:01"));
        students.add(new Student("짱수", "2024-12-10 10:00"));

        students.add(new Student("쿠키", "2024-12-09 13:03"));
        students.add(new Student("빙봉", "2024-12-09 13:02"));
        students.add(new Student("이든", "2024-12-09 13:01"));
        students.add(new Student("짱수", "2024-12-09 13:00"));

        students.add(new Student("빙봉", "2024-12-06 10:08"));
        students.add(new Student("이든", "2024-12-06 10:07"));
        students.add(new Student("빙티", "2024-12-06 10:01"));
        students.add(new Student("짱수", "2024-12-06 10:00"));

        students.add(new Student("쿠키", "2024-12-05 10:07"));
        students.add(new Student("빙봉", "2024-12-05 10:06"));
        students.add(new Student("빙티", "2024-12-05 10:06"));
        students.add(new Student("짱수", "2024-12-05 10:00"));

        students.add(new Student("이든", "2024-12-04 10:08"));
        students.add(new Student("빙봉", "2024-12-04 10:07"));
        students.add(new Student("빙티", "2024-12-04 10:02"));
        students.add(new Student("쿠키", "2024-12-04 10:02"));
        students.add(new Student("짱수", "2024-12-04 10:00"));

        students.add(new Student("빙티", "2024-12-03 10:07"));
        students.add(new Student("이든", "2024-12-03 10:06"));
        students.add(new Student("쿠키", "2024-12-03 10:06"));
        students.add(new Student("빙봉", "2024-12-03 10:03"));
        students.add(new Student("짱수", "2024-12-03 10:00"));

        students.add(new Student("빙봉", "2024-12-02 13:06"));
        students.add(new Student("이든", "2024-12-02 13:02"));
        students.add(new Student("쿠키", "2024-12-02 13:01"));
        students.add(new Student("빙티", "2024-12-02 13:00"));
        students.add(new Student("짱수", "2024-12-02 13:00"));
    }


    public List<String> getStudentName() {
        return students
                .stream()
                .map(Student::getName)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<String> getStudentAttendanceInfo(String nickName) {
        return students
                .stream()
                .filter(student -> student.getName().equals(nickName))
                .map(Student::getAttendanceTime)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void addStudentInfo(Student student) {
        students.add(student);
    }


    public String getAttendanceType(String attendanceTime, int nowNumDay) {
        int hour = Integer.parseInt(attendanceTime.substring(11, 13));
        int minute = Integer.parseInt(attendanceTime.substring(14, 16));
        String strDay = getDay(nowNumDay);

        return getAttendanceTypeByStrDay(hour, minute, strDay);

    }

    public String getUserAttendanceType(String attendanceTime, String strDay) {
        int numDay = Integer.parseInt(strDay);
        return getAttendanceType(attendanceTime, numDay);
    }

    public String getAttendanceTypeByStrDay(int hour, int minute, String strDay) {
        if (strDay.equals("월")) {
            return study_13(hour, minute);
        }
        if (strDay.equals("화") || strDay.equals("수") || strDay.equals("목") || strDay.equals("금")) {
            return study_10(hour, minute);
        }
        if (strDay.equals("토") || strDay.equals("일")) {
            return "(결석)";
        }
        return null;
    }

    private String study_13(int hour, int minute) {
        if (hour >= 8 && hour < 13) { // 8시 이상, 13시 전
            return "(출석)";
        }
        if (hour == 13 && minute <= 5) {
            return "(출석)";
        }
        if (hour >= 13 && minute > 5 && minute <= 30) {
            return "(지각)";
        }
        if (hour >= 13 && minute > 30) {
            return "(결석)";
        }
        return null;
    }

    private String study_10(int hour, int minute) {
        if (hour >= 8 && hour < 10) { // 8시 이상, 13시 전
            return "(출석)";
        }
        if (hour == 10 && minute <= 5) {
            return "(출석)";
        }
        if (hour >= 10 && minute > 5 && minute <= 30) {
            return "(지각)";
        }
        if (hour >= 10 && minute > 30) {
            return "(결석)";
        }
        return null;
    }


//    public void updateAttendanceType(Student student) {
//        String userDay = student.getAttendanceTime().substring(8,10); // 09
//        String strDay = getDay(Integer.parseInt(userDay)); // 09 -> 9 -> ~요일
//    }

    public String getDay(int num) {
        return DayType.checkedDay(num % 7);
    }


}

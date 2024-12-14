package attendance.model;

import attendance.constants.DayType;

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

    public Student getStudent(String name, int attendanceDay) {
        for(int i = 0 ; i< students.size(); i++ ) {
            if (students.get(i).getName().equals(name)) {
                int attendanceTimeDay = Integer.parseInt(students.get(i).getAttendanceTime().substring(8,10));
                if(attendanceTimeDay == attendanceDay) {
                    return students.get(i);
                }
            }
        }
        return null;
    }

    public Student getStudent(String name) {
        for(int i = 0; i< students.size(); i++ ) {
            if (students.get(i).getName().equals(name)) {
                return students.get(i);
            }
        }
        return null;
    }

    public Student getStudentByAttendanceTime(String attendanceTime) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getAttendanceTime().equals(attendanceTime)) {
                return students.get(i);
            }
        }
        return null;
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


    public String getDay(int num) {
        return DayType.checkedDay(num % 7);
    }

//    public List<Student> getDangerStudent() {
//        List<Student> dangerStudent = new ArrayList<>();
//        for(int i = 0 ; i< students.size(); i++ ){
//            if(students.get(i).getLateCount() > 0 || students.get(i).getAbsenceCount() > 0) {
//                dangerStudent.add(students.get(i));
//            }
//        }
//        return dangerStudent;
//    }



}

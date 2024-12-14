package attendance.model;


public class Student {

    private String name;
    private String attendanceTime;

    public Student(String name, String attendanceTime) {
        this.name = name;
        this.attendanceTime = attendanceTime;
    }

    public String getName() {
        return name;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        String newAttendanceTime = this.attendanceTime.substring(0,11);
        newAttendanceTime += attendanceTime;
        this.attendanceTime = newAttendanceTime;
    }
}

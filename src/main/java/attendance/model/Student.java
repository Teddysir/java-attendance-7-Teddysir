package attendance.model;


public class Student {

    private String name;
    private String attendanceTime;
    private int lateCount = 0;
    private int absenceCount = 0;

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
        String newAttendanceTime = this.attendanceTime.substring(0, 11);
        newAttendanceTime += attendanceTime;
        this.attendanceTime = newAttendanceTime;
    }

    public void setAbsenceCount() {
        this.absenceCount += 1;
    }

    public void setLateCount() {
        this.lateCount += 1;
    }

    public int getAbsenceCount() {
        return absenceCount;
    }

    public int getLateCount() {
        return lateCount;
    }
}

package attendance.model;

import java.time.LocalDate;

public class Student {

    private String name;
    private LocalDate attendanceTime;

    public Student(String name, LocalDate attendanceTime) {
        this.name = name;
        this.attendanceTime = attendanceTime;
    }

    public String getName() {
        return name;
    }

    public LocalDate getAttendanceTime() {
        return attendanceTime;
    }
}

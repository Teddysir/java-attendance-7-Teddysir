package attendance.constants;

public enum DayType {

    MONDAY("월",2),
    TUESDAY("화",3),
    WEDNESDAY("수",4),
    THURSDAY("목",5),
    FRIDAY("금",6),
    SATURDAY("토",0),
    SUNDAY("일",1);

    private final String strDay;
    private final int numDay;

    DayType(String strDay, int numDay) {
        this.strDay = strDay;
        this.numDay = numDay;
    }

    public static String checkedDay(int day) {
        DayType[] dayTypes = DayType.values();
        for (DayType dayType : dayTypes) {
            if (dayType.numDay == day) {
                return dayType.strDay;
            }
        }

        return null;
    }

    public String getStrDay() {
        return strDay;
    }

    public int getNumDay() {
        return numDay;
    }
}

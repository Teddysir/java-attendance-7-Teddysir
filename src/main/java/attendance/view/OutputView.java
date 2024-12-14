package attendance.view;

public class OutputView {

    public static void messageToday(String input, String day) {
        System.out.printf("오늘은 %s %s요일입니다. 기능을 선택해 주세요.\n", input, day);
    }

    public static void messageFunction() {
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
    }

    public static void messageAttendanceInfo(String nickName) {
        System.out.printf("\n이번 달 %s의 출석 기록입니다.\n\n", nickName);
    }

    public static void messageDangerStudents() {
        System.out.println("제적 위험자 조회 결과");
    }

    public static void messageDangerStudent(String nickName, int absenceCount, int lateCount, String type) {
        System.out.printf("- %s: 결석 %d회, 지각 %d회 %s", nickName, absenceCount, lateCount ,type);
    }


}

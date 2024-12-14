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

}

package attendance.constants;

public enum ErrorMessageType {

    ERROR_NULL("[ERROR] 빈 값은 입력할 수 없습니다. 다시 입력 해주세요.");

    private final String message;

    ErrorMessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
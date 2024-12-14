package attendance.constants;

public enum ErrorMessageType {

    ERROR_NULL("[ERROR] 잘못된 형식을 입력하였습니다.");

    private final String message;

    ErrorMessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
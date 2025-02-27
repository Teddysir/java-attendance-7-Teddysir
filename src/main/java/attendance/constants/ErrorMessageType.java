package attendance.constants;

public enum ErrorMessageType {

    ERROR_NULL("[ERROR] 잘못된 형식을 입력하였습니다."),
    ERROR_INVALID_USER("[ERROR] 등록되지 않은 닉네임입니다."),
    ERROR_FUTURE_FIXED("[ERROR] 아직 수정할 수 없습니다."),
    ERROR_TIME_FORMAT("[ERROR] 잘못된 형식을 입력하였습니다."),
    ERROR_OPERATION_TIME("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");

    private final String message;

    ErrorMessageType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
package Coordinate.coordikittyBE.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {
    SECURITY_EXCEPTION("SECURITY-001", "로그인이 필요합니다.", 401),

    MEMBER_NOT_FOUND("AUTH-001", "해당 유저를 찾을 수 없습니다.", 401),
    INVALID_TOKEN("AUTH-002", "유효하지 않은 토큰입니다.", 401),
    TOKEN_PAYLOAD_EXTRACTION_FAILURE("AUTH-003", "토큰 페이로드 추출에 실패했습니다", 401),
    TOKEN_NOT_FOUND("AUTH-004", "토큰을 찾을 수 없습니다.", 401),

    MISSING_REQUIRED_VALUE_ERROR("COMMON-001", "필수 요청값이 누락되었습니다.", 400),
    NOT_ALLOWED_PERMISSION_ERROR("COMMON-002", "허용되지 않은 권한입니다.", 403),
    INVALID_REQUEST_ERROR("COMMON-003", "올바르지 않은 데이터 요청입니다.", 400),
    INPUT_FORMAT_ERROR("COMMON-004", "올바르지 않은 입력 방식입니다", 400),
    INVALID_PARAMS("COMMON-005", "잘못된 Param값 입니다", 400),

    EMAIL_FORMAT_ERROR("USER-002", "올바르지 않은 이메일 입력 양식입니다.", 400),
    DUPLICATED_EMAIL_ERROR("USER-003", "중복된 이메일입니다.", 400),

    UNEXPECTED_SERVER_ERROR("SERVER-001", "서버 관리자에게 문의하세요.", 500);

    private final String errorCode;
    private final String message;
    private final int statusCode;

}

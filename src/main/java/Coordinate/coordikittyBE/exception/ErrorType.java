package Coordinate.coordikittyBE.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorType {
    SECURITY_EXCEPTION("SECURITY-001", "로그인이 필요합니다.", 404),

    MEMBER_NOT_FOUND("AUTH-001", "해당 유저를 찾을 수 없습니다.", 401),
    INVALID_TOKEN("AUTH-002", "유효하지 않은 토큰입니다.", 401),
    TOKEN_NOT_FOUND("AUTH-004", "토큰을 찾을 수 없습니다.", 401),
    TOKEN_EXPIRED("AUTH-005", "리프레시토큰이 이미 존재합니다.", 401),
    INVALID_USER_ID("AUTH-006", "잘못된 유저 ID 입니다.", 401),

    MISSING_REQUIRED_VALUE_ERROR("COMMON-001", "필수 요청값이 누락되었습니다.", 400),
    INVALID_REQUEST_ERROR("COMMON-003", "올바르지 않은 데이터 요청입니다.", 400),
    INPUT_FORMAT_ERROR("COMMON-004", "올바르지 않은 입력 방식입니다", 400),
    INVALID_PARAMS("COMMON-005", "잘못된 Param값 입니다", 400),

    EMAIL_NOT_FOUND("USER-001", "이메일을 찾을 수 없습니다", 400),
    EMAIL_FORMAT_ERROR("USER-002", "올바르지 않은 이메일 입력 양식입니다.", 400),
    DUPLICATED_EMAIL_ERROR("USER-003", "중복된 이메일입니다.", 400),

    UNEXPECTED_SERVER_ERROR("SERVER-001", "서버 관리자에게 문의하세요.", 500),

    FIREBASE_ERROR("CLOSET-001", "파이어베이스 통신 실패", 502),
    TRANSFORT_MULTIFILE_ERROR("CLOSET-002", "API통신 준비 실패", 504),
    CLOTH_NOT_FOUND("CLOSET-003", "해당 옷을 찾을 수 없습니다.", 404),

    ML_DL_SERVER_ERROR("API-001", "ML/DL서버와 통신 실패", 503),

    POST_NOT_FOUND("POST-001", "해당 게시글을 찾을 수 없습니다.", 404),
    POST_IMAGE_NOT_FOUND("POST-002", "해당 게시글 사진을 찾을 수 없습니다.", 404),
    HISTORY_NOT_FOUND("HISTORY-001", "해당 히스토리를 찾을 수 없습니다.", 404), ;

    private final String errorCode;
    private final String message;
    private final int statusCode;

}

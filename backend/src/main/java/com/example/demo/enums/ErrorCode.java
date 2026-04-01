package com.example.demo.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_ERROR(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    // images
    UPLOAD_IMAGE_FAILED(1001, "Lỗi khi tải hình ảnh lên hệ thống", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_FILE_TYPE(1002, "Định dạng file không hợp lệ (chỉ chấp nhận jpg, png)", HttpStatus.BAD_REQUEST),
    FILE_TOO_LARGE(1003, "Kích thước file quá lớn (tối đa 10MB)", HttpStatus.PAYLOAD_TOO_LARGE),
    EMPTY_FILE(1004, "Vui lòng chọn ít nhất một hình ảnh", HttpStatus.BAD_REQUEST),
    ;

    private int code;
    private String message;
    private HttpStatus httpStatus;

    ErrorCode(int code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}

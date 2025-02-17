package com.SpringSecurity.MinimumToWork.config.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ErrorMessage implements Serializable {
    private String message;
    private String backedMessage;
    private int httpCode;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getBackedMessage() {
        return backedMessage;
    }

    public void setBackedMessage(String backedMessage) {
        this.backedMessage = backedMessage;
    }
}

package com.workintech.zoo.exceptions;

import java.util.Objects;

public class ZooErrorResponse {
    String message;
    int status;
    long timestamp;

    public ZooErrorResponse() {}

    public ZooErrorResponse(int status, String message, long timestamp) {
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ZooErrorResponse that = (ZooErrorResponse) o;
        return status == that.status && timestamp == that.timestamp && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, status, timestamp);
    }


    @Override
    public String toString() {
        return "ZooErrorResponse{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", timestamp=" + timestamp +
                '}';
    }
}

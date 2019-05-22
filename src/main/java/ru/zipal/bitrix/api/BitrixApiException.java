package ru.zipal.bitrix.api;

public class BitrixApiException extends Exception {

    public BitrixApiException(String message) {
        super(message);
    }

    public BitrixApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public BitrixApiException(Throwable cause) {
        super(cause);
    }
}

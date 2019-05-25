package ru.zipal.bitrix.api;

public class UnauthorizedBitrixApiException extends BitrixApiException {

    public UnauthorizedBitrixApiException(String message) {
        super(message);
    }
}

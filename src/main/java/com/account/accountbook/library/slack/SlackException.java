package com.account.accountbook.library.slack;

public class SlackException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SlackException(Throwable cause) {
        super(cause);
    }
}
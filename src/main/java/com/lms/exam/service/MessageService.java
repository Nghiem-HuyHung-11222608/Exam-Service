package com.lms.exam.service;

import org.springframework.context.MessageSourceResolvable;

public interface MessageService {
    String getMessage(MessageSourceResolvable key);

    String getMessage(String key);

    String getMessage(String key, Object... args);
}

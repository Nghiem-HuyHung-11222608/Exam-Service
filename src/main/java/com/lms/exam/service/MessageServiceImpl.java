package com.lms.exam.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageSource messageSource;

    @Override
    public String getMessage(MessageSourceResolvable key) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, locale);
    }

    @Override
    public String getMessage(String key) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, null, locale);
    }

    @Override
    public String getMessage(String key, Object[] args) {
        Locale locale = LocaleContextHolder.getLocale();

        return messageSource.getMessage(key, args, locale);
    }
}

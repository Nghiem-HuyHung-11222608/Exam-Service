package com.lms.exam.exception;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        options = options.including(
                ErrorAttributeOptions.Include.MESSAGE,
                ErrorAttributeOptions.Include.STACK_TRACE
        );
        
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        Throwable error = getError(webRequest);
        if (error instanceof ValidationException) {
            errorAttributes.put("message", error.getMessage());
        }

        return errorAttributes;
    }
}

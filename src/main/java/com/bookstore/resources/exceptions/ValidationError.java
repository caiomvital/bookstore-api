package com.bookstore.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private final List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(){
        super();
    }
    public ValidationError(Long timeStamp, Integer status, String error) {
        super(timeStamp, status, error);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addErrors(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }
}

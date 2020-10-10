package com.example.finalSpringProject.model.exeptions;

public class EntityNotFoundRuntimeException extends RuntimeException {

    public EntityNotFoundRuntimeException() {
    }

    public EntityNotFoundRuntimeException(String message) {
        super(message);
    }

    public EntityNotFoundRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }
}


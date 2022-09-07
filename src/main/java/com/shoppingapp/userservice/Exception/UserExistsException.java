package com.shoppingapp.userservice.Exception;

@SuppressWarnings("serial")
public class UserExistsException extends RuntimeException {
    /**
     * @param message
     */
    public UserExistsException(String message) {
        super(message);
    }


}

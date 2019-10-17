package it.si2001.springMVC.util;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(Object resourceId) {
        super(resourceId != null ? resourceId.toString() : null);
    }

    public ResourceNotFoundException() { }
}

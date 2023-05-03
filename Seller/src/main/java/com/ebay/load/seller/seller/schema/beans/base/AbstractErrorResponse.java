package com.ebay.load.seller.seller.schema.beans.base;

/**
 * @author Mohammed Sohail
 * @since  05/21/2018
 *
 */



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AbstractErrorResponse implements Serializable {

    protected boolean errors;
    protected List<Message> messages = new ArrayList<>();

    public AbstractErrorResponse() {

    }

    public AbstractErrorResponse(boolean errors, List<Message> messages) {
        this.errors = errors;
        this.messages = messages;
    }

    public boolean isErrors() {
        return errors;
    }

    public void setErrors(boolean errors) {
        this.errors = errors;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

}

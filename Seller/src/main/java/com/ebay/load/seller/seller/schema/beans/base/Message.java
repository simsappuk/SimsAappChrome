package com.ebay.load.seller.seller.schema.beans.base;


/**
 * @author Mohammed Sohail
 * @since  05/21/2018
 *
 */


import java.io.Serializable;


public class Message implements Serializable {

    private MessageType messageType;
    private String messageKey;
    private String messageText;

    public Message() {
    }

    public Message(MessageType messageType, String messageKey, String messageText) {
        this.messageType = messageType;
        this.messageKey = messageKey;
        this.messageText = messageText;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Message withMessageType(MessageType messageType) {
        setMessageType(messageType);
        return this;
    }

    public Message withMessageKey(String messageKey) {
        setMessageKey(messageKey);
        return this;
    }

    public Message withMessageText(String messageText) {
        setMessageText(messageText);
        return this;
    }

    @Override
    public String toString() {
        return "Message{" + "messageType=" + messageType + ", messageKey=" + messageKey + ", messageText=" + messageText + '}';
    }

}

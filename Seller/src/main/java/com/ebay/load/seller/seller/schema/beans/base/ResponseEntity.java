package com.ebay.load.seller.seller.schema.beans.base;

/**
 * @author Mohammed Sohail
 * @since  05/21/2018
 *
 */



import java.util.List;

public class ResponseEntity<T> extends AbstractErrorResponse {

    private String requestId;
    private T results;
    private Long totalElements;
    private Integer totalPages;

    public ResponseEntity() {
    }

    public ResponseEntity(T results) {
        this.results = results;
    }

    public ResponseEntity(T results, boolean errors, List<Message> messages) {
        super(errors, messages);
        this.results = results;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

    public ResponseEntity withResults(T results) {
        this.results = results;
        return this;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public ResponseEntity withTotalElements(Long totalElements) {
        this.setTotalElements(totalElements);
        return this;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public ResponseEntity withTotalPages(Integer totalPages) {
        this.setTotalPages(totalPages);
        return this;
    }

    public ResponseEntity withErrors(boolean errors) {
        this.setErrors(errors);
        return this;
    }

    public ResponseEntity withMessages(List<Message> messages) {
        this.setMessages(messages);
        return this;
    }

    public ResponseEntity withMessages(Message message) {
        this.getMessages().add(message);
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public ResponseEntity withRequestId(String requestId) {
        setRequestId(requestId);
        return this;
    }

}

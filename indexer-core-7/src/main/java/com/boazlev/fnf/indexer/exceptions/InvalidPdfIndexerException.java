package com.boazlev.fnf.indexer.exceptions;

public class InvalidPdfIndexerException extends RuntimeException {

	private static final long serialVersionUID = -8173924250118695773L;

	public InvalidPdfIndexerException() {
        super();
    }

    public InvalidPdfIndexerException(String message) {
        super(message);
    }

    public InvalidPdfIndexerException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPdfIndexerException(Throwable cause) {
        super(cause);
    }

}

package com.boazlev.fnf.indexer.exceptions;

public class IndexerException extends RuntimeException {

	private static final long serialVersionUID = -8173924250118695773L;

	public IndexerException() {
        super();
    }

    public IndexerException(String message) {
        super(message);
    }

    public IndexerException(String message, Throwable cause) {
        super(message, cause);
    }

    public IndexerException(Throwable cause) {
        super(cause);
    }

}

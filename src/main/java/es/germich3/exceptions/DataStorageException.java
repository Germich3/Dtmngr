package es.germich3.exceptions;

public class DataStorageException extends Exception {
	
	private static final long serialVersionUID = 2221199887348964161L;

	public DataStorageException(String message) {
        super(message);
    }

    public DataStorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataStorageException(Throwable cause) {
        super(cause);
    }

}

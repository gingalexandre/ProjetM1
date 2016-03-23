package exception;

public class TooMuchPlayerException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public TooMuchPlayerException(String message){
		super(message);
	}
	
	public TooMuchPlayerException(String message, Throwable throwable) {
        super(message, throwable);
    }
}

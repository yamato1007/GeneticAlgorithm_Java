package geneticalgorithm.exception;

public class ImplementException extends RuntimeException {
	public enum ErrorCode{
		RETURNVALUEERROR("戻り値の実装が適切でない"),
		OTHERERROR("なんらかの実装が適切でない"),
		;
		
		private final String text;
		private ErrorCode(String text){
			this.text = text;
		}
		public String getText(){
			return this.text;
		}
	}
	
	private static final long serialVersionUID = 1L;
	private final ErrorCode errorCode;
	
	public ImplementException(ErrorCode errorCode,String message){
		super(message);
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode(){
		return this.errorCode;
	}
}

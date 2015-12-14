package util;

public class UnsignedIntegerFormatException extends IllegalArgumentException {
	private static final long serialVersionUID = 1L;
	
	public UnsignedIntegerFormatException(){
		super();
	}
	public UnsignedIntegerFormatException(String message){
		super(message);
	}

}

package util;

import java.text.MessageFormat;

//unsignd int のラッパークラス
public class UnsignedInteger extends Number{
	private static final long serialVersionUID = 1L;
	private final int num;

	//スタティックなファクトリメソッド
	public static UnsignedInteger valueOf(int num){
		return new UnsignedInteger(num);
	}
	
	public static UnsignedInteger valueOf(String num_str){
		return new UnsignedInteger(num_str);
	}
	
	
	//コンストラクタ
	public UnsignedInteger(int num) {
		super();
		if(num < 0){
			throw new UnsignedIntegerFormatException(
					MessageFormat.format("UnsignedInteger型の値は正の数でなければならない\nnum = {0}",num));
		}
		this.num = num;
	}
	
	public UnsignedInteger(String num_str) {
		this(Integer.valueOf(num_str));
	}
	

	
	@Override
	public int intValue() {
		return num;
	}

	@Override
	public long longValue() {
		return (long)num;
	}

	@Override
	public float floatValue() {
		return (float)num;
	}

	@Override
	public double doubleValue() {
		return (double)num;
	}
	
}

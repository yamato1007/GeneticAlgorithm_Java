package geneticalgorithm.userimplements;

import util.UnsignedInteger;

public interface Individualable {
	//その個体の評価
	//評価値は必ず正の値を取ること
	public UnsignedInteger Evaluation();
}

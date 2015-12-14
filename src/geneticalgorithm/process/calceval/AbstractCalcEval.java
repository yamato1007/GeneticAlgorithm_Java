package geneticalgorithm.process.calceval;

import java.util.List;

import geneticalgorithm.data.Gene;
import geneticalgorithm.data.ScoreMap;
import geneticalgorithm.userimplements.GeneIndividualFactory;

public abstract class AbstractCalcEval {
	//処理に必要な変数
	protected final GeneIndividualFactory factory;
	protected final List<Gene> genes;
	//処理した結果の変数
	private ScoreMap scoreMap;
	
	
	//コンストラクタ
	public AbstractCalcEval(GeneIndividualFactory factory,List<Gene> genes) {
		this.factory = factory;
		this.genes = genes;
	}
	
	//評価値の計算。メイン処理部分
	public abstract AbstractCalcEval calcEval();
	
	//計算結果をかえすアクセサメソッド
	public final ScoreMap getScoreMap(){
		return this.scoreMap;
	}
	
	//この中で最大のスコアのGeneを返す
	public final Gene getBestGene(){
		return genes.stream().reduce(
				(g1,g2) -> (scoreMap.get(g1) > scoreMap.get(g2)) ? g1 : g2
						).get();
	}
	
	//最大のスコアを返す
	public final int getBestScore(){
		return scoreMap.get(this.getBestGene());
	}
	
	//スコアの合計を返す
	public final int getTotalScore(){
		return genes.stream().reduce(
				0, 
				(score,gene) -> score + scoreMap.get(gene),
				(score1,score2) -> score1 + score2).intValue();
	}

	//計算結果をクラス変数に格納する。サブクラスで利用するためのメソッド
	protected final void setScoreMap(ScoreMap scoreMap){
		this.scoreMap = scoreMap;
	}
}

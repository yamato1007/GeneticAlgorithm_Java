package geneticalgorithm.process.calceval;

import java.util.List;

import geneticalgorithm.data.Gene;
import geneticalgorithm.data.ScoreMap;
import geneticalgorithm.userimplements.GeneIndividualFactory;
import geneticalgorithm.userimplements.Individualable;

public class CalcEval extends AbstractCalcEval {

	public CalcEval(GeneIndividualFactory factory, List<Gene> genes) {
		super(factory, genes);
	}

	@Override
	public AbstractCalcEval calcEval() {
		ScoreMap scoreMap = new ScoreMap();

		//TODO ParallelStream化して並列計算
		//各個体のスコアとスコアの合計を算出
		for(Gene gene : this.genes){
			//遺伝子から表現型オブジェクトのの生成
			Individualable individual = this.factory.newInstanceIndividual(gene);
			//個体のスコアの計算
			int score = individual.Evaluation().intValue();
			//スコアマップに格納
			scoreMap.put(gene, score);
		}
		//クラス変数に格納
		this.setScoreMap(scoreMap);
		return this;
	}

}

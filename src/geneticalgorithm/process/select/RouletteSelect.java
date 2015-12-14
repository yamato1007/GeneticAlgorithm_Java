package geneticalgorithm.process.select;

import java.util.*;

import geneticalgorithm.data.Gene;
import geneticalgorithm.data.ScoreMap;
import geneticalgorithm.process.calceval.AbstractCalcEval;
import geneticalgorithm.process.calceval.AbstractCalcEvalFactory;
import geneticalgorithm.userimplements.GeneIndividualFactory;

public class RouletteSelect extends AbstractSelect {
	public RouletteSelect(AbstractCalcEvalFactory calcEvalFactory,GeneIndividualFactory geneIndivFactory, List<Gene> genes) {
		super(calcEvalFactory, geneIndivFactory, genes);
	}

	@Override
	public AbstractSelect select() {
		//calcEvalFactoryから点数計算を行うAbstractCalcEvalインスタンスを生成
		AbstractCalcEval calcEval = this.calcEvalFactory.newInstance(geneIndivFactory, genes);
		//スコアマップ生成
		ScoreMap scoreMap = calcEval.calcEval().getScoreMap();
		//スコアの合計を得る
		int sumScore = calcEval.getTotalScore();
		
		//ランダム値の生成
		int rand_int = new Random().nextInt(sumScore);
		//ランダム値を各個体のスコアで引いていき、０未満になったらその個体を選択
		for(Gene gene: this.genes){
			if((rand_int -= scoreMap.get(gene)) < 0){
				this.setSelectedGene(gene);
				break;
			}
		}
		return this;
	}

}

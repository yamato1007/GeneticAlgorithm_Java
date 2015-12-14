package geneticalgorithm;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import geneticalgorithm.data.Gene;
import geneticalgorithm.data.ScoreMap;
import geneticalgorithm.process.calceval.AbstractCalcEval;
import geneticalgorithm.process.calceval.AbstractCalcEvalFactory;
import geneticalgorithm.process.calceval.CalcEvalFactory;
import geneticalgorithm.process.crossing.*;
import geneticalgorithm.process.mutation.AbstractMutation;
import geneticalgorithm.process.mutation.AbstractMutationFactory;
import geneticalgorithm.process.mutation.MutationFactory;
import geneticalgorithm.process.select.AbstractSelect;
import geneticalgorithm.process.select.AbstractSelectFactory;
import geneticalgorithm.process.select.RouletteSelectFactory;
import geneticalgorithm.userimplements.GeneIndividualFactory;
import geneticalgorithm.userimplements.Individualable;

public class GeneticAlgorithm {

	//個体の遺伝子
	private List<Gene> genes;
	
	//個体のファクトリメソッド
	private final GeneIndividualFactory geneIndivFactory;
	
	//採点に使用するクラス
	//private Class<? extends AbstractCalcEval> calcEvalClass = CalcEval.class;
	private AbstractCalcEvalFactory calcEvalFactory = new CalcEvalFactory();

	//選択に使用するクラス
	//private Class<? extends AbstractSelect> selectClass = RouletteSelect.class;
	private AbstractSelectFactory selectFactory = new RouletteSelectFactory();
	
	//交叉に使用するクラス
	//private Class<? extends AbstractCrossing> crossingClass = UniformCrossing.class;
	private AbstractCrossingFactory crossingFactory = new OnePointCrossingFactory();
	
	//突然変異に使用するクラス
	//private Class<? extends AbstractMutation> mutationClass = Mutation.class;
	private AbstractMutationFactory mutationFactory = new MutationFactory();
	
	//個体数
	private final int n;
	//交叉率
	private final float pc;
	//突然変異率
	private final float pm;
	
	//スコアマップ
	private ScoreMap scoreMap;
	//現在の世代数
	private int generation;
	
	
	//コンストラクタ。各初期値を設定
	public GeneticAlgorithm(GeneIndividualFactory factory, int n, float pc, float pm){
		//初期化値の正誤判断。おかしな値であればランタイムエクセプション
		if(!(n >= 2 && 
				0.0 < pc && pc <= 1.0 && 
				0.0 <= pm && pm <= 1.0)){
			throw new RuntimeException("初期化の値がおかしい");
		}
		this.geneIndivFactory = factory;
		this.n = n;
		this.pc = pc;
		this.pm = pm;
		
		//初期化
		this.initialize();
	}
	
	public void initialize(){
		//genesの初期化
		this.genes = new ArrayList<>();
		//スコアマップの初期化
		this.scoreMap = new ScoreMap();
		//世代数を0に設定
		this.generation = 0;
		
		//ランダムでGeneの作成
		for(int i=0; i < this.n ; i++){
			this.genes.add(new Gene(geneIndivFactory.getGeneSize()).randomInit());
		}
		//スコア計算とスコアマップ統合
		this.scoreMap.putAll(this.calcEval());
		//Genesをスコア昇順にソート
		this.genesSort();
	}

	//終了条件と各ステップで行う処理を引数に取る
	public void run(GAContinueCondition condtion,GAContinueCondition process){
		process.isContinue(generation, scoreMap.getBestScore());
		while(condtion.isContinue(generation, scoreMap.getBestScore())){
			stepRun();
			process.isContinue(generation, scoreMap.getBestScore());
		}
	}

	//終了条件をラムダ式で引数に取り、満たすまで計算
	public void run(GAContinueCondition condtion){
		while(condtion.isContinue(generation, scoreMap.getBestScore())){
			stepRun();
		}
	}
	
	//一世代すすめる
	//genes,scoreMap,generationを更新
	public void stepRun(){
		//子の生成
		List<Gene> children = this.makeChildren();
		//スコアが低いものから順に子供に入れ替え
		for(int i=0;i<children.size();i++){
			this.genes.set(i, children.get(i));
		}
		//突然変異
		this.mutation();
		//スコア計算とスコアマップ統合
		this.scoreMap.putAll(this.calcEval());
		//Genesをスコア昇順にソート
		this.genesSort();
		//世代をすすめる
		this.generation++;
	}
	
	//Genesをスコア昇順にソート
	private void genesSort(){
		ScoreMap sm_tmp = this.calcEval();
		Collections.sort(
    		this.genes,
        	(Gene g1, Gene g2) -> new Integer(sm_tmp.get(g1)).compareTo(sm_tmp.get(g2)));
	}
	
	//子を作る
	private List<Gene> makeChildren(){
		List<Gene> children = new ArrayList<>();
		for(int i=0; i < (int)(n*pc/2); i++){
			//親の選択
			Gene parent1 = this.selectGene();
			Gene parent2 = this.selectGene();
			//交差と子の取得
			Gene[] c_tmp = this.crossing(parent1, parent2);
			children.add(c_tmp[0]);
			children.add(c_tmp[1]);
		}
		return children;
	}
	
	//スコア計算。スコアマップ更新
	private ScoreMap calcEval(){
		//スコア計算インスタンス生成
		AbstractCalcEval calcEval = this.calcEvalFactory.newInstance(geneIndivFactory, genes);
		//現在の個体群のスコアマップ取得
		return calcEval.calcEval().getScoreMap();
	}
	//Gene一個選択
	private Gene selectGene(){
		AbstractSelect select = this.selectFactory.newInstance(calcEvalFactory, geneIndivFactory, genes);
		return select.select().getSelectedGene();
	}
	//交叉
	private Gene[] crossing(Gene gene1,Gene gene2){
		AbstractCrossing crossing = this.crossingFactory.newInstance(gene1, gene2);
		return crossing.cross().getChilds();
	}
	//突然変異
	private void mutation(){
		for(int i=0;i<this.genes.size();i++){
			//突然変異クラス生成
			AbstractMutation mutation = this.mutationFactory.newInstance(this.genes.get(i));
			//突然変異体を生成
			Gene mutant = mutation.mutate(pm).getMutant();
			//突然変異体でgenesを置換
			genes.set(i, mutant);
		}
	}
	
	//最良の遺伝子を得る
	public Gene getBestGene(){
		return this.scoreMap.getBestGene();
	}
	
	//最良の個体を得る
	public Individualable getBestIndividual(){
		Gene gene = getBestGene();
		return geneIndivFactory.newInstanceIndividual(gene);
	}

	
	
	

	//アクセサメソッド
	
	//選択クラスファクトリ
	public AbstractSelectFactory getSelectFactory() {
		return selectFactory;
	}
	public void setSelectFactory(AbstractSelectFactory selectFactory) {
		this.selectFactory = selectFactory;
	}
	//交叉クラスファクトリ
	public AbstractCrossingFactory getCrossingFactory() {
		return crossingFactory;
	}
	public void setCrossingFactory(AbstractCrossingFactory crossingFactory) {
		this.crossingFactory = crossingFactory;
	}
	//突然変異クラスファクトリ
	public AbstractMutationFactory getMutationFactory() {
		return mutationFactory;
	}
	public void setMutationFactory(AbstractMutationFactory mutationFactory) {
		this.mutationFactory = mutationFactory;
	}
	//スコア計算ファクトリ
	public AbstractCalcEvalFactory getCalcEvalFactory() {
		return calcEvalFactory;
	}
	public void setCalcEvalFactory(AbstractCalcEvalFactory calcEvalFactory) {
		this.calcEvalFactory = calcEvalFactory;
	}

	//遺伝子型表現型変換ファクトリ
	public GeneIndividualFactory getGeneIndivFactory() {
		return geneIndivFactory;
	}
	
	
	
	//現在の遺伝子群Getter
	public List<Gene> getGenes() {
		return genes;
	}


	//スコアマップGetter
	public ScoreMap getScoreMap() {
		return scoreMap;
	}
	
	//現在の世代Getter
	public int getGeneration() {
		return generation;
	}


	//確定数
	public int getN() {
		return n;
	}
	public float getPc() {
		return pc;
	}
	public float getPm() {
		return pm;
	}
	
	@Override
	public String toString(){
		return genes.stream().reduce(
				new StringBuilder(),
				(StringBuilder str,Gene gene)->
					str.append(gene.toString()).append(":"+scoreMap.get(gene)+"\n"),
				(StringBuilder str1,StringBuilder str2) ->
					str1.append(str2)
			).toString();
	}
}

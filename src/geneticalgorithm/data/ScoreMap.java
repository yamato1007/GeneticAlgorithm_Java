package geneticalgorithm.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ScoreMap {
	private final Map<Gene,Integer> scoreMap; 
	
	//コンストラクタ
	public ScoreMap(Map<Gene,Integer> scoreMap){
		this.scoreMap = new HashMap<Gene,Integer>(scoreMap);
	}
	public ScoreMap(){
		this(new HashMap<Gene,Integer>());
	}
	
	//スコア降順に並べたArrayListを返す
	public List<Map.Entry<Gene, Integer>> getRanking(){
		List<Map.Entry<Gene,Integer>> genesRanking = new ArrayList<>(scoreMap.entrySet());
        Collections.sort(
        	genesRanking,
        	(HashMap.Entry<Gene,Integer> entry1, HashMap.Entry<Gene,Integer> entry2)
        		-> (entry2.getValue()).compareTo(entry1.getValue()));
        return genesRanking;
	}
	//スコア最大のGeneを返す
	public Gene getBestGene(){
		return this.getRanking().get(0).getKey();
	}
	//最高のGeneのスコアを返す
	public int getBestScore(){
		return this.get(this.getBestGene());
	}
	
	//アクセサメソッド
	public Map<Gene, Integer> getScoreMap() {
		return scoreMap;
	}

	//Mapのもともと持つメソッドを定義
	public void put(Gene gene,Integer score){
		this.scoreMap.put(gene, score);
	}
	
	public void putAll(ScoreMap scoreMap){
		this.scoreMap.putAll(scoreMap.getScoreMap());
	}
	
	public int get(Gene gene){
		return this.scoreMap.get(gene);
	}
	
	public int size(){
		return this.scoreMap.size();
	}
}

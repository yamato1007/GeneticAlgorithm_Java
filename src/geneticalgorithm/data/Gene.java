package geneticalgorithm.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gene {
	private List<Boolean> gene;

	public Gene(List<Boolean> gene){
		this.gene = gene;
	}
	public Gene(int size){
		this.gene = new ArrayList<>(size);
		for(int i=0;i<size;i++){
			this.gene.add(null);
		}
	}
	
	public Gene randomInit(){
		Random random = new Random();
		for(int i = 0; i < this.size(); i++){
			gene.set(i, random.nextBoolean());
		}
		return this;
	}
	
	//read Method
	public void setGene(List<Boolean> gene){
		this.gene = gene;
	}
	public void setGeneIndex( int index,Boolean b){
		this.gene.set(index, b);
	}

	//Write Method
	public List<Boolean> getGene(){
		return this.gene;
	}
	public Boolean getGeneIndex(int i){
		if( i < 0 || this.gene.size() <= i){
			return null;
		}
		return this.gene.get(i);
	}
	
	public int size(){
		return this.gene.size();
	}
	
	//各遺伝子一個一個を01で表現
	@Override
	public String toString(){
		return this.gene.stream().reduce(
				new StringBuilder(),
				(StringBuilder str, Boolean bool)->str.append(bool ? "1" : "0"),
				(StringBuilder str1,StringBuilder str2) -> str1.append(str2)).toString();
	}
}

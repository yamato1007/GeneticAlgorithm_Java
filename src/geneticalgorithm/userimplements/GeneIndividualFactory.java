package geneticalgorithm.userimplements;

import java.text.MessageFormat;

import geneticalgorithm.data.Gene;
import geneticalgorithm.exception.ImplementException;

public abstract class GeneIndividualFactory {
	
	public final Individualable newInstanceIndividual(Gene gene){
		if(gene.size() != this.getGeneSize()){
			throw new IllegalArgumentException(
					MessageFormat.format("FactoryGeneIndiv#AbstractNewInstanceGene(Gene gene)に与えられたGeneの大きさがFactoryGeneIndiv#getGeneSizeの大きさと等しくない\n"+
					"gene.size() = {0}" + 
					"FactoryGeneIndiv#getGeneSize = {1}",gene.size(),this.getGeneSize())); 
		}
		Individualable indiv = this.abstractNewInstanceIndividual(gene);
		return indiv;
	}
	
	public final Gene newInstanceGene(Individualable indiv){
		Gene gene = this.abstractNewInstanceGene(indiv);
		if(gene.size() != this.getGeneSize()){
			throw new ImplementException(
					ImplementException.ErrorCode.RETURNVALUEERROR,
					MessageFormat.format("FactoryGeneIndiv#AbstractNewInstanceGene()の戻り値のGeneのsize()とFactoryGeneIndiv#getGeneSizeの大きさは等しくなければならない\n"+
					"FactoryGeneIndiv#AbstractNewInstanceGene().size() = {0}" + 
					"FactoryGeneIndiv#getGeneSize = {1}",gene.size(),this.getGeneSize())); 
		}
		return gene;
	}
	
	//GeneからIndivを生成するファクトリメソッド
	protected abstract Individualable abstractNewInstanceIndividual(Gene gene);
	
	//IndividualからGeneを生成するファクトリメソッド
	protected abstract Gene abstractNewInstanceGene(Individualable indiv);
	
	//Geneの大きさを定義してもらいたい
	public abstract int getGeneSize();
	
}

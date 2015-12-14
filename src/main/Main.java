package main;

import java.util.List;

import geneticalgorithm.GeneticAlgorithm;
import geneticalgorithm.data.Gene;
import geneticalgorithm.process.crossing.UniformCrossingFactory;
import geneticalgorithm.userimplements.GeneIndividualFactory;
import geneticalgorithm.userimplements.Individualable;
import util.UnsignedInteger;
import util.timespan.SimpleCalcTime;

public class Main {
	private static final int n = 20;
	private static final float pc = 0.9f;
	private static final float pm = 0.01f;
	private static final int steps = 3000;


	public static void main(String arg[]){
		SimpleCalcTime calcTime = new SimpleCalcTime();

		run();

		System.out.println(calcTime.checkPoint());
		//runSteps();
	}

	private static void run(){
		NumGeneFactory factory = new NumGeneFactory();
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(factory, n, pc, pm);
		//geneticAlgorithm.setCrossingFactory(new UniformCrossingFactory());
		System.out.println(geneticAlgorithm.toString());
		SimpleCalcTime calcTime = new SimpleCalcTime();
		geneticAlgorithm.run(
				(generation, score) -> generation<=steps,
				(generation, score) -> {
					if(generation % 100 == 0) {
						System.out.println(generation + "steps : " + score + "points");
						System.out.println(calcTime.checkPoint());
					}
					return true;
				});
		System.out.println(geneticAlgorithm);
	}

	private static void runSteps(){
		NumGeneFactory factory = new NumGeneFactory();
		GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(factory, n, pc, pm);
		//geneticAlgorithm.setCrossingFactory(new UniformCrossingFactory());
		System.out.println(geneticAlgorithm.toString());
		for(int i=0;i<steps;i++){
			geneticAlgorithm.stepRun();
			System.out.println();
			System.out.println(geneticAlgorithm.toString());
			//System.out.println(geneticAlgorithm.getBestIndividual().toString());
		}

	}

}

class Num implements Individualable{
	List<Boolean> bools;

	public Num(List<Boolean> bools){
		this.bools = bools;
	}
	
	@Override
	public UnsignedInteger Evaluation() {
		int score = 0;
		for(boolean bool : bools){
			score += bool ? 1 : 0;
		}
		return new UnsignedInteger(score);
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		for(boolean bool : bools){
			str.append(bool ? "1" : "0");
		}
		str.append(":" + this.Evaluation().intValue());
		return str.toString();
	}

	public List<Boolean> getBools() {
		return bools;
	}

	public void setBools(List<Boolean> bools) {
		this.bools = bools;
	}
}

class NumGeneFactory extends GeneIndividualFactory{

	@Override
	protected Individualable abstractNewInstanceIndividual(Gene gene) {
		return new Num(gene.getGene());
	}

	@Override
	protected Gene abstractNewInstanceGene(Individualable indiv) {
		return new Gene(((Num)indiv).getBools());
	}

	@Override
	public int getGeneSize() {
		return 100;
	}
	
}
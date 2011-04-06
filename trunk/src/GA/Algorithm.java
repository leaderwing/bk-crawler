/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GA;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Long
 */
public class Algorithm {

	private static double mutation_rate; // phan tram dot bien
	private static double crossover_rate; // phan tram lai ghep
	private static int population_size; // kich co quan the
	private static int number_generation; // số vòng đời thế hệ
	private static int gene_size; // kich co bo gen
	private static double total_fitness; // do fitness cua quan the moi
	private static boolean m_elitism;// chon loc
	private static Genome best_gene;
	private static ArrayList<String> arr_best_term;
	//private static ArrayList<Genome> firstGeneration; // quan the ban dau
	private static ArrayList<Genome> thisGeneration; // quan the hien tai
	private static ArrayList<Genome> nextGeneration; // quan the moi
	private static ArrayList<Double> fitness_table; // bang thich nghi : tinh xac suat
											// chon loc theo Roulette Wheel
	public static Random random = new Random();

	Algorithm() {

		Algorithm.mutation_rate = 0.1;
		Algorithm.crossover_rate = 0.80;
		Algorithm.population_size = 100;
		Algorithm.number_generation = 2000;
	}

	public Algorithm(ArrayList<Genome> initial_population, ArrayList<String>arr_best_term,int populationSize,
			double mutation_rate, double crossover_rate,int number_generation) {
		Algorithm.fitness_table = new ArrayList<Double>(populationSize);
		Algorithm.thisGeneration = initial_population;
		
		Algorithm.arr_best_term=arr_best_term;
		Algorithm.population_size = populationSize;
		// Algorithm.gene_size = genomeSize;
		Algorithm.crossover_rate = crossover_rate;
		Algorithm.mutation_rate = mutation_rate;
		Algorithm.number_generation=number_generation;
	}
    public static Genome getBestGene() {
    	return Algorithm.best_gene;
    }
    public String newKey() {
    	/*Random r= new Random();
    	int position_key=r.nextInt(Algorithm.population_size);
    	String new_key= Algorithm.arr_best_term.get(position_key);*/
    	double max_weight=0;
    	Integer pos=0;
    	
    	ArrayList<Double> chromosome=Algorithm.best_gene.getChromosome();
    	for(int i=0;i<chromosome.size();i++) {
    		if(chromosome.get(i)>max_weight) {
    			max_weight=chromosome.get(i);
    			pos=i;
    		}
    	}
    	String new_key= Algorithm.arr_best_term.get(pos);
    	return new_key;
    }
	public void caculateRouletteWheel(ArrayList<Genome> generation) {
		double max_fitness = 0;
		double sum_fitness = 0;
		Algorithm.fitness_table.clear();
		// caculate fitness of each gene
		for (int i = 0; i < generation.size(); i++) {
			Genome gene = generation.get(i);
			gene.caculateFitness();
			double fitness_gene_i = gene.GetFitness();
			Algorithm.fitness_table.add(i, fitness_gene_i);
			sum_fitness = sum_fitness + fitness_gene_i;
			if (fitness_gene_i > max_fitness) {
				max_fitness = fitness_gene_i;
				Algorithm.best_gene = gene;
			}

		}
		// caculate probability which gene choosed by fitness : Pi
		for (int i = 0; i < Algorithm.fitness_table.size(); i++) {
			Double fitness_i =  Algorithm.fitness_table.get(i);
			double probability_fitness=0;
			if(sum_fitness!=0) {
			BigDecimal d = new BigDecimal(fitness_i / sum_fitness);
			d = d.setScale(3, BigDecimal.ROUND_HALF_UP);
			probability_fitness = d.doubleValue();
			}
			
			Algorithm.fitness_table.remove(i);
			Algorithm.fitness_table.add(i, probability_fitness);

		}
		// caculate position probability of each gene Qi= sum(Pj) j=0->i
		for (int i = 1; i < Algorithm.fitness_table.size(); i++) {
			double Qi = Algorithm.fitness_table.get(i)
					+  Algorithm.fitness_table.get(i - 1);
			Algorithm.fitness_table.remove(i);
			Algorithm.fitness_table.add(i, Qi);
		}
		System.out.println("fitness ="+ Algorithm.fitness_table+"\n");
		System.out.println("best gene="+ Algorithm.best_gene.getChromosome());
	}

	public void Go() {

		// Algorithm.fitnessTable = new ArrayList();
		//Algorithm.thisGeneration = Algorithm.firstGeneration;
		Algorithm.nextGeneration = new ArrayList<Genome>(Algorithm.population_size);
		// Genome.setMutationRate(Algorithm.mutation_rate);
		Algorithm.best_gene = new Genome();
		this.caculateRouletteWheel(Algorithm.thisGeneration);
		for (int i = 0; i <= Algorithm.number_generation; i++) {

			this.CreateNextGeneration();
			this.caculateRouletteWheel(Algorithm.thisGeneration);
			/*
			 * for (int j = i;j <= Algorithm.population_size; j++) { Genome gen
			 * = (Genome) Algorithm.thisGeneration.get(j);
			 * gen.CaculateFitness(); if (gen.GetFitness() > maxFitness) {
			 * maxIndex = j; maxFitness = gen.GetFitness(); Algorithm.m_bestGen
			 * = gen; } } this.RankPopulation();
			 * Algorithm.m_bestGen=Algorithm.thisGeneration
			 * .get(Algorithm.population_size);
			 */
		}
	}

	public void CreateNextGeneration() {
		Algorithm.nextGeneration.clear();
		
		for (int i = 0; i < Algorithm.population_size; i += 2) {
			int pidx1 = 0;
			int pidx2 = 0;
			pidx1 = this.rouletteSelection();
			while (true) {
				pidx2 = this.rouletteSelection();
				if (pidx1 != pidx2) {
					break;
				}
			}
			Genome parent1, parent2, child1, child2;
			
			parent1 =  Algorithm.thisGeneration.get(pidx1);
			parent2 =  Algorithm.thisGeneration.get(pidx2);
			if (random.nextDouble() <= Algorithm.crossover_rate) { 
				child1 = parent1.Crossover(parent2);
				child2 = parent2.Crossover(parent1);
			} else {
				child1 = parent1;
				child2 = parent2;
			}
			child1.Mutate(Algorithm.mutation_rate);
			child2.Mutate(Algorithm.mutation_rate);
			Algorithm.nextGeneration.add(child1);
			Algorithm.nextGeneration.add(child2);
		}
		System.out.println("next generation ="+ Algorithm.nextGeneration.size()+"\n");
		for(int i=0;i<Algorithm.nextGeneration.size();i++) {
			System.out.println(Algorithm.nextGeneration.get(i).getChromosome()+"\n");
		}
		Algorithm.thisGeneration= Algorithm.nextGeneration;
		System.out.println("this generation ="+ Algorithm.thisGeneration.size());
		for(int i=0;i<Algorithm.thisGeneration.size();i++) {
			System.out.println(Algorithm.thisGeneration.get(i).getChromosome()+"\n");
		}
		/*for (int i = 0; i <= Algorithm.population_size; i++) {
			System.out.println("i="+i+"\n");
            Algorithm.thisGeneration.remove(i);		
			Algorithm.thisGeneration.set(i, Algorithm.nextGeneration.get(i));
		}*/
	}

	public int rouletteSelection() {
		// TODO Auto-generated method stub
		Double random = Math.random();
		int gene_i=0;
		for (int i = 0; i < Algorithm.fitness_table.size(); i++) {
			if (random <  Algorithm.fitness_table.get(i)) {
                gene_i=i;
                break;
			}
			else {
				continue;
			}
		}
		return gene_i;
	}
}

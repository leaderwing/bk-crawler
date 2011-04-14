/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GA;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import model.Newkeyword;

/**
 * 
 * @author Long
 */
public class Algorithm {

	private static double mutation_rate; // phan tram dot bien
	private static double crossover_rate; // phan tram lai ghep
	private static int population_size; // kich co quan the
	private static int number_generation; // số vòng đời thế hệ
	//private static int gene_size; // kich co bo gen
	//private static double total_fitness; // do fitness cua quan the moi
	private static double best_fitness; // do fitness tot nhat
	//private static boolean m_elitism;// chon loc
	private static Genome best_gene;
	private static ArrayList<String> arr_best_term;
	private static ArrayList<String> arr_initial_key;
	private static ArrayList<String> doc_link;
	//private static ArrayList<Genome> firstGeneration; // quan the ban dau
	private static ArrayList<Genome> thisGeneration = new ArrayList<Genome>(); // quan the hien tai
	private static Integer pos_newkey=0;
	private static ArrayList<Double> fitness_table; // bang thich nghi : tinh xac suat chon loc theo Roulette Wheel
	private static boolean is_GA= true;//nếu số gen của quần thể hơn một nửa là có fitness =0 thì không GA,is_GA=false 
	public static Random random = new Random();
	public static double suitable_fitness=0.8;

	Algorithm() {

		Algorithm.mutation_rate = 0.1;
		Algorithm.crossover_rate = 0.80;
		Algorithm.population_size = 100;
		Algorithm.number_generation = 2000;
	}

	public Algorithm(ArrayList<String> doc_link, ArrayList<Genome> initial_population,ArrayList<String> arr_initial_key, ArrayList<String>arr_best_term,int populationSize,
			double mutation_rate, double crossover_rate,int number_generation) {
		Algorithm.fitness_table = new ArrayList<Double>(populationSize);
		Algorithm.thisGeneration = initial_population;
		Algorithm.arr_initial_key=arr_initial_key;
		Algorithm.doc_link=doc_link;
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
    public  Integer getPositionNewKey()
    {
    	return pos_newkey;
    }
    public  double getBestFitness()
    {
    	return best_fitness;
    }
    public String newKey() {
    	
    	double max_weight=0;
    	Integer pos=0;
    	String new_key="";
		if (Algorithm.is_GA) {
			ArrayList<Double> chromosome = Algorithm.best_gene.getChromosome();
			for (int i = 0; i < chromosome.size(); i++) {
				String key = Algorithm.arr_best_term.get(i);
				boolean is_get = true;// key not equal initial key
				if (chromosome.get(i) > 0) {
					for (int j = 0; j < arr_initial_key.size(); j++) {
						if (arr_initial_key.get(j).equals(key)) {
							is_get = false;
							break;
						}
					}
					if ((is_get) && (chromosome.get(i) > max_weight)) {
						max_weight = chromosome.get(i);
						pos = i;
						new_key = Algorithm.arr_best_term.get(pos);
					}
				}

			}
			pos_newkey = pos;
		}
    	return new_key;
    }
	public void caculateRouletteWheel(ArrayList<Genome> generation) {
		double max_fitness = 0;
		double sum_fitness = 0;
		int pos_genome=0;
		Algorithm.fitness_table.clear();
		// caculate fitness of each gene
		for (int i = 0; i < generation.size(); i++) {
			Genome gene = generation.get(i);
			gene.caculateFitness();
			double fitness_gene_i = gene.GetFitness();
			Algorithm.fitness_table.add(i, fitness_gene_i);
			sum_fitness = sum_fitness + fitness_gene_i;
			if (fitness_gene_i >= max_fitness) {
				max_fitness = fitness_gene_i;
				Algorithm.best_gene = gene;
				pos_genome=i;
			}

		}
		
		// đếm xem có bao nhiêu gen trong quần thể là có fitness >0 
		int count_gen=0;
		for(int i=0;i<Algorithm.fitness_table.size();i++) {
			if(Algorithm.fitness_table.get(i)>0)
			{
				count_gen++;
			}
		}
		if(count_gen<Algorithm.fitness_table.size()/2) {
			Algorithm.is_GA=false;
			
		}
		//System.out.println(generation.get(pos_genome).getChromosome()+"\n");
		//System.out.println("best fitness ="+ max_fitness+"\n");
		best_fitness=max_fitness;
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
		System.out.println("max fitness ="+ max_fitness+"\n");
		System.out.println("best gene="+ Algorithm.best_gene.getChromosome());
	}

	public void Go() {

		// Algorithm.fitnessTable = new ArrayList();
		//Algorithm.thisGeneration = Algorithm.firstGeneration;
		
		// Genome.setMutationRate(Algorithm.mutation_rate);
		Algorithm.best_gene = new Genome();
		this.caculateRouletteWheel(Algorithm.thisGeneration);
		System.out.println("is GA 1 :"+ Algorithm.is_GA);
		if(Algorithm.is_GA) {
				
				int count_generation=0;
				while((count_generation<Algorithm.number_generation)&&(Algorithm.best_fitness<Algorithm.suitable_fitness)){
					count_generation++;
					this.CreateNextGeneration();
					this.caculateRouletteWheel(Algorithm.thisGeneration);
					if(Algorithm.is_GA) {
						System.out.println("is GA 2 :"+ Algorithm.is_GA);
						
						continue;
					}
					else {
						break;
					}
					
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
	}

	public void CreateNextGeneration() {
		ArrayList<Genome> nextGeneration = new ArrayList<Genome>(); // quan the moi
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
			System.out.println("this generation begin ="+ Algorithm.thisGeneration.size());
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
			System.out.println("child1 ="+ child1.getChromosome());
			nextGeneration.add(i, child1);
			nextGeneration.add(i+1,child2);
		}
		System.out.println("next generation ="+ nextGeneration.size()+"\n");
		for(int i=0;i<nextGeneration.size();i++) {
			System.out.println(nextGeneration.get(i).getChromosome()+"\n");
		}
		Algorithm.thisGeneration= nextGeneration;
		System.out.println("this generation result ="+ Algorithm.thisGeneration.size());
		for(int i=0;i<Algorithm.thisGeneration.size();i++) {
			System.out.println(Algorithm.thisGeneration.get(i).getChromosome()+"\n");
		}
		/*for (int i = 0; i <= Algorithm.population_size; i++) {
			System.out.println("i="+i+"\n");
            Algorithm.thisGeneration.remove(i);		
			Algorithm.thisGeneration.set(i, Algorithm.nextGeneration.get(i));
		}*/
	}
	//lai ghep da diem
	public void CreateNextGeneration2() {
		ArrayList<Genome> nextGeneration = new ArrayList<Genome>(); // quan the moi
		
		for (int i = 0; i < Algorithm.population_size; i += 2) {
			int pidx1 = 0;
			int pidx2 = 0;
			int pidx3 = 0;
			int pidx4 = 0;
			pidx1 = this.rouletteSelection();
			while (true) {
				pidx2 = this.rouletteSelection();
				if (pidx1 != pidx2) {
					while (true) {
						pidx3 = this.rouletteSelection();
						if ((pidx1 != pidx3)&&(pidx2 != pidx3)) {
							while (true) {
								pidx4 = this.rouletteSelection();
								if ((pidx1 != pidx4)&&(pidx2 != pidx4)&&((pidx3 != pidx4))) {
									break;
								}
							}
							break;
						}
					}
					break;
				}
			}
			Genome parent1, parent2, parent3, parent4, child;
			
			parent1 =  Algorithm.thisGeneration.get(pidx1);
			parent2 =  Algorithm.thisGeneration.get(pidx2);
			parent3 =  Algorithm.thisGeneration.get(pidx3);
			parent4 =  Algorithm.thisGeneration.get(pidx4);
			if (random.nextDouble() <= Algorithm.crossover_rate) { 
				ArrayList<Genome> arr_parent= new ArrayList<Genome>();
				child = parent1.CrossoverMulti(arr_parent);
				child.Mutate(Algorithm.mutation_rate);
				nextGeneration.add(child);
			} else {
				parent1.Mutate(mutation_rate);
				parent2.Mutate(mutation_rate);
				parent3.Mutate(mutation_rate);
				parent4.Mutate(mutation_rate);
				nextGeneration.add(parent1);
				nextGeneration.add(parent2);
				nextGeneration.add(parent3);
				nextGeneration.add(parent4);
			}
		}
		System.out.println("next generation ="+ nextGeneration.size()+"\n");
		for(int i=0;i<nextGeneration.size();i++) {
			System.out.println(nextGeneration.get(i).getChromosome()+"\n");
		}
		Algorithm.thisGeneration= nextGeneration;
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
	//Long code
   
}

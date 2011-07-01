/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GA;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import javax.print.attribute.standard.PDLOverrideSupported;

import model.Newkeyword;

/**
 * 
 * @author Long
 */
public class Algorithm {

	private static double mutation_rate; // phan tram dot bien
	private static double crossover_rate; // phan tram lai ghep
	private static int population_size; // kich co quan the
	private static int number_generation; // sá»‘ vÃ²ng Ä‘á»�i tháº¿ há»‡
	// private static int gene_size; // kich co bo gen
	private static ArrayList<Double> weight_new_key = new ArrayList<Double>(); // weight
																				// cá»§a
																				// new
																				// key
	private static double best_fitness; // do fitness tot nhat
	// private static boolean m_elitism;// chon loc
	private static Genome best_gene;
	private static ArrayList<String> arr_best_term;
	private static ArrayList<String> arr_initial_key;
	private static ArrayList<String> doc_link;
	// private static ArrayList<Genome> firstGeneration; // quan the ban dau
	private static ArrayList<Genome> thisGeneration = new ArrayList<Genome>(); // quan
																				// the
																				// hien
																				// tai
	private static ArrayList<String> link_of_newkey = new ArrayList<String>();
	private static ArrayList<Double> fitness_table; // bang thich nghi : tinh
													// xac suat chon loc theo
													// Roulette Wheel
	private static boolean is_GA = true;// náº¿u sá»‘ gen cá»§a quáº§n thá»ƒ
										// hÆ¡n má»™t ná»­a lÃ  cÃ³ fitness =0
										// thÃ¬ khÃ´ng GA,is_GA=false
	public static Random random = new Random();
	public static double suitable_fitness = 0.9;
	private ArrayList<Integer> bestTable = new ArrayList<Integer>();	

	Algorithm() {

		Algorithm.mutation_rate = 0.1;
		Algorithm.crossover_rate = 0.80;
		Algorithm.population_size = 100;
		Algorithm.number_generation = 2000;
	}

	public Algorithm(ArrayList<String> doc_link,
			ArrayList<Genome> initial_population,
			ArrayList<String> arr_initial_key, ArrayList<String> arr_best_term,
			int populationSize, double mutation_rate, double crossover_rate,
			int number_generation) {
		Algorithm.fitness_table = new ArrayList<Double>(populationSize);
		Algorithm.thisGeneration = initial_population;
		Algorithm.arr_initial_key = arr_initial_key;
		Algorithm.doc_link = doc_link;
		Algorithm.arr_best_term = arr_best_term;
		Algorithm.population_size = populationSize;
		// Algorithm.gene_size = genomeSize;
		Algorithm.crossover_rate = crossover_rate;
		Algorithm.mutation_rate = mutation_rate;
		Algorithm.number_generation = number_generation;
	}

	public static Genome getBestGene() {
		return Algorithm.best_gene;
	}

	public ArrayList<String> getLinkNewKey() {
		return link_of_newkey;
	}

	public double getBestFitness() {
		return best_fitness;
	}

	public ArrayList<Double> getWeightNewKey() {
		return weight_new_key;
	}

	public ArrayList<String> newKey() {

		double max_weight = 0;
		Integer pos = 0;
		ArrayList<String> new_key = new ArrayList<String>();
		if (Algorithm.is_GA) {
			ArrayList<Double> chromosome = Algorithm.best_gene.getChromosome();
			// sxep ná»•i bá»�t máº£ng trá»�ng sá»‘ theo chiá»�u tÄƒng dáº§n
			int counter, index;
			int length = chromosome.size();
			for (counter = 0; counter < length - 1; counter++) { // Loop once
																	// for each
																	// element
																	// in the
																	// array.
				for (index = 0; index < length - 1 - counter; index++) { // Once
																			// for
																			// each
																			// element,
																			// minus
																			// the
																			// counter.
					if (chromosome.get(index) > chromosome.get(index + 1)) { // Test
																				// if
																				// need
																				// a
																				// swap
																				// or
																				// not.
						double temp = chromosome.get(index); // These three
																// lines just
																// swap the two
																// elements:
						chromosome.set(index, chromosome.get(index + 1));
						chromosome.set(index + 1, temp);

						String key = arr_best_term.get(index);
						arr_best_term.set(index, arr_best_term.get(index + 1));
						arr_best_term.set(index + 1, key);

						key = doc_link.get(index);
						doc_link.set(index, doc_link.get(index + 1));
						doc_link.set(index + 1, key);

					}
				}
			}
			//
			int size = doc_link.size();
			for (int i = 0; i < 1; i++) {
				new_key.add(arr_best_term.get(size - 1 - i));
				link_of_newkey.add(doc_link.get(size - 1 - i));
				weight_new_key.add(chromosome.get(size - 1 - i));

			}

		}

		return new_key;
	}

	public void caculateRouletteWheel(ArrayList<Genome> generation) {
		double max_fitness = 0;
		double sum_fitness = 0;
		int pos_genome = 0;
		Algorithm.fitness_table.clear();
		// caculate fitness of each gene
		for (int i = 0; i < generation.size(); i++) {
			Genome gene = generation.get(i);
			// System.out.println("gene "+i+"="+ gene.getChromosome()+"\n");
			gene.caculateFitness();
			// gene.caculateFitness_2(arr_initial_key, arr_best_term);
			double fitness_gene_i = gene.GetFitness();
			Algorithm.fitness_table.add(i, fitness_gene_i);
			sum_fitness = sum_fitness + fitness_gene_i;
			if (fitness_gene_i >= max_fitness) {
				max_fitness = fitness_gene_i;
				Algorithm.best_gene = gene;
				pos_genome = i;
			}

		}
		// System.out.println("fitness table="+fitness_table+"\n");//System.exit(0);
		// Ä‘áº¿m xem cÃ³ bao nhiÃªu gen trong quáº§n thá»ƒ lÃ  cÃ³ fitness >0
		int count_gen = 0;
		for (int i = 0; i < Algorithm.fitness_table.size(); i++) {
			if (Algorithm.fitness_table.get(i) > 0) {
				count_gen++;
			}
		}
		if (count_gen < Algorithm.fitness_table.size() / 2) {
			Algorithm.is_GA = false;

		}
		// System.out.println(generation.get(pos_genome).getChromosome()+"\n");
		// System.out.println("best fitness ="+ max_fitness+"\n");
		best_fitness = max_fitness;
		// caculate probability which gene choosed by fitness : Pi
		for (int i = 0; i < Algorithm.fitness_table.size(); i++) {
			Double fitness_i = Algorithm.fitness_table.get(i);
			double probability_fitness = 0;
			if (sum_fitness != 0) {
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
					+ Algorithm.fitness_table.get(i - 1);
			Algorithm.fitness_table.remove(i);
			Algorithm.fitness_table.add(i, Qi);
		}
		// System.out.println("fitness ="+ Algorithm.fitness_table+"\n");
		// System.out.println("max fitness ="+ max_fitness+"\n");
		System.out.println("best gene=" + Algorithm.best_gene.getChromosome());
	}

	public void Go(Integer num_parent) {

		// Algorithm.fitnessTable = new ArrayList();
		// Algorithm.thisGeneration = Algorithm.firstGeneration;

		// Genome.setMutationRate(Algorithm.mutation_rate);
		Algorithm.best_gene = new Genome();
		this.caculateRouletteWheel(Algorithm.thisGeneration);
		initialBestTable();
		System.out.println("is GA 1 :" + Algorithm.is_GA);
		if (Algorithm.is_GA) {

			int count_generation = 0;
			while ((count_generation < Algorithm.number_generation)
					&& (Algorithm.best_fitness < Algorithm.suitable_fitness)) {
				count_generation++;
				this.CreateNextGeneration2(num_parent);
				this.caculateRouletteWheel(Algorithm.thisGeneration);
				if (Algorithm.is_GA) {
					System.out.println("is GA 2 :" + Algorithm.is_GA);

					continue;
				} else {
					break;
				}

				/*
				 * for (int j = i;j <= Algorithm.population_size; j++) { Genome
				 * gen = (Genome) Algorithm.thisGeneration.get(j);
				 * gen.CaculateFitness(); if (gen.GetFitness() > maxFitness) {
				 * maxIndex = j; maxFitness = gen.GetFitness();
				 * Algorithm.m_bestGen = gen; } } this.RankPopulation();
				 * Algorithm.m_bestGen=Algorithm.thisGeneration
				 * .get(Algorithm.population_size);
				 */
			}
		}
	}

	public void CreateNextGeneration() {
		ArrayList<Genome> nextGeneration = new ArrayList<Genome>(); // quan the
																	// moi
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
			System.out.println("this generation begin ="
					+ Algorithm.thisGeneration.size());
			parent1 = Algorithm.thisGeneration.get(pidx1);
			parent2 = Algorithm.thisGeneration.get(pidx2);
			if (random.nextDouble() <= Algorithm.crossover_rate) {
				child1 = parent1.Crossover(parent2);
				child2 = parent2.Crossover(parent1);
			} else {
				child1 = parent1;
				child2 = parent2;
			}
			child1.Mutate(Algorithm.mutation_rate);
			child2.Mutate(Algorithm.mutation_rate);
			System.out.println("child1 =" + child1.getChromosome());
			nextGeneration.add(i, child1);
			nextGeneration.add(i + 1, child2);
		}
		System.out.println("next generation =" + nextGeneration.size() + "\n");
		for (int i = 0; i < nextGeneration.size(); i++) {
			System.out.println(nextGeneration.get(i).getChromosome() + "\n");
		}

		Algorithm.thisGeneration = nextGeneration;
		System.out.println("this generation result ="
				+ Algorithm.thisGeneration.size());
		for (int i = 0; i < Algorithm.thisGeneration.size(); i++) {
			System.out.println(Algorithm.thisGeneration.get(i).getChromosome()
					+ "\n");
		}
		/*
		 * for (int i = 0; i <= Algorithm.population_size; i++) {
		 * System.out.println("i="+i+"\n"); Algorithm.thisGeneration.remove(i);
		 * Algorithm.thisGeneration.set(i, Algorithm.nextGeneration.get(i)); }
		 */
	}

	// lai ghep da diem	
	public void CreateNextGeneration2(Integer num_parent) {
		ArrayList<Genome> nextGeneration = new ArrayList<Genome>(); // quan the
																	// moi

		while (nextGeneration.size() < Algorithm.population_size) {
			ArrayList<Integer> pidx = new ArrayList<Integer>();
			/*
			 * for(int i=0;i<num_parent;i++){ pidx.add(0); }
			 */
			Integer count_parent = 0;
			while (count_parent < num_parent) {
				//Integer temp = this.rouletteSelection();
				Integer temp = this.bestGeneSelection();
				if (count_parent == 0) {
					pidx.add(temp);
					count_parent++;
					continue;
				}
				boolean check = false;
				for (int i = 0; i < count_parent; i++) {
					if (temp == pidx.get(i)) {
						check = false;
						break;
					} else {
						check = true;
					}
				}
				if (check) {
					pidx.add(temp);
					count_parent++;
				}
			}

			System.out.println("pidx =" + pidx + "\n");
			ArrayList<Genome> arr_parent = new ArrayList<Genome>();
			for (int i = 0; i < num_parent; i++) {
				arr_parent.add(Algorithm.thisGeneration.get(pidx.get(i)));
				System.out.println("parent "
						+ i
						+ "="
						+ Algorithm.thisGeneration.get(pidx.get(i))
								.getChromosome() + "\n");
			}
			Genome child;
			Genome parent = Algorithm.thisGeneration.get(pidx.get(0));
			if (random.nextDouble() <= Algorithm.crossover_rate) {
				//if(parent1.getChromosome().size()<30) System.exit(0);
				child = parent.CrossoverMultiRandom(arr_parent);
				//child = parent.CrossoverTwoPrs_Random(arr_parent, 0.5);
				System.out.println("child =" + child.getChromosome() + "\n");
				child.Mutate(Algorithm.mutation_rate);

				nextGeneration.add(child);
			}
		}

		Algorithm.thisGeneration = nextGeneration;

		System.out.println("this generation ="
				+ Algorithm.thisGeneration.size());
		for (int i = 0; i < Algorithm.thisGeneration.size(); i++) {
			System.out.println(Algorithm.thisGeneration.get(i).getChromosome()
					+ "\n");
		}

		/*
		 * for (int i = 0; i <= Algorithm.population_size; i++) {
		 * System.out.println("i="+i+"\n"); Algorithm.thisGeneration.remove(i);
		 * Algorithm.thisGeneration.set(i, Algorithm.nextGeneration.get(i)); }
		 */
	}

	public int rouletteSelection() {
		// TODO Auto-generated method stub
		Double random = Math.random();
		int gene_i = 0;
		for (int i = 0; i < Algorithm.fitness_table.size(); i++) {
			if (random < Algorithm.fitness_table.get(i)) {
				gene_i = i;
				break;
			} else {
				continue;
			}
		}
		return gene_i;
	}

	public void initialBestTable() {	
		bestTable.clear();
		for (int i = 0; i < 50; i++ ){
			bestTable.add(i);		
		}	
		for (int j = 0; j < thisGeneration.size(); j++) {
			for (int i = j; i < thisGeneration.size(); i++) {
				if (thisGeneration.get(i).GetFitness() > thisGeneration
						.get(j).GetFitness()) {					
					bestTable.set(i,j);
					bestTable.set(j,i);
				}
			}		

		}
	}

	public int bestGeneSelection() {
		int random = Algorithm.random.nextInt(bestTable.size());
		int gene_i = 0;
		for (int i = 0; i < bestTable.size(); i++) {
			if (random <= i) {
				gene_i = bestTable.get(i);
				break;
			} else {
				continue;
			}
		}
		return gene_i;
	}
	// Long code

}

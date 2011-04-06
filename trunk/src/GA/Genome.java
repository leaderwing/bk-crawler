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
public class Genome {

	// private int m_length;
	private int size_chromosome = 0;
	private double chromosome_fitness = 0;
	private ArrayList<Double> chromosome;
	static Random random = new Random();
	private static double mutation_rate;

	public Genome() {
		// this.m_length = m_length;
		chromosome = new ArrayList<Double>();
	}

	public Genome(ArrayList<Double> chromosome) {
		this.chromosome = chromosome;

	}

	public void AddChromosome(Double m_chromosome) {
		// this.chromosome.add(m_chromosome);
	}

	public void SetSize() {
		this.size_chromosome = this.chromosome.size();
	}

	public int GetSize() {
		this.SetSize();
		return this.size_chromosome;
	}

	public double GetFitness() {
		return this.chromosome_fitness;
	}

	public void SetFitness(double chromosome_fitness) {
		this.chromosome_fitness = chromosome_fitness;
	}

	public ArrayList<Double> getChromosome() {
		return this.chromosome;
	}

	// lai ghep

	public Genome Crossover(Genome genome2) {
		Genome temp = new Genome();
		this.SetSize();

		int pos = random.nextInt(size_chromosome);
		for (int i = 0; i < this.size_chromosome; i++) {
			if (i < pos) {
				temp.chromosome.add(i, this.chromosome.get(i));
			} else {
				temp.chromosome.add(i, genome2.chromosome.get(i));
			}
		}
		return temp;
	}

	// dot bien

	public void Mutate(Double mutation_rate) {
		Double r = random.nextDouble();
		if (r < mutation_rate) {
			int pos = random.nextInt(this.chromosome.size());
			this.chromosome.set(pos, random.nextDouble());
		}
	}

	// tinh do fitness

	public void caculateFitness() {
		Integer number_term = 0;
		double ts = 0;
		double ms = 0;
		for (int i = 0; i < this.chromosome.size(); i++) {
			if (this.chromosome.get(i) > 0) {
				number_term++;
				ts = ts + this.chromosome.get(i);
				ms = ms + this.chromosome.get(i) * this.chromosome.get(i);
			}
		}
		if (ms == 0) {
			this.chromosome_fitness = 0;
		} else {
			BigDecimal d = new BigDecimal(ts / number_term
					* Math.sqrt(ms * this.chromosome.size()));
			d = d.setScale(4, BigDecimal.ROUND_HALF_UP);
			this.chromosome_fitness = d.doubleValue();
		}
	}

	/**
	 * @return the mutation_rate
	 */
	/*
	 * public static double getmutation_rate() { return mutation_rate; }
	 *//**
	 * @param amutation_rate
	 *            the mutation_rate to set
	 */
	/*
	 * public static void setMutationRate(double amutation_rate) { mutation_rate
	 * = amutation_rate; }
	 */
}

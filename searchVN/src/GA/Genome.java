/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GA;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import searchEngine.index;

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
		this.chromosome.add(m_chromosome);
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

	public Genome CrossoverMulti(ArrayList<Genome> parent) {
		Genome temp = new Genome();
		this.SetSize();
		System.out.println("size chromosome =" + size_chromosome + "\n");

		int j = 0, index = 0, t = 0;
		int step = size_chromosome / parent.size();
		t = step;
		for (int i = 0; i < parent.size() - 1; i++) {
			System.out.println("cross i=" + i + "\n");
			System.out.println("cross j=" + j + "\n");
			System.out.println("cross t=" + t + "\n");
			index = getMaxIndex(parent, j, t);
			ArrayList<Double> nst = parent.get(index).getChromosome();
			for (int k = j; k < t; k++) {
				temp.AddChromosome(nst.get(k));
				System.out.println("temp =" + temp.getChromosome() + "\n");
			}
			j = t;
			t = t + step;
		}
		System.out.println("aaa cross j=" + j + "\n");
		System.out.println("aaa cross t=" + t + "\n");
		index = getMaxIndex(parent, j, size_chromosome - 1);
		ArrayList<Double> nst = parent.get(index).getChromosome();
		for (int k = j; k < size_chromosome; k++) {
			temp.AddChromosome(nst.get(k));
			System.out.println("temp =" + temp.getChromosome() + "\n");
		}
		// System.out.println("stop");System.exit(0);
		return temp;
	}

	public Genome CrossoverMultiRandom(ArrayList<Genome> parent) {
		Genome temp = new Genome();
		this.SetSize();
		int j = 0, index = 0, t = 0, pos = 0;
		for (int i = 1; i < parent.size(); i++) {
			while (true) {
				pos = random.nextInt(size_chromosome - parent.size() + i - t);
				System.out.println("pos=" + i + "=" + pos + "\n");
				if (pos != 0)
					break;
			}
			j = t;
			t = t + pos;
			index = getMaxIndex(parent, j, t);
			System.out.println("j=" + j + "//t=" + t + "//index=" + index
					+ "\n");
			for (int k = j; k < t; k++) {
				temp.AddChromosome(parent.get(index).chromosome.get(k));
			}
		}
		index = getMaxIndex(parent, t, size_chromosome - 1);
		System.out.println("index last=" + index + "\n");
		for (int k = t; k <= size_chromosome - 1; k++) {
			temp.AddChromosome(parent.get(index).chromosome.get(k));
		}

		return temp;
	}

	// Two parents, random crossover points
	public Genome CrossoverTwoPrs_Random(ArrayList<Genome> parents, double ratio) {
		Genome temp = new Genome();
		temp.chromosome = new ArrayList<Double>(index.num_nst);
		for (int i = 0; i < index.num_nst; i++) {
			temp.chromosome.set(i, -1.0);
		}
		parents.get(0).SetSize();
		int len = (int) (size_chromosome * ratio);
		for (int i = 0; i < len; i++) {
			int pos = 0;
			do {
				pos = random.nextInt(size_chromosome);
			} while (temp.chromosome.get(pos) != -1.0); // TODO: bug
			temp.chromosome.set(pos, parents.get(0).chromosome.get(pos));
		}
		for (int i = len; i < size_chromosome; i++) {
			int pos = 0;
			do {
				pos = random.nextInt(size_chromosome);
			} while (temp.chromosome.get(pos) != -1.0); // TODO: bug
			temp.chromosome.set(pos, parents.get(1).chromosome.get(pos));
		}
		/*
		 * for (int i = 0; i < size_chromosome; i++) { if }
		 */
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
			double ms_2 = Math.sqrt(ms * this.chromosome.size());
			// double ms_3 =ms_2*number_term;
			// System.out.println("chromosome ="+ this.chromosome+"\n");
			// System.out.println(" size ="+this.chromosome.size()+"\n" );
			// System.out.println("ms="+ ms+"\n");
			// System.out.println("ms 2="+ ms_2+"\n");
			//
			// System.out.println("num term ="+ number_term+"\n");
			// System.out.println("ts="+ts+"\n");
			// System.out.println("kq="+(double)ts/ms_2);
			BigDecimal d = new BigDecimal(ts / ms_2);
			d = d.setScale(4, BigDecimal.ROUND_HALF_UP);
			this.chromosome_fitness = d.doubleValue();

		}

	}

	/**
	 * tính theo cong thuc mà best term có chứa initial_key
	 */
	public void caculateFitness_2(ArrayList<String> initial_key,
			ArrayList<String> best_term) {

		Integer number_term = 0;
		double ts = 0;
		double ms = 0;

		for (int i = 0; i < this.chromosome.size(); i++) {
			boolean is_similar = false;
			for (int j = 0; j < initial_key.size(); j++) {
				if (best_term.get(i).equals(initial_key.get(j))) {

					is_similar = true;
					break;
				}
			}
			if (is_similar) {
				number_term++;
				ts = ts + this.chromosome.get(i);
				ms = ms + this.chromosome.get(i) * this.chromosome.get(i);
			}
		}
		if (ms == 0) {
			this.chromosome_fitness = 0;
		} else {
			double ms_2 = Math.sqrt(ms * this.chromosome.size());
			// double ms_3 =ms_2*number_term;
			BigDecimal d = new BigDecimal(ts / ms_2);
			d = d.setScale(4, BigDecimal.ROUND_HALF_UP);
			this.chromosome_fitness = d.doubleValue();

		}
		System.out.println("fitness of chromosome =" + this.chromosome_fitness
				+ "\n");
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
	// Long code
	public static int getMaxIndex(ArrayList<Genome> arr_parent, int last_pos,
			int pos) {
		int index = 0;
		double max_weight = 0;
		System.out.println("size parent =" + arr_parent.size() + "\n");
		for (int i = 0; i < arr_parent.size(); i++) {
			double weight = 0;
			for (int j = last_pos; j < pos; j++) {

				weight += arr_parent.get(i).getChromosome().get(j);
			}
			if (max_weight < weight) {
				max_weight = weight;
				index = i;
			}

		}
		return index;
	}
}

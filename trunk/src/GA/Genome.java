/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GA;

import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Long
 */
public class Genome {

    //private int m_length;
    private int m_size = 0;
    private double m_fitness = 0;
    private ArrayList<Chromosome> m_chromosomes;
    static Random m_random = new Random();
    private static double m_mutationRate;

    public Genome() {
        //this.m_length = m_length;
        this.m_chromosomes = new ArrayList<Chromosome>();
    }

    public Genome(ArrayList<Chromosome> m_chromosomes) {
        this.m_chromosomes = new ArrayList<Chromosome>();
        this.m_chromosomes = m_chromosomes;
    }

    public void AddChromosome(Chromosome m_chromosome) {
        this.m_chromosomes.add(m_chromosome);
    }

    public void SetSize() {
        this.m_size = this.m_chromosomes.size();
    }

    public int GetSize() {
        this.SetSize();
        return this.m_size;
    }

    public ArrayList GetChromosome(int i) {
        return this.m_chromosomes.get(i).GetChromosome();
    }

    public double GetFitness() {
        return this.m_fitness;
    }

    public void SetFitness(double m_fitness) {
        this.m_fitness = m_fitness;
    }
    //lai ghep

    public Genome Crossover(Genome genome2) {
        Genome temp = new Genome();
        this.SetSize();
        int pos = m_random.nextInt(m_size);
        for (int i = 0; i < this.m_size; i++) {
            if (i < pos) {
                temp.m_chromosomes.add(i, this.m_chromosomes.get(i));
            } else {
                temp.m_chromosomes.add(i, genome2.m_chromosomes.get(i));
            }
        }
        return temp;
    }
    //dot bien

    public void Mutate() {
       
        }
    
    //tinh do fitness

    public void CaculateFitness() {
    	
        
    }

    /**
     * @return the m_mutationRate
     */
    public static double getM_mutationRate() {
        return m_mutationRate;
    }

    /**
     * @param aM_mutationRate the m_mutationRate to set
     */
    public static void setMutationRate(double aM_mutationRate) {
        m_mutationRate = aM_mutationRate;
    }

    public ArrayList<Chromosome> getM_chromosomes() {
        return m_chromosomes;
    }

    public void setM_chromosomes(ArrayList<Chromosome> m_chromosomes) {
        this.m_chromosomes = m_chromosomes;
    }

}

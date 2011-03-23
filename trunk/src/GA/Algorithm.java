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
public class Algorithm {

    private static double m_mutationRate; //phan tram dot bien
    private static double m_crossoverRate; //phan tram lai ghep
    private static int m_populationSize; //kich co quan the ban dau
    private static int m_generationSize; //so quan the sinh ra
    private static int m_genomeSize; //kich co bo gen
    private static double m_totalFitness; //do fitness cua quan the moi
    private static boolean m_elitism;// chon loc
    private static Genome m_bestGen;
    private static ArrayList<Genome> firstGeneration; //quan the ban dau
    private static ArrayList<Genome> thisGeneration; //quan the hien tai
    private static ArrayList<Genome> nextGeneration; //quan the moi
    private static ArrayList fitnessTable; //bang thich nghi
    public static Random random = new Random();

    Algorithm () {
        this.InitialValues();
        Algorithm.m_mutationRate = 0.1;
        Algorithm.m_crossoverRate = 0.80;
        Algorithm.m_populationSize = 100;
        Algorithm.m_generationSize = 2000;
    }

    Algorithm (int populationSize, int genomeSize, double m_mutationRate, double m_crossoverRate) {
        this.InitialValues();
        Algorithm.m_populationSize = populationSize;
        Algorithm.m_genomeSize = genomeSize;
        Algorithm.m_crossoverRate = m_crossoverRate;
        Algorithm.m_mutationRate = m_mutationRate;
    }

    public void InitialValues() {
    	Algorithm.m_elitism = false;
    }

    private void InitFitnessTable() {
    	Algorithm.m_totalFitness = 0;
    	Algorithm.fitnessTable.clear();
        for (int i = 0; i <= Algorithm.m_populationSize; i++) {
            Genome temp = (Genome) Algorithm.thisGeneration.get(i);
            temp.CaculateFitness();
            Algorithm.m_totalFitness += temp.GetFitness();
            Algorithm.fitnessTable.add(Algorithm.m_totalFitness);
        }
    }

    private int RouletteSelection() {
        double randomFitness = random.nextDouble() * Algorithm.m_totalFitness;
        int index = -1;
        int mid;
        int first = 0;
        int last = Algorithm.m_populationSize;
        mid = (last - first) / 2;
        while (index == -1 && first <= last) {
            if (randomFitness < (Double) Algorithm.fitnessTable.get(mid)) {
                last = mid;
            } else {
                if (randomFitness > (Double) Algorithm.fitnessTable.get(mid)) {
                    first = mid;
                }
            }
            mid = (first + last) / 2;
            if ((last - first) == 1) {
                index = last;
            }
        }

        return index;
    }

    private void RankPopulation() {
    	Algorithm.m_totalFitness = 0;
    	Algorithm.fitnessTable.clear();
        for (int i = 0;i <= Algorithm.m_populationSize; i++) {
            Genome temp = (Genome) Algorithm.thisGeneration.get(i);
            Algorithm.m_totalFitness += temp.GetFitness();
            Algorithm.fitnessTable.add((Double) Algorithm.m_totalFitness);
        }
    }

    public void Go() {
        double maxFitness = 0;
        int maxIndex = 0;
        Algorithm.fitnessTable = new ArrayList();
        Algorithm.thisGeneration = new ArrayList();
        Algorithm.nextGeneration = new ArrayList(Algorithm.m_generationSize);
        Genome.setMutationRate(Algorithm.m_mutationRate);
        Algorithm.m_bestGen = new Genome();
        this.CreateFirstGeneration(); //ham bo qua
        this.InitFitnessTable();
        for (int i = 0;i <= Algorithm.m_generationSize; i++) {
            this.CreateNextGeneration();
            for (int j = i;j <= Algorithm.m_populationSize; j++) {
                Genome gen = (Genome) Algorithm.thisGeneration.get(j);
                gen.CaculateFitness();
                if (gen.GetFitness() > maxFitness) {
                    maxIndex = j;
                    maxFitness = gen.GetFitness();
                    Algorithm.m_bestGen = gen;
                }
            }
            this.RankPopulation();
            Algorithm.m_bestGen=Algorithm.thisGeneration.get(Algorithm.m_populationSize);
        }
    }

    public void CreateFirstGeneration() {
        
    }
    public void CreateNextGeneration() {
    	Algorithm.nextGeneration.clear();
        Genome gen = new Genome();
        if (Algorithm.m_elitism) {
            gen = (Genome) Algorithm.thisGeneration.get(Algorithm.m_populationSize);
        }
        for (int i = 0;i <= Algorithm.m_populationSize; i += 2) {
            int pidx1 = 0;
            int pidx2 = 0;
            pidx1 = this.RouletteSelection();
            while (true) {
                pidx2 = this.RouletteSelection();
                if (pidx1 != pidx2) {
                    break;
                }
            }
            Genome parent1, parent2, child1, child2;
            parent1 = (Genome) Algorithm.thisGeneration.get(pidx1);
            parent2 = (Genome) Algorithm.thisGeneration.get(pidx2);
            if (random.nextDouble() <= Algorithm.m_crossoverRate) { // xem lai random trong khoang 0-1
                child1 = parent1.Crossover(parent2);
                child2 = parent2.Crossover(parent1);
            } else {
                child1 = parent1;
                child2 = parent2;
            }
            child1.Mutate();
            child2.Mutate();
            Algorithm.nextGeneration.add(child1);
            Algorithm.nextGeneration.add(child2);
        }
     if (Algorithm.m_elitism && gen.GetSize() != 0) { // xem lai elitism
    	 Algorithm.nextGeneration.set(0, gen);
        }
     	Algorithm.thisGeneration.clear();
        for (int i = 0;i <= Algorithm.m_populationSize; i++) {
        	Algorithm.thisGeneration.set(i, Algorithm.nextGeneration.get(i));
        }
    }
}

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
public class GA {

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

    public GA() {
        this.InitialValues();
        GA.m_mutationRate = 0.1;
        GA.m_crossoverRate = 0.80;
        GA.m_populationSize = 100;
        GA.m_generationSize = 2000;
    }

    public GA(int populationSize, int genomeSize, double m_mutationRate, double m_crossoverRate) {
        this.InitialValues();
        GA.m_populationSize = populationSize;
        GA.m_genomeSize = genomeSize;
        GA.m_crossoverRate = m_crossoverRate;
        GA.m_mutationRate = m_mutationRate;
    }

    public void InitialValues() {
        GA.m_elitism = false;
    }

    private void InitFitnessTable() {
        GA.m_totalFitness = 0;
        GA.fitnessTable.clear();
        for (int i = 0; i <= GA.m_populationSize; i++) {
            Genome temp = (Genome) GA.thisGeneration.get(i);
            temp.CaculateFitness();
            GA.m_totalFitness += temp.GetFitness();
            GA.fitnessTable.add(GA.m_totalFitness);
        }
    }

    private int RouletteSelection() {
        double randomFitness = random.nextDouble() * GA.m_totalFitness;
        int index = -1;
        int mid;
        int first = 0;
        int last = GA.m_populationSize;
        mid = (last - first) / 2;
        while (index == -1 && first <= last) {
            if (randomFitness < (Double) GA.fitnessTable.get(mid)) {
                last = mid;
            } else {
                if (randomFitness > (Double) GA.fitnessTable.get(mid)) {
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
        GA.m_totalFitness = 0;
        GA.fitnessTable.clear();
        for (int i = 0; i <= GA.m_populationSize; i++) {
            Genome temp = ((Genome) GA.thisGeneration.get(i));
            GA.m_totalFitness += temp.GetFitness();
            GA.fitnessTable.add((Double) GA.m_totalFitness);
        }
    }

    public void Go() {
        double maxFitness = 0;
        int maxIndex = 0;
        GA.fitnessTable = new ArrayList();
        GA.thisGeneration = new ArrayList();
        GA.nextGeneration = new ArrayList(GA.m_generationSize);
        Genome.setMutationRate(GA.m_mutationRate);
        GA.m_bestGen = new Genome();
        this.CreateFirstGeneration(); //ham bo qua
        this.InitFitnessTable();
        for (int i = 0; i <= GA.m_generationSize; i++) {
            this.CreateNextGeneration();
            for (int j = i; j <= GA.m_populationSize; j++) {
                Genome gen = (Genome) GA.thisGeneration.get(j);
                gen.CaculateFitness();
                if (gen.GetFitness() > maxFitness) {
                    maxIndex = j;
                    maxFitness = gen.GetFitness();
                    GA.m_bestGen = gen;
                }
            }
            this.RankPopulation();
            GA.m_bestGen=GA.thisGeneration.get(GA.m_populationSize);
        }
    }

    public void CreateFirstGeneration() {
        
    }
    public void CreateNextGeneration() {
        GA.nextGeneration.clear();
        Genome gen = new Genome();
        if (GA.m_elitism) {
            gen = (Genome) GA.thisGeneration.get(GA.m_populationSize);
        }
        for (int i = 0; i <= GA.m_populationSize; i += 2) {
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
            parent1 = (Genome) GA.thisGeneration.get(pidx1);
            parent2 = (Genome) GA.thisGeneration.get(pidx2);
            if (random.nextDouble() <= GA.m_crossoverRate) { // xem lai random trong khoang 0-1
                child1 = parent1.Crossover(parent2);
                child2 = parent2.Crossover(parent1);
            } else {
                child1 = parent1;
                child2 = parent2;
            }
            child1.Mutate();
            child2.Mutate();
            GA.nextGeneration.add(child1);
            GA.nextGeneration.add(child2);
        }
        if (GA.m_elitism && gen.GetSize() != 0) { // xem lai elitism
            GA.nextGeneration.set(0, gen);
        }
        GA.thisGeneration.clear();
        for (int i = 0; i <= GA.m_populationSize; i++) {
            GA.thisGeneration.set(i, GA.nextGeneration.get(i));
        }
    }
}

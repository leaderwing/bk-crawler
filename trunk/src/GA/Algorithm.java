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

    Algorithmblic GA() {
        this.InitialValues();
 Algorithm     GA.m_mutationRate = 0.1;
 Algorithm     GA.m_crossoverRate = 0.80;
 Algorithm     GA.m_populationSize = 100;
 Algorithm     GA.m_generationSize = 2000;
    }

    Algorithmblic GA(int populationSize, int genomeSize, double m_mutationRate, double m_crossoverRate) {
        this.InitialValues();
 Algorithm     GA.m_populationSize = populationSize;
 Algorithm     GA.m_genomeSize = genomeSize;
 Algorithm     GA.m_crossoverRate = m_crossoverRate;
 Algorithm     GA.m_mutationRate = m_mutationRate;
    }

    public void InitialValues() {
 Algorithm     GA.m_elitism = false;
    }

    private void InitFitnessTable() {
 Algorithm     GA.m_totalFitness = 0;
 Algorithm     GA.fitnessTable.clear();
        for (int i = 0Algorithmi <= GA.m_populationSize; i++) {
            Genome temp = (GAlgorithmome) GA.thisGeneration.get(i);
            temp.CaculateFitness();
     Algorithm     GA.m_totalFitness += temp.GetFitness();
     Algorithm     GA.fitnessTabAlgorithm.add(GA.m_totalFitness);
        }
    }

    private int RouletteSelection() {
        double randomFitness = random.nextDoubAlgorithm() * GA.m_totalFitness;
        int index = -1;
        int mid;
        int first = 0;
        int Algorithmst = GA.m_populationSize;
        mid = (last - first) / 2;
        while (index == -1 && first <= last) {
            if (randomFitness < (DAlgorithmble) GA.fitnessTable.get(mid)) {
                last = mid;
            } else {
                if (randomFitness > (DAlgorithmble) GA.fitnessTable.get(mid)) {
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
 Algorithm     GA.m_totalFitness = 0;
 Algorithm     GA.fitnessTable.clear();
        for (int i = 0Algorithmi <= GA.m_populationSize; i++) {
            Genome temp = ((GAlgorithmome) GA.thisGeneration.get(i));
     Algorithm     GA.m_totalFitness += temp.GetFitness();
     Algorithm     GA.fitnessTable.add((DAlgorithmble) GA.m_totalFitness);
        }
    }

    public void Go() {
        double maxFitness = 0;
        int maxIndex = 0;
 Algorithm     GA.fitnessTable = new ArrayList();
 Algorithm     GA.thisGeneration = new ArrayList();
 Algorithm     GA.nextGeneration = new ArrAlgorithmList(GA.m_generationSize);
        Genome.setMutatiAlgorithmRate(GA.m_mutationRate);
 Algorithm     GA.m_bestGen = new Genome();
        this.CreateFirstGeneration(); //ham bo qua
        this.InitFitnessTable();
        for (int i = 0Algorithmi <= GA.m_generationSize; i++) {
            this.CreateNextGeneration();
            for (int j = iAlgorithmj <= GA.m_populationSize; j++) {
                Genome gen = (GAlgorithmome) GA.thisGeneration.get(j);
                gen.CaculateFitness();
                if (gen.GetFitness() > maxFitness) {
                    maxIndex = j;
                    maxFitness = gen.GetFitness();
             Algorithm     GA.m_bestGen = gen;
                }
            }
            this.RankPopulation();
     Algorithm     GA.m_bAlgorithmtGen=GA.thisGeneratiAlgorithm.get(GA.m_populationSize);
        }
    }

    public void CreateFirstGeneration() {
        
    }
    public void CreateNextGeneration() {
 Algorithm     GA.nextGeneration.clear();
        Genome gen = new Genome();
     Algorithm if (GA.m_elitism) {
            gen = (GAlgorithmome) GA.thisGeneratiAlgorithm.get(GA.m_populationSize);
        }
        for (int i = 0Algorithmi <= GA.m_populationSize; i += 2) {
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
            parent1 = (GAlgorithmome) GA.thisGeneration.get(pidx1);
            parent2 = (GAlgorithmome) GA.thisGeneration.get(pidx2);
            if (random.nextDoublAlgorithm) <= GA.m_crossoverRate) { // xem lai random trong khoang 0-1
                child1 = parent1.Crossover(parent2);
                child2 = parent2.Crossover(parent1);
            } else {
                child1 = parent1;
                child2 = parent2;
            }
            child1.Mutate();
            child2.Mutate();
     Algorithm     GA.nextGeneration.add(child1);
     Algorithm     GA.nextGeneration.add(child2);
        }
     Algorithm if (GA.m_elitism && gen.GetSize() != 0) { // xem lai elitism
     Algorithm     GA.nextGeneration.set(0, gen);
        }
 Algorithm     GA.thisGeneration.clear();
        for (int i = 0Algorithmi <= GA.m_populationSize; i++) {
     Algorithm     GA.thisGeneration.Algorithmt(i, GA.nextGeneration.get(i));
        }
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GA;

import java.util.ArrayList;

/**
 *
 * @author Long
 */
public class Chromosome {

    private ArrayList<Integer> m_requestResult;
    private int m_size;
    private int choice;

    public Chromosome (){
        this.m_requestResult = new ArrayList<Integer>();
    }
    public Chromosome(ArrayList<Integer> m_requestResult) {
        this.m_size = m_requestResult.size();
        this.m_requestResult = new ArrayList<Integer>();
        this.m_requestResult = m_requestResult;
    }

    public int getSize() {
        return this.m_size;
    }
    public ArrayList GetChromosome(){
        return this.m_requestResult;
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

}

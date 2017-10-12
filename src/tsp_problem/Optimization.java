/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp_problem;


import java.util.ArrayList;


/**
 *
 * @author XinZhou
 */
public class Optimization {
    
    private ArrayList<BestFirst> solutions = new ArrayList<BestFirst>();
    private int result = 0;
    public int New_MEB = 0;
    
    public void optimizationForSimulatedAnnealing(Solution path, int MEB){

        //System.out.println(path.getCities());
        int difference = 1; // If there is no optimal solution
        New_MEB = MEB;
        ArrayList<Integer> a = new ArrayList<>();
        //ArrayList<Integer> tempSolution = new ArrayList<>();
        a = path.getCities();
        BestFirst b = new BestFirst();
        System.out.println("Cost: " + b.caculateTotalCost(a));
        boolean changed = true; 
        while(changed){ //swap neighbor cities to find better solution if there exists
            
            changed = false;
            int oldCost = 0, newCost = 0;
         
            for(int i = 1; i < a.size() - 2; i++){
                
                oldCost =  b.costFunction(a.get(i-1), a.get(i)) + b.costFunction(a.get(i), a.get(i+1)) 
                        + b.costFunction(a.get(i+1), a.get(i+2));
                int temp = a.get(i);
                a.set(i, a.get(i+1));
                a.set(i+1, temp);
                newCost =  b.costFunction(a.get(i-1), a.get(i)) + b.costFunction(a.get(i), a.get(i+1)) 
                        + b.costFunction(a.get(i+1), a.get(i+2));
                difference = oldCost - newCost;
                //System.out.println("oldCost: " + oldCost);
                //System.out.println("newCost: " + newCost);
                //System.out.println("Solution: " + difference);
                if(difference > 0){
                    System.out.println("Solution: " + difference);
                    System.out.println(a.get(i) + " AND "+ a.get(i + 1)+ " Swapped ");
                    changed = true;
                }  
                else{ // change back
                    temp = a.get(i);
                    a.set(i, a.get(i+1));
                    a.set(i+1, temp);
                }
                difference = 0;
                New_MEB++;
            }  
            
        }
        System.out.println("Solution: " + a);
        System.out.println("Cost: " + b.caculateTotalCost(a));
        System.out.println("MEB: " +  New_MEB);
    }
    
    public void optimizationForBestFirst(){
        int optimizationSize = 3;
        int currentSolution = 0, bestSolution = 0;
        for(int i = 0; i < optimizationSize ; i++){
            BestFirst a = new BestFirst();
            a.getSolution();
            solutions.add(a);
            //System.out.println("get: " + solutions.get(i).getTraveledCities());
            //System.out.println("get: " + solutions.get(i).caculateTotalCost());
            a = null;
        }     
        bestSolution = solutions.get(0).caculateTotalCost();
        for(int i = 1; i < optimizationSize; i++){
            currentSolution = solutions.get(i).caculateTotalCost();
            if(currentSolution < bestSolution){
                bestSolution = currentSolution;
                result = i;
            }
        }
    }
    public void printBestFirstOptimization(){
        System.out.println("Result: " + solutions.get(result).getTraveledCities());
        System.out.println("MEB: " + solutions.get(result).MEB);
        System.out.println("Total Cost: " + solutions.get(result).caculateTotalCost()); 
    }
    public int bestFirstOptimizedCost(){
        return solutions.get(result).caculateTotalCost();
    }
    
    
    
}

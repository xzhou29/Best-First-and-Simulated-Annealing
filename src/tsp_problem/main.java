/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp_problem;
import java.util.ArrayList;
import java.util.Scanner;
import static tsp_problem.SimulatedAnnealing.coolingRate;
import static tsp_problem.SimulatedAnnealing.intialTemperature;
/**
 *
 * @author XinZhou
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static int numberOfCities = 10;
    public static int costFunctionType = 1;
    public static int searchStrategy = 0;
    private static ArrayList<Integer> intialCities = new ArrayList<Integer>();
    
    public static void main(String[] args) throws Exception  {
 
        input();
  
        if(searchStrategy == 1){ // Best First
            BestFirst  a = new BestFirst();
            Optimization b = new Optimization();
            a.getSolution();
            b.optimizationForBestFirst();
            
            resultPrint(null);
            System.out.println("-------------------------------- Before Optimization --------------------------------" );
            a.printResult();
            if(b.bestFirstOptimizedCost() > a.caculateTotalCost()){
                System.out.println("-------------------------------- After Optimization --------------------------------"); 
                a.printResult();
            }
            else{
                System.out.println("-------------------------------- After Optimization --------------------------------"); 
                b.printBestFirstOptimization();
            }       
        } 
        else{ //Simulated Annealing
            storeCities();
            main solution = new main();
            System.out.println(solution.intialCities); 
            Solution path = new Solution(solution.intialCities);
            SimulatedAnnealing a = new SimulatedAnnealing();
            path = a.findBetterPath(SimulatedAnnealing.intialTemperature, path);
            System.out.println("-------------------------------- Before Optimization --------------------------------" );
            resultPrint(path);
            System.out.println("MEB: " + a.MEB); 
            
            System.out.println("-------------------------------- After Optimization --------------------------------");
            Optimization b = new Optimization();          
            b.optimizationForSimulatedAnnealing(path, a.MEB);
            
            
        }    
        
    }

    public static void resultPrint(Solution path){
        if (searchStrategy == 1){
            System.out.println("----------------------------------------------  Result ---------------------------------------------- " );  
            System.out.println("Search Strategy: Best First Search. " + " --- Cost Function : C" + costFunctionType);  
        }
        else{
            System.out.println("----------------------------------------------  Result ----------------------------------------------  " ); 
            System.out.println("Search Strategy: Simulated Annealing. --- Intial Temperature: " + intialTemperature + " --- Cooling Rate: " + coolingRate + " --- Cost Function: C" + costFunctionType);
            System.out.println("Solution: " + path.getCities()); 
            System.out.println("Cost: " + path.caculateTotalCost()); 
            
        }   
    }
    public static void storeCities()  {
      for(int i = 0; i < numberOfCities; i++){
            intialCities.add(i);
        } // store all intial cities into ArrayList
    } 
        public static void input(){
        Scanner sc = new Scanner(System.in);
        int input;
        do {
            System.out.println("Please enter a integer to choose a search strategy (1): Best-First. (2): Simulated Annealing :");
            while (!sc.hasNextInt()) {
                System.out.println("This is not a number!!! Please enter (1): Best-First. (2): Simulated Annealing: ");
                sc.next(); // this is important!
            }
            input = sc.nextInt();
        } while (input < 1 || input > 2);
        searchStrategy = input;
        input = 0;
        do {
            System.out.println("Please enter a integer to choose a cost function type (1) or (2) or (3): ");
            while (!sc.hasNextInt()) {
                System.out.println("This is not a number!!! Please enter a integer to choose a cost function type (1) or (2) or (3): ");
                sc.next(); // this is important!
            }
            input = sc.nextInt();
        } while (input < 1 || input > 3);
        costFunctionType = input;
        input = 0; 
           do {
            System.out.println("Please enter number of cities ( greater than 1 ): ");
            while (!sc.hasNextInt()) {
                System.out.println("This is not a number!!! Please enter number of cities ( greater than 1 ): ");
                sc.next(); // this is important!
            }
            input = sc.nextInt();
        } while (input < 2 || input > 1000);
        numberOfCities = input;
        input = 0; 
    }
}

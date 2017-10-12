/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp_problem;
/**
 *
 * @author XinZhou
 */
public class SimulatedAnnealing {
    public static final double coolingRate = 0.001;
    public static final double intialTemperature = 1000;
    public static final double minTemperature = 1.0;  
    public static int MEB = 0;
    public Solution findBetterPath(double temperature, Solution currentSolution){
        Solution bestSolution = new Solution(currentSolution);
        Solution newSolution;        
        int count = 0;
        while(temperature > minTemperature){
            count++;
            newSolution = randomllySwap(new Solution(currentSolution));
            count++;
            if(currentSolution.caculateTotalCost() < bestSolution.caculateTotalCost()){
                bestSolution =  new Solution(currentSolution);
            }
            if(acceptanceProbability(currentSolution.caculateTotalCost(), newSolution.caculateTotalCost(), temperature) ){
                currentSolution =  new Solution(newSolution);       
            }
            System.out.println("Current Temperature : " + temperature);
            System.out.println("Shortest solution cost : " + bestSolution.getCities() );
            System.out.println("Shortest solution cost : " + bestSolution.caculateTotalCost() + " count: "+ count );
            temperature *= 1 - coolingRate;
        }
        MEB = count;
        return bestSolution;
    }
    
    public boolean acceptanceProbability(int currentDistance, int adjacentDistance, double temperature){
        String decision = null;
        double acceptProb = 1.0;
        boolean shorterDistance = true;
        boolean acceptLag = false;
        if(adjacentDistance >= currentDistance){
            //Probability = Exponential ((SolutionEnergy - neighbourenergy) / temperature )
            acceptProb = Math.exp((currentDistance - adjacentDistance) / temperature);
            //r = (max - min)
            shorterDistance = false;
        }
        double r = Math.random();
        if(acceptProb >= r){
            acceptLag = true;
        }
        if(shorterDistance) {
           
            System.out.println("A better solution found!!!");
        }
        else if(acceptLag){
            System.out.println("Acceptance Probility > Random 'r'. ( " + acceptProb + " > " + r + " ).");
        }
        else{
            System.out.println("Stay...");
        }

     
        return acceptLag;
    } 
    
    private Solution randomllySwap(Solution route){
        int x1 = 0, x2 = 0;
        while(x1 == x2){
            x1 = (int)(route.getCities().size() * Math.random());
            x2 = (int)(route.getCities().size() * Math.random());
        } 
        int temp = 0;
        temp = route.getCities().get(x1);

        route.getCities().set(x1, route.getCities().get(x2));
        route.getCities().set(x2, temp);
        return route;
    }
    
}

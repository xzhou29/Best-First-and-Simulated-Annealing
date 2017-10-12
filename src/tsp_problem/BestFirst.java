/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp_problem;

import java.util.Random;
import java.util.ArrayList;
import static tsp_problem.main.costFunctionType;
import static tsp_problem.main.numberOfCities;
/**
 *
 * @author XinZhou
 */
public class BestFirst {

    public  ArrayList<Integer> traveledCities = new ArrayList<Integer>();
    public int MEB = 0;
    
    public void getSolution(){
        ArrayList<Integer> remainingCities = new ArrayList<Integer>();

        int startCity = getRandomRoot(numberOfCities);
        int currentCity = 0;
        int nextCity = 0;
        
        traveledCities.add(startCity);
        
        for(int i = 0; i < numberOfCities; i++){
            if(i == startCity) continue;
            remainingCities.add(i);
        }
        System.out.println(remainingCities);   
        currentCity = startCity;
        MEB++;
        while(!remainingCities.isEmpty()){
            int bestCost = 0;
            int currentCost = 0;
            nextCity = remainingCities.get(0);
            bestCost = costFunction(currentCity, remainingCities.get(0));
            MEB++;
            //System.out.println("MEB: " + MEB);
            for(int i = 1; i < remainingCities.size()- 1; i++){
                currentCost = costFunction(currentCity, remainingCities.get(i));
                MEB++;
                if(currentCost < bestCost){
                    bestCost = currentCost;
                    nextCity = remainingCities.get(i);
                }
            }
            currentCity = nextCity;
            traveledCities.add(currentCity);
            //remainingCities.remove(Integer.valueOf(nextCity));
            remainingCities.remove(Integer.valueOf(currentCity));
            System.out.println("Remaining Cities: " + remainingCities);
            System.out.println("Traveled Cities: " + traveledCities);
        }
//        printResult();
     }
    public ArrayList<Integer> getTraveledCities() {
        return traveledCities;
    }
    
    public void printResult(){
        System.out.println("Result: " + traveledCities);
        System.out.println("MEB: " + MEB);
        System.out.println("caculateTotalCost(): " + caculateTotalCost()); 
    }
     
    
     public int caculateTotalCost(){
        int totalCost = 0;
        int size = traveledCities.size();
        for(int i = 0; i < size - 2; i++ ){

            totalCost += costFunction(traveledCities.get(i), traveledCities.get( i + 1));   
            //System.out.println("totalCost: " + totalCost);
        }
        totalCost += costFunction(traveledCities.get(size - 1), traveledCities.get(0)); // from last city to first city
        
       
        return totalCost;
     }
     
    public int caculateTotalCost(ArrayList<Integer> a){
        int totalCost = 0;
        int size = a.size();
        for(int i = 0; i < size - 1; i++ ){

            totalCost += costFunction(a.get(i), a.get( i + 1));   
            //System.out.println("totalCost: " + totalCost);
        }
        totalCost += costFunction(a.get(size - 1), a.get(0)); // from last city to first city
        
       
        return totalCost;
     }
     
     public int getRandomRoot(int numberOfCities){
        int min = 0, max = numberOfCities;
        Random n = new Random();
        int  randomRoot = n.nextInt(numberOfCities) + min;
        System.out.println("Random Root: " + randomRoot ) ;
        return randomRoot;
     }
     public int costFunction(int x, int y){
        switch (costFunctionType) {
            case 1: //cost function 1
                if(x == y){
                    return 0;
                }
                else if (x < 3 && y < 3){
                    return 1;
                }
                else if (x < 3){
                    return 200;
                }
                else if (y < 3){
                    return 200;
                }
                else if ( x % 7 == y % 7){
                    return 2;
                }
                return Math.abs(x + y) + 3;
                
            case 2: //cost function 2
                if(x == y){
                    return 0;
                }
                else if( (x + y) < 10){
                    return Math.abs(x - y) + 4;
                }
                else if ( (x + y) % 11 == 0){
                    return 3;
                }
                return (x - y) * (x - y) + 10;
                
            default:
                return (x + y) * (x + y); //cost function 3
        }
     }
}

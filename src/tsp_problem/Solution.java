/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp_problem;
import java.util.ArrayList;
import java.util.Collections;
import static tsp_problem.main.costFunctionType;

/**
 *
 * @author XinZhou
 */
public class Solution {

    
    private ArrayList<Integer> cities = new ArrayList<Integer>();
    private int costType = 1;
    
    public Solution(ArrayList<Integer> cities) {
        this.cities.addAll(cities);
        Collections.shuffle(this.cities);
        //this.costType = costFunctionType;
        //System.out.println("costFunctionType: " + costFunctionType);
    }
    
    public Solution(Solution route) {
        this.cities.addAll(route.cities);
        //this.costType = costFunctionType;
    }
    public ArrayList<Integer> getCities(){return cities;}
    
    public int caculateTotalCost(){
        int totalCost = 0;
        int size = this.cities.size();
        for(int i = 0; i < size - 1; i++ ){
            totalCost += costFunction(this.cities.get(i), this.cities.get( i+ 1)); 
        }
        totalCost += costFunction(this.cities.get(size - 1), this.cities.get(0)); // from last city to first city
        
        //System.out.println("totalCost: " + totalCost);
        return totalCost;
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
                return (x + y)*(x + y); //cost function 3
        }
    }  

}

package ga;

import java.util.ArrayList;
import java.util.Collections;

// represents individual in population
public class Tour {
    private ArrayList Cities = new ArrayList<City>();
    private double fitness = 0;
    private double distance = 0;

    public Tour(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            Cities.add(null);
        }
    }

    public Tour(ArrayList tour){
        Cities = tour;
    }

    public void genIndividual(){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            setCity(i, TourManager.getCity(i));
        }
        Collections.shuffle(Cities);
    }

    public void setCity(int index, City city){
        Cities.set(index, city);
        fitness = 0;
        distance = 0;
    }

    public City getCity(int index){
        return (City)Cities.get(index);
    }

    public double CalcDistance(){
        if( distance == 0) {
            double tourDist = 0;
            for (int i = 0; i < size(); i++) {
                City fromCity = (City) Cities.get(i);
                City destCity;
                if( i + 1 < size()){
                    destCity = getCity(i+1);
                }
                else{
                    destCity = getCity(0);
                }
                tourDist += fromCity.distanceTo(destCity);
            }
            distance = tourDist;
        }
        return distance;
    }

    public double getFitness(){
        if( fitness == 0){
            fitness = 1/CalcDistance();
        }
        return fitness;
    }

    public int size(){
        return Cities.size();
    }

    @Override
    public String toString(){
        String result = "";
        for (int i = 0; i < size(); i++) {
            result += " || ";
            result += this.getCity(i);
            result += " || ";
        }
        return result;
    }

}

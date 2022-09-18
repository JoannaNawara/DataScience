package si;

import java.util.ArrayList;

public class TourManager {
    public static ArrayList destinationCities = new ArrayList<City>();
    public static double[][] pheromone_trails;
    public static double[][] distance;

    public static void addCity(City city){
        destinationCities.add(city);
    }

    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    public static int numberOfCities(){
        return destinationCities.size();
    }

    //new
    //initialise after adding all cities
    public static void setPheromones(){
        int size = numberOfCities();
        pheromone_trails = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if( i == j ) { pheromone_trails[i][j] = 0; }
                else { pheromone_trails[i][j] = 1; }
            }
        }

    }

    public static void setDistance(){
        int size = numberOfCities();
        distance = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if( i == j ){
                    distance[i][j] = 0;
                }
                else{
                    City firstCity = (City) destinationCities.get(i);
                    City secondCity = (City) destinationCities.get(j);
                    distance[i][j] = firstCity.distanceTo(secondCity);
                }
            }
        }
    }

    public static void printPheromones(){
        for (int i = 0; i < numberOfCities(); i++) {
            String line = "";
            for (int j = 0; j < numberOfCities(); j++) {
                line += pheromone_trails[i][j];
                line += " ";
            }
            System.out.println(line);
        }
    }

    public static void printDistance(){
        for (int i = 0; i < numberOfCities(); i++) {
            String line = "";
            for (int j = 0; j < numberOfCities(); j++) {
                line += distance[i][j];
                line += " ";
            }
            System.out.println(line);
        }
    }



}

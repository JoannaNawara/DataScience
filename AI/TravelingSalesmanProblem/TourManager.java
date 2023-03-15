package ga;

import java.util.ArrayList;

//stores Cities to visit
public class TourManager {
    private static ArrayList destinationCities = new ArrayList<City>();

    public static void addCity(City city){
        destinationCities.add(city);
    }

    public static City getCity(int index){
        return (City)destinationCities.get(index);
    }

    public static int numberOfCities(){
        return destinationCities.size();
    }
}

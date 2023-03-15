package si;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static si.TourManager.destinationCities;
import static si.TourManager.distance;
import static si.TourManager.numberOfCities;

public class Ant {
    ArrayList<Integer> visited_attractions = new ArrayList<Integer>(); //list of visited attractions
    ArrayList<Integer> possible_attractions = new ArrayList<Integer>();
    ArrayList<Integer> all_attractions = new ArrayList<Integer>();
    double BestDistance;

    private ArrayList possible_indexes = new ArrayList<>();
    private double[] possible_probabilities;
    private double total_probabilities;


    Ant(int attraction_count){
        setAllAttractions();
        // create array for ant's path
        visited_attractions = new ArrayList<>();
        // select beginning of the path
        int random_city = (int) (Math.random() * attraction_count);
        visited_attractions.add(random_city);

        // set all possible attractions (cities)
        for (int i = 0; i < attraction_count; i++) {
            if( i != visited_attractions.get(0))
                possible_attractions.add(i);
        }
    }

    public double get_distance_travelled(){
        double total_distance = 0;
        for (int i = 0; i < visited_attractions.size(); i++) {
            int index = visited_attractions.get(i);
            if( index + 1 < visited_attractions.size()){
                City actualCity = ((City)destinationCities.get(index));
                City nextCity = ((City)destinationCities.get(index + 1));
                total_distance += actualCity.distanceTo(nextCity);
            } else{
                City actualCity = ((City)destinationCities.get(index));
                City nextCity = ((City)destinationCities.get(0));
                total_distance += actualCity.distanceTo(nextCity);
            }
        }
        BestDistance = total_distance;
        return total_distance;
    }

    // probabilistic choice
    public void visit_probabilistic_attraction(double[][] pheromone_trails, int attraction_count, int alpha, int beta){
        int current_attraction = this.visited_attractions.get(visited_attractions.size()-1);
        updatePossible_attractions();

        possible_indexes = new ArrayList();
        possible_probabilities = new double[possible_attractions.size()];
        total_probabilities = 0.0;


        int index = 0;
        for (int attraction : possible_attractions){
            possible_indexes.add(attraction);
            double pheromones_on_path = Math.pow(pheromone_trails[current_attraction][attraction], alpha);
            double heuristic_for_path = Math.pow(1.0/(distance[current_attraction][attraction]), beta);
            double probability = pheromones_on_path * heuristic_for_path;
            possible_probabilities[index] = probability;
            total_probabilities += probability;
            index++;
        }

        for (int i = 0; i < possible_probabilities.length; i++) {
            possible_probabilities[i] = possible_probabilities[i]/total_probabilities;
        }

    }

    // returns next chosen city: index
    public int roulette_wheel_selection(){
        ArrayList<double[]> slices = new ArrayList();
        double total = 0;
        for (int i = 0; i < possible_attractions.size(); i++) {
            double[] slice = new double[3];
            slice[0] = (int)possible_indexes.get(i);
            slice[1] = total;
            total = total+possible_probabilities[i];
            slice[2] = total;
            slices.add(slice);
        }
        double spin = Math.random(); //number between 0 and 1
        int result = 0;

        for (int i = 0; i < slices.size(); i++) {
            double[] check_slice = slices.get(i);
            if(check_slice[1] < spin && spin <= check_slice[2])
                result = (int)check_slice[0];
        }

        return result;
    }

    public void updatePossible_attractions(){
        for (int i = 0; i < visited_attractions.size(); i++) {
            if( possible_attractions.contains(visited_attractions.get(i))){
                for (int j = 0; j < possible_attractions.size(); j++) {
                    if(possible_attractions.get(j) == visited_attractions.get(i))
                        possible_attractions.remove(j);
                }
            }
        }
    }

    private void setAllAttractions(){
        for (int i = 0; i < numberOfCities(); i++) {
            all_attractions.add(i);
        }
    }

    @Override
    public String toString(){
        String result = "";
        result += "Distance: ";
        result += this.BestDistance;
        result += "\nVisited attractions: ";
        result += this.visited_attractions;
        return result;
    }
}

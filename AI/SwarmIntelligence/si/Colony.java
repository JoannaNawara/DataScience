package si;

import java.util.ArrayList;

public class Colony {
    ArrayList<Ant> colony = new ArrayList<Ant>();

    // setup colony of ants
    Colony(int attraction_count, double number_of_ants_factor){
        int number_of_ants = (int) Math.round( ((double)attraction_count) * number_of_ants_factor );
        for (int i = 0; i < number_of_ants; i++) {
            Ant nextAnt = new Ant(attraction_count);
            colony.add(nextAnt);
        }
    }

    public void move_ants(){
        for (Ant ant : colony){
            ant.visit_probabilistic_attraction(TourManager.pheromone_trails, TourManager.numberOfCities(), SI.alpha, SI.beta);
            int index = ant.roulette_wheel_selection();
            ant.visited_attractions.add(index);
        }
    }


}

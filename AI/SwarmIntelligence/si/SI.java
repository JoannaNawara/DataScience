package si;

public class SI {
    public static double evaporation_rate = 0.4;
    public static double number_of_ants_factor = 0.5;
    public static int alpha = 2;
    public static int beta = 1;
    public static int total_iterations = 1000;


    public static void updatePheromones(double evaporation_rate, Colony c){
        for (int i = 0; i < TourManager.numberOfCities(); i++) {
            for (int j = 0; j < TourManager.numberOfCities(); j++) {
                TourManager.pheromone_trails[i][j] = TourManager.pheromone_trails[i][j] * evaporation_rate;
                for (Ant ant : c.colony){
                    TourManager.pheromone_trails[i][j] += 1/ant.get_distance_travelled();
                }
            }
        }
    }

    public static Ant get_best(Colony c, Ant previous_best_ant){
        Ant best_ant = previous_best_ant;
        for (Ant ant : c.colony){
            double distance_traveled = ant.get_distance_travelled();
            if( best_ant == null){
                best_ant = ant;
            }
            else if (distance_traveled < best_ant.BestDistance)
                best_ant = ant;
        }
        return best_ant;
    }

    public static Ant solve(int total_iterations, double evaporation_rate, double number_of_ants_factor, int attraction_count){
        TourManager.setPheromones();
        TourManager.setDistance();
        Ant best_ant = null;
        for (int i = 0; i < total_iterations; i++) {
            Colony ants_colony = new Colony(attraction_count, number_of_ants_factor);
            for (int j = 0; j < attraction_count - 1; j++) {
                ants_colony.move_ants();
            }
            updatePheromones(evaporation_rate, ants_colony);
            best_ant = get_best(ants_colony, best_ant);

        }
        return best_ant;
    }
}

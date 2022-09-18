package si;

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            City city = new City();
            TourManager.addCity(city);
        }

        Ant result = SI.solve(5, SI.evaporation_rate, SI.number_of_ants_factor, TourManager.numberOfCities());

        System.out.println(result.BestDistance);
        System.out.println(result.visited_attractions);


    }
}

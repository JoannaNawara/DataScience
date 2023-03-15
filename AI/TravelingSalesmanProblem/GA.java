package ga;

public class GA {
    private static double mutationRate = 0.015;
    private static int tournamentRate = 5;
    private static boolean elitism = true;

    public static Population evolvePopulation(Population pop){
        Population nextGeneration = new Population(pop.size(), false);

        int elitismOffset = 0;
        if(elitism){
            elitismOffset = 1;
            nextGeneration.saveIndividual(0, pop.getFittest());
        }

        for (int i = elitismOffset; i < nextGeneration.size(); i++) {
            //selecting parents for crossover
            Tour parent1 = TournamentSelection(pop);
            Tour parent2 = TournamentSelection(pop);

            //child from crossover
            Tour child = crossover(parent1, parent2);

            //child mutation
            mutate(child);

            //save child for next generation
            nextGeneration.saveIndividual(i, child);
        }

        return nextGeneration;

    }

    private static Tour crossover(Tour tour1, Tour tour2){
        Tour child = new Tour();

        //indexes for inserting first parent between
        int startPos = (int) (Math.random()*tour1.size());
        int endPos = (int) (Math.random()*tour1.size());

        //swap indexes for correct order
        if( endPos < startPos){
            int temp = endPos;
            endPos = startPos;
            startPos = temp;
        }

        //iterating through the child to set cities
        for (int i = 0; i < child.size(); i++) {
            if( i >= startPos & i <= endPos ){
                City parentCity = tour1.getCity(i);
                child.setCity(i, parentCity);
            }
            else{
                City parentCity = tour2.getCity(i);
                child.setCity(i, parentCity);
            }
        }
        return child;

    }

    //let them fight
    private static Tour TournamentSelection(Population pop){
        Population tournament = new Population(tournamentRate, false);
        for (int i = 0; i < tournamentRate; i++) {
            int index = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(index));
        }
        Tour fittest = tournament.getFittest();
        return fittest;
    }

    private static void mutate(Tour tour){
        for (int i = 0; i < tour.size(); i++) {
            if (Math.random() < mutationRate){
                int Pos = (int) (Math.random() * tour.size());
                swapCities(i, Pos, tour);
            }
        }
    }

    public static void swapCities(int i, int newPos, Tour tour){
        City actual = tour.getCity(i);
        City next = tour.getCity(newPos);
        //swap Cities
        tour.setCity(i, next);
        tour.setCity(newPos, actual);
    }

    public static void setTournamentRate(int rate){
        tournamentRate = rate;
    }

    public static void setMutationRate(double rate){
        mutationRate = rate;
    }

    public static void setElitism(boolean value){
        elitism = value;
    }

}

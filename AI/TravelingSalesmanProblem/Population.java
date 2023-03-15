package ga;

public class Population {
    private Tour[] tours;
    private int PopulationSize;

    Population(int PopulationSize, boolean initialise){
        this.PopulationSize = PopulationSize;
        this.tours = new Tour[PopulationSize];
        if(initialise){
            for (int i = 0; i < PopulationSize; i++) {
                Tour newTour = new Tour();
                newTour.genIndividual();
                saveIndividual(i, newTour);
            }
        }
    }

    public int size(){
        return tours.length;
    }

    public Tour getIndividual(int index){
        return this.tours[index];
    }

    public Tour getFittest(){
        Tour fittest = tours[0];
        for (int i = 1; i < PopulationSize; i++) {
            if( tours[i].getFitness() >= fittest.getFitness()){
                fittest = tours[i];
            }
        }
        return fittest;
    }


    public void saveIndividual(int index, Tour indiv) {
        tours[index] = indiv;
    }

}

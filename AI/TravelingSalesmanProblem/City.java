package ga;

public class City {
    private int x;
    private int y;

    City(){
        this.x = (int) (Math.random()*200);
        this.y = (int) (Math.random()*200);
    }

    City(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public String toString(){
        return getX()+", "+getY();
    }

    public double distanceTo(City nextCity){
        int xDistance = Math.abs(getX() - nextCity.getX());
        int yDistance = Math.abs(getY() - nextCity.getY());
        double distance = Math.sqrt( (xDistance*xDistance) + (yDistance*yDistance) );
        return distance;
    }

}

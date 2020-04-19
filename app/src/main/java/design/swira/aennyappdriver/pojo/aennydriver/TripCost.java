package design.swira.aennyappdriver.pojo.aennydriver;

public class TripCost {

    double cost;

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public TripCost(double cost) {
        this.cost = cost;
    }

    public TripCost() {
    }

    @Override
    public String toString() {
        return cost+"";
    }
}

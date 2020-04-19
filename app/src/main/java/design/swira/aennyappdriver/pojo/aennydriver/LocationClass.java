package design.swira.aennyappdriver.pojo.aennydriver;

public class LocationClass {
    double late;
    double longg;

    public double getLate() {
        return late;
    }

    public void setLate(double late) {
        this.late = late;
    }

    public double getLongg() {
        return longg;
    }

    public void setLongg(double longg) {
        this.longg = longg;
    }

    public LocationClass(double late, double longg) {
        this.late = late;
        this.longg = longg;
    }
}

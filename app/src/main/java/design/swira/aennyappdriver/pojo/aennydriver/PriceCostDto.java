package design.swira.aennyappdriver.pojo.aennydriver;

public class PriceCostDto {

    int Trip_ID;
    double NormalKM;
    double RusKM;
    double TotalWaitingMinutes;
    boolean IsAdditionalFees;
    String PickUpTime;
    String DropOffTime;
    int WeekDay;
    double TotalMinutes;

    public int getTrip_ID() {
        return Trip_ID;
    }

    public void setTrip_ID(int trip_ID) {
        Trip_ID = trip_ID;
    }

    public double getNormalKM() {
        return NormalKM;
    }

    public void setNormalKM(double normalKM) {
        NormalKM = normalKM;
    }

    public double getRusKM() {
        return RusKM;
    }

    public void setRusKM(double rusKM) {
        RusKM = rusKM;
    }

    public double getTotalWaitingMinutes() {
        return TotalWaitingMinutes;
    }

    public void setTotalWaitingMinutes(double totalWaitingMinutes) {
        TotalWaitingMinutes = totalWaitingMinutes;
    }

    public boolean isAdditionalFees() {
        return IsAdditionalFees;
    }

    public void setAdditionalFees(boolean additionalFees) {
        IsAdditionalFees = additionalFees;
    }

    public String getPickUpTime() {
        return PickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        PickUpTime = pickUpTime;
    }

    public String getDropOffTime() {
        return DropOffTime;
    }

    public void setDropOffTime(String dropOffTime) {
        DropOffTime = dropOffTime;
    }

    public int getWeekDay() {
        return WeekDay;
    }

    public void setWeekDay(int weekDay) {
        WeekDay = weekDay;
    }

    public double getTotalMinutes() {
        return TotalMinutes;
    }

    public void setTotalMinutes(double totalMinutes) {
        TotalMinutes = totalMinutes;
    }

    public PriceCostDto(int trip_ID, double normalKM, double rusKM, double totalWaitingMinutes, boolean isAdditionalFees, String pickUpTime, String dropOffTime, int weekDay , double totalMinutes) {
        Trip_ID = trip_ID;
        NormalKM = normalKM;
        RusKM = rusKM;
        TotalWaitingMinutes = totalWaitingMinutes;
        IsAdditionalFees = isAdditionalFees;
        PickUpTime = pickUpTime;
        DropOffTime = dropOffTime;
        WeekDay = weekDay;
        TotalMinutes=totalMinutes;
    }


    @Override
    public String toString() {
        return "PriceCostDto{" +
                "Trip_ID=" + Trip_ID +
                ", NormalKM=" + NormalKM +
                ", RusKM=" + RusKM +
                ", TotalWaitingMinutes=" + TotalWaitingMinutes +
                ", IsAdditionalFees=" + IsAdditionalFees +
                ", PickUpTime='" + PickUpTime + '\'' +
                ", DropOffTime='" + DropOffTime + '\'' +
                ", WeekDay=" + WeekDay +
                '}';
    }
}

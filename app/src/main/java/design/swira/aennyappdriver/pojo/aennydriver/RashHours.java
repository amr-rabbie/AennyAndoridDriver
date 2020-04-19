package design.swira.aennyappdriver.pojo.aennydriver;

import java.io.Serializable;

public class RashHours implements Serializable {
    int financials_RushHours_Id;
    String rashHour_1_From;
    String rashHour_1_To;
    String rashHour_2_From;
    String rashHour_2_To;
    int week_Day;
    String weekDayName;
    double price_Per_KilloMeter_RashHour;

    public int getFinancials_RushHours_Id() {
        return financials_RushHours_Id;
    }

    public void setFinancials_RushHours_Id(int financials_RushHours_Id) {
        this.financials_RushHours_Id = financials_RushHours_Id;
    }

    public String getRashHour_1_From() {
        return rashHour_1_From;
    }

    public void setRashHour_1_From(String rashHour_1_From) {
        this.rashHour_1_From = rashHour_1_From;
    }

    public String getRashHour_1_To() {
        return rashHour_1_To;
    }

    public void setRashHour_1_To(String rashHour_1_To) {
        this.rashHour_1_To = rashHour_1_To;
    }

    public String getRashHour_2_From() {
        return rashHour_2_From;
    }

    public void setRashHour_2_From(String rashHour_2_From) {
        this.rashHour_2_From = rashHour_2_From;
    }

    public String getRashHour_2_To() {
        return rashHour_2_To;
    }

    public void setRashHour_2_To(String rashHour_2_To) {
        this.rashHour_2_To = rashHour_2_To;
    }

    public int getWeek_Day() {
        return week_Day;
    }

    public void setWeek_Day(int week_Day) {
        this.week_Day = week_Day;
    }

    public String getWeekDayName() {
        return weekDayName;
    }

    public void setWeekDayName(String weekDayName) {
        this.weekDayName = weekDayName;
    }

    public double getPrice_Per_KilloMeter_RashHour() {
        return price_Per_KilloMeter_RashHour;
    }

    public void setPrice_Per_KilloMeter_RashHour(double price_Per_KilloMeter_RashHour) {
        this.price_Per_KilloMeter_RashHour = price_Per_KilloMeter_RashHour;
    }

    public RashHours(int financials_RushHours_Id, String rashHour_1_From, String rashHour_1_To, String rashHour_2_From, String rashHour_2_To, int week_Day, String weekDayName, double price_Per_KilloMeter_RashHour) {
        this.financials_RushHours_Id = financials_RushHours_Id;
        this.rashHour_1_From = rashHour_1_From;
        this.rashHour_1_To = rashHour_1_To;
        this.rashHour_2_From = rashHour_2_From;
        this.rashHour_2_To = rashHour_2_To;
        this.week_Day = week_Day;
        this.weekDayName = weekDayName;
        this.price_Per_KilloMeter_RashHour = price_Per_KilloMeter_RashHour;
    }

    @Override
    public String toString() {
        return "RashHours{" +
                "financials_RushHours_Id=" + financials_RushHours_Id +
                ", rashHour_1_From='" + rashHour_1_From + '\'' +
                ", rashHour_1_To='" + rashHour_1_To + '\'' +
                ", rashHour_2_From='" + rashHour_2_From + '\'' +
                ", rashHour_2_To='" + rashHour_2_To + '\'' +
                ", week_Day=" + week_Day +
                ", weekDayName='" + weekDayName + '\'' +
                ", price_Per_KilloMeter_RashHour=" + price_Per_KilloMeter_RashHour +
                '}';
    }
}

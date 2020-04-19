package design.swira.aennyappdriver.pojo.aennydriver;

import java.io.Serializable;

public class FinancialsRushHoursDto implements Serializable {

    int Financials_RushHours_Id;
    String RashHour_1_From;
    String RashHour_1_To;
    String RashHour_2_From;
    String RashHour_2_To;
    int Week_Day;
    String WeekDayName;


    public int getFinancials_RushHours_Id() {
        return Financials_RushHours_Id;
    }

    public void setFinancials_RushHours_Id(int financials_RushHours_Id) {
        Financials_RushHours_Id = financials_RushHours_Id;
    }

    public String getRashHour_1_From() {
        return RashHour_1_From;
    }

    public void setRashHour_1_From(String rashHour_1_From) {
        RashHour_1_From = rashHour_1_From;
    }

    public String getRashHour_1_To() {
        return RashHour_1_To;
    }

    public void setRashHour_1_To(String rashHour_1_To) {
        RashHour_1_To = rashHour_1_To;
    }

    public String getRashHour_2_From() {
        return RashHour_2_From;
    }

    public void setRashHour_2_From(String rashHour_2_From) {
        RashHour_2_From = rashHour_2_From;
    }

    public String getRashHour_2_To() {
        return RashHour_2_To;
    }

    public void setRashHour_2_To(String rashHour_2_To) {
        RashHour_2_To = rashHour_2_To;
    }

    public int getWeek_Day() {
        return Week_Day;
    }

    public void setWeek_Day(int week_Day) {
        Week_Day = week_Day;
    }

    public String getWeekDayName() {
        return WeekDayName;
    }

    public void setWeekDayName(String weekDayName) {
        WeekDayName = weekDayName;
    }

    public FinancialsRushHoursDto(int financials_RushHours_Id, String rashHour_1_From, String rashHour_1_To, String rashHour_2_From, String rashHour_2_To, int week_Day, String weekDayName) {
        Financials_RushHours_Id = financials_RushHours_Id;
        RashHour_1_From = rashHour_1_From;
        RashHour_1_To = rashHour_1_To;
        RashHour_2_From = rashHour_2_From;
        RashHour_2_To = rashHour_2_To;
        Week_Day = week_Day;
        WeekDayName = weekDayName;
    }

    public FinancialsRushHoursDto(int financials_RushHours_Id, String rashHour_1_From, String rashHour_1_To, String rashHour_2_From, String rashHour_2_To) {
        Financials_RushHours_Id = financials_RushHours_Id;
        RashHour_1_From = rashHour_1_From;
        RashHour_1_To = rashHour_1_To;
        RashHour_2_From = rashHour_2_From;
        RashHour_2_To = rashHour_2_To;
    }

    @Override
    public String toString() {
        return "FinancialsRushHoursDto{" +
                "Financials_RushHours_Id=" + Financials_RushHours_Id +
                ", RashHour_1_From='" + RashHour_1_From + '\'' +
                ", RashHour_1_To='" + RashHour_1_To + '\'' +
                ", RashHour_2_From='" + RashHour_2_From + '\'' +
                ", RashHour_2_To='" + RashHour_2_To + '\'' +
                ", Week_Day=" + Week_Day +
                ", WeekDayName='" + WeekDayName + '\'' +
                '}';
    }
}

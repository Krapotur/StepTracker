public class MonthData {
    int [] dayToMonth = new int[30];

    void createDays(){
        for (int day: dayToMonth){
            dayToMonth[day] = 0;
        }
    }
}

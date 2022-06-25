public class MonthData {
    int [] dayToMonth = new int[30];

    MonthData(){
        for (int day: dayToMonth){
            dayToMonth[day] = 0;
        }
    }
}

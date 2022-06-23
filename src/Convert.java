public class Convert {
    int sumSteps;

    Convert(int steps) {
        sumSteps = steps;
    }

    double calcDistance() {
        return sumSteps * 0.75;
    }

    double caloriesBurned(){
        int calories = sumSteps / 50;
        return (double) calories / 1000;
    }
}

import java.util.Scanner;

public class StepTracker {
    int goalStepsByDay = 10000;
    int steps;
    Scanner scanner = new Scanner(System.in);
    MonthData[] monthToData;

    public StepTracker() {
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }
    
     void saveSumStepsByDay() {
        System.out.print("Выберите месяц(от 1-12): ");
        int month = scanner.nextInt();
        System.out.print("Выберите день(от 1-30): ");
        int day = scanner.nextInt();
        while (true) {
            System.out.print("Укажите количество шагов, которые прошли сегодня: ");
            steps = scanner.nextInt();
            if (steps < 0) {
                System.out.println("Количество шагов не может быть отрицательным!");
            } else {
                monthToData[month - 1].dayToMonth[day - 1] = steps;
                return;
            }
        }
    }

    void showStatsByMonth() {
        int sumSteps = 0;

        System.out.print("За какой месяц Вас интересует статистика?:  ");
        int month = scanner.nextInt();

        System.out.println("Количество пройденных шагов по дням: ");
        for (int i = 0; i < 30; i++) {
            System.out.print(i + 1 + " день: " + monthToData[month - 1].dayToMonth[i] + ", ");
            sumSteps += monthToData[month - 1].dayToMonth[i];
        }

        Convert convert = new Convert(sumSteps);

        System.out.println();
        System.out.println("Общее количество шагов за месяц: " + sumSteps);
        System.out.println("Максимальное пройденное количество шагов в месяце: " + getMaxStepsByMonth(month));
        System.out.println("Среднее количество шагов: " + averageNumberSteps(sumSteps, month));
        System.out.println("Пройденная дистанция (в км): " + (int) convert.calcDistance());
        System.out.println("Количество сожжённых килокалорий: " + convert.caloriesBurned());
        System.out.println("Лучшая серия: максимальное количество подряд идущих дней, в течение которых количество шагов за день было равно или выше целевого: " + bestSeries(month));

    }

    int getMaxStepsByMonth(int month) {
        int maxSteps = 0;
        for (int i = 0; i < 30; i++) {
            if (monthToData[month - 1].dayToMonth[i] > maxSteps) {
                maxSteps = monthToData[month - 1].dayToMonth[i];
            }
        }
        return maxSteps;
    }

    int averageNumberSteps(int sumSteps, int month) {
        int sumDays = 0;
        for (int i = 0; i < 30; i++) {
            if (monthToData[month - 1].dayToMonth[i] > 0 && i == 0) {
                sumDays += i + 1;
            } else if (monthToData[month - 1].dayToMonth[i] > 0) {
                sumDays += i;
            }
        }
        return sumSteps / sumDays;
    }

    void changeGoalStepsByDay() {
        while (true) {
            System.out.print("Укажите количество шагов в день для новой цели: ");
            int goal = scanner.nextInt();
            if (goal < 0) {
                System.out.println("Введенное число не может быть отрицательным!");
            } else {
                goalStepsByDay = goal;
                return;
            }
        }

    }

    int bestSeries(int month) {
        int bestSeries = 0;
        int sumSteps = 0;

        while (sumSteps <= goalStepsByDay) {
            for (int i = 0; i < 30; i++) {
                if (monthToData[month - 1].dayToMonth[i] > 0) {
                    sumSteps += monthToData[month - 1].dayToMonth[i];
                    bestSeries++;
                }
            }
        }
        return bestSeries;
    }
}

import java.util.Scanner;

public class StepTracker {
    static int goalStepsByDay;
    static int steps;
    static Scanner scanner;
    static MonthData[] monthToData;

    public static void main(String[] args) {
        goalStepsByDay = 10000;
        scanner = new Scanner(System.in);
        monthToData = new MonthData[12];

        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
            monthToData[i].createDays();
        }

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                saveSumStepsByDay();
            } else if (command == 2) {
                showStatsByMonth();
            } else if (command == 3) {
                changeGoalStepsByDay();
            } else if (command == 4) {
                return;
            } else {
                System.out.println("Такой команды нет!");
            }
        }
    }

    public static void printMenu() {
        System.out.println("1. Ввести количество шагов за определённый день");
        System.out.println("2. Напечатать статистику за определённый месяц");
        System.out.println("3. Изменить цель по количеству шагов в день");
        System.out.println("4. Выйти из приложения");
    }

    public static void saveSumStepsByDay() {
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

    public static void showStatsByMonth() {
        int sumSteps = 0;

        System.out.println("За какой месяц Вас интересует статистика?  ");
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

    public static int getMaxStepsByMonth(int month) {
        int maxSteps = 0;
        for (int i = 0; i < 30; i++) {
            if (monthToData[month - 1].dayToMonth[i] > maxSteps) {
                maxSteps = monthToData[month - 1].dayToMonth[i];
            }
        }
        return maxSteps;
    }

    public static int averageNumberSteps(int sumSteps, int month) {
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

    public static void changeGoalStepsByDay() {
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

    public static int bestSeries(int month) {
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

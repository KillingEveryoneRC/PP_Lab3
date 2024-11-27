package app;

import droids.*;
import battle.Battle;

import java.util.ArrayList;
import java.util.Scanner;

public class DroidBattleApp {
    private ArrayList<Droid> droids = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private boolean working = false;

    public static void main(String[] args) {
        new DroidBattleApp().run();
    }

    public void run() {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> createDroid();
                case 2 -> listDroids();
                case 3 -> startBattle();
                case 4 -> exit();
                default -> System.out.println("Неправильний вибір. Спробуйте ще раз.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n1. Почати вибір дроїдів");
        System.out.println("2. Показати список дроїдів");
        System.out.println("3. Почати бій");
        System.out.println("4. Вийти з програми");
        System.out.print("Ваш вибір: ");
    }

    private void createDroid() {
        if (!working) {
            working = true;
            for (int i = 1; i <= 2; i++) {
                System.out.print("\n\nВибір 4-рьох дроідів для команди " + i + ".");
                for (int j = 1; j <= 4; j++) {
                    System.out.println("\nКлас дроіду номер " + j + ".");
                    System.out.println("1. Воїн");
                    System.out.println("2. Медик");
                    System.out.println("3. Маг");
                    System.out.println("4. Танк");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    Droid droid = switch (type) {
                        case 2 -> new Medic("Medic", i);
                        case 3 -> new Mage("Mage", i);
                        case 4 -> new Tank("Tank", i);
                        default -> new Warrior("Warrior", i);
                    };

                    droids.add(droid);
                    System.out.println("Дроїд створений: " + droid);
                }
            }
        } else {
            System.out.println("Дроїди вже створені");
        }
    }

    private void listDroids() {
        if (!working) {
            System.out.println("Дроїди не створені.");
        } else {
            droids.forEach(System.out::println);
            System.out.println("\n");
        }
    }

    private void startBattle() {
        if (!working) {
            System.out.println("Дроїди не створені.");
        } else {
            Battle battle = new Battle(droids);
            battle.start();
        }
    }

    private void exit() {
        System.out.println("Програма завершена.");
        System.exit(0);
    }
}
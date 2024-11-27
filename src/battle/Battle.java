package battle;

import droids.Droid;

import java.util.ArrayList;
import java.util.Scanner;

public class Battle {
    private ArrayList<Droid> droids;

    private int[][] matrix;
    private Scanner scanner;

    public Battle(ArrayList<Droid> droids) {
        this.droids = new ArrayList<>(droids);
        this.scanner = new Scanner(System.in);

        this.matrix = new int[][] {
                {1, 0, 0, 5},
                {2, 0, 0, 6},
                {3, 0, 0, 7},
                {4, 0, 0, 8}
        };
    }

    public void printMap() {
        System.out.println("Карта бою:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void updateMap(int x, int y, int value) {
        if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
            matrix[x][y] = value;
        }
    }

    public int getValue(int x, int y) {
        if (x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length) {
            return matrix[x][y];
        } else {
            return 10;
        }
    }

    private boolean checkTeamAlive(int team) {
        for (Droid droid : droids) {
            if (droid.getTeam() == team && droid.getHealth() > 0) {
                return true;
            }
        }
        return false;
    }

    private boolean checkRange(int row1, int row2, int col1, int col2, int range) {
        return row2 - row1 <= range && col2 - col1 <= range && row2 - row1 >= -range && col2 - col1 >= -range;
    }

    // Метод для початку бою
    public void start() {
        boolean win = false;
        int team = 1;

        while (!win) {
            System.out.println("\nХід команди номер " + team);
            printMap();
            System.out.println("\nВведіть координати вашого бійця.");
            System.out.print("\nРядок: ");
            int row1 = scanner.nextInt() - 1;
            System.out.print("\nСтовпець: ");
            int col1 = scanner.nextInt() - 1;

            int from = getValue(row1, col1);

            if (from == 0) {
                System.out.print("\nБоєць відсутній");
                continue;
            } else if (droids.get(from).getTeam() != team) {
                System.out.print("\nДаний боєць не ваш");
                continue;
            } else if (from == 10) {
                System.out.print("\nНекоректні координати.");
                continue;
            }
            System.out.print("\n Ви вибрали бійця " + droids.get(from).getName());
            System.out.println("\nВведіть куди хочете перемістити його (Введіть 0 щоб скасувати хід).");
            System.out.print("\nРядок: ");
            int row2 = scanner.nextInt() - 1;
            System.out.print("\nСтовпець: ");
            int col2 = scanner.nextInt() - 1;
            if (row2+1 == 0 || col2+1 == 0) {
                System.out.print("\nХід скасовано.");
                continue;
            }

            int to = getValue(row2, col2);
            if (to == 0) {
                if (checkRange(row1, row2, col1, col2, 1)) {
                    updateMap(row1, col1, 0);
                    updateMap(row2, col2, from);
                } else {
                    System.out.print("\nВи можете переміщатися не більше 1 клітинки в один хід.");
                    continue;
                }
            } else if (to >= 9) {
                System.out.print("\nНекоректні координати.");
                continue;
            } else {
                if (checkRange(row1, row2, col1, col2, 3)) {
                    if (droids.get(to).getTeam() != team) {
                        droids.get(from).attack(droids.get(to));
                        System.out.print("\nБоєць " + droids.get(from).getName() + " атакує " + droids.get(to).getName() + " (HP:" + droids.get(to).getHealth() + ").");

                        if (!droids.get(to).isAlive()) {
                            System.out.print("\nБоєць " + droids.get(from).getName() + " вбив " + droids.get(to).getName() + "!");
                            updateMap(row2, col2, 0);

                            if (!checkTeamAlive(droids.get(to).getTeam())) {
                                System.out.print("\nКоманда " + droids.get(to).getTeam() + " програла!");
                                System.out.print("\nВітаю команда номер" + droids.get(from).getTeam() + "!");
                                win = true;
                                continue;
                            }
                        }
                    } else {
                        if (droids.get(to).getName().equals("Medic")) {
                            droids.get(from).heal(droids.get(to));
                        } else {
                            System.out.print("\nЦе є союзний боєць.");
                            continue;
                        }
                    }
                }
            }

            team = (team == 1) ? 2 : 1;
        }
    }
}
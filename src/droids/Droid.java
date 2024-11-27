package droids;

import java.util.Random;

public class Droid {
    protected String name;
    protected int team;
    protected int health;
    protected int damage;
    protected int range;

    public Droid(String name, int team, int health, int damage, int range) {
        this.name = name;
        this.team = team;
        this.health = health;
        this.damage = damage;
        this.range = range;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getTeam() {
        return team;
    }

    public void takeDamage(int damage) {
        Random rand = new Random();
        double critChance = rand.nextDouble();

        int finalDamage = damage;

        if (critChance <= 0.15) {
            finalDamage *= 1.5;
            System.out.println("\nКритичне попадання! Пошкодження збільшені на 20%.");
        }

        this.health -= finalDamage;
        if (this.health < 0) {
            this.health = 0;
        }
    }

    public void setHeal(int heal) {
        this.health += damage*2;
    }

    public void attack(Droid opponent){

    }
    public void heal(Droid opponent){

    }

    public boolean isAlive() {
        return this.health > 0;
    }

    @Override
    public String toString() {
        return "Team: " + team + " Class: " + name + " [Health: " + health + ", Damage: " + damage + ", Range: " + range + "]";
    }
}
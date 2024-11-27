package droids;

public class Warrior extends Droid {
    public Warrior(String name, int team) {
        super(name, team, 100, 40, 1);
    }

    @Override
    public void attack(Droid opponent) {
        opponent.takeDamage(this.damage);
    }
}
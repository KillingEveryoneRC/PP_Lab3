package droids;

public class Tank extends Droid {
    public Tank(String name, int team) {
        super(name, team, 120, 30, 1);
    }

    @Override
    public void attack(Droid opponent) {
        opponent.takeDamage(this.damage);
    }
}
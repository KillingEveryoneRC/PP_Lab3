package droids;

public class Medic extends Droid {
    public Medic(String name, int team) {
        super(name, team, 80, 30, 1);
    }

    @Override
    public void attack(Droid opponent) {
        opponent.takeDamage(this.damage);
    }

    public void heal(Droid ally) {
        System.out.println(this.name + " зцілює " + ally.getName());
        ally.health += 15;
    }
}

package droids;

public class Mage extends Droid {
    public Mage(String name, int team) {
        super(name, team, 70, 25, 2);
    }

    @Override
    public void attack(Droid opponent) {
        opponent.takeDamage(this.damage);
    }

    public void heal(Droid ally) {
        ally.setHeal(this.damage*2);
        System.out.println(this.name + " зцілює " + ally.getName() + "(HP: " + ally.getHealth() + ")");
    }
}

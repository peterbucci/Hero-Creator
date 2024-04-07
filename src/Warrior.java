public class Warrior extends Hero implements Armable {

  // MIN values for each attribute
  private static final int MIN_STRENGTH = 6;
  private static final int MIN_DEXTERITY = 3;
  private static final int MIN_CONSTITUTION = 5;
  private static final int MIN_INTELLIGENCE = 1;
  private static final int MIN_WISDOM = 2;
  private static final int MIN_CHARISMA = 2;
  // MAX values for each attribute
  private static final int MAX_STRENGTH = 10;
  private static final int MAX_DEXTERITY = 7;
  private static final int MAX_CONSTITUTION = 10;
  private static final int MAX_INTELLIGENCE = 5;
  private static final int MAX_WISDOM = 6;
  private static final int MAX_CHARISMA = 6;

  private boolean hasTransport;

  public Warrior(String name, boolean hasTransport) {
    super(
      name,
      MIN_STRENGTH,
      MAX_STRENGTH,
      MIN_DEXTERITY,
      MAX_DEXTERITY,
      MIN_CONSTITUTION,
      MAX_CONSTITUTION,
      MIN_INTELLIGENCE,
      MAX_INTELLIGENCE,
      MIN_WISDOM,
      MAX_WISDOM,
      MIN_CHARISMA,
      MAX_CHARISMA
    );
    this.hasTransport = hasTransport;
  }

  public Warrior() {
    this("Warrior", false);
  }

  public boolean getHasTransport() {
    return hasTransport;
  }

  public void setHasTransport(boolean hasTransport) {
    this.hasTransport = hasTransport;
  }

  @Override
  public String wieldWeapon(Weapon weapon) {
    return name + " is wielding " + weapon.getName();
  }

  @Override
  public String toString() {
    return (
      super.toString() + ", hasTransport=" + hasTransport + ", class=Warrior"
    );
  }
}

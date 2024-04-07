public class Faerie extends Hero implements Armable {

  // MIN values for each attribute
  private static final int MIN_STRENGTH = 1;
  private static final int MIN_DEXTERITY = 7;
  private static final int MIN_CONSTITUTION = 2;
  private static final int MIN_INTELLIGENCE = 5;
  private static final int MIN_WISDOM = 6;
  private static final int MIN_CHARISMA = 6;
  // MAX values for each attribute
  private static final int MAX_STRENGTH = 3;
  private static final int MAX_DEXTERITY = 10;
  private static final int MAX_CONSTITUTION = 5;
  private static final int MAX_INTELLIGENCE = 8;
  private static final int MAX_WISDOM = 9;
  private static final int MAX_CHARISMA = 10;

  private static final int MIN_HEIGHT = 1;
  private static final int MAX_HEIGHT = 10;

  private StatValue height;

  public Faerie(String name, int height) {
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
    this.height = new StatValue("Height", height, MIN_HEIGHT, MAX_HEIGHT);
  }

  public Faerie() {
    this("Faerie", MIN_HEIGHT);
  }

  public StatValue getHeight() {
    return height;
  }

  public void setHeight(int height) {
    try {
      this.height.setValue(height);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  @Override
  public String wieldWeapon(Weapon weapon) {
    return name + " cannot wield weapons because they are too small!";
  }

  @Override
  public String toString() {
    return (
      super.toString() + ", height=" + height.getValue() + ", class=Faerie"
    );
  }
}

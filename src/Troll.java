public class Troll extends Monster {

  // MIN values for each attribute
  private static final int MIN_STRENGTH = 5;
  private static final int MIN_DEXTERITY = 3;
  private static final int MIN_CONSTITUTION = 6;
  private static final int MIN_INTELLIGENCE = 2;
  private static final int MIN_WISDOM = 1;
  private static final int MIN_CHARISMA = 1;
  // MAX values for each attribute
  private static final int MAX_STRENGTH = 8;
  private static final int MAX_DEXTERITY = 5;
  private static final int MAX_CONSTITUTION = 9;
  private static final int MAX_INTELLIGENCE = 3;
  private static final int MAX_WISDOM = 3;
  private static final int MAX_CHARISMA = 2;

  private static final int MIN_HEIGHT = 1;
  private static final int MAX_HEIGHT = 10;

  private StatValue height;

  public Troll(String name, String habitat, int height) {
    super(
      name,
      habitat,
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

  public Troll() {
    this("Troll", "Forest", MIN_HEIGHT);
  }

  public StatValue getHeight() {
    return height;
  }

  public void setHeight(int height) {
    try {
      this.height.setValue(height);
    } catch (IllegalArgumentException ex) {
      throw ex;
    }
  }

  @Override
  public String toString() {
    return super.toString() + ", height=" + height.getValue() + ", class=Troll";
  }
}

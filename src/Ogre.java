public class Ogre extends Monster {

  // MIN values for each attribute
  private static final int MIN_STRENGTH = 6;
  private static final int MIN_DEXTERITY = 2;
  private static final int MIN_CONSTITUTION = 7;
  private static final int MIN_INTELLIGENCE = 1;
  private static final int MIN_WISDOM = 2;
  private static final int MIN_CHARISMA = 1;
  // MAX values for each attribute
  private static final int MAX_STRENGTH = 10;
  private static final int MAX_DEXTERITY = 4;
  private static final int MAX_CONSTITUTION = 10;
  private static final int MAX_INTELLIGENCE = 3;
  private static final int MAX_WISDOM = 4;
  private static final int MAX_CHARISMA = 2;

  private boolean hasScales;

  public Ogre(String name, String habitat, boolean hasScales) {
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
    this.hasScales = hasScales;
  }

  public Ogre() {
    this("Ogre", "Swamp", false);
  }

  public boolean isHasScales() {
    return hasScales;
  }

  public void setHasScales(boolean hasScales) {
    this.hasScales = hasScales;
  }

  @Override
  public String toString() {
    return super.toString() + ", hasScales=" + hasScales + ", class=Ogre";
  }
}

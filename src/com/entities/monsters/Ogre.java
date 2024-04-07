package com.entities.monsters;

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
  // Properties
  private boolean hasScales; // This is whether the ogre has scales

  /**
   * This constructor initializes the Ogre object with the specified values.
   * @param name The name of the ogre
   * @param habitat The habitat the ogre is found in
   * @param hasScales Whether the ogre has scales
   */
  public Ogre(String name, String habitat, boolean hasScales) {
    // Call the super constructor
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
    this.hasScales = hasScales; // Set the hasScales property
  }

  /**
   * This constructor initializes the Ogre object with default values.
   */
  public Ogre() {
    // Call the other constructor
    this("Ogre", "Swamp", false);
  }

  /**
   * This method returns whether the ogre has scales.
   * @return A boolean indicating whether the ogre has scales
   */
  public boolean isHasScales() {
    return hasScales;
  }

  /**
   * This method sets whether the ogre has scales.
   * @param hasScales A boolean indicating whether the ogre has scales
   */
  public void setHasScales(boolean hasScales) {
    this.hasScales = hasScales;
  }

  /**
   * This method returns a string representation of the Ogre object.
   * @return Calls the super toString method and appends the hasScales property and class.
   */
  @Override
  public String toString() {
    return super.toString() + ", hasScales=" + hasScales + ", class=Ogre";
  }
}

package com.entities.monsters;

import com.stats.StatValue;

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
  // MIN and MAX values for height
  private static final int MIN_HEIGHT = 1;
  private static final int MAX_HEIGHT = 10;
  // Property specific to Troll
  private StatValue height; // This is the height of the troll

  /**
   * This constructor initializes the Troll object with the specified values.
   * @param name The name of the troll
   * @param habitat The habitat the troll is found in
   * @param height The height of the troll
   */
  public Troll(String name, String habitat, int height) {
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
    // Set the height property
    this.height = new StatValue("Height", height, MIN_HEIGHT, MAX_HEIGHT);
  }

  /**
   * This constructor initializes the Troll object with default values.
   */
  public Troll() {
    // Call the other constructor
    this("Troll", "Forest", MIN_HEIGHT);
  }

  /**
   * This method returns the height of the troll.
   * @return The height of the troll
   */
  public StatValue getHeight() {
    return height;
  }

  /**
   * This method sets the height of the troll.
   * @param height The height of the troll
   */
  public void setHeight(int height) {
    try {
      this.height.setValue(height);
    } catch (IllegalArgumentException ex) {
      throw ex;
    }
  }

  /**
   * This method returns a string representation of the Troll object.
   * @return Calls the super toString method and appends the height property and class.
   */
  @Override
  public String toString() {
    return super.toString() + ", height=" + height.getValue() + ", class=Troll";
  }
}

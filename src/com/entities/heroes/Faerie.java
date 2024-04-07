package com.entities.heroes;

import com.interfaces.Armable;
import com.stats.StatValue;
import com.weapons.Weapon;

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
  // MIN and MAX values for height
  private static final int MIN_HEIGHT = 1;
  private static final int MAX_HEIGHT = 10;
  // Property specific to Faerie
  private StatValue height; // This is the height of the faerie

  /**
   * This constructor initializes the Faerie object with the specified values.
   * @param name The name of the faerie
   * @param height The height of the faerie
   */
  public Faerie(String name, int height) {
    // Call the super constructor
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
    // Set the height property
    this.height = new StatValue("Height", height, MIN_HEIGHT, MAX_HEIGHT);
  }

  /**
   * This constructor initializes the Faerie object with default values.
   */
  public Faerie() {
    // Call the other constructor
    this("Faerie", MIN_HEIGHT);
  }

  /**
   * This method returns the height of the faerie.
   * @return The height of the faerie
   */
  public StatValue getHeight() {
    return height;
  }

  /**
   * This method sets the height of the faerie.
   * @param height The height of the faerie
   */
  public void setHeight(int height) {
    try {
      this.height.setValue(height);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  /**
   * This method allows an faerie to wield a weapon, but faeries are too small to wield weapons.
   * @param weapon The weapon the faerie is wielding
   * @return A string indicating the faerie cannot wield weapons
   */
  @Override
  public String wieldWeapon(Weapon weapon) {
    return name + " cannot wield weapons because they are too small!";
  }

  /**
   * This method returns a string representation of the Faerie object.
   * @return Calls the super toString method and appends the height property and class.
   */
  @Override
  public String toString() {
    return (
      super.toString() + ", height=" + height.getValue() + ", class=Faerie"
    );
  }
}

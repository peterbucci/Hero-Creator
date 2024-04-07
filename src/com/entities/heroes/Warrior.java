package com.entities.heroes;

import com.interfaces.Armable;
import com.weapons.Weapon;

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
  // Property specific to Warrior
  private boolean hasTransport; // This is whether the warrior has a mode of transport

  /**
   * This constructor initializes the Warrior object with the specified values.
   * @param name The name of the warrior
   * @param hasTransport Whether the warrior has a mode of transport
   */
  public Warrior(String name, boolean hasTransport) {
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
    this.hasTransport = hasTransport; // Set the hasTransport property
  }

  /**
   * This constructor initializes the Warrior object with default values.
   */
  public Warrior() {
    // Call the other constructor
    this("Warrior", false);
  }

  /**
   * This method returns whether the warrior has a mode of transport.
   * @return A boolean indicating whether the warrior has a mode of transport
   */
  public boolean getHasTransport() {
    return hasTransport;
  }

  /**
   * This method sets whether the warrior has a mode of transport.
   * @param hasTransport A boolean indicating whether the warrior has a mode of transport
   */
  public void setHasTransport(boolean hasTransport) {
    this.hasTransport = hasTransport;
  }

  /**
   * This method allows the warrior to wield a weapon.
   * @param weapon The weapon to wield
   * @return A string indicating the weapon the warrior is wielding
   */
  @Override
  public String wieldWeapon(Weapon weapon) {
    return name + " is wielding " + weapon.getName();
  }

  /**
   * This method returns a string representation of the Warrior object.
   * @return Calls the super toString method and appends the hasTransport property and class.
   */
  @Override
  public String toString() {
    return (
      super.toString() + ", hasTransport=" + hasTransport + ", class=Warrior"
    );
  }
}

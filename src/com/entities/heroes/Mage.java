package com.entities.heroes;

import com.interfaces.Armable;
import com.stats.StatValue;
import com.weapons.Weapon;

public class Mage extends Hero implements Armable {

  // MIN values for each attribute
  private static final int MIN_STRENGTH = 1;
  private static final int MIN_DEXTERITY = 2;
  private static final int MIN_CONSTITUTION = 2;
  private static final int MIN_INTELLIGENCE = 8;
  private static final int MIN_WISDOM = 4;
  private static final int MIN_CHARISMA = 3;
  // MAX values for each attribute
  private static final int MAX_STRENGTH = 4;
  private static final int MAX_DEXTERITY = 6;
  private static final int MAX_CONSTITUTION = 5;
  private static final int MAX_INTELLIGENCE = 10;
  private static final int MAX_WISDOM = 8;
  private static final int MAX_CHARISMA = 7;
  // MIN and MAX values for age
  private static final int MIN_AGE = 1;
  private static final int MAX_AGE = 100;
  // Property specific to Mage
  private StatValue age; // This is the age of the mage

  /**
   * This constructor initializes the Mage object with the specified values.
   * @param name The name of the mage
   * @param age The age of the mage
   */
  public Mage(String name, int age) {
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
    // Set the age property
    this.age = new StatValue("Age", age, MIN_AGE, MAX_AGE);
  }

  /**
   * This constructor initializes the Mage object with default values.
   */
  public Mage() {
    // Call the other constructor
    this("Mage", MIN_AGE);
  }

  /**
   * This method returns the age of the mage.
   * @return The age of the mage
   */
  public StatValue getAge() {
    return age;
  }

  /**
   * This method sets the age of the mage.
   * @param age The age of the mage
   */
  public void setAge(int age) {
    try {
      this.age.setValue(age);
    } catch (IllegalArgumentException ex) {
      throw ex;
    }
  }

  /**
   * This method allows the mage to wield a weapon as long as it is a spell.
   * @param weapon The weapon to wield
   * @return A string indicating the weapon the mage is wielding or that the mage cannot wield the weapon.
   */
  @Override
  public String wieldWeapon(Weapon weapon) {
    if (weapon.getType() == Weapon.Type.SPELL) {
      return name + " is wielding " + weapon.getName();
    } else {
      return (
        name + " cannot wield " + weapon.getName() + " as it is not a spell."
      );
    }
  }

  /**
   * This method returns a string representation of the Mage object.
   * @return Calls the super toString method and appends the age property and class.
   */
  @Override
  public String toString() {
    return super.toString() + ", age=" + age.getValue() + ", class=Mage";
  }
}

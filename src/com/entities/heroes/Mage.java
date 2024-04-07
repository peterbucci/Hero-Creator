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

  private static final int MIN_AGE = 1;
  private static final int MAX_AGE = 100;

  private StatValue age;

  public Mage(String name, int age) {
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
    this.age = new StatValue("Age", age, MIN_AGE, MAX_AGE);
  }

  public Mage() {
    this("Mage", MIN_AGE);
  }

  public StatValue getAge() {
    return age;
  }

  public void setAge(int age) {
    try {
      this.age.setValue(age);
    } catch (IllegalArgumentException ex) {
      throw ex;
    }
  }

  @Override
  public String wieldWeapon(Weapon weapon) {
    if (weapon.getType() == Weapon.Type.SPELL) {
      return name + " wielding " + weapon.getName();
    } else {
      return (
        name + " cannot wield " + weapon.getName() + " as it is not a spell."
      );
    }
  }

  @Override
  public String toString() {
    return super.toString() + ", age=" + age.getValue() + ", class=Mage";
  }
}

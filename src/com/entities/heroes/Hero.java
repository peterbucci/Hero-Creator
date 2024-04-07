package com.entities.heroes;

import com.entities.Entity;
import com.stats.StatValue;
import com.weapons.Weapon;
import java.util.ArrayList;

public abstract class Hero extends Entity {

  /**
   * I kept the follow properties in Hero class despite them being in the Entity class because
   * it was in the instructions for this assignment. Realistically, I would have removed them
   * from this class and used the ones in the Entity class.
   */
  protected String name; // This is the name of the hero
  protected StatValue strength; // This is the strength of the hero
  protected StatValue health; // This is the health of the hero
  protected ArrayList<Weapon> weapons; // This is the list of weapons in the hero's possession

  /**
   * This constructor initializes the Hero object with the specified values.
   * @param name The name of the hero
   * @param minStrength The minimum strength value for the hero
   * @param maxStrength The maximum strength value for the hero
   * @param minDexterity The minimum dexterity value for the hero
   * @param maxDexterity The maximum dexterity value for the hero
   * @param minConstitution The minimum constitution value for the hero
   * @param maxConstitution The maximum constitution value for the hero
   * @param minIntelligence The minimum intelligence value for the hero
   * @param maxIntelligence The maximum intelligence value for the hero
   * @param minWisdom The minimum wisdom value for the hero
   * @param maxWisdom The maximum wisdom value for the hero
   * @param minCharisma The minimum charisma value for the hero
   * @param maxCharisma The maximum charisma value for the hero
   */
  public Hero(
    String name,
    int minStrength,
    int maxStrength,
    int minDexterity,
    int maxDexterity,
    int minConstitution,
    int maxConstitution,
    int minIntelligence,
    int maxIntelligence,
    int minWisdom,
    int maxWisdom,
    int minCharisma,
    int maxCharisma
  ) {
    super(
      name,
      minStrength,
      maxStrength,
      minDexterity,
      maxDexterity,
      minConstitution,
      maxConstitution,
      minIntelligence,
      maxIntelligence,
      minWisdom,
      maxWisdom,
      minCharisma,
      maxCharisma
    );
  }

  /**
   * This method returns a string representation of the Hero object.
   * @return The name, type, strength, dexterity, constitution, intelligence, wisdom, charisma, and health of the hero.
   */
  @Override
  public String toString() {
    return (
      "name=" +
      name +
      ", type=Hero" +
      ", strength=" +
      strength.getValue() +
      ", dexterity=" +
      dexterity.getValue() +
      ", constitution=" +
      constitution.getValue() +
      ", intelligence=" +
      intelligence.getValue() +
      ", wisdom=" +
      wisdom.getValue() +
      ", charisma=" +
      charisma.getValue() +
      ", health=" +
      health.getValue()
    );
  }
}

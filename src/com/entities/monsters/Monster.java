package com.entities.monsters;

import com.entities.Entity;
import com.interfaces.Armable;
import com.stats.StatValue;
import com.weapons.Weapon;
import java.util.ArrayList;

public abstract class Monster extends Entity implements Armable {

  /*
   * I kept the follow properties in Monster class despite them being in the Entity class because
   * it was in the instructions for this assignment. Realistically, I would have removed them from
   * this class and used the ones in the Entity class.
   */
  protected String name; // This is the name of the monster
  protected StatValue health; // This is the health of the monster
  protected ArrayList<Weapon> weapons; // This is the list of weapons in the monster's possession
  // Unique to Monster
  protected String habitat; // This is the habitat the monster is found in

  /**
   * This constructor initializes the Monster object with the specified values.
   * @param name The name of the monster
   * @param habitat The habitat the monster is found in
   * @param minStrength The minimum strength value for the monster
   * @param maxStrength The maximum strength value for the monster
   * @param minDexterity The minimum dexterity value for the monster
   * @param maxDexterity The maximum dexterity value for the monster
   * @param minConstitution The minimum constitution value for the monster
   * @param maxConstitution The maximum constitution value for the monster
   * @param minIntelligence The minimum intelligence value for the monster
   * @param maxIntelligence The maximum intelligence value for the monster
   * @param minWisdom The minimum wisdom value for the monster
   * @param maxWisdom The maximum wisdom value for the monster
   * @param minCharisma The minimum charisma value for the monster
   * @param maxCharisma The maximum charisma value for the monster
   */
  public Monster(
    String name,
    String habitat,
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
    // Call the super constructor
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
    this.habitat = habitat; // Set the habitat
  }

  /**
   * This method allows the monster to wield a weapon.
   * @param weapon The weapon the monster is wielding
   */
  @Override
  public String wieldWeapon(Weapon weapon) {
    // Logic for wielding any weapon
    return name + "is wielding " + weapon.getName();
  }

  /**
   * This method gets the habitat of the monster.
   * @return The habitat of the monster
   */
  public String getHabitat() {
    return habitat;
  }

  /**
   * This method sets the habitat of the monster.
   * @param habitat The habitat of the monster
   */
  public void setHabitat(String habitat) {
    // Validate the habitat
    if (habitat == null || habitat.isEmpty()) {
      throw new IllegalArgumentException("Habitat cannot be empty"); // Throw an exception if the habitat is empty
    }

    this.habitat = habitat; // Set the habitat
  }

  /**
   * This method returns a string representation of the Monster object.
   * @return The name, type, strength, dexterity, constitution, intelligence, wisdom, charisma, health, and habitat of the monster.
   */
  @Override
  public String toString() {
    return (
      "name=" +
      name +
      ", type=Monster" +
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
      health.getValue() +
      ", habitat=" +
      habitat
    );
  }
}

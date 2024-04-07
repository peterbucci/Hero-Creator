package com.entities.monsters;

import com.entities.Entity;
import com.interfaces.Armable;
import com.stats.StatValue;
import com.weapons.Weapon;
import java.util.ArrayList;

public abstract class Monster extends Entity implements Armable {

  // Health constants
  private static final int BASE_HEALTH = 10;
  private static final int HEALTH_PER_CONSTITUTION = 2;
  private static final int MAX_HEALTH = 100;

  // Properties
  private String id;
  protected String name;
  // Attributes
  protected StatValue strength;
  protected StatValue dexterity;
  protected StatValue constitution;
  protected StatValue intelligence;
  protected StatValue wisdom;
  protected StatValue charisma;
  protected StatValue health;
  // Weapons
  protected ArrayList<Weapon> weapons;
  protected String habitat;

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
    this.name = name;
    this.weapons = new ArrayList<>();
    this.strength =
      new StatValue("Strength", minStrength, minStrength, maxStrength);
    this.dexterity =
      new StatValue("Dexterity", minDexterity, minDexterity, maxDexterity);
    this.constitution =
      new StatValue(
        "Constitution",
        minConstitution,
        minConstitution,
        maxConstitution
      );
    this.intelligence =
      new StatValue(
        "Intelligence",
        minIntelligence,
        minIntelligence,
        maxIntelligence
      );
    this.wisdom = new StatValue("Wisdom", minWisdom, minWisdom, maxWisdom);
    this.charisma =
      new StatValue("Charisma", minCharisma, minCharisma, maxCharisma);
    // Initialize health based on the constitution provided
    this.health = new StatValue("Health", BASE_HEALTH, BASE_HEALTH, MAX_HEALTH);
    updateHealth();
    this.habitat = habitat;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty");
    }
    this.name = name;
  }

  public StatValue getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    try {
      this.strength.setValue(strength);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  public StatValue getDexterity() {
    return dexterity;
  }

  public void setDexterity(int dexterity) {
    try {
      this.dexterity.setValue(dexterity);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  public StatValue getConstitution() {
    return constitution;
  }

  public void setConstitution(int constitution) {
    try {
      this.constitution.setValue(constitution);
      updateHealth(); // Update health based on new constitution
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  public StatValue getIntelligence() {
    return intelligence;
  }

  public void setIntelligence(int intelligence) {
    try {
      this.intelligence.setValue(intelligence);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  public StatValue getWisdom() {
    return wisdom;
  }

  public void setWisdom(int wisdom) {
    try {
      this.wisdom.setValue(wisdom);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  public StatValue getCharisma() {
    return charisma;
  }

  public void setCharisma(int charisma) {
    try {
      this.charisma.setValue(charisma);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  public StatValue[] getStats() {
    return new StatValue[] {
      strength,
      dexterity,
      constitution,
      intelligence,
      wisdom,
      charisma,
    };
  }

  public StatValue getHealth() {
    return health;
  }

  public void updateHealth() {
    int newHealth =
      BASE_HEALTH + constitution.getValue() * HEALTH_PER_CONSTITUTION;
    try {
      health.setValue(newHealth);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  public ArrayList<Weapon> getWeapons() {
    return weapons;
  }

  public void setWeapons(ArrayList<Weapon> weapons) {
    this.weapons = weapons;
  }

  public void addWeapon(Weapon weapon) {
    this.weapons.add(weapon);
  }

  @Override
  public String wieldWeapon(Weapon weapon) {
    // Logic for wielding any weapon
    return name + "is wielding " + weapon.getName();
  }

  public String getHabitat() {
    return habitat;
  }

  public void setHabitat(String habitat) {
    if (habitat == null || habitat.isEmpty()) {
      throw new IllegalArgumentException("Habitat cannot be empty");
    }

    this.habitat = habitat;
  }

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

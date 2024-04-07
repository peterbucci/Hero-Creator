package com.entities;

import com.stats.StatValue;
import com.weapons.Weapon;
import java.util.ArrayList;

public abstract class Entity implements Comparable<Entity> {

  // MIN values for each attribute
  protected static final int MIN_STRENGTH = 1;
  protected static final int MIN_DEXTERITY = 1;
  protected static final int MIN_CONSTITUTION = 1;
  protected static final int MIN_INTELLIGENCE = 1;
  protected static final int MIN_WISDOM = 1;
  protected static final int MIN_CHARISMA = 1;
  // MAX values for each attribute
  protected static final int MAX_STRENGTH = 10;
  protected static final int MAX_DEXTERITY = 10;
  protected static final int MAX_CONSTITUTION = 10;
  protected static final int MAX_INTELLIGENCE = 10;
  protected static final int MAX_WISDOM = 10;
  protected static final int MAX_CHARISMA = 10;
  // Health constants
  protected static final int BASE_HEALTH = 10;
  protected static final int HEALTH_PER_CONSTITUTION = 2;
  protected static final int MAX_HEALTH = 100;

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

  /**
   * This constructor initializes the Entity object with the specified values.
   * @param name The name of the entity
   * @param minStrength The minimum strength value for the entity
   * @param maxStrength The maximum strength value for the entity
   * @param minDexterity The minimum dexterity value for the entity
   * @param maxDexterity The maximum dexterity value for the entity
   * @param minConstitution The minimum constitution value for the entity
   * @param maxConstitution The maximum constitution value for the entity
   * @param minIntelligence The minimum intelligence value for the entity
   * @param maxIntelligence The maximum intelligence value for the entity
   * @param minWisdom The minimum wisdom value for the entity
   * @param maxWisdom The maximum wisdom value for the entity
   * @param minCharisma The minimum charisma value for the entity
   * @param maxCharisma The maximum charisma value for the entity
   */
  public Entity(
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
    this.name = name; // Set the name
    this.weapons = new ArrayList<>(); // Initialize the weapons list
    // Initialize the attributes
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
    this.health = new StatValue("Health", BASE_HEALTH, BASE_HEALTH, MAX_HEALTH);
    // Initialize health based on the constitution provided
    updateHealth();
  }

  /**
   * This method implements the Comparable interface to compare two entities based on their strength.
   * @param other The other entity to compare to.
   * @return An integer indicating the comparison result. 0 if equal, -1 if less than, 1 if greater than.
   */
  @Override
  public int compareTo(Entity other) {
    return Integer.compare(
      getStrength().getValue(),
      other.getStrength().getValue()
    );
  }

  /**
   * This method gets the ID of the entity.
   * @return The ID of the entity.
   */
  public String getId() {
    return id;
  }

  /**
   * This method sets the ID of the entity.
   * @param id The ID of the entity.
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * This method gets the name of the entity.
   * @return The name of the entity.
   */
  public String getName() {
    return name;
  }

  /**
   * This method sets the name of the entity.
   * @param name The name of the entity.
   */
  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be empty.");
    }

    this.name = name;
  }

  /**
   * This method gets the strength of the entity.
   * @return The strength of the entity as a StatValue object.
   */
  public StatValue getStrength() {
    return strength;
  }

  /**
   * This method sets the strength of the entity.
   * @param strength The strength of the entity.
   */
  public void setStrength(int strength) {
    try {
      this.strength.setValue(strength);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  /**
   * This method gets the dexterity of the entity.
   * @return The dexterity of the entity as a StatValue object.
   */
  public StatValue getDexterity() {
    return dexterity;
  }

  /**
   * This method sets the dexterity of the entity.
   * @param dexterity The dexterity of the entity.
   */
  public void setDexterity(int dexterity) {
    try {
      this.dexterity.setValue(dexterity);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  /**
   * This method gets the constitution of the entity.
   * @return The constitution of the entity as a StatValue object.
   */
  public StatValue getConstitution() {
    return constitution;
  }

  /**
   * This method sets the constitution of the entity.
   * @param constitution The constitution of the entity.
   */
  public void setConstitution(int constitution) {
    try {
      this.constitution.setValue(constitution);
      updateHealth(); // Update health based on new constitution
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  /**
   * This method gets the intelligence of the entity.
   * @return The intelligence of the entity as a StatValue object.
   */
  public StatValue getIntelligence() {
    return intelligence;
  }

  /**
   * This method sets the intelligence of the entity.
   * @param intelligence The intelligence of the entity.
   */
  public void setIntelligence(int intelligence) {
    try {
      this.intelligence.setValue(intelligence);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  /**
   * This method gets the wisdom of the entity.
   * @return The wisdom of the entity as a StatValue object.
   */
  public StatValue getWisdom() {
    return wisdom;
  }

  /**
   * This method sets the wisdom of the entity.
   * @param wisdom The wisdom of the entity.
   */
  public void setWisdom(int wisdom) {
    try {
      this.wisdom.setValue(wisdom);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  /**
   * This method gets the charisma of the entity.
   * @return The charisma of the entity as a StatValue object.
   */
  public StatValue getCharisma() {
    return charisma;
  }

  /**
   * This method sets the charisma of the entity.
   * @param charisma The charisma of the entity.
   */
  public void setCharisma(int charisma) {
    try {
      this.charisma.setValue(charisma);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  /**
   * This method gets all the stats of the entity.
   * @return An array of StatValue objects representing the stats of the entity.
   */
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

  /**
   * This method gets the health of the entity.
   * @return The health of the entity as a StatValue object.
   */
  public StatValue getHealth() {
    return health;
  }

  /**
   * This method sets the health of the entity based on
   * BASE_HEALTH, HEALTH_PER_CONSTITUTION, and constitution.
   */
  public void updateHealth() {
    int newHealth =
      BASE_HEALTH + constitution.getValue() * HEALTH_PER_CONSTITUTION;
    try {
      health.setValue(newHealth);
    } catch (IllegalArgumentException e) {
      throw e;
    }
  }

  /**
   * This method gets the weapons of the entity.
   * @return An ArrayList of Weapon objects representing the weapons of the entity.
   */
  public ArrayList<Weapon> getWeapons() {
    return weapons;
  }

  /**
   * This method sets the weapons of the entity.
   * @param weapons An ArrayList of Weapon objects representing the weapons of the entity.
   */
  public void setWeapons(ArrayList<Weapon> weapons) {
    this.weapons = weapons;
  }

  /**
   * This method adds a weapon to the entity.
   * @param weapon The weapon to add to the entity.
   */
  public void addWeapon(Weapon weapon) {
    this.weapons.add(weapon);
  }

  /**
   * This method gets the minimum value for strength.
   * @return The minimum value for strength.
   */
  public static int getMinStrength() {
    return MIN_STRENGTH;
  }

  /**
   * This method gets the minimum value for dexterity.
   * @return The minimum value for dexterity.
   */
  public static int getMinDexterity() {
    return MIN_DEXTERITY;
  }

  /**
   * This method gets the minimum value for constitution.
   * @return The minimum value for constitution.
   */
  public static int getMinConstitution() {
    return MIN_CONSTITUTION;
  }

  /**
   * This method gets the minimum value for intelligence.
   * @return The minimum value for intelligence.
   */
  public static int getMinIntelligence() {
    return MIN_INTELLIGENCE;
  }

  /**
   * This method gets the minimum value for wisdom.
   * @return The minimum value for wisdom.
   */
  public static int getMinWisdom() {
    return MIN_WISDOM;
  }

  /**
   * This method gets the minimum value for charisma.
   * @return The minimum value for charisma.
   */
  public static int getMinCharisma() {
    return MIN_CHARISMA;
  }

  /**
   * This method gets the maximum value for strength.
   * @return The maximum value for strength.
   */
  public static int getMaxStrength() {
    return MAX_STRENGTH;
  }

  /**
   * This method gets the maximum value for dexterity.
   * @return The maximum value for dexterity.
   */
  public static int getMaxDexterity() {
    return MAX_DEXTERITY;
  }

  /**
   * This method gets the maximum value for constitution.
   * @return The maximum value for constitution.
   */
  public static int getMaxConstitution() {
    return MAX_CONSTITUTION;
  }

  /**
   * This method gets the maximum value for intelligence.
   * @return The maximum value for intelligence.
   */
  public static int getMaxIntelligence() {
    return MAX_INTELLIGENCE;
  }

  /**
   * This method gets the maximum value for wisdom.
   * @return The maximum value for wisdom.
   */
  public static int getMaxWisdom() {
    return MAX_WISDOM;
  }

  /**
   * This method gets the maximum value for charisma.
   * @return The maximum value for charisma.
   */
  public static int getMaxCharisma() {
    return MAX_CHARISMA;
  }
}

package com.weapons;

public class Weapon {

  // Declare the fields for the Weapon class
  private String name; // Name of the weapon
  private int power; // Power of the weapon
  private String ability; // Ability of the weapon
  private Type type; // Type of the weapon

  // Enum for the type of weapon
  public enum Type {
    SPELL,
    EXPLOSIVE,
    STAFF,
    CUDGEL,
    THROWING_DEVICE,
  }

  /**
   * Constructor for the Weapon class.
   * @param name Name of the weapon
   * @param power Power of the weapon
   * @param ability Ability of the weapon
   * @param type Type of the weapon
   */
  public Weapon(String name, int power, String ability, Type type) {
    this.name = name;
    this.power = power;
    this.ability = ability;
    this.type = type;
  }

  /**
   * Get the name of the weapon.
   * @return Name of the weapon
   */
  public String getName() {
    return name;
  }

  /**
   * Set the name of the weapon.
   * @param name Name of the weapon
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the power of the weapon.
   * @return Power of the weapon
   */
  public int getPower() {
    return power;
  }

  /**
   * Set the power of the weapon.
   * @param power Power of the weapon
   */
  public void setPower(int power) {
    this.power = power;
  }

  /**
   * Get the ability of the weapon.
   * @return Ability of the weapon
   */
  public String getAbility() {
    return ability;
  }

  /**
   * Set the ability of the weapon.
   * @param ability Ability of the weapon
   */
  public void setAbility(String ability) {
    this.ability = ability;
  }

  /**
   * Get the type of the weapon.
   * @return Type of the weapon
   */
  public Type getType() {
    return type;
  }

  /**
   * Set the type of the weapon.
   * @param type Type of the weapon
   */
  public void setType(Type type) {
    this.type = type;
  }
}

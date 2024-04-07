package com;

public class Weapon {

  private String name;
  private int power;
  private String ability;
  private Type type;

  public enum Type {
    SPELL,
    EXPLOSIVE,
    STAFF,
    CUDGEL,
    THROWING_DEVICE,
  }

  public Weapon(String name, int power, String ability, Type type) {
    this.name = name;
    this.power = power;
    this.ability = ability;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPower() {
    return power;
  }

  public void setPower(int power) {
    this.power = power;
  }

  public String getAbility() {
    return ability;
  }

  public void setAbility(String ability) {
    this.ability = ability;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }
}

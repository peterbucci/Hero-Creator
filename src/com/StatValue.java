package com;

public class StatValue {

  public static final StatValue[] DEFAULT_VALUES = {
    new StatValue("Strength", 0, 0, 0),
    new StatValue("Dexterity", 0, 0, 0),
    new StatValue("Constitution", 0, 0, 0),
    new StatValue("Intelligence", 0, 0, 0),
    new StatValue("Wisdom", 0, 0, 0),
    new StatValue("Charisma", 0, 0, 0),
  };

  private String name;
  private int value;
  private final int minValue;
  private final int maxValue;

  public StatValue(String name, int initialValue, int minValue, int maxValue) {
    this.name = name;
    this.minValue = minValue;
    this.maxValue = maxValue;
    setValue(initialValue);
  }

  public void increase() {
    if (value < maxValue) {
      value++;
    } else {
      throw new IllegalArgumentException(name + " cannot exceed " + maxValue);
    }
  }

  public void decrease() {
    if (value > minValue) {
      value--;
    } else {
      throw new IllegalArgumentException(
        name + " cannot be less than " + minValue
      );
    }
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    if (value < minValue || value > maxValue) {
      throw new IllegalArgumentException(
        name + " must be between " + minValue + " and " + maxValue
      );
    }

    this.value = value;
  }

  public int getMinValue() {
    return minValue;
  }

  public int getMaxValue() {
    return maxValue;
  }
}

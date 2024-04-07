package com.stats;

public class StatValue {

  // Default values for the six basic stats
  public static final StatValue[] DEFAULT_VALUES = {
    new StatValue("Strength", 0, 0, 0),
    new StatValue("Dexterity", 0, 0, 0),
    new StatValue("Constitution", 0, 0, 0),
    new StatValue("Intelligence", 0, 0, 0),
    new StatValue("Wisdom", 0, 0, 0),
    new StatValue("Charisma", 0, 0, 0),
  };

  // Properties
  private String name; // Name of the stat
  private int value; // Current value of the stat
  private final int minValue; // Minimum value of the stat
  private final int maxValue; // Maximum value of the stat

  /**
   * This constructor initializes a new instance of StatValue with the specified
   * @param name Name of the stat
   * @param initialValue Initial value of the stat
   * @param minValue Minimum value of the stat
   * @param maxValue Maximum value of the stat
   */
  public StatValue(String name, int initialValue, int minValue, int maxValue) {
    this.name = name;
    this.minValue = minValue;
    this.maxValue = maxValue;
    setValue(initialValue);
  }

  /**
   * This method increases the value of the stat by 1. If the value exceeds the
   * maximum value, an IllegalArgumentException is thrown.
   * @throws IllegalArgumentException If the value exceeds the maximum value
   */
  public void increase() {
    if (value < maxValue) { // If the value is less than the maximum value
      value++; // Increase the value by 1
    } else { // If the value is equal to the maximum value
      // Throw an IllegalArgumentException
      throw new IllegalArgumentException(name + " cannot exceed " + maxValue);
    }
  }

  /**
   * This method decreases the value of the stat by 1. If the value is less than
   * the minimum value, an IllegalArgumentException is thrown.
   * @throws IllegalArgumentException If the value is less than the minimum value
   */
  public void decrease() {
    if (value > minValue) { // If the value is greater than the minimum value
      value--; // Decrease the value by 1
    } else { // If the value is equal to the minimum value
      // Throw an IllegalArgumentException
      throw new IllegalArgumentException(
        name + " cannot be less than " + minValue
      );
    }
  }

  /**
   * This method returns the name of the stat.
   * @return Name of the stat
   */
  public String getName() {
    return name;
  }

  /**
   * This method sets the name of the stat to the specified value.
   * @param name Name of the stat
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * This method returns the current value of the stat.
   * @return Current value of the stat
   */
  public int getValue() {
    return value;
  }

  /**
   * This method sets the value of the stat to the specified value. If the value
   * is less than the minimum value or greater than the maximum value, an
   * IllegalArgumentException is thrown.
   * @param value Value of the stat
   * @throws IllegalArgumentException If the value is less than the minimum value
   * or greater than the maximum value
   */
  public void setValue(int value) {
    if (value < minValue || value > maxValue) { // If the value is out of range
      // Throw an IllegalArgumentException
      throw new IllegalArgumentException(
        name + " must be between " + minValue + " and " + maxValue
      );
    }

    this.value = value; // Set the value to the specified value
  }

  /**
   * This method returns the minimum value of the stat.
   * @return Minimum value of the stat
   */
  public int getMinValue() {
    return minValue;
  }

  /**
   * This method returns the maximum value of the stat.
   * @return Maximum value of the stat
   */
  public int getMaxValue() {
    return maxValue;
  }
}

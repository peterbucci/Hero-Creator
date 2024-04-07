package com.entities;

import com.StatValue;
import com.Weapon;
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

  @Override
  public int compareTo(Entity other) {
    return Integer.compare(
      getStrength().getValue(),
      other.getStrength().getValue()
    );
  }

  public abstract String getId();

  public abstract void setId(String id);

  public abstract String getName();

  public abstract StatValue getStrength();

  public abstract StatValue getDexterity();

  public abstract StatValue getConstitution();

  public abstract StatValue getIntelligence();

  public abstract StatValue getWisdom();

  public abstract StatValue getCharisma();

  public abstract void setStrength(int strength);

  public abstract void setDexterity(int dexterity);

  public abstract void setConstitution(int constitution);

  public abstract void setIntelligence(int intelligence);

  public abstract void setWisdom(int wisdom);

  public abstract void setCharisma(int charisma);

  public abstract StatValue[] getStats();

  public abstract void updateHealth();

  public abstract ArrayList<Weapon> getWeapons();

  public abstract void setWeapons(ArrayList<Weapon> weapons);

  public abstract void addWeapon(Weapon weapon);

  public static int getMinStrength() {
    return MIN_STRENGTH;
  }

  public static int getMinDexterity() {
    return MIN_DEXTERITY;
  }

  public static int getMinConstitution() {
    return MIN_CONSTITUTION;
  }

  public static int getMinIntelligence() {
    return MIN_INTELLIGENCE;
  }

  public static int getMinWisdom() {
    return MIN_WISDOM;
  }

  public static int getMinCharisma() {
    return MIN_CHARISMA;
  }

  public static int getMaxStrength() {
    return MAX_STRENGTH;
  }

  public static int getMaxDexterity() {
    return MAX_DEXTERITY;
  }

  public static int getMaxConstitution() {
    return MAX_CONSTITUTION;
  }

  public static int getMaxIntelligence() {
    return MAX_INTELLIGENCE;
  }

  public static int getMaxWisdom() {
    return MAX_WISDOM;
  }

  public static int getMaxCharisma() {
    return MAX_CHARISMA;
  }
}

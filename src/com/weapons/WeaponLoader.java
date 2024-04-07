package com.weapons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WeaponLoader {

  /**
   * Load weapons from a file.
   * @param filename Name of the file to load weapons from
   * @return List of weapons loaded from the file
   */
  public static List<Weapon> loadWeapons(String filename) {
    List<Weapon> weapons = new ArrayList<>(); // Create a new list of weapons
    File file = new File(filename); // Create a new file object

    /*
     * Try to read the file line by line using a Scanner. Parse the attributes
     * of each line into a map of key-value pairs. Create a new weapon object
     * from the attributes and add it to the list of weapons.
     */
    try (Scanner scanner = new Scanner(file)) {
      // Read the file line by line
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine(); // Read the next line
        Map<String, String> attributes = parseAttributes(line); // Parse the attributes
        Weapon weapon = createWeaponFromAttributes(attributes); // Create a weapon from the attributes
        // Add the weapon to the list of weapons if it is not null
        if (weapon != null) {
          weapons.add(weapon);
        }
      }
    } catch (FileNotFoundException e) { // Catch file not found exception
      // Print an error message if the file is not found
      System.err.println("Could not find the file: " + filename);
    }

    return weapons;
  }

  /**
   * Parse the attributes of a weapon from a line of text.
   * @param line Line of text containing the attributes of a weapon
   * @return Map of key-value pairs representing the attributes of the weapon
   */
  private static Map<String, String> parseAttributes(String line) {
    Map<String, String> attributes = new HashMap<>(); // Create a new map of attributes
    String[] parts = line.split(", "); // Split the line into parts using a comma and space as the delimiter
    // Iterate over the parts and split each part into a key-value pair
    for (String part : parts) {
      String[] keyValue = part.split("="); // Split the part into a key-value pair using an equals sign as the delimiter
      // Add the key-value pair to the map of attributes if it contains two elements
      if (keyValue.length == 2) {
        attributes.put(keyValue[0].trim(), keyValue[1].trim());
      }
    }
    return attributes;
  }

  /**
   * Create a weapon object from a map of attributes.
   * @param attributes Map of key-value pairs representing the attributes of the weapon
   * @return Weapon object created from the attributes
   */
  private static Weapon createWeaponFromAttributes(
    Map<String, String> attributes
  ) {
    /*
     * Try to create a new weapon object from the attributes. If an exception
     * occurs during parsing, print an error message and return null.
     */
    try {
      String name = attributes.get("name"); // Get the name attribute
      int power = Integer.parseInt(attributes.get("power")); // Parse the power attribute as an integer
      String ability = attributes.get("ability"); // Get the ability attribute
      Weapon.Type type = Weapon.Type.valueOf(attributes.get("type")); // Parse the type attribute as a Weapon.Type enum
      // Create a new weapon object with the parsed attributes
      return new Weapon(name, power, ability, type);
    } catch (IllegalArgumentException e) { // Catch illegal argument exception
      // Print an error message if an exception occurs during parsing
      System.err.println("Error parsing weapon attributes: " + e.getMessage());
      return null;
    }
  }
}

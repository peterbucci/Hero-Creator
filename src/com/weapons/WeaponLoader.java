package com.weapons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WeaponLoader {

  public static List<Weapon> loadWeapons(String filename) {
    List<Weapon> weapons = new ArrayList<>();
    File file = new File(filename);

    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        Map<String, String> attributes = parseAttributes(line);
        Weapon weapon = createWeaponFromAttributes(attributes);
        if (weapon != null) {
          weapons.add(weapon);
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("Could not find the file: " + filename);
    }

    return weapons;
  }

  private static Map<String, String> parseAttributes(String line) {
    Map<String, String> attributes = new HashMap<>();
    String[] parts = line.split(", ");
    for (String part : parts) {
      String[] keyValue = part.split("=");
      if (keyValue.length == 2) {
        attributes.put(keyValue[0].trim(), keyValue[1].trim());
      }
    }
    return attributes;
  }

  private static Weapon createWeaponFromAttributes(
    Map<String, String> attributes
  ) {
    try {
      String name = attributes.get("name");
      int power = Integer.parseInt(attributes.get("power"));
      String ability = attributes.get("ability");
      Weapon.Type type = Weapon.Type.valueOf(attributes.get("type"));
      return new Weapon(name, power, ability, type);
    } catch (IllegalArgumentException e) {
      System.err.println("Error parsing weapon attributes: " + e.getMessage());
      return null;
    }
  }
}

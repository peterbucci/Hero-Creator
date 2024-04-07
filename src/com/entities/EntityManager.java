package com.entities;

import com.entities.heroes.*;
import com.entities.monsters.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.scene.control.ListView;

public class EntityManager {

  private List<Entity> entities; // List of all entities
  private Map<String, String> nameToIdMap; // Map of entity names to entity IDs
  private ListView<String> entityListView; // ListView for displaying entity names

  /**
   * This constructor initializes the EntityManager object with the specified ListView.
   * @param entityListView The ListView for displaying entity names
   */
  public EntityManager(ListView<String> entityListView) {
    this.entityListView = entityListView; // Set the entityListView property
    this.nameToIdMap = new HashMap<>(); // Initialize the nameToIdMap
    this.entities = new ArrayList<>(); // Initialize the entities list
  }

  /**
   * This method refreshes the entity list by loading entities from a file.
   */
  public void refreshEntityList() {
    entities = loadEntities(); // Load entities from file
    entityListView.getItems().clear(); // Clear the ListView
    nameToIdMap.clear(); // Clear the nameToIdMap

    // Add entity names to the ListView and populate the nameToIdMap
    for (Entity entity : entities) {
      entityListView.getItems().add(entity.getName());
      nameToIdMap.put(entity.getName(), entity.getId());
    }
  }

  /**
   * This method loads entities from a file and returns a list of entities.
   * @return A list of entities
   */
  private List<Entity> loadEntities() {
    List<Entity> entities = new ArrayList<>(); // Initialize the list of entities
    File file = new File("peterbucci.txt"); // Create a File object
    /*
     * Use a try-with-resources block to create a Scanner object for reading the file.
     * Read each line from the file and parse it to create an Entity object.
     * Add the Entity object to the list of entities.
     */
    try (Scanner scanner = new Scanner(file)) {
      // Read each line from the file
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine(); // Read the line
        Entity entity = parseEntity(line); // Parse the line to create an Entity object
        if (entity != null) { // Check if the Entity object is not null
          entities.add(entity); // Add the Entity object to the list of entities
        }
      }
    } catch (FileNotFoundException e) { // Catch FileNotFoundException
      e.printStackTrace(); // Handle missing file scenario
    }
    return entities;
  }

  /**
   * This method parses a line from the file to create an Entity object.
   * @param line The line to parse
   * @return An Entity object
   */
  private static Entity parseEntity(String line) {
    //Split the line by ", " to get the attributes of the Entity.
    try {
      String[] parts = line.split(", "); // Split the line by ", "
      Map<String, String> attributes = new HashMap<>(); // Create a map to store the attributes
      // Iterate over the parts to split each part by "=" and add it to the map
      for (String part : parts) {
        String[] keyValue = part.split("="); // Split the part by "="
        if (keyValue.length == 2) { // Check if the length is 2
          attributes.put(keyValue[0].trim(), keyValue[1].trim()); // Add the key-value pair to the map
        }
      }

      String entityClass = attributes.get("class"); // Get the class of the Entity
      Entity entity = null; // Initialize the Entity object
      switch (entityClass) { // Switch on the entity class
        case "Mage": // If the class is Mage create a Mage object
          entity =
            new Mage(
              attributes.get("name"),
              Integer.parseInt(attributes.get("age"))
            );
          break;
        case "Faerie": // If the class is Faerie create a Faerie object
          entity =
            new Faerie(
              attributes.get("name"),
              Integer.parseInt(attributes.get("height"))
            );
          break;
        case "Warrior": // If the class is Warrior create a Warrior object
          entity =
            new Warrior(
              attributes.get("name"),
              Boolean.parseBoolean(attributes.get("hasTransport"))
            );
          break;
        case "Ogre": // If the class is Ogre create an Ogre object
          entity =
            new Ogre(
              attributes.get("name"),
              attributes.get("habitat"),
              Boolean.parseBoolean(attributes.get("hasScales"))
            );
          break;
        case "Troll": // If the class is Troll create a Troll object
          entity =
            new Troll(
              attributes.get("name"),
              attributes.get("habitat"),
              Integer.parseInt(attributes.get("height"))
            );
          break;
      }

      /*
       * If the Entity object is not null, set the attributes of the Entity.
       */
      if (entity != null) {
        entity.setId(attributes.get("id"));
        entity.setStrength(Integer.parseInt(attributes.get("strength")));
        entity.setDexterity(Integer.parseInt(attributes.get("dexterity")));
        entity.setConstitution(
          Integer.parseInt(attributes.get("constitution"))
        );
        entity.setIntelligence(
          Integer.parseInt(attributes.get("intelligence"))
        );
        entity.setWisdom(Integer.parseInt(attributes.get("wisdom")));
        entity.setCharisma(Integer.parseInt(attributes.get("charisma")));
        entity.updateHealth(); // Update the health of the Entity
      }

      return entity;
    } catch (Exception e) { // Catch any exceptions
      e.printStackTrace();
      return null; // Return null if an exception occurs
    }
  }

  /**
   * This method finds an Entity by its ID.
   * @param id The ID of the Entity
   * @return The Entity object
   */
  public Entity findEntityById(String id) {
    for (Entity entity : entities) { // Iterate over the entities list
      if (entity.getId().equals(id)) { // Check if the ID matches
        return entity; // Return the Entity object
      }
    }
    return null; // Return null if the Entity is not found
  }

  /**
   * This method finds an Entity by its name.
   * @param name The name of the Entity
   * @return The Entity object
   */
  public String getEntityIdByName(String name) {
    return nameToIdMap.get(name);
  }

  /**
   * This method returns the list of entities.
   * @return The list of entities
   */
  public List<Entity> getEntities() {
    return entities;
  }
}

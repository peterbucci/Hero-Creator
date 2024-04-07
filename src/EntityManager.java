import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.scene.control.ListView;

public class EntityManager {

  private List<Entity> entities;
  private Map<String, String> nameToIdMap;
  private ListView<String> entityListView;

  public EntityManager(ListView<String> entityListView) {
    this.entityListView = entityListView;
    this.nameToIdMap = new HashMap<>();
    this.entities = new ArrayList<>();
  }

  public void refreshEntityList() {
    entities = loadEntities();
    entityListView.getItems().clear();
    nameToIdMap.clear();

    for (Entity entity : entities) {
      entityListView.getItems().add(entity.getName());
      nameToIdMap.put(entity.getName(), entity.getId());
    }
  }

  private List<Entity> loadEntities() {
    List<Entity> entities = new ArrayList<>();
    File file = new File("peterbucci.txt");
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        Entity entity = parseEntity(line);
        if (entity != null) {
          entities.add(entity);
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace(); // Handle missing file scenario
    }
    return entities;
  }

  private static Entity parseEntity(String line) {
    try {
      String[] parts = line.split(", ");
      Map<String, String> attributes = new HashMap<>();
      for (String part : parts) {
        String[] keyValue = part.split("=");
        if (keyValue.length == 2) {
          attributes.put(keyValue[0].trim(), keyValue[1].trim());
        }
      }

      String entityClass = attributes.get("class");
      Entity entity = null;
      switch (entityClass) {
        case "Mage":
          entity =
            new Mage(
              attributes.get("name"),
              Integer.parseInt(attributes.get("age"))
            );
          break;
        case "Faerie":
          entity =
            new Faerie(
              attributes.get("name"),
              Integer.parseInt(attributes.get("height"))
            );
          break;
        case "Warrior":
          entity =
            new Warrior(
              attributes.get("name"),
              Boolean.parseBoolean(attributes.get("hasTransport"))
            );
          break;
        case "Ogre":
          entity =
            new Ogre(
              attributes.get("name"),
              attributes.get("habitat"),
              Boolean.parseBoolean(attributes.get("hasScales"))
            );
          break;
        case "Troll":
          entity =
            new Troll(
              attributes.get("name"),
              attributes.get("habitat"),
              Integer.parseInt(attributes.get("height"))
            );
          break;
      }

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
        entity.updateHealth();
      }

      return entity;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public Entity findEntityById(String id) {
    for (Entity entity : entities) {
      if (entity.getId().equals(id)) {
        return entity;
      }
    }
    return null;
  }

  public String getEntityIdByName(String name) {
    return nameToIdMap.get(name);
  }

  public List<Entity> getEntities() {
    return entities;
  }
}

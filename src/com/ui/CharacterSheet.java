package com.ui;

import com.entities.Entity;
import com.weapons.Weapon;
import com.weapons.WeaponLoader;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CharacterSheet {

  // Create the Entity object and list of Entity objects
  private Entity entity;
  private List<Entity> entities;

  /**
   * Constructor for the CharacterSheet class.
   * @param entity Entity object to display the character sheet for
   * @param entities List of Entity objects to compare the character sheet with
   */
  public CharacterSheet(Entity entity, List<Entity> entities) {
    this.entity = entity;
    this.entities = entities;
    setupStage(); // Setup the stage for the character sheet
  }

  /**
   * Setup the stage for the character sheet.
   */
  private void setupStage() {
    /*
     * Create a new stage for the character sheet. Set the title and modality
     * of the stage. Modality.APPLICATION_MODAL blocks events from being
     * delivered to any other application window.
     */
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Character Sheet");
    List<String> comparisons = new ArrayList<>();

    // Compare the entity with each entity in the list
    for (Entity entity : entities) {
      int comparison = this.entity.compareTo(entity); // Compare the entities
      if (entity == this.entity) continue; // Skip self-comparison
      if (comparison == 0) {
        // Handle entity equal to current entity
        comparisons.add(
          this.entity.getName() + " is equal in strength to " + entity.getName()
        );
      } else if (comparison < 0) {
        // Handle entity less than current entity
        comparisons.add(
          this.entity.getName() + " is weaker than " + entity.getName()
        );
      } else {
        // Handle entity greater than current entity
        comparisons.add(
          this.entity.getName() + " is stronger than " + entity.getName()
        );
      }
    }

    // Create a VBox layout to hold the character sheet information
    VBox layout = new VBox(10);
    layout
      .getChildren()
      .addAll(
        new Label("Name: " + entity.getName()), // Add the entity name
        new Label("ID: " + entity.getId()) // Add the entity ID
      );

    // Add the comparison labels to the layout
    layout
      .getChildren()
      .addAll(comparisons.stream().map(Label::new).toArray(Label[]::new));

    // Create a button to add all the weapons to the entity
    Button addWeapons = new Button("Collect Weapons");
    // Set the action for the button when clicked loading the weapons from a file and add them to the entity
    addWeapons.setOnAction(e -> {
      ArrayList<Weapon> weapons = WeaponLoader.loadWeapons("weapons.txt");
      entity.setWeapons(weapons);

      // display a popup message when the weapons are added
      Stage popup = new Stage();
      popup.initModality(Modality.APPLICATION_MODAL);
      popup.setTitle("Weapons Collected");
      VBox popupLayout = new VBox(10);
      popupLayout
        .getChildren()
        .add(
          new Label("Weapons added to " + entity.getName() + "'s inventory")
        );
      Scene popupScene = new Scene(popupLayout, 200, 100);
      popup.setScene(popupScene);
      popup.show();
    });

    // Create a button to show the inventory of the entity
    Button showInventory = new Button("Show Inventory");
    // Set the action for the button when clicked showing the inventory of the entity
    showInventory.setOnAction(e -> showInventory());

    // Add the button to the layout
    layout.getChildren().addAll(addWeapons, showInventory);

    // Create a new scene with the layout and set the scene on the stage
    Scene scene = new Scene(layout, 300, 250);
    stage.setScene(scene);
    stage.show();
  }

  private void showInventory() {
    // Create a new stage for the inventory
    Stage inventoryStage = new Stage();
    inventoryStage.initModality(Modality.APPLICATION_MODAL);
    inventoryStage.setTitle("Inventory");

    // Create a VBox layout to hold the inventory information
    VBox layout = new VBox(10);
    layout.getChildren().add(new Label("Inventory:"));

    // List the weapons of the entity
    for (Weapon weapon : entity.getWeapons()) {
      Button equipButton = new Button("Equip");
      Label weaponLabel = new Label(weapon.getName());
      HBox weaponBox = new HBox(10);
      weaponBox.getChildren().addAll(weaponLabel, equipButton);

      // Set the action for the equip button when clicked
      equipButton.setOnAction(e -> {
        String message = entity.wieldWeapon(weapon);
        // Display a popup message when the weapon is equipped
        Stage popup = new Stage();
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("Message");
        VBox popupLayout = new VBox(10);
        popupLayout.getChildren().add(new Label(message));
        Scene popupScene = new Scene(popupLayout, 200, 100);
        popup.setScene(popupScene);
        popup.show();
      });

      layout.getChildren().add(weaponBox);
    }

    // Create a new scene with the layout and set the scene on the stage
    Scene scene = new Scene(layout, 200, 200);
    inventoryStage.setScene(scene);
    inventoryStage.show();
  }
}

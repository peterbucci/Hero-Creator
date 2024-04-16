/**
 * @Author: Peter Bucci
 * @Filename: App.java
 * @Date: 04/04/2024
 * @Description: This application allows the user to create and manage entities for a fantasy game.
 * The user can create new entities, view the details of an entity, and edit the attributes of an entity.
 * The application uses JavaFX for the user interface and stores the entities in a txt file.
 *
 * Add the weapons.txt file to the root directory of the project to load the weapons data.
 */

package com;

import com.entities.Entity;
import com.entities.EntityManager;
import com.ui.CharacterSheet;
import com.ui.EntityCreationForm;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

  // Constants for the width and height of the window
  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;
  // Entity manager to manage the entities
  private EntityManager entityManager;

  /**
   * Start the application.
   * @param primaryStage The primary stage of the application
   */
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Fantasy Game"); // Set the title of the window

    // Create a list view to display the entities
    ListView<String> entityListView = new ListView<>();
    entityManager = new EntityManager(entityListView);
    entityManager.refreshEntityList(); // Refresh the list of entities

    // Add a listener to the list view to show the details of the selected entity
    entityListView
      .getSelectionModel()
      .selectedItemProperty()
      .addListener((observable, oldValue, newValue) -> {
        String selectedHeroId = entityManager.getEntityIdByName(newValue);
        showEntityDetails(entityManager.findEntityById(selectedHeroId));
      });

    /*
     * Create a button to create a new entity. When the button is clicked, a new
     * entity creation form is displayed.
     */
    Button createEntityButton = new Button("Create Entity");
    createEntityButton.setOnAction(e -> {
      new EntityCreationForm(entityManager::refreshEntityList);
    });

    // Create an HBox layout to display the list view and the create entity button
    HBox layout = new HBox(10);
    layout.getChildren().addAll(entityListView, createEntityButton);

    // Create a scene with the layout and set it on the primary stage
    Scene scene = new Scene(layout, WIDTH, HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Show the details of an entity in a character sheet.
   * @param entity Entity to show the details of
   */
  private void showEntityDetails(Entity entity) {
    if (entity == null) return;
    new CharacterSheet(entity, entityManager.getEntities());
  }

  /**
   * Launch the application.
   * @param args Command line arguments
   */
  public static void main(String[] args) {
    launch(args);
  }
}

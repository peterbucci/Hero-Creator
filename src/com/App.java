/**
 * @Author: Peter Bucci
 * @Filename: App.java
 * @Date: 04/04/2024
 * @Description: This is the main class that launches the application.
 */

package com;

import com.entities.Entity;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

  private static final int WIDTH = 800;
  private static final int HEIGHT = 600;

  private EntityManager entityManager;

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Fantasy Game");

    ListView<String> entityListView = new ListView<>();
    entityManager = new EntityManager(entityListView);
    entityManager.refreshEntityList();

    entityListView
      .getSelectionModel()
      .selectedItemProperty()
      .addListener((observable, oldValue, newValue) -> {
        String selectedHeroId = entityManager.getEntityIdByName(newValue);
        showEntityDetails(entityManager.findEntityById(selectedHeroId));
      });

    Button createEntityButton = new Button("Create Entity");
    createEntityButton.setOnAction(e -> {
      new EntityCreationForm(entityManager::refreshEntityList);
    });

    HBox layout = new HBox(10);
    layout.getChildren().addAll(entityListView, createEntityButton);

    Scene scene = new Scene(layout, WIDTH, HEIGHT);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void showEntityDetails(Entity entity) {
    if (entity == null) return;
    new CharacterSheet(entity, entityManager.getEntities());
  }

  public static void main(String[] args) {
    launch(args);
  }
}

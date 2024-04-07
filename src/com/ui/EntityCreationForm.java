package com.ui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EntityCreationForm {

  // Create the HeroCreationPanel and MonsterCreationPanel objects
  private HeroCreationPanel heroCreationPanel;
  private MonsterCreationPanel monsterCreationPanel;
  // Runnable to be executed when the form is closed
  private Runnable onFormClose;

  /**
   * Constructor for the EntityCreationForm class.
   * @param onFormClose Runnable to be executed when the form is closed
   */
  public EntityCreationForm(Runnable onFormClose) {
    this.onFormClose = onFormClose;
    display(); // Display the form
  }

  /**
   * Display the entity creation form. The form stage contains a TabPane with
   * two tabs for Heroes and Monsters.
   */
  public void display() {
    /*
     * Create a new stage for the entity creation form.
     * Set the title and modality of the stage. Modality.APPLICATION_MODAL
     * blocks events from being delivered to any other application window.
     */
    Stage formStage = new Stage();
    formStage.initModality(Modality.APPLICATION_MODAL);
    formStage.setTitle("Create Entity");

    // Create the HeroCreationPanel and MonsterCreationPanel objects
    heroCreationPanel = new HeroCreationPanel();
    monsterCreationPanel = new MonsterCreationPanel();

    // Create a TabPane to hold the Hero and Monster creation panels
    TabPane tabPane = new TabPane();
    tabPane
      .getTabs()
      .addAll(
        createTab("Heroes", heroCreationPanel.createHeroLayout()),
        createTab("Monsters", monsterCreationPanel.createMonsterLayout())
      );

    // Set the close handlers for the Hero and Monster creation panels
    heroCreationPanel.setCloseHandler(() -> {
      formStage.close(); // Close the form stage
      if (onFormClose != null) {
        onFormClose.run(); // Execute the onFormClose runnable
      }
    });

    // Set the close handler for the Monster creation panel
    monsterCreationPanel.setCloseHandler(() -> {
      formStage.close(); // Close the form stage
      if (onFormClose != null) {
        onFormClose.run(); // Execute the onFormClose runnable
      }
    });

    // Create a new scene with the TabPane and set it to the form stage
    Scene scene = new Scene(tabPane, 600, 500);
    formStage.setScene(scene);
    formStage.showAndWait(); // Display the form stage and wait for it to close
  }

  /**
   * Create a new tab with the specified title and content.
   * @param title The title of the tab
   * @param content The content of the tab
   * @return Tab The created tab
   */
  private Tab createTab(String title, Node content) {
    Tab tab = new Tab();
    tab.setText(title);
    tab.setClosable(false);
    tab.setContent(content);
    tab.setStyle("-fx-cursor: hand;");
    return tab;
  }
}

package com.ui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EntityCreationForm {

  private HeroCreationPanel heroCreationPanel;
  private MonsterCreationPanel monsterCreationPanel;
  private Runnable onFormClose;

  public EntityCreationForm(Runnable onFormClose) {
    this.onFormClose = onFormClose;
    display();
  }

  public void display() {
    Stage formStage = new Stage();
    formStage.initModality(Modality.APPLICATION_MODAL);
    formStage.setTitle("Create Entity");

    heroCreationPanel = new HeroCreationPanel();
    monsterCreationPanel = new MonsterCreationPanel();

    TabPane tabPane = new TabPane();
    tabPane
      .getTabs()
      .addAll(
        createTab("Heroes", heroCreationPanel.createHeroLayout()),
        createTab("Monsters", monsterCreationPanel.createMonsterLayout())
      );

    heroCreationPanel.setCloseHandler(() -> {
      formStage.close();
      if (onFormClose != null) {
        onFormClose.run();
      }
    });

    monsterCreationPanel.setCloseHandler(() -> {
      formStage.close();
      if (onFormClose != null) {
        onFormClose.run();
      }
    });

    Scene scene = new Scene(tabPane, 600, 500);
    formStage.setScene(scene);
    formStage.showAndWait();
  }

  private Tab createTab(String title, Node content) {
    Tab tab = new Tab();
    tab.setText(title);
    tab.setClosable(false);
    tab.setContent(content);
    tab.setStyle("-fx-cursor: hand;");
    return tab;
  }
}

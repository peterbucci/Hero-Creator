import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CharacterSheet {

  private Entity entity;
  private List<Entity> entities;

  public CharacterSheet(Entity entity, List<Entity> entities) {
    this.entity = entity;
    this.entities = entities;
    setupStage();
  }

  private void setupStage() {
    Stage stage = new Stage();
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Character Sheet");
    List<String> comparisons = new ArrayList<>();

    for (Entity entity : entities) {
      int comparison = this.entity.compareTo(entity);
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

    VBox layout = new VBox(10);
    layout
      .getChildren()
      .addAll(
        new Label("Name: " + entity.getName()),
        new Label("ID: " + entity.getId())
      );

    layout
      .getChildren()
      .addAll(comparisons.stream().map(Label::new).toArray(Label[]::new));

    Scene scene = new Scene(layout, 300, 250);
    stage.setScene(scene);
    stage.show();
  }
}

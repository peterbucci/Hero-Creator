package com.ui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class SwitchButton extends StackPane {

  // Components
  private final Rectangle back = new Rectangle(30, 10, Color.RED); // Background
  private final Button button = new Button(); // Button
  // Styles
  private final String buttonStyleOff =
    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: WHITE; -fx-cursor: hand;";
  private final String buttonStyleOn =
    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 0.2, 0.0, 0.0, 2); -fx-background-color: #00893d; -fx-cursor: hand;";
  // Properties
  private boolean state;

  /**
   * This method initializes the components and properties of the SwitchButton.
   * It sets the minimum size of the SwitchButton to 30x15, initializes the
   * background rectangle, and sets the initial state of the button to false.
   */
  private void init() {
    getChildren().addAll(back, button); // Add the background and button to the StackPane
    setMinSize(30, 15); // Set the minimum size of the SwitchButton
    back.maxWidth(30); // Set the maximum width of the background rectangle
    back.minWidth(30); // Set the minimum width of the background rectangle
    back.maxHeight(10); // Set the maximum height of the background rectangle
    back.minHeight(10); // Set the minimum height of the background rectangle
    /*
     * Set the arc height and width of the background rectangle to the height of the
     * rectangle. This creates rounded corners for the rectangle.
     */
    back.setArcHeight(back.getHeight());
    back.setArcWidth(back.getHeight());
    back.setFill(Color.valueOf("#ced5da")); // Set the fill color of the background rectangle
    back.setStyle("-fx-cursor: hand;"); // Set the cursor style of the background rectangle
    Double r = 2.0; // Radius of the button
    button.setShape(new Circle(r)); // Set the shape of the button to a circle
    setAlignment(button, Pos.CENTER_LEFT); // Set the alignment of the button
    button.setMaxSize(15, 15); // Set the maximum size of the button
    button.setMinSize(15, 15); // Set the minimum size of the button
    button.setStyle(buttonStyleOff); // Set the style of the button to the off state
  }

  /**
   * This constructor initializes a new instance of SwitchButton with the default
   * properties and components. It sets the initial state of the button to false
   * and adds an event handler to toggle the state of the button when clicked.
   */
  public SwitchButton() {
    init(); // Initialize the components and properties of the SwitchButton
    /*
     * Create an event handler to toggle the state of the button when clicked. If the
     * state is true, set the style of the button to the off state and the fill color
     * of the background rectangle to a light gray color. If the state is false, set
     * the style of the button to the on state and the fill color of the background
     * rectangle to a light green color.
     */
    EventHandler<Event> click = new EventHandler<Event>() {
      /**
       * This method handles the event when the SwitchButton is clicked. It toggles the
       * state of the button and updates the style and fill color of the button and
       * background rectangle accordingly.
       * @param e The event that triggered the handler
       */
      @Override
      public void handle(Event e) {
        if (state) { // If the state is true
          button.setStyle(buttonStyleOff); // Set the style of the button to the off state
          back.setFill(Color.valueOf("#ced5da")); // Set the fill color of the background rectangle
          setAlignment(button, Pos.CENTER_LEFT); // Set the alignment of the button
          state = false; // Set the state to false
        } else { // If the state is false
          button.setStyle(buttonStyleOn); // Set the style of the button to the on state
          back.setFill(Color.valueOf("#80C49E")); // Set the fill color of the background rectangle
          setAlignment(button, Pos.CENTER_RIGHT); // Set the alignment of the button
          state = true; // Set the state to true
        }
      }
    };

    /*
     * Set the focus traversable property of the button to false. This prevents the
     * button from receiving focus when the user presses the Tab key.
     */
    button.setFocusTraversable(false);
    setOnMouseClicked(click); // Add the event handler to the SwitchButton
    button.setOnMouseClicked(click); // Add the event handler to the button
  }

  /**
   * This method returns the state of the SwitchButton.
   * @return The state of the SwitchButton
   */
  public boolean getState() {
    return state;
  }
}

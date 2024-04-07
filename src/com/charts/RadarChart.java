package com.charts;

import com.stats.StatValue;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;

public class RadarChart {

  private static final int MAX_VALUE = 10;
  private static final int DEFAULT_NUM_SIDES = 6; // Number of sides for the hexagon/radar chart
  private static final double DEFAULT_WIDTH = 250;
  private static final double DEFAULT_HEIGHT = 250;

  private StackPane stackPane;
  private Pane pane;
  private int numSides;
  private StatValue[] values;
  private double width;
  private double height;

  public RadarChart(StatValue[] values, double width, double height) {
    if (values.length > 0 && values.length < 3) {
      throw new IllegalArgumentException("Number of sides must be at least 3");
    } else if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException(
        "Width and height must be greater than 0"
      );
    }
    this.numSides = values.length == 0 ? DEFAULT_NUM_SIDES : values.length;
    this.values = values;
    this.width = width;
    this.height = height;
    this.stackPane = new StackPane();
    this.pane = new Pane();
    stackPane.getChildren().add(pane);
    stackPane.setPadding(new Insets(10, 0, 10, 50));

    drawChart();
  }

  public RadarChart(StatValue[] values) {
    this(values, DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  public RadarChart(double width, double height) {
    this(new StatValue[0], width, height);
  }

  public RadarChart() {
    this(new StatValue[0], DEFAULT_WIDTH, DEFAULT_HEIGHT);
  }

  public void setValues(StatValue[] values) {
    if (values.length > 0 && values.length < 3) {
      throw new IllegalArgumentException("Number of sides must be at least 3");
    }
    this.values = values;
    this.numSides = values.length == 0 ? DEFAULT_NUM_SIDES : values.length;
    drawChart();
  }

  public void drawChart() {
    pane.getChildren().clear();
    double radius = Math.min(width, height) / 2 * 0.9;
    double centerX = width / 2;
    double centerY = height / 2;

    drawHexagons(centerX, centerY, radius);
    drawLinesAndLabels(centerX, centerY, radius);
    plotValues(centerX, centerY, radius);
  }

  private void drawHexagons(double centerX, double centerY, double radius) {
    for (int j = 0; j < 5; j++) {
      drawPolygon(centerX, centerY, radius - j * radius / 5, numSides, false);
    }
  }

  private void drawLinesAndLabels(
    double centerX,
    double centerY,
    double radius
  ) {
    for (int i = 0; i < numSides; i++) {
      double x = calculateCoordinate(centerX, radius, i, true);
      double y = calculateCoordinate(centerY, radius, i, false);
      drawLine(centerX, centerY, x, y);
      if (values.length >= i + 1) {
        String name = values[i].getName();
        drawLabel(centerX, centerY, x, y, name);
      }
    }
  }

  private double calculateCoordinate(
    double center,
    double radius,
    int index,
    boolean isX
  ) {
    return (
      center +
      radius *
      (
        isX
          ? Math.cos(2 * Math.PI * index / numSides)
          : Math.sin(2 * Math.PI * index / numSides)
      )
    );
  }

  private void drawLine(
    double startX,
    double startY,
    double endX,
    double endY
  ) {
    Line line = new Line(startX, startY, endX, endY);
    pane.getChildren().add(line);
  }

  private void drawLabel(
    double centerX,
    double centerY,
    double x,
    double y,
    String label
  ) {
    Text text = new Text(label);
    text.setX(x + (x - centerX) * 0.1);
    text.setY(y + (y - centerY) * 0.1);
    adjustTextPosition(text, centerX, centerY, x, y, label);
    pane.getChildren().add(text);
  }

  private void adjustTextPosition(
    Text text,
    double centerX,
    double centerY,
    double x,
    double y,
    String label
  ) {
    if (x >= centerX) {
      text.setTextOrigin(VPos.CENTER);
    } else {
      if (!"Intelligence".equals(label)) text.setTextOrigin(VPos.CENTER);
      text.setX(x - text.getLayoutBounds().getWidth() - 8);
    }

    if (y > centerY) {
      text.setY(text.getY() + 5);
    } else if (y < centerY) {
      text.setY(text.getY() - 5);
    }
  }

  private void plotValues(double centerX, double centerY, double radius) {
    drawPolygon(centerX, centerY, radius, values.length, true);
  }

  private void drawPolygon(
    double centerX,
    double centerY,
    double radius,
    int points,
    boolean fill
  ) {
    Polygon polygon = new Polygon();
    for (int i = 0; i < points; i++) {
      double r = fill
        ? radius * (double) values[i].getValue() / MAX_VALUE
        : radius;
      double x = calculateCoordinate(centerX, r, i, true);
      double y = calculateCoordinate(centerY, r, i, false);
      polygon.getPoints().addAll(x, y);
    }
    if (fill) {
      polygon.setFill(Color.DARKGREEN);
      polygon.setOpacity(0.5);
    } else {
      polygon.setStroke(Color.BLACK);
      polygon.setFill(Color.TRANSPARENT);
    }
    pane.getChildren().add(polygon);
  }

  public StackPane getPane() {
    return stackPane;
  }
}

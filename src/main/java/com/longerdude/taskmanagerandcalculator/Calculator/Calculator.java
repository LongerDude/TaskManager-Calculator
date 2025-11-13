package com.longerdude.taskmanagerandcalculator.Calculator;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class Calculator {
    private final DoubleProperty value;
    private final Label currentValue;
    private double previous;
    private int operation;
    //0 = addition
    //1 = subtraction
    //2 = multiplication
    //3 = division


    public Calculator(BorderPane mainLayout) {
        this.value = new SimpleDoubleProperty(0.0);
        operation = 0;
        previous = 0.0;
        StringProperty valueString = new SimpleStringProperty();
        valueString.bind(value.asString("%.2f"));
        this.currentValue = new Label("");
        currentValue.setStyle(
                "-fx-border-color: grey;" + // Sets the border color
                        "-fx-border-width: 5px;" +   // Sets the border width
                        "-fx-padding: 10px;" +      // Sets padding around the text
                        "-fx-background-color: #f0f0f0;" // Sets the background color
        );

        currentValue.setPrefSize(400, 100);
        currentValue.setFont(new Font("Arial", 24));
        currentValue.textProperty().bind(valueString);

        GridPane digitsGridPane = new GridPane();
        digitsGridPane.setHgap(2);
        populateGrid(digitsGridPane);
        mainLayout.setTop(currentValue);
        mainLayout.setCenter(digitsGridPane);
    }

    public void populateGrid(GridPane grid) {
        int x = 0; //row
        //digits from 1-3
        for (int i = 1; i < 4; i++) {
            Button button = new Button(String.valueOf(i));
            button.setPrefSize(100, 100); // top, right, bottom, left
            button.setFont(new Font("Arial", 24));
            button.setStyle(
                    "-fx-border-color: grey;" + // Sets the border color
                            "-fx-border-width: 5px;" +   // Sets the border width
                            "-fx-padding: 10px;" +      // Sets padding around the text
                            "-fx-background-color: #f0f0f0;" // Sets the background color
            );
            double number = i;
            button.setOnAction(event -> {
                value.set(value.getValue() * 10 + number);
            });
            grid.add(button, i, x);
        }
        //Division button
        Button division = new Button("%");
        division.setStyle(
                "-fx-border-color: grey;" + // Sets the border color
                        "-fx-border-width: 5px;" +   // Sets the border width
                        "-fx-padding: 10px;" +      // Sets padding around the text
                        "-fx-background-color: #f0f0f0;" // Sets the background color
        );
        division.setOnAction(event -> {
            this.operation = 3;
            previous = value.getValue();
            value.set(0);


        });
        division.setPrefSize(100, 100);
        division.setFont(new Font("Arial", 24));
        grid.add(division, 4, x);
        x++;
        // digits from 4 to 6
        for (int i = 1; i < 4; i++) {
            Button button = new Button(String.valueOf(i + 3));
            button.setPrefSize(100, 100); // top, right, bottom, left
            button.setFont(new Font("Arial", 24));
            button.setStyle(
                    "-fx-border-color: grey;" + // Sets the border color
                            "-fx-border-width: 5px;" +   // Sets the border width
                            "-fx-padding: 10px;" +      // Sets padding around the text
                            "-fx-background-color: #f0f0f0;" // Sets the background color
            );

            double number = i + 3;
            button.setOnAction(event -> {
                value.set(value.getValue() * 10 + number);
            });
            grid.add(button, i, x);
        }
        //Multiplication
        Button multiplication = new Button("x");
        multiplication.setStyle(
                "-fx-border-color: grey;" + // Sets the border color
                        "-fx-border-width: 5px;" +   // Sets the border width
                        "-fx-padding: 10px;" +      // Sets padding around the text
                        "-fx-background-color: #f0f0f0;" // Sets the background color
        );
        multiplication.setPrefSize(100, 100);
        multiplication.setOnAction(event -> {
            this.operation = 2;
            previous = value.getValue();
            value.set(0);

        });
        multiplication.setFont(new Font("Arial", 24));
        grid.add(multiplication, 4, x);
        x++;
        //digits from 7 to 9
        for (int i = 1; i < 4; i++) {
            Button button = new Button(String.valueOf(i + 6));
            button.setPrefSize(100, 100); // top, right, bottom, left
            button.setFont(new Font("Arial", 24));
            button.setStyle(
                    "-fx-border-color: grey;" + // Sets the border color
                            "-fx-border-width: 5px;" +   // Sets the border width
                            "-fx-padding: 10px;" +      // Sets padding around the text
                            "-fx-background-color: #f0f0f0;" // Sets the background color
            );

            double number = i + 6;
            button.setOnAction(event -> {
                value.set(value.getValue() * 10 + number);
            });
            grid.add(button, i, x);

        }
        //Subtraction
        Button subtraction = new Button("-");
        subtraction.setStyle(
                "-fx-border-color: grey;" + // Sets the border color
                        "-fx-border-width: 5px;" +   // Sets the border width
                        "-fx-padding: 10px;" +      // Sets padding around the text
                        "-fx-background-color: #f0f0f0;" // Sets the background color
        );
        subtraction.setPrefSize(100, 100);
        subtraction.setFont(new Font("Arial", 24));
        subtraction.setOnAction(event -> {
            this.operation = 1;
            previous = value.getValue();
            value.set(0);
        });
        grid.add(subtraction, 4, x);
        //Zero
        Button zero = new Button("0");
        zero.setStyle(
                "-fx-border-color: grey;" + // Sets the border color
                        "-fx-border-width: 5px;" +   // Sets the border width
                        "-fx-padding: 10px;" +      // Sets padding around the text
                        "-fx-background-color: #f0f0f0;" // Sets the background color
        );
        zero.setPrefSize(100, 100);
        zero.setFont(new Font("Arial", 24));
        zero.setOnAction(event -> {
            value.set(value.getValue() * 10);
        });
        grid.add(zero, 2, x + 1);
        //CLEAR
        Button clear = new Button("Clr");
        clear.setStyle(
                "-fx-border-color: grey;" + // Sets the border color
                        "-fx-border-width: 5px;" +   // Sets the border width
                        "-fx-padding: 10px;" +      // Sets padding around the text
                        "-fx-background-color: #f0f0f0;" // Sets the background color
        );
        clear.setPrefSize(100, 100);
        clear.setFont(new Font("Arial", 24));
        clear.setOnAction(event -> {
            value.set(0);
        });
        grid.add(clear, 4, x + 1);


        // Addition
        Button addition = new Button("+");
        addition.setStyle(
                "-fx-border-color: grey;" + // Sets the border color
                        "-fx-border-width: 5px;" +   // Sets the border width
                        "-fx-padding: 10px;" +      // Sets padding around the text
                        "-fx-background-color: #f0f0f0;" // Sets the background color
        );
        addition.setPrefSize(100, 100);
        addition.setFont(new Font("Arial", 24));
        addition.setOnAction(event -> {
            this.operation = 0;
            previous = value.getValue();
            value.set(0);
        });
        grid.add(addition, 3, x + 1);
        //RESULT
        Button result = new Button("=");
        result.setStyle(
                "-fx-border-color: grey;" + // Sets the border color
                        "-fx-border-width: 5px;" +   // Sets the border width
                        "-fx-padding: 10px;" +      // Sets padding around the text
                        "-fx-background-color: #f0f0f0;" // Sets the background color
        );
        result.setPrefSize(100, 100);
        result.setFont(new Font("Arial", 24));
        result.setOnAction(event -> {
            if (this.operation == 0) {
                value.set(value.getValue() + previous);
            } else if (this.operation == 1) {
                value.set(previous - value.getValue());
            } else if (this.operation == 2) {
                value.set(value.getValue() * previous);

            } else if (this.operation == 3) {
                value.set(previous / value.getValue());
            }
        });
        grid.add(result, 1, x + 1);


    }
}

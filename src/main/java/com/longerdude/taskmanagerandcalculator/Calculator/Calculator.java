package com.longerdude.taskmanagerandcalculator.Calculator;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class Calculator {

    public double add(double a, double b) {
        return a + b;
    }
    public double subtract(double a, double b) {
        return a - b;
    }
    public double multiply(double a, double b) {
        return a * b;
    }
    public double divide(double a, double b) {
        return a / b;
    }
    public void populateGrid(GridPane grid) {
        int x = 0; //row
        for (int i = 1; i < 4; i++){
            Button button = new Button(String.valueOf(i));
            button.setPrefSize(100, 100); // top, right, bottom, left
            button.setFont(new Font("Arial", 24));

            button.setOnAction(event -> {});
            grid.add(button,i,x);
        }
        //Division button
        Button division = new Button("%");
        division.setPrefSize(100, 100);
        division.setFont(new Font("Arial", 24));
        grid.add(division,4,x);
        x++;
        // digits from 4 to 6
        for (int i = 1; i < 4; i++){
            Button button = new Button(String.valueOf(i+3));
            button.setPrefSize(100, 100); // top, right, bottom, left
            button.setFont(new Font("Arial", 24));

            button.setOnAction(event -> {});
            grid.add(button,i,x);
        }
        Button multiplication = new Button("x");
        multiplication.setPrefSize(100, 100);
        multiplication.setFont(new Font("Arial", 24));
        grid.add(multiplication,4,x);
        x++;
        //digits from 7 to 9
        for (int i = 1; i < 4; i++){
            Button button = new Button(String.valueOf(i+6));
            button.setPrefSize(100, 100); // top, right, bottom, left
            button.setFont(new Font("Arial", 24));

            button.setOnAction(event -> {});
            grid.add(button,i,x);

        }
        Button subtraction = new Button("-");
        subtraction.setPrefSize(100, 100);
        subtraction.setFont(new Font("Arial", 24));
        grid.add(subtraction,4,x);
    }
}

package com.longerdude.taskmanagerandcalculator;

import com.longerdude.taskmanagerandcalculator.TaskManager.Task;
import com.longerdude.taskmanagerandcalculator.TaskManager.TaskCollection;
import com.sun.source.tree.Tree;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class TaskUI extends Application {
    @Override
    public void start(Stage window){
        // main layout
        BorderPane layout = new BorderPane();

        // Scene setter buttons
        Button tasksButton = new Button("Task Manager");
        Button calculationButton = new Button("Calculator");
        HBox hBox = new HBox();
        hBox.getChildren().addAll(tasksButton,calculationButton);
        hBox.setAlignment(Pos.CENTER);

        // Main layout for calculator and Task Manager
        BorderPane calculatorLayout = new BorderPane();
        BorderPane taskManagerLayout = new BorderPane();

        // Components for the UPPER Task Manager
        Button addTaskButton = new Button("Add Task");
        Button removeTaskButton = new Button("Remove Task");
        DatePicker datePicker = new DatePicker();
        TextField taskName = new TextField("Task Name");
        TextArea taskDescription = new TextArea("Task Description");

        // Layout configuration for the Task Manager
        VBox taskManagerButtons = new VBox();
        HBox taskButtons = new HBox();
        taskButtons.setAlignment(Pos.CENTER);
        taskButtons.setSpacing(130);
        taskButtons.getChildren().addAll(addTaskButton,removeTaskButton);
        taskManagerButtons.getChildren().addAll(taskButtons, datePicker,taskName,taskDescription);
        taskManagerLayout.setTop(taskManagerButtons);

        // Components for the LOWER Task Manager
        TreeItem rootItem = new TreeItem("Tasks");
        TreeView treeView = new TreeView<>();
        treeView.setRoot(rootItem);
        treeView.setShowRoot(false);

        // Collecting Tasks
        TaskCollection taskCollection = new TaskCollection();
        taskCollection.load(rootItem);

        //Add Task Button logic
        addTaskButton.setOnAction(event -> {
            Task task = new Task(taskName.getText(),taskDescription.getText());
            taskCollection.addTask(task, datePicker.getValue());
            if (!(rootItem.getChildren().contains(taskCollection.getTreeRoot(datePicker.getValue())))) {
                rootItem.getChildren().add(taskCollection.getTreeRoot(datePicker.getValue()));
            }
        });

        treeView.setOnMouseClicked(event -> {
            TreeItem item =(TreeItem) treeView.getSelectionModel().getSelectedItem();
            removeTaskButton.setOnAction(event2 -> {
                TreeItem parent = item.getParent();
                parent.getChildren().remove(item);
                taskCollection.remove(item.getValue());

            });

        });



        taskManagerLayout.setCenter(treeView);
        layout.setTop(hBox);
        layout.setCenter(taskManagerLayout);
        tasksButton.setOnAction(event -> {layout.setCenter(taskManagerLayout);});
        calculationButton.setOnAction(event -> {layout.setCenter(calculatorLayout);});

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
        window.setOnCloseRequest(event -> save(taskCollection));



    }
    public void save(TaskCollection taskCollection){
        taskCollection.save();

    }
}

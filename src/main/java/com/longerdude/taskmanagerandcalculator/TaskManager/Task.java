package com.longerdude.taskmanagerandcalculator.TaskManager;

public class Task {
    private String name;
    private String description;
    private boolean done;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

}

package com.longerdude.taskmanagerandcalculator.TaskManager;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import javafx.scene.control.*;

public class TaskCollection {
    private HashMap<LocalDate, ArrayList<Task>> tasks;
    private HashMap<LocalDate, TreeItem<LocalDate>> treeRoot;


    //CONSTRUCT
    public TaskCollection() {
        this.tasks = new HashMap<>();
        this.treeRoot = new HashMap<>();
    }

    //COLLECT
    public void addTask(Task task, LocalDate date) {
        this.tasks.putIfAbsent(date, new ArrayList<>());
        this.tasks.get(date).add(task);
        this.treeRoot.putIfAbsent(date, new TreeItem<>(date));
        TreeItem<String> taskDescription = new TreeItem<>(task.getDescription());
        TreeItem taskName = new TreeItem<>(task.getName());
        taskName.getChildren().add(taskDescription);
        this.treeRoot.get(date).getChildren().add(taskName);
    }

    public TreeItem<LocalDate> getTreeRoot(LocalDate date) {
        return this.treeRoot.get(date);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TaskCollection that = (TaskCollection) o;
        return Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tasks);
    }

    public boolean containsTask(LocalDate date) {
        return this.tasks.containsKey(date);
    }

    public void load(TreeItem root) {
        try (Scanner fileScanner = new Scanner(Paths.get("data.csv"))){
            while(fileScanner.hasNext()){
                String row = fileScanner.nextLine();
                String[] parts =  row.split(";");
                Task task = new Task(parts[1], parts[2]);
                this.addTask(task, LocalDate.parse(parts[0]));
            }

        } catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
        for (TreeItem treeRoot : this.treeRoot.values()) {
            root.getChildren().add(treeRoot);
        }
    }
    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.csv"))) {
            for (LocalDate date : this.tasks.keySet()) {
                ArrayList<Task> tasksForDate = this.tasks.get(date);
                for (Task task : tasksForDate) {
                    writer.println(date.toString() + ";" + task.getName() +";"+ task.getDescription());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove(Object object){

        if (object instanceof LocalDate) {
            this.tasks.remove((LocalDate) object);

            //this.treeRoot.get((LocalDate)object).getChildren().remove(object);

        }
        if (object instanceof String){
            for (ArrayList<Task> tasksForDate : this.tasks.values()) {
                Iterator<Task> iterator = tasksForDate.iterator();
                while (iterator.hasNext()){
                    Task task = iterator.next();
                    if (task.getName().equals((String) object)){
                        iterator.remove();
                    }

                }
                //for (Task task : tasksForDate) {
                  //  if (task.getName().equals((String) object)){
                    //    tasksForDate.remove(task);
                      //  break;
                    //}
                //}

            }


        }
    }

}








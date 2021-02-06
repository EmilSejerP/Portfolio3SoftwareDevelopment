package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.ArrayList;

public class Controller {

    SchoolModel model;
    SchoolView view;

    public Controller(SchoolModel model) {
        this.model = model;
        try {
            model.connect();
            model.createStmt();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<Person> getPeople(){
        ArrayList<Person> people = model.studentListQueryStmt();
        ObservableList<Person> peopleData = FXCollections.observableArrayList(people);
        return peopleData;
    }

    public Integer getMaxID(){
        Integer maxID = model.highestID();
        return maxID;
    }



    public void setView(SchoolView view){
        this.view = view;
    }
}

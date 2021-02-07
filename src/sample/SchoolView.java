package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.sql.SQLException;

public class SchoolView {

    SchoolModel model;
    Controller control;
    private GridPane StartView;
    //--------------------------------------------------------
    TableView<Person> TVPersons = new TableView<Person>();
    TableColumn TCFirstName = new TableColumn("First Name");
    TableColumn TCLastName = new TableColumn("Last Name");
    TableColumn TCResidence = new TableColumn("Residence");
    TableColumn TCPersonalID = new TableColumn("ID");
    Label FirstName = new Label("First Name: ");
    Label LastName = new Label("Last Name: ");
    Label Residence = new Label("Residence: ");
    Label Student = new Label("Student ");
    Label Teacher = new Label("Teacher ");
    Label currentEd = new Label("Current Education: ");
    Label teacherTitle = new Label("Title: ");
    TextField TFCurrentEd = new TextField();
    TextField TFTeacherTitle = new TextField();
    TextField TFFirstName = new TextField();
    TextField TFLastName = new TextField();
    TextField TFResidence = new TextField();
    CheckBox CBStudent = new CheckBox();
    CheckBox CBTeacher = new CheckBox();
    Button CreatePerson = new Button("Add Person to Database");
    MenuBar menuBar = new MenuBar();
    //------------------------------------------------------------




    public SchoolView(SchoolModel model, Controller control) {
        this.model = model;
        this.control = control;
        createAndConfigure();
    }

    private void createAndConfigure(){

        StartView = new GridPane();
        StartView.setMinSize(300,200);
        StartView.setPadding(new Insets(10,10,10,10));
        StartView.setVgap(5);
        StartView.setHgap(1);

        StartView.add(FirstName,0,1);
        StartView.add(LastName,0,2);
        StartView.add(Residence,0,3);
        StartView.add(Student,0,4);
        StartView.add(Teacher,0,5);
        StartView.add(TFFirstName,1,1);
        StartView.add(TFLastName,1,2);
        StartView.add(TFResidence,1,3);
        StartView.add(CBStudent,1,4);
        StartView.add(CBTeacher,1,5);
        StartView.add(currentEd,2,4);
        StartView.add(TFCurrentEd,3,4);
        StartView.add(CreatePerson,1,6);
        StartView.add(teacherTitle,2,5);
        StartView.add(TFTeacherTitle,3,5);
        StartView.add(TVPersons,1,7);

        TVPersons.getColumns().addAll(TCFirstName,TCLastName,TCResidence,TCPersonalID);

        // Loader databasens informationer for People in i tableview:

        ObservableList<Person> people = control.getPeople();
        TVPersons.setItems(people);

        TCFirstName.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName")
        );
        TCLastName.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName")
        );
        TCResidence.setCellValueFactory(
                new PropertyValueFactory<Person, String>("residence")
        );
        TCPersonalID.setCellValueFactory(
                new PropertyValueFactory<Person, Integer>("personalID")
        );

        System.out.println(control.getMaxID());
        //knap til at uploade en ny person:
        CreatePerson.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Integer userID = control.getMaxID() + 1;
                //Person person = new Person(userID,TFFirstName.getText(),TFLastName.getText(),TFResidence.getText());
                //people.add(person);
                if (CBStudent.isSelected() && TFCurrentEd != null){
                    model.addStudent(userID,TFCurrentEd.getText());
                }
                if (CBTeacher.isSelected() && teacherTitle != null){
                    model.addTeacher(userID,TFTeacherTitle.getText());
                }

                model.addPerson(userID,TFFirstName.getText(),TFLastName.getText(),TFResidence.getText());
                ObservableList<Person> people = control.getPeople();
                TVPersons.setItems(people);
            }
        });


    }
    public Parent asParent(){
        return StartView;
    }
}

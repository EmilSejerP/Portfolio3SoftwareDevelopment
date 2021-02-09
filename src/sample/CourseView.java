package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;



public class CourseView {

    SchoolModel model;
    Controller control;
    private GridPane CourseGrid;

    public CourseView(SchoolModel model, Controller control) {
    this.model = model;
    this.control = control;
    createAndConfigure();
}
    TableView<StudentEnrollment> TVEnrollment = new TableView<StudentEnrollment>();

    Button buttonSAddtoCourse = new Button("Add student to course");
    Button buttonGradeStudent = new Button("Add grade to student");
    ComboBox<Course> courseComboBox1 = new ComboBox<Course>();
    ComboBox<Course> courseComboBox2 = new ComboBox<Course>();
    ComboBox<Student> studentNamesComboBox1 = new ComboBox<Student>();
    ComboBox<Student> studentNamesComboBox2 = new ComboBox<Student>();
    ComboBox<Double> gradesComboBox = new ComboBox<Double>();

    TableColumn TCPersonID = new TableColumn("ID");
    TableColumn TCPersonName = new TableColumn("Name");
    TableColumn TCCourseID = new TableColumn("Course ID");
    TableColumn TCCourseName = new TableColumn("Course Name");
    TableColumn TCGrade = new TableColumn("Grade");


    private void createAndConfigure(){

        CourseGrid = new GridPane();
        CourseGrid.setMinSize(300,200);
        CourseGrid.setPadding(new Insets(10,10,10,10));
        CourseGrid.setVgap(5);
        CourseGrid.setHgap(1);

        //-- Setup for being able to add students to classes. --
        CourseGrid.add(studentNamesComboBox1,1,0);
        CourseGrid.add(courseComboBox1,2,0);
        CourseGrid.add(buttonSAddtoCourse,4,0);
        ObservableList<Student> studentList = control.getStudents();
        studentNamesComboBox1.setItems(studentList);
        studentNamesComboBox1.getSelectionModel().selectFirst();
        ObservableList<Course> Course = control.getCourses();
        courseComboBox1.setItems(Course);
        courseComboBox1.getSelectionModel().selectFirst();

        //-- Setup for being able to add grades to students. --
        CourseGrid.add(studentNamesComboBox2,1,2);
        CourseGrid.add(gradesComboBox,2,2);
        CourseGrid.add(courseComboBox2,3,2);
        CourseGrid.add(buttonGradeStudent,4,2);
        studentNamesComboBox2.setItems(studentList);
        studentNamesComboBox2.getSelectionModel().selectFirst();
        courseComboBox2.setItems(Course);
        courseComboBox2.getSelectionModel().selectFirst();
        ObservableList<Double> Grades = control.getGrades();
        gradesComboBox.setItems(Grades);
        gradesComboBox.getSelectionModel().selectFirst();

        System.out.println(studentNamesComboBox1.getValue().getPersonalID());

        //-- Setup TableView --
        CourseGrid.add(TVEnrollment,2,5);
        TVEnrollment.getColumns().addAll(TCPersonID,TCPersonName,TCCourseID,TCCourseName,TCGrade);

        ObservableList<StudentEnrollment> Enrollments = control.getEnrollments();
        TVEnrollment.setItems(Enrollments);
        TCPersonID.setCellValueFactory(
                new PropertyValueFactory<StudentEnrollment, Integer>("studentID")
        );
        TCPersonName.setCellValueFactory(
                new PropertyValueFactory<StudentEnrollment, String>("studentName")
        );
        TCCourseID.setCellValueFactory(
                new PropertyValueFactory<StudentEnrollment, Integer>("courseID")
        );
        TCCourseName.setCellValueFactory(
                new PropertyValueFactory<StudentEnrollment, String>("courseName")
        );
        TCGrade.setCellValueFactory(
                new PropertyValueFactory<StudentEnrollment, Double>("grade")
        );

        // -- Setup Enroll Student Button --
        buttonSAddtoCourse.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Integer courseID = courseComboBox1.getValue().getCourseID();
                Integer studentID = studentNamesComboBox1.getValue().getPersonalID();
                Double studentGrade = null;
                model.enrollStudent(courseID,studentID,studentGrade);

                ObservableList<StudentEnrollment> Enrollments = control.getEnrollments();
                TVEnrollment.setItems(Enrollments);
                System.out.println(Enrollments);
            }
        });
        buttonGradeStudent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Integer courseID = courseComboBox2.getValue().getCourseID();
                Integer studentID = studentNamesComboBox2.getValue().getPersonalID();
                Double studentGrade = gradesComboBox.getValue();
                model.giveGrade(courseID,studentID,studentGrade);

                ObservableList<StudentEnrollment> Enrollments = control.getEnrollments();
                TVEnrollment.setItems(Enrollments);
                System.out.println(Enrollments);
            }
        });
    }
    public Parent asParent(){
        return CourseGrid;
    }
}


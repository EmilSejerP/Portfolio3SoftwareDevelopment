package sample;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    TableView <StudentEnrollment> TVEnrollment = new TableView<StudentEnrollment>();

    Button buttonSAddtoCourse = new Button("Add student to course");
    Button buttonTAddtoCourse = new Button("Add teacher to course");
    Button buttonGradeStudent = new Button("Add grade to student");
    ComboBox<String> courseComboBox1 = new ComboBox<String>();
    ComboBox<String> courseComboBox2 = new ComboBox<String>();
    ComboBox<String> courseComboBox3 = new ComboBox<String>();
    ComboBox<Student> studentNamesComboBox1 = new ComboBox<Student>();
    ComboBox<Student> studentNamesComboBox2 = new ComboBox<Student>();
    ComboBox<String> teacherNamesComboBox = new ComboBox<String>();
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
        ObservableList<String> courseNameList = control.getCourseNames();
        courseComboBox1.setItems(courseNameList);
        courseComboBox1.getSelectionModel().selectFirst();

        //-- Setup for being able to add teachers to classes. --
        CourseGrid.add(teacherNamesComboBox,1,1);
        CourseGrid.add(courseComboBox2,2,1);
        CourseGrid.add(buttonTAddtoCourse,4,1);
        ObservableList<String> teacherNameList = control.getTeacherNames();
        teacherNamesComboBox.setItems(teacherNameList);
        teacherNamesComboBox.getSelectionModel().selectFirst();
        courseComboBox2.setItems(courseNameList);
        courseComboBox2.getSelectionModel().selectFirst();

        //-- Setup for being able to add grades to students. --
        CourseGrid.add(studentNamesComboBox2,1,2);
        CourseGrid.add(gradesComboBox,2,2);
        CourseGrid.add(courseComboBox3,3,2);
        CourseGrid.add(buttonGradeStudent,4,2);
        studentNamesComboBox2.setItems(studentList);
        studentNamesComboBox2.getSelectionModel().selectFirst();
        courseComboBox3.setItems(courseNameList);
        courseComboBox3.getSelectionModel().selectFirst();
        ObservableList<Double> Grades = control.getGrades();
        gradesComboBox.setItems(Grades);
        gradesComboBox.getSelectionModel().selectFirst();

        //--  --
    }
    public Parent asParent(){
        return CourseGrid;
    }
}


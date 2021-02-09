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

    public ObservableList<Student> getStudents(){
        ArrayList<Student> student = model.studentFullQueryStmt();
        ObservableList<Student> students = FXCollections.observableArrayList(student);
        return students;
    }

    public ObservableList<Course> getCourses(){
        ArrayList<Course> courses = model.courseNameQueryStmt();
        ObservableList<Course> Courses = FXCollections.observableArrayList(courses);
        return Courses;
    }

    public ObservableList<StudentEnrollment> getEnrollments(){
        ArrayList<StudentEnrollment> enrollments = model.queryEnrollmentData();
        ObservableList<StudentEnrollment> Enrollments = FXCollections.observableArrayList(enrollments);
        return Enrollments;
    }

    public ObservableList<Double> getGrades(){
        ArrayList<Double> Grades = new ArrayList<Double>();
        Grades.add(null);
        Grades.add((double) 00);
        Grades.add((double) 02);
        Grades.add((double) 4);
        Grades.add((double) 7);
        Grades.add((double) 10);
        Grades.add((double) 12);

        ObservableList<Double> grades = FXCollections.observableArrayList(Grades);
        return grades;
    }

    public void setView(SchoolView view){
        this.view = view;
    }
}

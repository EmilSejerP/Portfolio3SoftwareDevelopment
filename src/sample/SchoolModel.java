package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;

public class SchoolModel {
    Connection conn = null;
    Statement stmt = null;
    String url;

    public SchoolModel(String url) {
        this.url = url;
    }

    public void connect() throws SQLException {
        conn = getConnection(this.url);
    }

    public void createStmt() throws SQLException {
        this.stmt = conn.createStatement();
    }

    //-- Query to get every person in PersonDB --
    public ArrayList<Person> studentListQueryStmt() {
        ArrayList<Person> People = new ArrayList<Person>();
        String sql = "SELECT * FROM PersonDB;";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                Integer PersonalID = rs.getInt("PersonalID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String residence = rs.getString("RESIDENCE");
                Person person = new Person(PersonalID, firstName, lastName, residence);
                People.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return People;
    }

    //-- Query to get highest current ID from datbase needed to create a new ID when a person is added --
    public Integer highestID() {
        Integer maxID = null;
        String sql = "SELECT MAX(PersonalID) as max_id FROM PersonDB;";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                maxID = rs.getInt("max_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return maxID;

    }

    // -- Adds a Person to PersonDB --
    public void addPerson(Integer PersonalID, String FirstName, String LastName, String RESIDENCE) {
        String sql = "INSERT INTO PersonDB (PersonalID,FirstName,LastName,RESIDENCE) VALUES (" + PersonalID + ",'" + FirstName +
                "','" + LastName + "','" + RESIDENCE + "') ON DUPLICATE KEY UPDATE PersonalID=PersonalID;";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    //-- Adds a student to StudentDB --
    public void addStudent(Integer PersonalID, String CurrentEd) {
        String sql = "INSERT INTO StudentDB (PersonalID, CurrentEd) VALUES (" + PersonalID + ",'" + CurrentEd + "');";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    //-- Adds a teacher to TeacherDB --
    public void addTeacher(Integer PersonalID, String Title) {
        String sql = "INSERT INTO TeacherDB (PersonalID, Title) VALUES (" + PersonalID + ",'" + Title + "');";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    //-- Gets full student information, including Super information PersonDB & StudentDB --
    public ArrayList<Student> studentFullQueryStmt() {
        ArrayList<Student> Students = new ArrayList<Student>();
        String sql = "SELECT PersonDB.PersonalID, Firstname, LastName, RESIDENCE, StudentDB.CurrentED FROM PersonDB INNER JOIN StudentDB ON PersonDB.PersonalID = StudentDB.PersonalID;";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                Integer personalID = rs.getInt("PersonalID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String Residence = rs.getString("RESIDENCE");
                String currentED = rs.getString("CurrentED");
                Student student = new Student(personalID,firstName,lastName,Residence,currentED);
                Students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return Students;
    }

    //-- Gets full course data in CourseDB --
    public ArrayList<Course> courseNameQueryStmt() {
        ArrayList<Course> courses = new ArrayList<Course>();
        String sql = "SELECT * FROM CourseDB;";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                Integer CourseID = rs.getInt("CourseID");
                String Semester = rs.getString("Semester");
                String Name = rs.getString("Name");
                Integer TeacherID = rs.getInt("TeacherID");
                Course course = new Course(CourseID,TeacherID,Semester,Name);
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return courses;
    }

    // -- Enroll Student uploads to StudentEnrollment Database --
    public void enrollStudent(Integer CourseID, Integer PersonalID, Double Grade) {
        String sql = "INSERT INTO StudentEnrollment (CourseID, StudentID,Grade) VALUES ("
                + CourseID + "," + PersonalID + "," + Grade + ");";
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<StudentEnrollment> queryEnrollmentData() {
        ArrayList<StudentEnrollment> Enrollments = new ArrayList<StudentEnrollment>();
        String sql = "SELECT " +
                "StudentEnrollment.StudentID, " +
                "PersonDB.FirstName, " +
                "PersonDB.LastName, " +
                "CourseID, " +
                "StudentID, " +
                "Grade " +
                "FROM StudentEnrollment " +
                "JOIN PersonDB " +
                "ON PersonDB.PersonalID = StudentEnrollment.StudentID;";
        ResultSet rs;
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                Integer CourseID = rs.getInt("CourseID");
                Integer PersonalID = rs.getInt("StudentID");
                String StudentFirstName = rs.getString("FirstName");
                String StudentLastName = rs.getString("LastName");
                String StudentName = StudentFirstName + " " + StudentLastName;
                //String CourseName = rs.getString("Name");
                String CourseName = "course";
                double Grade = rs.getDouble("Grade");

                StudentEnrollment studentEnrollment = new StudentEnrollment(CourseID,PersonalID,Grade,StudentName,CourseName);
                Enrollments.add(studentEnrollment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return Enrollments;
    }

    public void giveGrade(Integer CourseID, Integer StudentID,Double Grade){
        String sql = "UPDATE StudentEnrollment SET Grade = " + Grade + " WHERE CourseID = " + CourseID + " AND StudentID = " + StudentID;
        ResultSet rs;
        System.out.println(sql);
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}

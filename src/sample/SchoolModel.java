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

    public void createStmt() throws SQLException{
        this.stmt = conn.createStatement();
    }

    public ArrayList<Person> studentListQueryStmt(){
        ArrayList<Person> People = new ArrayList<Person>();
        String sql = "SELECT * FROM PersonDB;";
        ResultSet rs;
        try{
            rs = stmt.executeQuery(sql);
            while(rs!=null && rs.next()){
                Integer PersonalID = rs.getInt("PersonalID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String residence = rs.getString("RESIDENCE");
                Person person = new Person(PersonalID,firstName,lastName,residence);
                People.add(person);
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return People;
    }
    public Integer highestID(){
        Integer maxID = null;
        String sql = "SELECT MAX(PersonalID) as max_id FROM PersonDB;";
        ResultSet rs;
        try{
            rs = stmt.executeQuery(sql);
            while(rs!=null && rs.next()){
                maxID = rs.getInt("max_id");
            }
        }catch(SQLException i){
            i.printStackTrace();
            System.out.println(i.getMessage());
        }
        return maxID;

    }

    public void addPerson(Integer PersonalID, String FirstName, String LastName, String RESIDENCE){
        String sql = "INSERT INTO PersonDB (PersonalID,FirstName,LastName,RESIDENCE) VALUES (" + PersonalID + ",'" + FirstName +
                "','" + LastName + "','" + RESIDENCE + "') ON DUPLICATE KEY UPDATE PersonalID=PersonalID;";
        System.out.println(sql);
        try{
            stmt.executeUpdate(sql);
        }catch (SQLException j){
            j.printStackTrace();
            System.out.println(j.getMessage());
        }

    }

    public void addStudent(Integer PersonalID,String CurrentEd){
        String sql = "INSERT INTO StudentDB (PersonalID, CurrentEd) VALUES (" + PersonalID + ",'" + CurrentEd + "');";
        try{
            stmt.executeUpdate(sql);
        }catch (SQLException j){
            j.printStackTrace();
            System.out.println(j.getMessage());
        }
    }

    public void addTeacher(Integer PersonalID, String Title){
        String sql = "INSERT INTO TeacherDB (PersonalID, Title) VALUES (" + PersonalID + ",'" + Title + "');";
        try{
            stmt.executeUpdate(sql);
        }catch (SQLException j){
            j.printStackTrace();
            System.out.println(j.getMessage());
        }
    }

}

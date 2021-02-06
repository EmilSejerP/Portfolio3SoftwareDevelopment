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

    public ArrayList<Student> studentListQueryStmt(){
        ArrayList<Student> Students = new ArrayList<Student>();
        String sql = "SELECT * FROM PersonDB FULL OUTER JOIN StudentDB";
        ResultSet rs;
        try{
            rs = stmt.executeQuery(sql);
            while(rs!=null && rs.next()){
                Integer PersonalID = rs.getInt("PersonalID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String residence = rs.getString("RESIDENCE");
                String CurrentED = rs.getString("CurrentED");
                Student student = new Student(PersonalID,firstName,CurrentED);
                Students.add(student);
            }
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return Students;
    }

}

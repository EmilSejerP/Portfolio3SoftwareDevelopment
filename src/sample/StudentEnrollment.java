package sample;

public class StudentEnrollment {
    Integer CourseID, StudentID;
    Double Grade;
    String StudentName, CourseName;

    public StudentEnrollment(Integer courseID, Integer studentID, Double grade, String studentName, String courseName) {
        CourseID = courseID;
        StudentID = studentID;
        Grade = grade;
        StudentName = studentName;
        CourseName = courseName;
    }

    public Integer getCourseID() {
        return CourseID;
    }

    public void setCourseID(Integer courseID) {
        CourseID = courseID;
    }

    public Integer getStudentID() {
        return StudentID;
    }

    public void setStudentID(Integer studentID) {
        StudentID = studentID;
    }

    public Double getGrade() {
        return Grade;
    }

    public void setGrade(Double grade) {
        Grade = grade;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    @Override
    public String toString() {
        return "StudentEnrollment{" +
                "CourseID=" + CourseID +
                ", StudentID=" + StudentID +
                ", Grade=" + Grade +
                ", StudentName='" + StudentName + '\'' +
                ", CourseName='" + CourseName + '\'' +
                '}';
    }
}

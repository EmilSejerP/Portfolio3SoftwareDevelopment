package sample;

public class StudentEnrollment {
    Integer CourseID, StudentID;
    Float Grade;

    public StudentEnrollment(Integer courseID, Integer studentID) {
        CourseID = courseID;
        StudentID = studentID;
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

    public Float getGrade() {
        return Grade;
    }

    public void setGrade(Float grade) {
        Grade = grade;
    }

    @Override
    public String toString() {
        return "StudentEnrollment{" +
                "CourseID=" + CourseID +
                ", StudentID=" + StudentID +
                ", Grade=" + Grade +
                '}';
    }
}

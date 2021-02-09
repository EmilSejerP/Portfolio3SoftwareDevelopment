package sample;

public class Course {
    Integer CourseID, TeacherID;
    String Semester, Name;

    public Course(Integer courseID, Integer teacherID, String semester, String name) {
        CourseID = courseID;
        TeacherID = teacherID;
        Semester = semester;
        Name = name;
    }

    public Integer getCourseID() {
        return CourseID;
    }

    public void setCourseID(Integer courseID) {
        CourseID = courseID;
    }

    public Integer getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(Integer teacherID) {
        TeacherID = teacherID;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return Name + " " + Semester;
    }
}

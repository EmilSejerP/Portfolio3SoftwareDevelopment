package sample;

public class Student extends Person{

    private String CurrentED;


    public Student(Integer personalID, String firstName, String currentED) {
        super(personalID, firstName);
        CurrentED = currentED;
    }
}

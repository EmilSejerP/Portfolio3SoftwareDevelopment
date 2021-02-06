package sample;

public class Student extends Person{

    private String CurrentED;


    public Student(Integer personalID, String firstName, String lastName, String residence, String currentED) {
        super(personalID, firstName, lastName, residence);
        CurrentED = currentED;
    }
}

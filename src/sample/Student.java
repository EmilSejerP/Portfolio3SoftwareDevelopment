package sample;

public class Student extends Person{

    private String CurrentED;


    public Student(Integer personalID, String firstName, String lastName, String residence, String currentED) {
        super(personalID, firstName, lastName, residence);
        CurrentED = currentED;
    }

    public String getCurrentED() {
        return CurrentED;
    }

    public void setCurrentED(String currentED) {
        CurrentED = currentED;
    }

    @Override
    public String toString() {
        return super.getPersonalID().toString() + " " + super.getFirstName() + " " + super.getLastName();
    }
}

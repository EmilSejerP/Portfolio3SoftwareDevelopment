package sample;

public class Person {
    private Integer personalID;
    private String firstName,lastName,residence;

    public Person(Integer personalID, String firstName, String lastName, String residence) {
        this.personalID = personalID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.residence = residence;
    }

    public Integer getPersonalID() {
        return personalID;
    }

    public void setPersonalID(Integer personalID) {
        this.personalID = personalID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String info() {
        return "Person{" +
                "personalID=" + personalID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", residence='" + residence + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "ID: " + personalID + " First Name: " + firstName + " Last Name: " + lastName + " Residence: " + residence;
    }
}

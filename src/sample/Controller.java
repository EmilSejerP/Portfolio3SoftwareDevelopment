package sample;

import java.sql.SQLException;

public class Controller {

    SchoolModel model;
    SchoolView view;

    public Controller(SchoolModel model) {
        this.model = model;
        try {
            model.connect();
            model.createStmt();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void setView(SchoolView view){
        this.view = view;
    }
}

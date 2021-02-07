package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    static String url = "jdbc:sqlite:C:/Users/emils/Documents/SD/SchoolDB";
    static SchoolModel SDB = new SchoolModel(url);

    @Override
    public void start(Stage primaryStage) throws Exception{

        Controller controller = new Controller(SDB);
        SchoolView view1 = new SchoolView(SDB,controller);
        controller.setView(view1);
        CourseView view2 = new CourseView(SDB, controller);


        primaryStage.setTitle("School System");
        primaryStage.setScene(new Scene(view2.asParent(), 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

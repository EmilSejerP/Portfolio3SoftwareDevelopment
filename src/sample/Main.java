package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    static String url = "jdbc:sqlite:C:/Users/emils/Documents/SD/SchoolDB";
    static SchoolModel SDB = new SchoolModel(url);

    @Override
    public void start(Stage primaryStage) throws Exception{

        Controller controller = new Controller(SDB);
        SchoolView view = new SchoolView(SDB,controller);
        controller.setView(view);

        primaryStage.setTitle("School System");
        primaryStage.setScene(new Scene(view.asParent(), 600, 475));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

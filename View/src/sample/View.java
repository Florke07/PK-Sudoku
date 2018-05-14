package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class View extends Application implements EventHandler<ActionEvent> {
    Button Izi = new Button("Izi");
    Button Medium = new Button("Medium");
    Button Hard = new Button("Hard");

    @Override
    public void start(Stage primaryStage) throws Exception{
        int width = 300;
        int height = 275;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");


        Izi.setPrefSize(100,20);
        Izi.setOnAction(this);
        Medium.setPrefSize(100,20);
        Hard.setPrefSize(100,20);

        BorderPane basic = new BorderPane();

        HBox layout = new HBox();
        layout.setPadding(new Insets(120, 12, 15, 12));
        layout.setSpacing(10);
        layout.getChildren().add(Izi);
        layout.getChildren().add(Medium);
        layout.getChildren().add(Hard);

        basic.setCenter(layout);



        primaryStage.setScene(new Scene(basic, width, height));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event) {
        if(event.getSource() == Izi){
            System.out.println("Lamus");
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}

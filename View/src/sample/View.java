package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import levels.Difficulty;
import levels.FieldsRemover;
import sudoku.SudokuBoard;

import java.util.ArrayList;

public class View extends Application {
    Button Izi = new Button("Easy");
    Button Medium = new Button("Medium");
    Button Hard = new Button("Hard");
    Scene playBord;
    SudokuBoard sb = new SudokuBoard();

    @Override
    public void start(Stage primaryStage) throws Exception{
        int width = 300;
        int height = 275;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        sb.fillBoard();

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

        Izi.setPrefSize(100,20);
        Izi.setOnAction(e -> {
            FieldsRemover.removeField(Difficulty.EASY, sb);
            setScene();
            primaryStage.setScene(playBord);
        });
        Medium.setPrefSize(100,20);
        Medium.setOnAction(e -> {
            FieldsRemover.removeField(Difficulty.MEDIUM, sb);
            setScene();
            primaryStage.setScene(playBord);
        });
        Hard.setPrefSize(100,20);
        Hard.setOnAction(e -> {
            FieldsRemover.removeField(Difficulty.HARD, sb);
            setScene();
            primaryStage.setScene(playBord);
        });

    }

    public static void main(String[] args) {

        launch(args);
    }

    private void setScene() {
        ArrayList<TextField> fields = new ArrayList<>();
        GridPane gameLayout = new GridPane();
        gameLayout.setGridLinesVisible(false);
        gameLayout.setPadding(new Insets(5,5,5,5));
        gameLayout.setHgap(20);
        gameLayout.setVgap(20);
        gameLayout.addRow(12);
        gameLayout.addColumn(12);
        int k = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                fields.add(new TextField(String.valueOf(sb.getValue(i, j))));
                gameLayout.add(fields.get(k), j, i);
                k++;
            }
        }
        playBord = new Scene(gameLayout, 400, 400);
    }
}

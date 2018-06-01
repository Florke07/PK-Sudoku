package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import levels.Difficulty;
import levels.FieldsRemover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.Dao;
import sudoku.SudokuBoard;
import sudoku.SudokuBoardDaoFactory;
import sudokupart.SudokuBox;
import sudokupart.SudokuField;

import java.util.ArrayList;

public class View extends Application {
    Button Izi = new Button("Easy");
    Button Medium = new Button("Medium");
    Button Hard = new Button("Hard");
    Scene playBord;
    SudokuBoard sb = new SudokuBoard();
    SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    Dao dao = factory.getFileDao("view.ser");
    Button save = new Button("Save");
    Button load = new Button("Load");
    Button check = new Button("Check");
    ArrayList<TextField> fields = new ArrayList<>();
    final Logger logger = LoggerFactory.getLogger(View.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        int width = 300;
        int height = 275;
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Sudoku, WM, MS");
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

        Izi.setPrefSize(100, 20);
        Izi.setOnAction(e -> {
            FieldsRemover.removeField(Difficulty.EASY, sb);
            logger.info("Easy");
            setScene();
            primaryStage.setScene(playBord);
        });
        Medium.setPrefSize(100, 20);
        Medium.setOnAction(e -> {
            FieldsRemover.removeField(Difficulty.MEDIUM, sb);
            logger.info("Medium");
            setScene();
            primaryStage.setScene(playBord);
        });
        Hard.setPrefSize(100, 20);
        Hard.setOnAction(e -> {
            FieldsRemover.removeField(Difficulty.HARD, sb);
            logger.info("Hard");
            setScene();
            primaryStage.setScene(playBord);
        });
        save.setOnAction(e -> {
            logger.info("save");
            getFields();
            dao.write(sb);
        });
        load.setOnAction(e -> {
            logger.info("load");
            sb = (SudokuBoard) dao.read();
            setScene();
            primaryStage.setScene(playBord);
        });
        check.setOnAction(e -> {
            logger.info("Check");
            getFields();
            if (sb.checkBoard()) {
                logger.info("Good");
            } else {
                logger.info("Bad");
            }
        });
    }

    public static void main(String[] args) {

        launch(args);
    }

    private void setScene() {
        fields.clear();
        BorderPane trueGameLayout = new BorderPane();
        GridPane gameLayout = new GridPane();
        gameLayout.setGridLinesVisible(true);
        gameLayout.setPadding(new Insets(5, 5, 5, 5));
        gameLayout.setHgap(8);
        gameLayout.setVgap(8);
        gameLayout.addRow(3);
        gameLayout.addColumn(3);
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                GridPane innergrid = new GridPane();
                innergrid.setGridLinesVisible(false);
                innergrid.setPadding(new Insets(8, 8, 8, 8));
                innergrid.setHgap(8);
                innergrid.setVgap(8);
                innergrid.addRow(3);
                innergrid.addColumn(3);

                SudokuBox sudokuBox;
                sudokuBox = sb.getBox(i, j);
                for (int x = 0; x < 9; x++) {
                    TextField tx = new TextField(String.valueOf(sudokuBox.elements.get(x).getFieldValue()));
                    tx.setPrefWidth(35);
                    tx.setPrefHeight(35);
                    if (!sudokuBox.elements.get(x).isModifiable()) tx.setDisable(true);
                    fields.add(tx);
                    innergrid.add(fields.get(k), x%3, x/3);
                    k++;
                }
                gameLayout.add(innergrid, i, j);

            }
        }
        //System.out.println(sb.toString());
        trueGameLayout.setCenter(gameLayout);
        HBox options = new HBox();
        options.setSpacing(50);
        options.setPadding(new Insets(5, 5, 5, 75));
        options.getChildren().add(save);
        options.getChildren().add(load);
        options.getChildren().add(check);
        trueGameLayout.setBottom(options);

        playBord = new Scene(trueGameLayout, 400, 400);
    }

    private void getFields() {
        int k = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for(int x = 0; x<3;x++){
                    for(int y = 0; y<3;y++){
                        sb.setValue((i*3)+x,(j*3)+y,Integer.parseInt(fields.get(k).getText()));
                        k++;
                    }
                }
                //sb.setValue(i, j, Integer.parseInt(fields.get(k).getText()));
                //k++;
            }
        }
    }
}

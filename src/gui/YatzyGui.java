package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Die;
import models.RaffleCup;

public class YatzyGui extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yatzy");
        GridPane dicePane = new GridPane();
        this.initDicePane(dicePane);

        GridPane scorePane = new GridPane();
        this.initScorePane(scorePane);

        VBox mainPane = new VBox(20, dicePane, scorePane);

        Scene scene = new Scene(mainPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private final TextField[] txfDice = new TextField[5];

    private final CheckBox[] chbDice = new CheckBox[5];

    private final RaffleCup raffleCup = new RaffleCup();

    private int throwsLeft = 3;
    private final Label lblThrows = new Label(String.valueOf(throwsLeft));

    private final Button btnThrowDice = new Button("Kast terningerne");

    private final TextField[] txfPoint = new TextField[6];

    private void initDicePane(GridPane dicePane) {
        dicePane.setGridLinesVisible(false);
        dicePane.setPadding(new Insets(20));
        dicePane.setHgap(10);
        dicePane.setVgap(10);
        dicePane.setStyle("-fx-border-color: grey; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-color: #f5f5f5;");

        for (int i = 0; i < 5; i++) { // Loop opretter tekstfelter og check buttons og placerer dem
            txfDice[i] = new TextField();
            txfDice[i].setEditable(false);
            txfDice[i].setPrefWidth(50);
            txfDice[i].setPrefHeight(50);
            txfDice[i].setStyle("-fx-font-size: 15pt");
            txfDice[i].setAlignment(Pos.CENTER);
            dicePane.add(txfDice[i], i, 0);

            chbDice[i] = new CheckBox("Hold");
            dicePane.add(chbDice[i], i, 1);
        }
        Label lblThrowsLeft = new Label("Antal Kast tilbage:");
        dicePane.add(lblThrowsLeft, 0, 2, 2, 1);

        dicePane.add(btnThrowDice, 3, 2, 2, 1);

        dicePane.add(lblThrows, 2, 2);

        btnThrowDice.setOnAction(event -> this.throwAction());
    }

    private void throwAction() {
        raffleCup.throwDice();
        Die[] dice = raffleCup.getDice();

        if (throwsLeft > 0) {
            txfDice[0].setText(String.valueOf(dice[0].getEyes())); // Kan måske også loopes for simplicitet
            txfDice[1].setText(String.valueOf(dice[1].getEyes()));
            txfDice[2].setText(String.valueOf(dice[2].getEyes()));
            txfDice[3].setText(String.valueOf(dice[3].getEyes()));
            txfDice[4].setText(String.valueOf(dice[4].getEyes()));
        }
        throwsLeft--;
        lblThrows.setText(String.valueOf(throwsLeft));

        if (throwsLeft == 0) {
            btnThrowDice.setDisable(true);
        }

    }

    private void initScorePane(GridPane scorePane) {
        scorePane.setPadding(new Insets(20));
        scorePane.setHgap(50);
        scorePane.setVgap(10);
        scorePane.setStyle("-fx-border-color: grey; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-color: #f5f5f5;");

        Label lbl1 = new Label("1'ere");
        Label lbl2 = new Label("2'ere");
        Label lbl3 = new Label("3'ere");
        Label lbl4 = new Label("4'ere");
        Label lbl5 = new Label("5'ere");
        Label lbl6 = new Label("6'ere");
        scorePane.add(lbl1, 0, 0);
        scorePane.add(lbl2, 0, 1);
        scorePane.add(lbl3, 0, 2);
        scorePane.add(lbl4, 0, 3);
        scorePane.add(lbl5, 0, 4);
        scorePane.add(lbl6, 0, 5);

        for (int i = 0; i < 6; i++) {
            txfPoint[i] = new TextField();
            scorePane.add(txfPoint[i], 1, i);
            txfPoint[i].setPrefWidth(60);
            txfPoint[i].setPrefHeight(20);
        }

    }

}

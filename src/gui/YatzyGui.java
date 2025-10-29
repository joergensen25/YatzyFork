package gui;

import com.sun.javafx.scene.control.IntegerField;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Die;
import models.RaffleCup;

public class YatzyGui extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yatzy");
        GridPane dicePane = new GridPane();
        this.initContent(dicePane);
        // GridPane scorePane = new GridPane();

        Scene scene = new Scene(dicePane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private final TextField[] txfDice = new TextField[5];

    private final CheckBox[] chbDice = new CheckBox[5];

    private final RaffleCup raffleCup = new RaffleCup();

    private int throwsLeft = 3;
    private final Label lblThrows = new Label(String.valueOf(throwsLeft));

    private final Button btnThrowDice = new Button("Kast terningerne");

    IntegerField
    private void initContent(GridPane dicePane) {
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
}

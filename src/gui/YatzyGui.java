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
import javafx.stage.Stage;
import models.Die;
import models.RaffleCup;

public class YatzyGui extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yatzy");
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private final TextField[] txfDice = new TextField[5];

    private final CheckBox[] chbDice = new CheckBox[5];

    private final RaffleCup raffleCup = new RaffleCup();

    private int throwsLeft = 3;
    private final Label lblThrows = new Label(String.valueOf(throwsLeft));

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        for (int i = 0; i < 5; i++) {
            txfDice[i] = new TextField();
            txfDice[i].setEditable(false);
            txfDice[i].setPrefWidth(50);
            txfDice[i].setPrefHeight(50);
            txfDice[i].setStyle("-fx-font-size: 15pt");
            txfDice[i].setAlignment(Pos.CENTER);
            pane.add(txfDice[i], i, 0);

            chbDice[i] = new CheckBox("Hold");
            pane.add(chbDice[i], i, 1);
        }
        Label lblThrowsLeft = new Label("Antal Kast tilbage:");
        pane.add(lblThrowsLeft, 0, 2, 2, 1);

        Button btnThrowDice = new Button("Kast terningerne");
        pane.add(btnThrowDice, 3, 2, 2, 1);

        pane.add(lblThrows, 2, 2);

        btnThrowDice.setOnAction(event -> this.throwAction());
    }

    private void throwAction() {
        raffleCup.throwDice();

        Die[] dice = raffleCup.getDice();

        txfDice[0].setText(String.valueOf(dice[0].getEyes()));
        txfDice[1].setText(String.valueOf(dice[1].getEyes()));
        txfDice[2].setText(String.valueOf(dice[2].getEyes()));
        txfDice[3].setText(String.valueOf(dice[3].getEyes()));
        txfDice[4].setText(String.valueOf(dice[4].getEyes()));

    }
}

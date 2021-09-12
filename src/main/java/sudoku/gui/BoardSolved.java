package sudoku.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import sudoku.Main;
import sudoku.solver.Board;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardSolved implements Initializable {

    private int MAX = Main.getMax();
    private Board board;

    @FXML
    GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board = Board.getBoardInstance(MAX);
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                boolean rightBorder = false;
                boolean topBorder = false;
                Label label = new Label(board.getTileAsString(i, j));
                label.setAlignment(Pos.CENTER);
                GridPane.setFillWidth(label, true);
                GridPane.setFillHeight(label, true);
                label.setMaxHeight(Double.MAX_VALUE);
                label.setMaxWidth(Double.MAX_VALUE);
                label.setFont(Font.font(55));
                if (i == 2 || i == 5){
                    rightBorder = true;
                }
                if (j == 3 || j == 6){
                    topBorder = true;
                }
                if (topBorder && !rightBorder){
                    label.setStyle("-fx-border-color: black; -fx-border-width: 4 0 0 0;");
                }
                if (!topBorder && rightBorder){
                    label.setStyle("-fx-border-color: black; -fx-border-width: 0 4 0 0;");
                }
                if (topBorder && rightBorder){
                    label.setStyle("-fx-border-color: black; -fx-border-width: 4 4 0 0;");
                }
                gridPane.add(label, i, j);
            }
        }
    }
}

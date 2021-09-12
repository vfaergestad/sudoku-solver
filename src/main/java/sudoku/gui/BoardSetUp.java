package sudoku.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import sudoku.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardSetUp implements Initializable {

    private int MAX = Main.getMax();
    private int[][] initialBoard;

    @FXML
    GridPane gridPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createInitialBoard();
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                boolean rightBorder = false;
                boolean topBorder = false;
                TextField textField = new TextField();
                textField.setAlignment(Pos.CENTER);
                GridPane.setFillWidth(textField, true);
                GridPane.setFillHeight(textField, true);
                textField.setMaxHeight(Double.MAX_VALUE);
                textField.setMaxWidth(Double.MAX_VALUE);
                textField.setFont(Font.font(55));
                if (initialBoard[i][j] != 0){
                    textField.setText(String.format("%d", initialBoard[i][j]));
                }
                if (i == 2 || i == 5){
                    rightBorder = true;
                }
                if (j == 3 || j == 6){
                    topBorder = true;
                }
                if (topBorder && !rightBorder){
                    textField.setStyle("-fx-border-color: black; -fx-border-width: 4 0 0 0;");
                }
                if (!topBorder && rightBorder){
                    textField.setStyle("-fx-border-color: black; -fx-border-width: 0 4 0 0;");
                }
                if (topBorder && rightBorder){
                    textField.setStyle("-fx-border-color: black; -fx-border-width: 4 4 0 0;");
                }

                textField.textProperty().addListener((ov, oldValue, newValue) -> {
                    if (textField.getText().length() > 1) {
                        String s = textField.getText().substring(0, 1);
                        textField.setText(s);
                    }
                    if (!newValue.matches("\\d*")) {
                        textField.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                });

                gridPane.add(textField, i, j);
            }
        }
    }

    private void createInitialBoard(){
        initialBoard = new int[MAX][MAX];
        initialBoard[0][4] = 6;
        initialBoard[0][6] = 4;
        initialBoard[0][7] = 2;
        initialBoard[1][0] = 5;
        initialBoard[1][5] = 7;
        initialBoard[1][7] = 8;
        initialBoard[2][2] = 7;
        initialBoard[3][0] = 6;
        initialBoard[3][1] = 1;
        initialBoard[3][6] = 5;
        initialBoard[4][4] = 8;
        initialBoard[4][7] = 3;
        initialBoard[5][1] = 5;
        initialBoard[5][2] = 8;
        initialBoard[5][5] = 9;
        initialBoard[6][0] = 4;
        initialBoard[6][4] = 2;
        initialBoard[7][2] = 1;
        initialBoard[7][3] = 4;
        initialBoard[7][8] = 6;
        initialBoard[8][0] = 2;
        initialBoard[8][2] = 6;
        initialBoard[8][8] = 9;
    }

    public int[][] getInputsAsIntArray(){
        int[][] gridPaneNodes = new int[MAX][MAX] ;
        for (Node child : gridPane.getChildren()) {
            Integer column = GridPane.getColumnIndex(child);
            Integer row = GridPane.getRowIndex(child);
            if (column != null && row != null && child instanceof TextField) {
                String text = ((TextField) child).getText();
                if (!text.isBlank()){
                    gridPaneNodes[column][row] = Integer.parseInt(text);
                } else {
                    gridPaneNodes[column][row] = 0;
                }
            }
        } return gridPaneNodes;
    }
}

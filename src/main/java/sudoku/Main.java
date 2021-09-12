package sudoku;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import sudoku.gui.BoardSetUp;
import sudoku.solver.Solver;

public class Main extends Application {

    public static final int MAX = 9;

    public  Solver solver;
    private static BoardSetUp boardSetUp;
    Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/boardSetUp.fxml"));
        Parent root = loader.load();
        boardSetUp = loader.getController();
        Scene scene = new Scene(root);
        this.stage = stage;
        this.stage.setScene(scene);
        this.stage.show();

        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                startSolve();
            }
        });

    }

    private void openSolved() throws Exception{
        stage.close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/boardSolved.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static int[][] getBoardSetup(){
        return boardSetUp.getInputsAsIntArray();
    }

    private void startSolve(){
        solver = Solver.getSolverInstance(MAX);
        boolean solved = solver.solve(0);
        if (solved) {
            try {
                openSolved();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("NO SOLUTION");
            alert.setContentText("The sudoku has no solution");
            alert.showAndWait();
            stage.close();
        }
    }



    public static int getMax(){
        return MAX;
    }

    public static void main(String[] args) {
        launch();
    }


}

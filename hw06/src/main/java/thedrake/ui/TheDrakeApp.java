package thedrake.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import thedrake.src.*;

public class TheDrakeApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    private static GameState newSampleGameState() {
        Board board = new Board(4);
        PositionFactory positionFactory = board.positionFactory();
        board = board.withTiles(new Board.TileAt(positionFactory.pos(1, 1), BoardTile.MOUNTAIN));
        return new StandardDrakeSetup().startState(board).placeFromStack(positionFactory.pos(0, 0)).placeFromStack(positionFactory.pos(3, 3))
                .placeFromStack(positionFactory.pos(0, 1)).
                placeFromStack(positionFactory.pos(3, 2)).placeFromStack(positionFactory.pos(1, 0)).placeFromStack(positionFactory.pos(2, 3));

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BoardView boardView = new BoardView(newSampleGameState());
        Scene scene = new Scene(boardView, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("The Drake");
        primaryStage.show();
    }
}

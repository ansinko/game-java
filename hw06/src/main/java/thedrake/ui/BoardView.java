package thedrake.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import thedrake.src.BoardPos;
import thedrake.src.GameState;
import thedrake.src.Move;
import thedrake.src.PositionFactory;

import java.util.List;

public class BoardView extends GridPane implements TileViewContext {
    private GameState gameState;
    private ValidMoves validMoves;
    private TileView tileViewSelected;

    public BoardView(GameState gameState) {
        this.gameState = gameState;
        validMoves = new ValidMoves(gameState);
        PositionFactory positionFactory = gameState.board().positionFactory();
        for (int y = 0; y < positionFactory.dimension(); y++){
            for (int x = 0; x < positionFactory.dimension(); x++){
                BoardPos boardPos = positionFactory.pos(x,positionFactory.dimension() - y - 1);
                add(new TileView(gameState.tileAt(boardPos), boardPos, this), x, y);
            }
        }

        setHgap(5);
        setVgap(5);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);
    }

    private void updateTiles() {
        for (Node node : getChildren()) {
            TileView tileView = (TileView) node;
            tileView.setTile(gameState.tileAt(tileView.pos()));
        }
    }

    @Override
    public void tileViewSelected(TileView tileView) {
        if (tileViewSelected != null && tileViewSelected != tileView) {
            tileViewSelected.unselect();
        }
        tileViewSelected = tileView;
        removeMoves();
        showMoves(validMoves.boardMoves(tileView.pos()));
    }

    @Override
    public void executeMove(Move move) {
        tileViewSelected.unselect();
        tileViewSelected = null;
        removeMoves();

        gameState = move.execute(gameState);
        validMoves = new ValidMoves(gameState);
        updateTiles();
    }

    private void showMoves(List<Move> moves){
        for (Move move : moves){
            tileViewAt(move.target()).setMove(move);
        }
    }

    private void removeMoves(){
        for (Node node : getChildren()){
            TileView tileView = (TileView) node;
            tileView.removeMove();
        }
    }

    private TileView tileViewAt(BoardPos pos){
        int index = (gameState.board().dimension() - 1 - pos.j()) * 4 + pos.i();
        return (TileView) getChildren().get(index);
    }


}

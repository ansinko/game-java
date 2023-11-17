package thedrake.ui;

import thedrake.src.Move;

public interface TileViewContext {
    public void tileViewSelected(TileView tileView);

    void executeMove(Move move);
}

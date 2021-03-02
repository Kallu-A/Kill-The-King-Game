package Game;

import Game.util.D2Dim.Coord;
import Game.util.Player.Player;

import java.awt.*;

public class KindomMap extends WindowInitializer{

    public KindomMap() throws HeadlessException {
    }

    @Override
    protected void setDimBoard(int dimBoard) {
        super.setDimBoard(30);
        playerIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/player.png"));
    }


    @Override
    protected void setWall() {
        super.setWall();
        player = new Player(this, new Coord(1,1));
    }

    @Override
    protected void setShop() {
        super.setShop();
    }

    @Override
    protected void setObject() {
        super.setObject();
    }
}

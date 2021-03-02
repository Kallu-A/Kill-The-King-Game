package Game;

import Game.util.Case;
import Game.util.D2Dim.Coord;
import Game.util.Object.PnjMerchand;
import Game.util.Object.buy.ViewHidden;
import Game.util.Object.enemy.PnjCastle;
import Game.util.Object.enemy.PnjDragon;
import Game.util.Object.enemy.PnjKnight;
import Game.util.Object.enemy.PnjSoldier;
import Game.util.Player.Player;

import java.awt.*;

public class KindomMap extends WindowInitializer{

    public KindomMap() throws HeadlessException {
    }

    @Override
    protected void setDimBoard(int dimBoard) {
        super.setDimBoard(29);
        bombIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/bomb.png"));
        castleIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/castle.png"));
        dragonIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/dragon.png"));
        gorilaIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/gorila.png"));
        groundIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/ground.png"));
        kingIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/king.png"));
        knightIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/knight.png"));
        mountainIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/mountain.png"));
        playerIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/player.png"));
        playerDeadIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/playerDead.png"));
        playerGetBombIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/playerGetBomb.png"));
        shieldIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/shield.png"));
        shopIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/shop.png"));
        soldierIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/soldier.png"));
        treeIcon = new javax.swing.ImageIcon(getClass().getResource("icon23px/tree.png"));
    }


    @Override
    protected void setWall() {
        super.setWall();
        player = new Player(this, new Coord(14,14));

        for (int i=0; i<BOARD_DIM; i++) {
            if (  i == 21 || i == 7) board[i][0].setWall(mountainIcon);
            if (  i == 21 || i == 7) board[i][1].setWall(mountainIcon);
            if ( i == 20 || i == 8 ||  i == 21 || i == 7) board[i][2].setWall(mountainIcon);
            if ( i == 20 || i == 8) board[i][3].setWall(mountainIcon);
            if ( i == 19 || i == 9 ||  i == 20 || i == 8) board[i][4].setWall(mountainIcon);
            if ( i == 19 || i == 9) board[i][5].setWall(mountainIcon);
            if (i == 18 || i == 10 || i == 19 || i == 9) board[i][6].setWall(mountainIcon);
            if (i == 18 || i == 10) board[i][7].setWall(mountainIcon);
            if (i == 11 || i == 17 || i == 10 || i == 18) board[i][8].setWall(mountainIcon);
            if (i == 11 || i == 17 || i == 12 || i == 16) board[i][9].setWall(mountainIcon);
            if (i == 12 || i == 13 || i == 16 || i == 15) board[i][10].setWall(mountainIcon);
            if (i == -1) board[i][11].setWall(mountainIcon);
            if (i <= 10 && i >= 8 || i >= 18 && i <= 21 ) board[i][12].setWall(mountainIcon);
            if (i == 10 || i == 18) board[i][13].setWall(mountainIcon);
            if (i == -1) board[i][14].setWall(mountainIcon);
            if (i == 10 || i == 18) board[i][15].setWall(mountainIcon);
            if (i == 10 || i == 18) board[i][16].setWall(mountainIcon);
            if (i == - 1) board[i][17].setWall(mountainIcon);
            if (i == 12 || i == 13 || i == 16 || i == 15) board[i][18].setWall(mountainIcon);
            if (i == 11 || i == 17 || i == 12 || i == 16) board[i][19].setWall(mountainIcon);
            if (i == 11 || i == 17 || i == 10 || i == 18) board[i][20].setWall(mountainIcon);
            if (i == 18 || i == 10) board[i][21].setWall(mountainIcon);
            if (i == 18 || i == 10 || i == 19 || i == 9) board[i][22].setWall(mountainIcon);
            if (i == 19 || i == 9) board[i][23].setWall(mountainIcon);
            if (i == 19 || i == 9 ||  i == 20 || i == 8) board[i][24].setWall(mountainIcon);
            if (i == 20 || i == 8) board[i][25].setWall(mountainIcon);
            if (i == 20 || i == 8 ||  i == 21 || i == 7) board[i][26].setWall(mountainIcon);
            if ( i == 21 || i == 7) board[i][27].setWall(mountainIcon);
            if ( i == 21 || i == 7) board[i][28].setWall(mountainIcon);
        }
    }

    @Override
    protected void setShop() {
        super.setShop();

    }

    @Override
    protected void setObject() {
        super.setObject();

        putPnj(new Coord(11, 11), new PnjSoldier(), soldierIcon);
        putPnj(new Coord(11, 17), new PnjKnight(), knightIcon);
        putPnj(new Coord(17, 17), new PnjSoldier(), soldierIcon);
        putPnj(new Coord(17, 11), new PnjKnight(), knightIcon);
        putPnj(new Coord(14, 10), new PnjDragon(), dragonIcon);
        putPnj(new Coord(14, 18), new PnjDragon(), dragonIcon);
        putPnj(new Coord(10, 14), new PnjCastle(), castleIcon);
        putPnj(new Coord(18, 14), new PnjCastle(), castleIcon);
    }
}

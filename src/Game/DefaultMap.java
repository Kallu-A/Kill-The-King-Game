package Game;

import Game.util.D2Dim.Coord;
import Game.util.Difficulty;
import Game.util.Object.Coin;
import Game.util.Object.PnjMerchand;
import Game.util.Object.Shield;
import Game.util.Object.buy.Potion;
import Game.util.Object.buy.ShieldBuy;
import Game.util.Object.buy.ViewHidden;
import Game.util.Object.enemy.*;
import Game.util.Object.item.Axe;
import Game.util.Object.item.LegendarieWeapon;
import Game.util.Object.item.Spear;
import Game.util.Object.item.Sword;
import Game.util.Player.Player;

import java.awt.*;

/** default map*/
public class DefaultMap extends WindowInitializer {

    public DefaultMap() throws HeadlessException {
    }

    @Override
    protected void setWall() {
        super.setWall();
        player = new Player(this, new Coord(8,0));

        //wall
        for (int i=0; i<BOARD_DIM; i++){
            if (i < 4 || i > 14) board[i][0].setWall(wallIcon);
            if (i < 5 || i > 13) board[i][1].setWall(wallIcon);
            if (i < 6 && i > 2 || i > 12 && i < 16 || i > 17) board[i][2].setWall(wallIcon);
            if (i < 7 && i > 3 || i > 10 && i < 14) board[i][3].setWall(wallIcon);
            if (i == 6 || i == 11) board[i][4].setWall(wallIcon);
            if (i == 6 || i == 11) board[i][5].setWall(wallIcon);
            if (i == 6 || i > 10 && i < 14) board[i][6].setWall(wallIcon);
            if (i > 12 && i < 18) board[i][7].setWall(wallIcon);
            if (i > 13 && i < 17) board[i][8].setWall(wallIcon);
            if (i == 11 || i < 7) board[i][9].setWall(wallIcon);
            if (i == 11 || i < 7) board[i][10].setWall(wallIcon);
            if (i == 11) board[i][11].setWall(wallIcon);
            if (i == 11) board[i][12].setWall(wallIcon);
            if (i > 4 && i < 12 || i == 19) board[i][13].setWall(wallIcon);
            if (i >17 || i > 10 && i < 13 || i == 5) board[i][14].setWall(wallIcon);
            if (i >16 || i > 3 && i < 6 || i == 12) board[i][15].setWall(wallIcon);
            if (i >16 || i == 4 || i == 12) board[i][16].setWall(wallIcon);
            if (i >16 || i > 11 && i< 14) board[i][17].setWall(wallIcon);
            if (i >17 || i == 13 || i == 2) board[i][18].setWall(wallIcon);
            if (i >17 || i == 13 || i == 2) board[i][19].setWall(wallIcon);
        }
    }


    @Override
    protected void setShop() {
        super.setShop();

        PnjMerchand pnj1 = new PnjMerchand();
        PnjMerchand pnj2 = new PnjMerchand();
        PnjMerchand pnj3 = new PnjMerchand();

        pnj1.addItemToSell(new Sword());
        pnj1.addItemToSell(new Axe());
        pnj1.addItemToSell(new ShieldBuy());
        pnj1.addItemToSell(new Potion(40));
        pnj1.addItemToSell(new Potion(40));
        pnj1.addItemToSell(new Potion(40));
        pnj1.addItemToSell(new Potion(40));
        pnj1.addItemToSell(new Potion(40));
        pnj1.addItemToSell(new Potion(70));
        pnj1.addItemToSell(new Potion(70));
        pnj1.addItemToSell(new Potion(70));

        pnj2.addItemToSell(new Sword());
        pnj2.addItemToSell(new Spear());
        pnj2.addItemToSell(new ViewHidden(this));
        pnj2.addItemToSell(new ShieldBuy());
        pnj2.addItemToSell(new ShieldBuy());
        pnj2.addItemToSell(new ShieldBuy());
        pnj2.addItemToSell(new Potion(10));
        pnj2.addItemToSell(new Potion(10));
        pnj2.addItemToSell(new Potion(10));
        pnj2.addItemToSell(new Potion(20));
        pnj2.addItemToSell(new Potion(20));
        pnj2.addItemToSell(new Potion(30));
        pnj2.addItemToSell(new Potion(30));
        pnj2.addItemToSell(new Potion(30));
        pnj2.addItemToSell(new Potion(40));
        pnj2.addItemToSell(new Potion(40));
        pnj2.addItemToSell(new Potion(40));
        pnj2.addItemToSell(new Potion(50));
        pnj2.addItemToSell(new Potion(50));
        pnj2.addItemToSell(new Potion(50));
        pnj2.addItemToSell(new Potion(50));

        pnj3.addItemToSell(new LegendarieWeapon());
        pnj3.addItemToSell(new ShieldBuy());
        pnj3.addItemToSell(new Potion(50));
        pnj3.addItemToSell(new Potion(50));
        pnj3.addItemToSell(new Potion(50));
        pnj3.addItemToSell(new Potion(60));
        pnj3.addItemToSell(new Potion(60));
        pnj3.addItemToSell(new Potion(60));
        pnj3.addItemToSell(new Potion(60));
        pnj3.addItemToSell(new Potion(90));
        pnj3.addItemToSell(new Potion(90));

        putPnj(new Coord(10, 12), pnj2, pnjIcon);
        putPnj(new Coord(12, 4), pnj1, pnjIcon);
        putPnj(new Coord(6, 14), pnj3, pnjIcon);
    }

    @Override
    protected void setObject() {
        super.setObject();

        putPnj(new Coord(6, 7), new PnjKnight(), knight);
        putPnj(new Coord(6, 8), new PnjKnight(), knight);

        putPnj(new Coord(6, 11), new PnjKnight(), knight);
        putPnj(new Coord(6, 12), new PnjKnight(), knight);

        putPnj(new Coord(19, 7), new PnjKnight(), knight);
        putPnj(new Coord(18, 7), new PnjKnight(), knight);

        putPnj(new Coord(7, 16), new PnjKnight(), knight);
        putPnj(new Coord(6, 18), new PnjKnight(), knight);
        putPnj(new Coord(9, 14), new PnjKnight(), knight);

        putPnj(new Coord(3, 17), new PnjCastle(), castle);
        putPnj(new Coord(16, 19), new PnjCastle(), castle);

        putPnj(new Coord(10, 17), new PnjKing(), king);
        putPnj(new Coord(10, 16), new PnjDragon(), dragon);
        putPnj(new Coord(10, 18), new PnjDragon(), dragon);

        board[8][7].putShield(shieldIcon);

        putEnemyRandom(2, Difficulty.MEDIUM);

    }

}

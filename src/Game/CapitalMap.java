package Game;

import Game.util.D2Dim.Coord;
import Game.util.Object.PnjMerchand;
import Game.util.Object.Shield;
import Game.util.Object.buy.Potion;
import Game.util.Object.buy.ShieldBuy;
import Game.util.Object.buy.ViewHidden;
import Game.util.Object.enemy.PnjCastle;
import Game.util.Object.enemy.PnjDragon;
import Game.util.Object.enemy.PnjKing;
import Game.util.Object.enemy.PnjKnight;
import Game.util.Object.item.*;
import Game.util.Player.Player;

import java.awt.*;

/** capital map*/
public class CapitalMap extends WindowInitializer{

    public CapitalMap() throws HeadlessException {
    }



    @Override
    protected void setWall() {
        super.setWall();

        player = new Player(this, new Coord(9,0));

        for (int i=0; i<BOARD_DIM; i++){
            if (i <= 2 || i >= 17) board[i][0].setWall(wallIcon);
            if (i <= 1 || i >= 18) board[i][1].setWall(wallIcon);
            if (i == 0 || i == 19) board[i][2].setWall(wallIcon);
            if (i == 3 || i == 11) board[i][3].setWall(wallIcon);
            if (i == 3 || i == 17) board[i][4].setWall(wallIcon);
            if (i == 6) board[i][6].setWall(wallIcon);
            if (i == 16) board[i][7].setWall(wallIcon);
            if (i > 5 && i < 9 || i > 10 && i < 14) board[i][8].setWall(wallIcon);
            if (i == 5 || i == 6 || i == 14 || i == 13) board[i][9].setWall(wallIcon);
            if (i == 5  || i == 14) board[i][10].setWall(wallIcon);
            if (i <= 5 && i >= 3 || i >= 14 && i <=16) board[i][11].setWall(wallIcon);
            if (i <= 3 && i >= 2 || i >= 16 && i <=17) board[i][12].setWall(wallIcon);
            if (i ==  2 || i == 17 || i >= 6 && i <= 8 || i <= 13 && i >= 11) board[i][13].setWall(wallIcon);
            if (i ==  2 || i == 17 || i == 5 || i == 6 || i == 14 || i ==  13) board[i][14].setWall(wallIcon);
            if (i ==  2 || i == 17 || i == 5 || i == 14) board[i][15].setWall(wallIcon);
            if (i <=  2 && i > 0|| i >= 17 && i < 19 || i == 5 || i == 14 || i == 7 || i == 12 || i == 8 || i == 11) board[i][16].setWall(wallIcon);
            final boolean b = i <= 1 || i >= 18 || i == 5 || i == 14 || i == 7 || i == 12;
            if (b) board[i][17].setWall(wallIcon);
            if (b) board[i][18].setWall(wallIcon);
            if (b) board[i][19].setWall(wallIcon);
        }
    }

    @Override
    protected void setShop() {
        super.setShop();

        PnjMerchand pnjFirstZone = new PnjMerchand();
        PnjMerchand pnjSecondZone = new PnjMerchand();
        PnjMerchand pnjLastZone = new PnjMerchand();

        pnjFirstZone.addItemToSell(new Sword());
        pnjFirstZone.addItemToSell(new Spear());
        pnjFirstZone.addItemToSell(new ShieldBuy());
        pnjFirstZone.addItemToSell(new ShieldBuy());
        pnjFirstZone.addItemToSell(new ViewHidden(this));
        pnjFirstZone.addItemToSell(new Potion(30));
        pnjFirstZone.addItemToSell(new Potion(30));
        pnjFirstZone.addItemToSell(new Potion(30));
        pnjFirstZone.addItemToSell(new Potion(30));
        pnjFirstZone.addItemToSell(new Potion(30));
        pnjFirstZone.addItemToSell(new Potion(50));
        pnjFirstZone.addItemToSell(new Potion(50));
        pnjFirstZone.addItemToSell(new Potion(50));
        pnjFirstZone.addItemToSell(new Potion(70));
        pnjFirstZone.addItemToSell(new Potion(70));

        putPnj(new Coord(1,2), pnjFirstZone, pnjIcon);

        pnjSecondZone.addItemToSell(new Axe());
        pnjSecondZone.addItemToSell(new ViewHidden(this));
        pnjSecondZone.addItemToSell(new ShieldBuy());
        pnjSecondZone.addItemToSell(new ShieldBuy());
        pnjSecondZone.addItemToSell(new ShieldBuy());
        pnjSecondZone.addItemToSell(new ShieldBuy());
        pnjSecondZone.addItemToSell(new Potion(50));
        pnjSecondZone.addItemToSell(new Potion(50));
        pnjSecondZone.addItemToSell(new Potion(50));
        pnjSecondZone.addItemToSell(new Potion(80));
        pnjSecondZone.addItemToSell(new Potion(80));
        pnjSecondZone.addItemToSell(new Potion(80));

        putPnj(new Coord(17,17), pnjSecondZone, pnjIcon);

        pnjLastZone.addItemToSell(new LegendarieWeapon());
        pnjLastZone.addItemToSell(new ShieldBuy());
        pnjLastZone.addItemToSell(new Potion(60));
        pnjLastZone.addItemToSell(new Potion(60));
        pnjLastZone.addItemToSell(new Potion(60));
        pnjLastZone.addItemToSell(new Potion(60));

        putPnj(new Coord(8, 17), pnjLastZone, pnjIcon);
    }

    @Override
    protected void setObject() {
        super.setObject();

        putPnj(new Coord(9, 8), new PnjKnight(), knight);
        putPnj(new Coord(10, 8), new PnjKnight(), knight);

        putPnj(new Coord(9, 13), new PnjCastle(), castle);
        putPnj(new Coord(10, 13), new PnjCastle(), castle);

        putPnj(new Coord(9, 16), new PnjDragon(), dragon);
        putPnj(new Coord(10, 16), new PnjDragon(), dragon);

        putPnj(new Coord(9, 19), new PnjKing(), king);
        putPnj(new Coord(10, 19), new PnjDragon(), dragon);


        putEnemyRandom(2);
    }
}

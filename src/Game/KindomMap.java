package Game;

import Game.util.D2Dim.Coord;
import Game.util.Difficulty;
import Game.util.Object.PnjMerchand;
import Game.util.Object.buy.Potion;
import Game.util.Object.buy.ShieldBuy;
import Game.util.Object.buy.ViewHidden;
import Game.util.Object.enemy.*;
import Game.util.Object.item.*;
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
            if (i <= 21 && i >= 7 && i != 14) board[i][2].setWall(mountainIcon);
            if ( i == 20 || i == 8) board[i][3].setWall(mountainIcon);
            if ( i == 19 || i == 9 ||  i == 20 || i == 8) board[i][4].setWall(mountainIcon);
            if ( i == 19 || i == 9) board[i][5].setWall(mountainIcon);
            if (i <= 19 && i >= 9 && i != 14) board[i][6].setWall(mountainIcon);
            if (i == 18 || i == 10) board[i][7].setWall(mountainIcon);
            boolean b = i == 11 || i == 17 || i == 10 || i == 18 || i <= 2|| i >= 26 && i <= 29;
            if (b) board[i][8].setWall(mountainIcon);
            boolean b1 = i == 11 || i == 17 || i == 12 || i == 16 || i <= 4 && i >= 2 || i >= 24 && i <= 26;
            if (b1) board[i][9].setWall(mountainIcon);
            boolean b2 = i == 12 || i == 13 || i == 16 || i == 15 || i <= 6 && i >= 4 || i >= 22 && i <= 24;
            if (b2) board[i][10].setWall(mountainIcon);
            if (i <= 8 && i >= 6 || i >= 20 && i <= 22 ) board[i][11].setWall(mountainIcon);
            if (i <= 10 && i >= 8 || i >= 18 && i <= 20 ) board[i][12].setWall(mountainIcon);
            if (i == 10 || i == 18) board[i][13].setWall(mountainIcon);
            if (i == 10 || i == 18) board[i][15].setWall(mountainIcon);
            if (i <= 10 && i >= 8 || i >= 18 && i <= 20 ) board[i][16].setWall(mountainIcon);
            if (i <= 8 && i >= 6 || i >= 20 && i <= 22 ) board[i][17].setWall(mountainIcon);
            if (b2) board[i][18].setWall(mountainIcon);
            if (b1) board[i][19].setWall(mountainIcon);
            if (b) board[i][20].setWall(mountainIcon);
            if (i == 18 || i == 10) board[i][21].setWall(mountainIcon);
            if (i <= 19 && i >= 9 && i != 14) board[i][22].setWall(mountainIcon);
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

        PnjMerchand pnj1 = new PnjMerchand();
        PnjMerchand pnj2 = new PnjMerchand();
        PnjMerchand pnj3 = new PnjMerchand();
        PnjMerchand pnj4 = new PnjMerchand();
        PnjMerchand pnj5 = new PnjMerchand();
        PnjMerchand pnj6 = new PnjMerchand();
        PnjMerchand pnj7 = new PnjMerchand();
        PnjMerchand pnj8 = new PnjMerchand();

        pnj1.addItemToSell(new Spear());
        addPotion(pnj1);

        pnj2.addItemToSell(new ShieldBuy());
        pnj2.addItemToSell(new ShieldBuy());
        pnj2.addItemToSell(new Potion(70));
        pnj2.addItemToSell(new Potion(70));
        pnj2.addItemToSell(new Potion(70));

        pnj3.addItemToSell(new Axe());
        pnj3.addItemToSell(new ShieldBuy());
        pnj3.addItemToSell(new Potion(50));
        pnj3.addItemToSell(new Potion(50));
        pnj3.addItemToSell(new Potion(50));

        pnj4.addItemToSell(new Potion(80));
        pnj4.addItemToSell(new Potion(80));

        pnj5.addItemToSell(new KingSword());

        pnj6.addItemToSell(new Spear());
        addPotion(pnj6);

        pnj7.addItemToSell(new LegendarieWeapon());
        pnj7.addItemToSell(new ShieldBuy());
        pnj7.addItemToSell(new Potion(70));
        pnj7.addItemToSell(new Potion(70));
        pnj7.addItemToSell(new Potion(70));

        pnj8.addItemToSell(new ShieldBuy());
        pnj8.addItemToSell(new ShieldBuy());
        pnj8.addItemToSell(new Potion(50));
        pnj8.addItemToSell(new Potion(50));
        pnj8.addItemToSell(new Potion(50));

        putPnj(new Coord(3, 4), pnj1, shopIcon);
        putPnj(new Coord(3, 14), pnj2, shopIcon);
        putPnj(new Coord(3, 24), pnj3, shopIcon);
        putPnj(new Coord(14, 4), pnj4, shopIcon);
        putPnj(new Coord(14, 24), pnj5, shopIcon);
        putPnj(new Coord(26, 4), pnj6, shopIcon);
        putPnj(new Coord(26, 14), pnj7, shopIcon);
        putPnj(new Coord(26, 24), pnj8, shopIcon);




    }

    private void addPotion(PnjMerchand pnj1) {
        pnj1.addItemToSell(new ViewHidden(this));
        pnj1.addItemToSell(new Potion(20));
        pnj1.addItemToSell(new Potion(20));
        pnj1.addItemToSell(new Potion(20));
        pnj1.addItemToSell(new Potion(40));
        pnj1.addItemToSell(new Potion(40));
        pnj1.addItemToSell(new Potion(80));
        pnj1.addItemToSell(new Potion(80));
    }

    @Override
    protected void setObject() {
        super.setObject();

        player.addItemInventory(new Sword());

        putPnj(new Coord(11, 11), new PnjSoldier(), soldierIcon);
        putPnj(new Coord(11, 17), new PnjKnight(), knightIcon);
        putPnj(new Coord(17, 17), new PnjSoldier(), soldierIcon);
        putPnj(new Coord(17, 11), new PnjKnight(), knightIcon);
        putPnj(new Coord(14, 10), new PnjDragon(), dragonIcon);
        putPnj(new Coord(14, 9), new PnjCastle(), castleIcon);
        putPnj(new Coord(14, 18), new PnjDragon(), dragonIcon);
        putPnj(new Coord(10, 14), new PnjCastle(), castleIcon);
        putPnj(new Coord(18, 14), new PnjCastle(), castleIcon);
        putPnj(new Coord(14, 22), new PnjCastle(), castleIcon);
        putPnj(new Coord(14, 2), new PnjDragon(), dragonIcon);
        putPnj(new Coord(14, 6), new PnjDragon(), dragonIcon);

        putPnj(new Coord(14, 0), new PnjKing(), kingIcon);

        putEnemyRandom(1, Difficulty.HARD);
    }

    @Override
    protected void setCoin(int valueMaxOfACoins) {
        super.setCoin(15);
    }

    @Override
    protected void setBomb(int numberOfBomb) {
        super.setBomb(WindowInitializer.LESS_NUMBER_BOMB);
    }

}

package Game;

import Game.util.Case;
import Game.util.D2Dim.Coord;
import Game.util.Object.*;
import Game.util.Object.Object;
import Game.util.Object.buy.Potion;
import Game.util.Object.buy.ShieldBuy;
import Game.util.Object.buy.ViewHidden;
import Game.util.Object.ennemy.*;
import Game.util.Object.item.Axe;
import Game.util.Object.item.LegendarieWeapon;
import Game.util.Object.item.Spear;
import Game.util.Object.item.Sword;
import Game.util.Player.Health;
import Game.util.Player.Player;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/** Init the JFrame for the graphism
 * @author kallu */
public class WindowInitializer extends JFrame {

    public static  boolean hasCheat = false;

    protected static final int BOARD_DIM = 20;
    protected JPanel contentPane;
    protected Case[][] board;
    protected JPanel grid;
    protected Player player;
    protected JToolBar toolBar;
    protected JLabel accountMoney;
    protected JLabel healthBar;
    protected JLabel shieldBar;
    protected JLabel currentItem;
    protected Coord move = new Coord(0, 0);

    private final ImageIcon blast = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/blastvoid.png"));
    private final ImageIcon ground = new javax.swing.ImageIcon(this.getClass().getResource("util/floor.png"));
    private final ImageIcon deathPlayer = new javax.swing.ImageIcon(this.getClass().getResource("util/Player/deathWarrior.png"));
    private final ImageIcon shieldIcon = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/armor.png"));
    private final ImageIcon wallIcon = new javax.swing.ImageIcon(this.getClass().getResource("util/montain.png"));
    private final ImageIcon pnjIcon = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/shop.png"));
    private final ImageIcon playerAlive = new javax.swing.ImageIcon(this.getClass().getResource("util/Player/warrior.png"));

    private final ImageIcon soldier = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/ennemy/soldier.png"));
    private final ImageIcon knight = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/ennemy/knight.png"));
    private final ImageIcon gorila = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/ennemy/gorila.png"));
    private final ImageIcon king = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/ennemy/king.png"));
    private final ImageIcon castle = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/ennemy/castle.png"));
    private final ImageIcon dragon = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/ennemy/dragon.png"));


    public static final Font font = new Font("Fira sans", Font.PLAIN, 22);

    public WindowInitializer() throws HeadlessException {
        super("Kallu Game | Kill The King");
        setSize(900, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.contentPane = (JPanel) getContentPane();

        initGrid();
        initUi();
        initToolBar();

        grid.setFocusable(true);
        grid.requestFocus();
        grid.setBackground(Color.decode("#b98b5e"));
        grid.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                keyAction(e);
            }
        });

        setPnj();
        setObject();
        setBomb();
        setVisible(true);
        JOptionPane.showMessageDialog(this, "Press R for the commands " +
                "\n To fight an enemy you must go to is postion " +
                "\n Objective to kill the king" +
                "\n---------------------------------------------------" +
                "\n [Tips] Don't fight with you'r hand " +
                "\n [Tips] Remember to scan your enemy before facing it " +
                "\n [Tips] Don't underestimate the armor " +
                "\n [Tips] Going to a space for the first time earns you coins","Start", JOptionPane.INFORMATION_MESSAGE);
        System.gc();
    }

    /** return if the Coord is in the board*/
    public static boolean isBoard(Coord coord){
        return ( (coord.line >= 0 && coord.line < BOARD_DIM) && (coord.col >= 0 && coord.col < BOARD_DIM) );
    }

    /** set the look and feel*/
    private void initUi(){
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException error) {
            JOptionPane.showMessageDialog(contentPane, "Sorry the look have some issue", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /** make the grid*/
    private void initGrid(){
        grid = new JPanel(new GridLayout(BOARD_DIM, BOARD_DIM));
        board = new Case[BOARD_DIM][BOARD_DIM];
        for (int line = 0; line< BOARD_DIM; line++){
            for (int col = 0; col < BOARD_DIM; col++){
                board[line][col] = new Case(new Coord(line, col));
                board[line][col].setIcon(ground);
                grid.add(board[line][col]);
            }
        }
        player = new Player(this, new Coord(8,0));
        setWall();
        contentPane.add(grid, BorderLayout.CENTER);
    }

    /** make the toolBar*/
    private void initToolBar(){
        toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setLayout(new FlowLayout());
        accountMoney = new JLabel();
        healthBar = new JLabel();
        shieldBar = new JLabel();
        currentItem = new JLabel();
        shieldBar.setFont(font);
        healthBar.setFont(font);
        accountMoney.setFont(font);
        currentItem.setFont(font);

        toolBar.add(accountMoney);
        toolBar.addSeparator(new Dimension(10, 15));
        toolBar.add(healthBar);
        toolBar.addSeparator(new Dimension(10, 15));
        toolBar.add(shieldBar);
        toolBar.addSeparator(new Dimension(10, 15));
        toolBar.add(currentItem);
        toolBar.setOpaque(true);
        toolBar.setBackground(Color.decode("#1b1005"));
        contentPane.add(toolBar, BorderLayout.NORTH);

    }

    /** set all the info like the money the life*/
    public void setInfoGame(){
        setAccountMoneyText();
        setHealth();
        setShieldBar();
        setCurrentItem();
    }

    /** set the money info*/
    private void setAccountMoneyText(){
        accountMoney.setText("<html> <body> <font color='dcb52f'>" +
                "You have "+ player.getMoney() + " coins"+
                "</body> </html>");
    }

    /** set the health info*/
    private void setHealth() {
        healthBar.setText("<html> <body> <font color='df4a38'>" +
                "Health : "+Health.life + "/"+Health.MAXIMUN_LIFE+
                "</body> </html>");
    }

    /** set the shield info*/
    private void setShieldBar(){
        shieldBar.setText("<html> <body> <font color='5dd8e5'>" +
                "Armor level "+ Health.levelShield +
                "</body> </html>");
    }

    /** set the current item hold*/
    private void setCurrentItem(){
        currentItem.setText("<html> <body> <font color='bf1de7'>" +
                "Current item : "+ player.current.toStringPrint() +
                "</body> </html>");
    }

    /** do the right action in case of the key */
    private void keyAction(KeyEvent e){
        if (Health.life == 0){
            getCaseFromCoord(player.getCoord()).setIcon(deathPlayer);
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                Health.life = 10;
                Health.stillAlive = true;
                hasCheat = true;
                getCaseFromCoord(player.getCoord()).setIcon(playerAlive);
                setInfoGame();
            }
            return;
        }
        switch (e.getKeyCode()){

            case KeyEvent.VK_UP:
                move =new Coord(player.getLine()-1, player.getCol());
                if (WindowInitializer.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(player);
                    player.setCoord(move);
                    setInfoGame();
                }
                break;

            case KeyEvent.VK_RIGHT:
                move =new Coord(player.getLine(), player.getCol()+1);
                if (WindowInitializer.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(player);
                    player.setCoord(move);
                    setInfoGame();
                }
                break;

            case KeyEvent.VK_LEFT:
                move = new Coord(player.getLine(), player.getCol()-1);
                if (WindowInitializer.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(player);
                    player.setCoord(move);
                    setInfoGame();
                }
                break;

            case KeyEvent.VK_DOWN:
                move = new Coord(player.getLine()+1, player.getCol());
                if (WindowInitializer.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(player);
                    player.setCoord(move);
                    setInfoGame();
                }
                break;

            case KeyEvent.VK_A:
                interactWithPnj(true);
                setInfoGame();
                break;

            case KeyEvent.VK_Q:
                interactWithPnj(false);
                setInfoGame();
                break;

            case KeyEvent.VK_E:
                player.getItemInventory();
                setInfoGame();
                break;

            case KeyEvent.VK_Z:
                scanEnnemies();
                break;

            case KeyEvent.VK_R:
                showCommand();
                break;

            default: break;
        }
    }

    /** show all the commands*/
    public void showCommand(){
        JOptionPane.showMessageDialog(this, "Arrows: to move \n" +
                "A: open the store next door for buying \n" +
                "Q: open the store next door  for selling \n" +
                "E: open the inventory and allows you to change the current weapon \n" +
                "Z: scan the surrounding enemies\n" +
                "R: show commands\n" +
                "SPACE : if you're dead you still can cheat and revive","Commands", JOptionPane.INFORMATION_MESSAGE);
    }

    /** get the btnObject from coord */
    public Case getCaseFromCoord(Coord coord){
        if (WindowInitializer.isBoard(coord))
         return board[coord.line][coord.col];
        else return null;
    }

    /** put the bomb */
    private void setBomb(){
        int bombe;
        for (int i = 0; i < BOARD_DIM; i++){
            for (int j = 0; j < BOARD_DIM; j++){
                if (!board[i][j].isWall()){
                    bombe = (int) (Math.random() * 16);
                    if (bombe <= 1 && board[i][j].isFree(player)) {
                        board[i][j].setObjectCase(new Bomb());
                    }
                }
            }
        }
    }

    /** put the objet on all the case*/
    private void setObject(){
        int random;
        int maxiShield = 3;
        int shield = 0;

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

        board[8][7].setObjectCase(new Shield());
        board[8][7].setIcon(shieldIcon);
        shield++;

        for (int i=0; i<BOARD_DIM; i++){
            for (int j=0; j<BOARD_DIM; j++){
                if ((!board[i][j].isWall()) && (i != player.getLine() || j != player.getCol() )){
                    board[i][j].setObjectCase(new Coin((int) (Math.random() * 20) ) );
                    random = (int) (Math.random() * 108);
                    if (random <= 9 && getCaseFromCoord( new Coord(i,j)).isFree(player) ) {
                        putPnj(new Coord(i, j), new PnjSoldier(), soldier);
                    }
                    else if (random == 11 || random == 12 && getCaseFromCoord( new Coord(i,j)).isFree(player) ){
                        putPnj(new Coord(i, j), new PnjGorila(), gorila);
                    }
                    else if (maxiShield > shield && getCaseFromCoord( new Coord(i,j)).isFree(player) ){
                        if (random ==  13 || random == 14 ) {
                            board[i][j].setObjectCase(new Shield());
                            board[i][j].setIcon(shieldIcon);
                            shield++;
                        }
                    }
                }
            }
        }

        setInfoGame();
    }

    /** set all the wall of the game*/
    private void setWall(){
        for (int i=0; i<7; i++) {
            board[i][9].setWall(wallIcon);
            board[i][10].setWall(wallIcon);
        }

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
            if (i == 11) board[i][9].setWall(wallIcon);
            if (i == 11) board[i][10].setWall(wallIcon);
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

    private void setPnj(){

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
        pnj2.addItemToSell(new Potion(20));
        pnj2.addItemToSell(new Potion(10));
        pnj2.addItemToSell(new Potion(10));
        pnj2.addItemToSell(new Potion(10));
        pnj2.addItemToSell(new Potion(10));
        pnj2.addItemToSell(new Potion(10));
        pnj2.addItemToSell(new Potion(30));
        pnj2.addItemToSell(new Potion(30));
        pnj2.addItemToSell(new Potion(30));
        pnj2.addItemToSell(new Potion(30));
        pnj2.addItemToSell(new Potion(30));
        pnj2.addItemToSell(new Potion(40));
        pnj2.addItemToSell(new Potion(40));
        pnj2.addItemToSell(new Potion(40));
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

    /** put a pnj at the coord */
    private void putPnj(Coord coord, Object objectPnj, ImageIcon pnjIcon){
        board[coord.line][coord.col].setObjectCase(objectPnj);
        board[coord.line][coord.col].setWall(pnjIcon);
    }

    /** function who interact with the adjacent coord of the player*/
    private void interactWithPnj(boolean buy) {
        boolean interact = false;
        LinkedList<Coord> coordAdj = Coord.getCoordAdjacent(player.getCoord());
        for (Coord value : coordAdj)
            if (getCaseFromCoord(value).interactWithPnj(player, this, buy)) interact = true;
        setInfoGame();

        if (!interact) JOptionPane.showMessageDialog(this, "there is no store nearby", "no store", JOptionPane.INFORMATION_MESSAGE);
    }

    /** show or hide all the bomb */
    public void showBomb(boolean hidden){
        for (int i = 0; i<BOARD_DIM; i++){
            for (int j = 0; j<BOARD_DIM; j++){
                if (board[i][j].getBomb(false) != 0){
                    if (hidden) board[i][j].setIcon(ground);
                    else board[i][j].setIcon(blast);
                }
            }
        }
    }

    /** look for stats of nearby ennemies*/
    private void scanEnnemies(){
        boolean ennemyScan = false;
        LinkedList<Coord> coordAdj = Coord.getCoordAdjacent(player.getCoord());
        for ( Coord coord : coordAdj){
            if (getCaseFromCoord(coord).scanEnnemy()) ennemyScan = true;
        }
        if (!ennemyScan) JOptionPane.showMessageDialog(this, "there is no enemy in the vicinity", "no enemy", JOptionPane.INFORMATION_MESSAGE);
    }
}

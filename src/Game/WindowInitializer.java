package Game;

import Game.util.Case;
import Game.util.D2Dim.Coord;
import Game.util.Object.*;
import Game.util.Object.Object;
import Game.util.Object.enemy.PnjGorila;
import Game.util.Object.enemy.PnjSoldier;
import Game.util.Player.Health;
import Game.util.Player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/** Init the JFrame for the graphism
 * @author kallu
 * For create a new map you juste to extends WindowInitializer and overide
 *         setWall();
 *         setPnj();
 *         setObject();
 *         */
public class WindowInitializer extends JFrame {

    public boolean hasCheat = false;

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

    protected final ImageIcon blast = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/blastvoid.png"));
    protected final ImageIcon ground = new javax.swing.ImageIcon(this.getClass().getResource("util/floor.png"));
    protected final ImageIcon deathPlayer = new javax.swing.ImageIcon(this.getClass().getResource("util/Player/deathWarrior.png"));
    protected final ImageIcon shieldIcon = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/armor.png"));
    protected final ImageIcon wallIcon = new javax.swing.ImageIcon(this.getClass().getResource("util/montain.png"));
    protected final ImageIcon pnjIcon = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/shop.png"));
    protected final ImageIcon playerAlive = new javax.swing.ImageIcon(this.getClass().getResource("util/Player/warrior.png"));

    protected final ImageIcon soldier = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/enemy/soldier.png"));
    protected final ImageIcon knight = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/enemy/knight.png"));
    protected final ImageIcon gorila = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/enemy/gorila.png"));
    protected final ImageIcon king = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/enemy/king.png"));
    protected final ImageIcon castle = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/enemy/castle.png"));
    protected final ImageIcon dragon = new javax.swing.ImageIcon(this.getClass().getResource("util/Object/enemy/dragon.png"));


    public static final Font font = new Font("Fira sans", Font.PLAIN, 22);

    public WindowInitializer() throws HeadlessException {
        super("Kallu Game | Kill The King");
        setSize(900, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.contentPane = (JPanel) getContentPane();

        initGrid();
        initToolBar();
        setUpBoard();


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

        setVisible(true);
        JOptionPane.showMessageDialog(this, "Press R for the commands " +
                "\n To fight an enemy you have to go on his postion " +
                "\n Objective to kill the king" +
                "\n ESCAPE for relaunch a new game" +
                "\n---------------------------------------------------" +
                "\n [Tips] Don't fight with your hand " +
                "\n [Tips] Remember to scan your enemy before facing it " +
                "\n [Tips] Don't underestimate the armor " +
                "\n [Tips] Going to a place for the first time earns you coins","Start", JOptionPane.INFORMATION_MESSAGE);
        System.gc();
    }

    /** return if the Coord is in the board*/
    public static boolean isBoard(Coord coord){
        return ( (coord.line >= 0 && coord.line < BOARD_DIM) && (coord.col >= 0 && coord.col < BOARD_DIM) );
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
        contentPane.add(grid, BorderLayout.CENTER);
    }


    /** do the right action in case of the key */
    private void keyAction(KeyEvent e){
        if (player.health.life == 0){
            getCaseFromCoord(player.getCoord()).setIcon(deathPlayer);
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                player.health.life = 10;
                player.health.stillAlive = true;
                hasCheat = true;
                getCaseFromCoord(player.getCoord()).setIcon(playerAlive);
                setInfoGame();
            } else if ( e.getKeyCode() == KeyEvent.VK_ESCAPE) relance();
            return;
        }
        switch (e.getKeyCode()){

            case KeyEvent.VK_UP:
                move =new Coord(player.getLine()-1, player.getCol());
                if (WindowInitializer.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(this);
                    player.setCoord(move);
                    setInfoGame();
                }
                break;

            case KeyEvent.VK_RIGHT:
                move =new Coord(player.getLine(), player.getCol()+1);
                if (WindowInitializer.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(this);
                    player.setCoord(move);
                    setInfoGame();
                }
                break;

            case KeyEvent.VK_LEFT:
                move = new Coord(player.getLine(), player.getCol()-1);
                if (WindowInitializer.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(this);
                    player.setCoord(move);
                    setInfoGame();
                }
                break;

            case KeyEvent.VK_DOWN:
                move = new Coord(player.getLine()+1, player.getCol());
                if (WindowInitializer.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(this);
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

            case KeyEvent.VK_ESCAPE:
                relance();
                break;

            default: break;
        }
    }


    /** détruit le jeu pour la relance */
    private void relance(){
        int optionSelect = JOptionPane.showConfirmDialog(this, "Do you want to launch a new game?");

        if (optionSelect == 0) {
            dispose();
            new MapSelector();
        }
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
                "Health : "+player.health.life + "/"+Health.MAXIMUN_LIFE+
                "</body> </html>");
    }

    /** set the shield info*/
    private void setShieldBar(){
        shieldBar.setText("<html> <body> <font color='5dd8e5'>" +
                "Armor level "+ player.health.levelShield +
                "</body> </html>");
    }

    /** set the current item hold*/
    private void setCurrentItem(){
        currentItem.setText("<html> <body> <font color='bf1de7'>" +
                "Current item : "+ player.current.toStringPrint() +
                "</body> </html>");
    }

    /** show all the commands*/
    public void showCommand(){
        JOptionPane.showMessageDialog(this, "Arrows: to move \n" +
                "A: open the store next door for buying \n" +
                "Q: open the store next door  for selling \n" +
                "E: open the inventory and allows you to change the current weapon \n" +
                "Z: scan the surrounding enemies\n" +
                "R: show commands\n" +
                "SPACE : if you're dead you still can cheat and revive \n" +
                "ESCAPE: relaunch a new game","Commands", JOptionPane.INFORMATION_MESSAGE);
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

    /** make the setup a of the board*/
    private void setUpBoard(){
        setWall();
        setShop();
        setObject();
        setBomb();
        setInfoGame();
    }

    // override methods

    /** set all the wall of the game*/
    protected void setWall(){}

    /** set all the Pnj */
    protected void setShop(){}

    /** put the objet on all the case*/
    protected void setObject(){}


    /** put a pnj at the coord */
    protected void putPnj(Coord coord, Object objectPnj, ImageIcon pnjIcon){
        board[coord.line][coord.col].setObjectCase(objectPnj);
        board[coord.line][coord.col].setWall(pnjIcon);
    }

    /** function who interact with the adjacent coord of the player*/
    private void interactWithPnj(boolean buy) {
        boolean interact = false;
        LinkedList<Coord> coordAdj = player.getCoord().getCoordAdjacent();
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
        LinkedList<Coord> coordAdj = player.getCoord().getCoordAdjacent();
        for ( Coord coord : coordAdj){
            if (getCaseFromCoord(coord).scanEnnemy()) ennemyScan = true;
        }
        if (!ennemyScan) JOptionPane.showMessageDialog(this, "there is no enemy in the vicinity", "no enemy", JOptionPane.INFORMATION_MESSAGE);
    }


    /** put the enemy randomly in the map with a maxShield number*/
    protected void putEnemyRandom(int maxShield){
        int random;
        int shield = 0;
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
                    else if (maxShield > shield && getCaseFromCoord( new Coord(i,j)).isFree(player) ){
                        if (random ==  13 || random == 14 ) {
                            board[i][j].putShield(shieldIcon);
                            shield++;
                        }
                    }
                }
            }
        }
    }

    public Player getPlayer() {
        return player;
    }
}

package Game;

import Game.util.Case;
import Game.util.D2Dim.Coord;
import Game.util.Difficulty;
import Game.util.DifficultySelector;
import Game.util.Object.*;
import Game.util.Object.Object;
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

    protected int BOARD_DIM = 20;
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

    protected ImageIcon bombIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/bomb.png"));
    protected ImageIcon groundIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/ground.png"));
    protected ImageIcon playerDeadIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/playerDead.png"));
    protected ImageIcon shieldIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/shield.png"));
    protected ImageIcon mountainIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/mountain.png"));
    protected ImageIcon treeIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/tree.png"));
    protected ImageIcon shopIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/shop.png"));
    protected ImageIcon playerIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/player.png"));
    protected ImageIcon playerGetBombIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/playerGetBomb.png"));

    protected ImageIcon soldierIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/soldier.png"));
    protected ImageIcon knightIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/knight.png"));
    protected ImageIcon gorilaIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/gorila.png"));
    protected ImageIcon kingIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/king.png"));
    protected ImageIcon castleIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/castle.png"));
    protected ImageIcon dragonIcon = new javax.swing.ImageIcon(this.getClass().getResource("icon35px/dragon.png"));

    private static boolean firstLaunch = true;

    public static final int LOT_NOMBER_BOMB= 9;
    public static final int MEDIUM_NOMBER_BOMB= 15;
    public static final int LESS_NUMBER_BOMB= 23;

    public static final Font font = new Font("Fira sans", Font.PLAIN, 22);

    public WindowInitializer() throws HeadlessException {
        super("Kallu Game | Kill The King");
        setSize(900, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        this.contentPane = (JPanel) getContentPane();
        setDimBoard(20);
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
        if (firstLaunch){
            JOptionPane.showMessageDialog(this, "Press Q for the commands " +
                    "\n To fight an enemy you have to go on his postion " +
                    "\n Objective to kill the king" +
                    "\n L for launch a new game" +
                    "\n---------------------------------------------------" +
                    "\n [Tips] Don't fight with your hand " +
                    "\n [Tips] Remember to scan your enemy before facing it " +
                    "\n [Tips] Don't underestimate the armor " +
                    "\n [Tips] Going to a place for the first time earns you coins","Start", JOptionPane.INFORMATION_MESSAGE);
            firstLaunch = false;
        }

        System.gc();
    }

    /** overide methode*/
    protected void setDimBoard(int dimBoard){
        this.BOARD_DIM = dimBoard;
    }

    /** return if the Coord is in the board*/
    public boolean isBoard(Coord coord){
        return ( (coord.line >= 0 && coord.line < BOARD_DIM) && (coord.col >= 0 && coord.col < BOARD_DIM) );
    }

    /** close the game */
    private void closeWindow(){
        int optionSelect = JOptionPane.showConfirmDialog(this, "Do you want to leave ?");

        if (optionSelect == 0) {
            dispose();
            System.exit(0);
        }
    }

    /** make the grid*/
    private void initGrid(){
        grid = new JPanel(new GridLayout(BOARD_DIM, BOARD_DIM));
        board = new Case[BOARD_DIM][BOARD_DIM];
        for (int line = 0; line< BOARD_DIM; line++){
            for (int col = 0; col < BOARD_DIM; col++){
                board[line][col] = new Case(new Coord(line, col));
                board[line][col].setIcon(groundIcon);
                grid.add(board[line][col]);
            }
        }
        contentPane.add(grid, BorderLayout.CENTER);
    }

    public ImageIcon[] getPlayerState(){
        ImageIcon[] state = new ImageIcon[3];
        state[0] = playerIcon;
        state[1] = playerGetBombIcon;
        state[2] = groundIcon;
        return state;
    }

    /** do the right action in case of the key */
    private void keyAction(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) closeWindow();
        if (player.health.life == 0){
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                player.health.life = 10;
                hasCheat = true;
                getCaseFromCoord(player.getCoord()).setIcon(playerIcon);
                setInfoGame();
            } else if ( e.getKeyCode() == KeyEvent.VK_L) relance();
            else if (e.getKeyCode() == KeyEvent.VK_Q) showCommand();
            return;
        }
        switch (e.getKeyCode()){

            case KeyEvent.VK_UP:
                move =new Coord(player.getLine()-1, player.getCol());
                if (this.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(this);
                    doAction(move);
                }
                break;

            case KeyEvent.VK_RIGHT:
                move =new Coord(player.getLine(), player.getCol()+1);
                if (this.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(this);
                    doAction(move);
                }
                break;

            case KeyEvent.VK_LEFT:
                move = new Coord(player.getLine(), player.getCol()-1);
                if (this.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(this);
                    doAction(move);
                }
                break;

            case KeyEvent.VK_DOWN:
                move = new Coord(player.getLine()+1, player.getCol());
                if (this.isBoard(move)) {
                    getCaseFromCoord(move).interactWithEnnemy(this);
                    doAction(move);
                }
                break;

            case KeyEvent.VK_A:
                interactWithPnj(true);
                setInfoGame();
                break;

            case KeyEvent.VK_R:
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

            case KeyEvent.VK_Q:
                showCommand();
                break;

            case KeyEvent.VK_L:
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

    /** do the action when you move*/
    private void doAction(Coord move){
        player.setCoord(move);
        setInfoGame();
        playerDead(player.getCoord());
    }

    /**if there is a player at the coord and is die make him die*/
    private void playerDead(Coord coordPlayer){
        if ( !coordPlayer.isCoordEqual(player.getCoord()))  return;
        if (player.health.life <= Health.MINIMUN_LIFE){
            getCaseFromCoord(player.getCoord()).setIcon(playerDeadIcon);
            setPlayerDeadHealth();
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

    /** set the dead message */
    private void setPlayerDeadHealth(){
        healthBar.setText("<html> <body> <font color='df4a38'>" +
                "YOUR DEAD"+
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
                "Current : "+ player.current.toStringPrint() +
                "</body> </html>");
    }

    /** show all the commands*/
    public void showCommand(){
        if (player.health.life <= Health.MINIMUN_LIFE){
            JOptionPane.showMessageDialog(this,
                    "Q: show commands\n" +
                            "SPACE : you can cheat and revive \n" +
                            "L: launch a new game", "Commands when dead", JOptionPane.INFORMATION_MESSAGE);
        } else
            JOptionPane.showMessageDialog(this, "Arrows: to move \n" +
                "A: open the store next door for buying \n" +
                "R: open the store next door  for selling \n" +
                "E: open the inventory and allows you to change the current weapon \n" +
                "Z: scan the surrounding enemies\n" +
                "Q: show commands\n" +
                "SPACE : if you're dead you still can cheat and revive \n" +
                "ESCAPE :  close the game\n" +
                "L: launch a new game","Commands", JOptionPane.INFORMATION_MESSAGE);
    }

    /** get the btnObject from coord */
    public Case getCaseFromCoord(Coord coord){
        if (this.isBoard(coord))
         return board[coord.line][coord.col];
        else return null;
    }

    /** put the bomb */
    private void setBombPrivate(int numberOfBomb){
        if (numberOfBomb == 0) return;
        int bombe;
        for (int i = 0; i < BOARD_DIM; i++){
            for (int j = 0; j < BOARD_DIM; j++){
                if (!board[i][j].isWall()){
                    bombe = (int) (Math.random() * numberOfBomb);
                    if (bombe == 1 && board[i][j].isFree(player)) {
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
        setBomb(WindowInitializer.MEDIUM_NOMBER_BOMB);
        setInfoGame();
    }

    // override methods

    /** set all the wall of the game*/
    protected void setWall(){}

    /** set all the Pnj */
    protected void setShop(){}

    /** put the objet on all the case*/
    protected void setObject(){}

    /** overide methods possibly*/
    protected void setBomb(int numberOfBomb){
        setBombPrivate(numberOfBomb);
    }

    // ------ end override ------

    /** put a pnj at the coord */
    protected void putPnj(Coord coord, Object objectPnj, ImageIcon pnjIcon){
        board[coord.line][coord.col].setObjectCase(objectPnj);
        board[coord.line][coord.col].setWall(pnjIcon);
    }

    /** function who interact with the adjacent coord of the player*/
    private void interactWithPnj(boolean buy) {
        boolean interact = false;
        LinkedList<Coord> coordAdj = player.getCoord().getCoordAdjacent(this);
        for (Coord value : coordAdj)
            if (getCaseFromCoord(value).interactWithPnj(player, this, buy)) interact = true;
        setInfoGame();

        if (!interact) JOptionPane.showMessageDialog(this, "there is no store nearby", "no store", JOptionPane.INFORMATION_MESSAGE);
    }

    /** show or hide all the bomb */
    public void showBomb(boolean hidden){
        for (int i = 0; i<BOARD_DIM; i++){
            for (int j = 0; j<BOARD_DIM; j++){
                if (board[i][j].getBomb(false, this) != 0){
                    if (hidden) board[i][j].setIcon(groundIcon);
                    else board[i][j].setIcon(bombIcon);
                }
            }
        }
    }

    /** look for stats of nearby ennemies*/
    private void scanEnnemies(){
        boolean ennemyScan = false;
        LinkedList<Coord> coordAdj = player.getCoord().getCoordAdjacent(this);
        for ( Coord coord : coordAdj){
            if (getCaseFromCoord(coord).scanEnnemy()) ennemyScan = true;
        }
        if (!ennemyScan) JOptionPane.showMessageDialog(this, "there is no enemy in the vicinity", "no enemy", JOptionPane.INFORMATION_MESSAGE);
    }

    /** put the enemy randomly in the map with a maxShield number*/
    protected void putEnemyRandom(int maxShield, Difficulty difficultySelect){
        if (difficultySelect == Difficulty.FREE) difficultySelect = DifficultySelector.getDifficulty();
        if ( difficultySelect == null) difficultySelect = Difficulty.MEDIUM;
        switch (difficultySelect){

            case EASY:
                RandomGenerator.difficultyEasy(this, maxShield);
                break;

            case MEDIUM:
                RandomGenerator.difficultyMedium(this, maxShield);
                break;

            case HARD:
                RandomGenerator.difficultyHard(this, maxShield);
                break;

            default:break;
        }
    }

    /** get the player of the game */
    public Player getPlayer() {
        return player;
    }
}

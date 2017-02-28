package arcasul.GUI;

import arcasul.Level.GameState;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Lydya0103
 */
public class GUI extends Thread{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final String GAME_NAME = "The Archer";
    
    private final JFrame frame = new JFrame(GAME_NAME);

    private final JPanel cards = new JPanel(new CardLayout());
    private final GameGUI gameGui = new GameGUI();
    private final MeniuPanelGUI menuGui = new MeniuPanelGUI();
    private final WonGamePanel wonGamePanel = new WonGamePanel();
    private final LostGamePanel lostGamePanel = new LostGamePanel();    
    
    private boolean DrowGame = false;
    private String cardShown = "menugui";
        
    public GUI()
    {
        gameGui.setVisible(true);
        menuGui.setVisible(true);
        wonGamePanel.setVisible(true);
        lostGamePanel.setVisible(true);
        
        cards.add(gameGui, "gamegui");
        cards.add(menuGui, "menugui");
        cards.add(wonGamePanel, "wonGamePanel");
        cards.add(lostGamePanel, "lostGamePanel");
        
        frame.add(cards);
        frame.setVisible(true); 
        
        frame.pack();
        frame.setSize(new Dimension(WIDTH, HEIGHT));
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
    public void run()
    {
        try {
            // folosit pentru a avea timp sa creeze Joc tot ce trebuie
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        
        show("menugui");
        long lastTime = System.currentTimeMillis();
        while (true)
        {     
            while (menuGui.isShown() == true)         
                lastTime = frameTime(lastTime);            
            if(menuGui.shouldExit() == true)           
                break;            
            if(menuGui.getStartMenuGui() == true) 
            {
                DrowGame = true;
                menuGui.setStartMenuGui(false);
            }            
            if(DrowGame == true)
            {
                show("gamegui");
                drawGame();
            }
        }
        exitGame();
    }

    public void show(String card) {
        if (card.equals("menugui")) menuGui.setShown(true);
        else menuGui.setShown(false);
        
        cardShown = card;
        CardLayout cardLayout = (CardLayout) cards.getLayout();
        cardLayout.show(cards, card);
    }
    
    private long frameTime(long lastTime) {
        while(System.currentTimeMillis() - lastTime < 15)
            {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                }  
            }
        return System.currentTimeMillis();
    }
    
    private void drawGame() {
        long lastTime = System.currentTimeMillis();
        while (gameGui.isLevelRunning() == true)
        {
            lastTime = frameTime(lastTime);            
            gameGui.repaint();
        }
    }
    
    public boolean isBetweenLevels() {
        if(cardShown.equals("wonGamePanel") == true || cardShown.equals("lostGamePanel"))
            return true;
        else
            return false;
    }
    
    public void waitForPlayerAnswareWON() {
        long lastTime = System.currentTimeMillis();
        while(wonGamePanel.getNextLevelButtonPressed() == false && wonGamePanel.getMenuButtonPressed() == false)
            lastTime = frameTime(lastTime);
    }
    
    public void waitForPlayerAnswareLOSE() {
        long lastTime = System.currentTimeMillis();
        while(lostGamePanel.getMenuButtonPressed() == false)       
            lastTime = frameTime(lastTime);            
    }
    
    public String waitForPlayerName() {
        long lastTime = System.currentTimeMillis();
        while(lostGamePanel.getAddScoreButtonPressed() == false && lostGamePanel.getMenuButtonPressed() == false)       
            lastTime = frameTime(lastTime);
        lostGamePanel.setAddScoreButtonPressed(false);
        return lostGamePanel.getPlayerName();
    }
    
    public void nextLevel() {
        show("gamegui");
        if(gameGui.isLevelRunning() == true)
           drawGame();
    }
    
    public void set(GameState _gameState) {
        gameGui.set(_gameState);
    }

    public boolean isGameRunning() {
        if(menuGui.shouldExit() == true)
            return false;
        return !menuGui.isShown();
    }
    
    public boolean getNextLevelButtonPressed() {
        return wonGamePanel.getNextLevelButtonPressed();
    }
    
    public void setNextLevelButtonPressed(boolean NextButton) {
        wonGamePanel.setNextLevelButtonPressed(NextButton);
    }
    
    public boolean getMenuButtonPressed() {
        return wonGamePanel.getMenuButtonPressed();
    }
    
    public void setMenuButtonPressed(boolean MenuPressed) {
        wonGamePanel.setMenuButtonPressed(MenuPressed);
        lostGamePanel.setMenuButtonPressed(MenuPressed);
    }
    
    public boolean getDrowGame() {
        return DrowGame;
    }
    
    public void setDrowGame(boolean _DrowGame) {
        DrowGame = _DrowGame;
        if(DrowGame == true)
            show("gamegui");
    }
    
    public boolean isCardShown(String card) {
        if(cardShown.equals(card))
            return true;
        else
            return false;
    }
    
    public void writeScoreTable(String ScoreTable) {
        lostGamePanel.setScoreAreaText(ScoreTable);
    }
    
    public void writeFinalScore(int score) {
        lostGamePanel.setScore(Integer.toString(score));
    }
    
    private void exitGame() {
        System.exit(0);
    }
}

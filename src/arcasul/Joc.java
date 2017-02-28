package arcasul;

import arcasul.GUI.GUI;
import arcasul.Level.GameState;

/**
 *
 * @author Lydya0103
 */
public class Joc extends Thread {
    private static final FileScore ScoreTable = new FileScore();
    
    private static int Level = 1;
    private static int FinalScore = 0;
    
    private GameState gameState; 
    
    @Override
    public void run() {        
        GUI Gui = new GUI();
        Gui.start();
        
        long lastTime = System.currentTimeMillis();
        while(true)
        {                        
            if(Gui.isGameRunning() == true)
            {
                gameState = new GameState(Level);                
                Gui.set(gameState);
                
                while(true)
                {
                    if(Gui.isCardShown("gamegui") == true)
                    {
                        while(gameState.isLevelRunning() == true)
                        {
                            lastTime = frameTime(lastTime);
                            gameState.update();
                        }
                        FinalScore += gameState.getScore();
                        setElementsForGUI(Gui);                        
                        break;
                    }
                    else
                    {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ex) {
                        }
                    }
                }
            }            
            
            while(Gui.isBetweenLevels() == true)
            {
                if(gameState.getPlayerWon() == true)
                {
                    Gui.waitForPlayerAnswareWON();
                    if(Gui.getNextLevelButtonPressed() == true)
                    {
                        Level++;
                        Gui.setDrowGame(true);
                        Gui.setNextLevelButtonPressed(false);
                        break;
                    }
                    else if (Gui.getMenuButtonPressed() == true)
                    {
                        Gui.setDrowGame(false);
                        Level = 1;
                        FinalScore = 0;
                        Gui.show("menugui");
                        Gui.setMenuButtonPressed(false);
                        break;
                    }
                }
                else
                {
                    Gui.writeScoreTable(ScoreTable.getScoreTable());
                    Gui.writeFinalScore(FinalScore);
                    String PlayerName = Gui.waitForPlayerName();
                    if(PlayerName != null || !"".equals(PlayerName))
                    {
                        ScoreTable.adaugaScor(PlayerName, FinalScore);
                        Gui.writeScoreTable(ScoreTable.getScoreTable());
                    }                                        
                    Gui.waitForPlayerAnswareLOSE();
                    
                    Level = 1;
                    ScoreTable.salveazaTabelaScor();
                    FinalScore = 0;
                    Gui.show("menugui");
                    Gui.setMenuButtonPressed(false);
                }                
            }
            if (Gui.isAlive() == false) 
                break;
        }
    }
    
    private long frameTime(long lastTime) {
        while(System.currentTimeMillis() - lastTime < 50)
            {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                }  
            }
        return System.currentTimeMillis();
    }
    
    private void setElementsForGUI(GUI Gui) {
        Gui.setDrowGame(false);        
        if(gameState.getPlayerWon() == true)                            
            Gui.show("wonGamePanel");
        else
            Gui.show("lostGamePanel");
    }
}

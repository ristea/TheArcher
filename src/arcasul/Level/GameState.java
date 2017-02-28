package arcasul.Level;

import arcasul.Balon.*;
import arcasul.Entity.Player;
import arcasul.Music.SageataMusic;
import arcasul.Sageti.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Lydya0103
 */
public class GameState {
    private static final List<Balon> ListaBaloane = Collections.synchronizedList(new LinkedList<Balon>());
    private static final List<Sageata> ListaSageti = Collections.synchronizedList(new LinkedList<Sageata>()); 
    private static final Collision collision = new Collision();
    
    private static final int NR_BALOANE = 15;
    private static final int NR_SAGETI = 15;
    private static final int TIMP_CADERE_BALOANE = 1500;
    
    private static SageataMusic arrowMusic;
    private static int ScoreForWin;
    private static int Score;
    private static int Time = 5;
    
    private boolean PlayerWon;
    private boolean levelRunning;
    private boolean SecondElapsed;
    private double lastTime;
    
    private List<String> KeyList;
    private final Player player;
    
    public GameState(int _Level)
    {
        player = new Player();
        setElementForLevel(_Level);
        levelRunning = true;
        PlayerWon = false;
        SecondElapsed = false;
        lastTime = System.currentTimeMillis();
        System.out.println(_Level);
    }
    
    public List<Balon> getBaloane() {
        return ListaBaloane;
    }
    
    public List<Sageata> getSageti() {
        return ListaSageti;
    }
    
    public Player getPlayer() {
        return player;
    }
    
    public void update() {
        player.update();
        solveKeyPressed();
        if(System.currentTimeMillis() - lastTime > 500)
        {
            TimeScore(); // se ocupa de numararea secundelor din scor
            if(System.currentTimeMillis() - lastTime > TIMP_CADERE_BALOANE)
            {
                cadeBalon();
                SecondElapsed = false;
                lastTime = System.currentTimeMillis();
            }
        }
        
        for(Balon b : ListaBaloane)
        {
            if(b.getIsFlying() == false)
                continue;
            b.update();
        }
            
        for(Sageata s : ListaSageti)
        {
            if(s.getArrowInArch() == true)
                s.setY(player.getY());
            if(s.getIsFlying() == false)   
                continue;
            
            for(Balon b : ListaBaloane)
            {
                if(b.getIsFlying() == false)    
                    continue;                
                s.update();
                
                if(collision.areColliding(s, b) == true)              
                    Score += b.getPoints();
            }
        }
        if(levelIsOK() == false)
            levelRunning = false;
    }
   
    private boolean levelIsOK() {
        if( Time <= 0 )
        {
            verifyPlayerWon();
            clearLists();
            return false;
        }
        else
        {
            for(Sageata s : ListaSageti)
            {
                if(!(s.getIsFlying() == false && s.getIsCollided() == true))
                {
                    //conditia verifica daca mai am sageti in lista care mai pot fi lansate
                    return true;
                }                    
            }
            verifyPlayerWon();
            clearLists();
            return false;
        }   
    }
    
    private void solveKeyPressed() {
        if(KeyList.isEmpty() == true)
            return;
        else
        {
            String CurrentKey = KeyList.get(0);
            switch (CurrentKey) {
                case "W pressed":
                    player.movePlayer("UP");
                    break;
                case "S pressed":
                    player.movePlayer("DOWN");
                    break;
                case "W released":
                case "S released":
                    player.movePlayer("STOP");
                    break;
                case "SPACE pressed":
                    lanseazaSageata();
                    break;
                default:
                    break;
            }
            KeyList.remove(CurrentKey);
        }
    }
    
    public boolean isLevelRunning() {
        return levelRunning;
    }
    
    public int getScore() {
        return Score;
    }
    
    public int getScoreTime() {
        return Time;
    }
    
    private void cadeBalon() {             
        for(Balon b : ListaBaloane)
        {
            if(b.getIsFlying() == false && b.getIsCollided() == false)
            {
                b.setIsFlying(true);
                break;
            }
        }
    }
    
    private void lanseazaSageata() {
        for(Sageata s : ListaSageti)
        {
            if(s.getArrowInArch() == true)
            {                
                s.setIsFlying(true);
                s.setArrowInArch(false);                
            }
            if(s.getIsFlying() == false && s.getIsCollided() == false)
            {
                arrowMusic = new SageataMusic();
                arrowMusic.start();
                s.setArrowInArch(true);
                break;
            }
        }
    }
    
    private void setElementForLevel(int Level) {
        Score = 0;
        Time = 15 + Level  ;
        ScoreForWin = 30 + 5 * Level;
        Random rand = new Random();
        for(int i = 0; i < NR_BALOANE; i++)
        {
            int randBalonType = rand.nextInt(3);            
            switch (randBalonType) {
                case 0:
                    ListaBaloane.add(new Balon5( 2 + Level));
                    break;
                case 1:
                    ListaBaloane.add(new Balon10( 3 + Level));
                    break;
                case 2:
                    ListaBaloane.add(new Balon15( 4 + Level));
                    break;
                default:
                    break;
            }
        }     
        for(int i=0; i < NR_SAGETI; i++)
        {
            ListaSageti.add(new SageataStandard(player.getY()));
        }
        ListaSageti.get(0).setArrowInArch(true);
        //ListaSageti.add(new SageataPremium(player.getY()));
    }
    
    private void TimeScore() {
        if(SecondElapsed == false)
        {
            Time--;
            SecondElapsed = true;
        }
    }
    
    private void clearLists() {
        ListaBaloane.clear();
        ListaSageti.clear();
    }
    
    public void setKeyList(List l) {
        KeyList = l;
    }
    
    public boolean getPlayerWon() {
        return PlayerWon;
    }
    
    private void verifyPlayerWon() {
        if(Score >= ScoreForWin)
            PlayerWon = true;
        else
            PlayerWon = false;
    }
}

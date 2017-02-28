package arcasul.GUI;

import arcasul.Balon.Balon;
import arcasul.Entity.Player;
import arcasul.Level.GameState;
import arcasul.Sageti.Sageata;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Lydya0103
 */
public class GameGUI extends JPanel {
    private static final String BALLON_IMAGE_PATH_TYPE0 = "resources" + File.separator + "BalonMov.png";
    private static final String BALLON_IMAGE_PATH_TYPE1= "resources" + File.separator + "BalonPortocaliu.png";
    private static final String BALLON_IMAGE_PATH_TYPE2 = "resources" + File.separator + "BalonRosu.png";
    private static final String AROW_STANDARD_IMAGE_PATH = "resources" + File.separator + "arrow1.png";
    private static final String AROW_PREMIUM_IMAGE_PATH = "resources" + File.separator + "arrow3.png";
    private static final String PLAYER_IMAGE_PATH = "resources" + File.separator + "bow2.png";
    
    private List<String> KeyList = Collections.synchronizedList(new LinkedList<String>());
    private List<Balon> ListaBaloane;
    private List<Sageata> ListaSageti;    
    
    private BufferedImage BalloonImageType0;
    private BufferedImage BalloonImageType1;
    private BufferedImage BalloonImageType2;
    private BufferedImage ArrowsStandardImage;
    private BufferedImage ArrowsPremiumImage;
    private BufferedImage PlayerImage;
    
    private Player player;
    private GameState gameState;

    private BufferedImage bImage = new BufferedImage(GUI.WIDTH, GUI.HEIGHT, BufferedImage.TYPE_INT_RGB);
    
    public GameGUI()
    {
        try {
            BalloonImageType0 = ImageIO.read(new File(BALLON_IMAGE_PATH_TYPE0));
            BalloonImageType1 = ImageIO.read(new File(BALLON_IMAGE_PATH_TYPE1));
            BalloonImageType2 = ImageIO.read(new File(BALLON_IMAGE_PATH_TYPE2));
            ArrowsStandardImage = ImageIO.read(new File(AROW_STANDARD_IMAGE_PATH));
            ArrowsPremiumImage = ImageIO.read(new File(AROW_PREMIUM_IMAGE_PATH));
            PlayerImage = ImageIO.read(new File(PLAYER_IMAGE_PATH));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        this.setFocusable(true);
        this.requestFocusInWindow(true);
        this.setOpaque(true);  
        this.setLayout(null);
        startKeyboardListener();
    }
    
    public void set(GameState _gameState) {
        gameState = _gameState;
        gameState.setKeyList(KeyList);
    }
    
    @Override
    protected void paintComponent(Graphics gg) {
        super.paintComponent(gg);
        
        if (gameState != null)
        {
            Graphics2D g2D = (Graphics2D)gg;
            g2D.setColor(Color.YELLOW);
            g2D.fillRect(0, 0,  bImage.getWidth(),  bImage.getHeight());            
            
            ListaBaloane = gameState.getBaloane();
            ListaSageti = gameState.getSageti();
            player = gameState.getPlayer();
            
            g2D.drawImage(PlayerImage, player.getX(), player.getY() - 30, null);            
            for(Balon b : ListaBaloane)
            {
                if(b.getIsFlying() == false)
                    continue;
                switch (b.getBaloonType()) {
                    case 0:
                        g2D.drawImage(BalloonImageType0, b.getX(), b.getY(), null);
                        break;
                    case 1:
                        g2D.drawImage(BalloonImageType1, b.getX(), b.getY(), null);
                        break;
                    default:
                        g2D.drawImage(BalloonImageType2, b.getX(), b.getY(), null);
                        break;
                }
            }                
            for(Sageata s : ListaSageti)
            {
                if(s.getArrowInArch() == true)
                {
                    g2D.drawImage(ArrowsStandardImage, s.getX(), s.getY(), null);
                    continue;
                }
                if(s.getIsCollided() == false && s.getIsFlying() == true && s.getId().equals("SAGEATA_PREMIUM"))
                    g2D.drawImage(ArrowsPremiumImage, s.getX(), s.getY(), null);
                else if(s.getIsCollided() == false && s.getIsFlying() == true)
                    g2D.drawImage(ArrowsStandardImage, s.getX(), s.getY(), null);
            }                
            paintScore(g2D);
            g2D.dispose();
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600);
    }
    
    public boolean isLevelRunning() {
        return gameState.isLevelRunning();
    }
    
    private void paintScore(Graphics2D g2D) {
        g2D.setFont(new Font("TimesRoman", Font.PLAIN, 15)); 
        g2D.setColor(Color.BLUE);
        g2D.drawString("Time left : " + Integer.toString(gameState.getScoreTime()), 10, 15);
        g2D.drawString("Score : " + Integer.toString(gameState.getScore()), 10, 30);
    }

    private void startKeyboardListener() {        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "W pressed");
        this.getActionMap().put("W pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                KeyList.add("W pressed");
            }
        });
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "W released");
        this.getActionMap().put("W released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                KeyList.add("W released");
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "S pressed");
        this.getActionMap().put("S pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                KeyList.add("S pressed");
            }
        });
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "S released");
        this.getActionMap().put("S released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                KeyList.add("S released");
            }
        });
        
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false), "SPACE pressed");
        this.getActionMap().put("SPACE pressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                KeyList.add("SPACE pressed");
            }
        });
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true), "SPACE released");
        this.getActionMap().put("SPACE released", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                KeyList.add("SPACE released");
            }
        });
    }
}


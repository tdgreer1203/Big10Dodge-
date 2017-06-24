
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Theodore Greer && Ruen Mesta
 */
public class Game extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
    Timer tim;
    JLabel beaverStadium, hits, totalHits;
    JLabel field;
    boolean mouseReady, readyToMove;
    ImageIcon background;
    player myPlayer;
    int difficulty, numOfEnemies, numOfHits, numOfTotalHits;
    int level = 1;
    int delay;
    myJFrame mjf1;
    ArrayList<enemy> enemies = new ArrayList<enemy>();
    
    public Game(int lvl, myJFrame mjf, int sp){
        numOfHits = 0;
        numOfTotalHits = 0;
        setLayout(null);
        myPlayer = new player();
        mjf1 = mjf;
        difficulty = lvl;
        field = new JLabel();
        hits = new JLabel();
        totalHits = new JLabel();
        hits.setText("Hits this round: " + numOfHits);
        totalHits.setText("Total Hits: " + numOfTotalHits);
        background = new ImageIcon("images/field.png");
        myPlayer.setBounds(myPlayer.x, myPlayer.y, myPlayer.myWidth, myPlayer.myHeight);
        add(myPlayer);
        setFocusable(true);
        addKeyListener(this);
        requestFocusInWindow();
        field.setBounds(0, 0, 800, 600);
        field.setIcon(background);
        
        if(difficulty == 0)
            numOfEnemies = 3;
        if(difficulty == 1)
            numOfEnemies = 5;
        if(difficulty == 2)
            numOfEnemies = 8;
        
        if(sp == 0)
            delay = 12;
        if(sp == 1)
            delay = 10;
        if(sp == 2)
            delay = 8;
        
        for(int i = 0; i < numOfEnemies; i++){
            enemy temp = new enemy(i);
            enemies.add(temp);
        }
        for(enemy temp: enemies)
            add(temp);
        
        hits.setBounds(5, 610, 150, 20);
        totalHits.setBounds(5, 630, 150, 20);
        add(hits);
        add(totalHits);
        
        add(field);
        tim = new Timer(delay, this);
        tim.start();
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    public void updateLabels(){
        hits.setText("Hits this round: " + numOfHits);
        totalHits.setText("Total Hits: " + numOfTotalHits);
    }
    
    public void addNewEnemies(){
        enemy temp = new enemy(numOfEnemies);
        enemies.add(temp);
        
        add(temp);
        add(field);
    }
    
    public void nextLevel() {
        level += 1;
        numOfTotalHits = numOfTotalHits + numOfHits;
        numOfHits = 0;
        mouseReady = false;
        if((level % 2) > 0){
            delay = delay - 1;
            tim.setDelay(delay);
        }
        else{
            if(numOfEnemies < 13){
                numOfEnemies = numOfEnemies + 1;
                addNewEnemies();
            }
        }
        updateLabels();
        myPlayer.resetPlayer();
        tim.start();
    }
    
    public void levelComplete(){
        if(myPlayer.y <= 25){
            String[] options = new String[] {"Next Level", "Quit", "Replay Level"};
            int keepGoing = JOptionPane.showOptionDialog(null, "Congratulations, you have completed level: " + level, "Level Completed",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(keepGoing==0){
                nextLevel();
            }
            if(keepGoing==1){
                this.mjf1.mjp1.setVisible(true);
                this.mjf1.remove(mjf1.g);
                tim.stop();
            }
            if(keepGoing==2){
                myPlayer.resetPlayer();
                numOfHits = 0;
                updateLabels();
            }
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();
        
        if(obj == tim){
            for(enemy temp: enemies){
                    temp.moveEnemy();
                    if(temp.myBounds().intersects(myPlayer.myBounds())){
                        myPlayer.resetPlayer();
                        numOfHits = numOfHits + 1;
                        updateLabels();
                        mouseReady = false;
                    }
            }
            levelComplete();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT)
            myPlayer.changeX = -10;
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            myPlayer.changeX = 10;
        if(e.getKeyCode() == KeyEvent.VK_UP)
            myPlayer.changeY = -10;
        if(e.getKeyCode() == KeyEvent.VK_DOWN)
            myPlayer.changeY = 10;
        myPlayer.movePlayer();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        myPlayer.changeX = 0;
        myPlayer.changeY = 0;
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        requestFocusInWindow();
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        Point p = me.getPoint();
        if(myPlayer.myBounds().contains(p.x, p.y))
            mouseReady = true;
    }

    @Override
    public void mouseExited(MouseEvent me) {
        mouseReady = false;
    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {
        Point p = me.getPoint();
        int mX = p.x - 38;
        int mY = p.y - 25;
        if(mouseReady){
            if(p.x > 0 && p.y < mjf1.getHeight()){
               myPlayer.x = p.x;
               myPlayer.y = p.y;
               myPlayer.setLocation(mX, mY);
            }
        }
    }
}
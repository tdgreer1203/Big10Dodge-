/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Theo
 */
public class myJPanel1 extends JPanel implements ActionListener {

    myButton start, instruct, credits, level, speed;
    JLabel backHolder, title;
    ImageIcon titleIcon, background,startIcon, levelIcon, creditsIcon, instructIcon, speedIcon;
    ImageIcon startRoll, levelRoll, creditsRoll, instructRoll, speedRoll;
    JFrame startFrame;
    int difficulty = 1;
    int setSpeed = 1;
    Game theGame;
    myJFrame mjf;
            
    public myJPanel1(myJFrame g) {
        setLayout(null);
        mjf = g;
        backHolder = new JLabel();
        title = new JLabel();
        startFrame = new JFrame();
                
        background = new ImageIcon("images/background.jpg");
        startIcon = new ImageIcon("images/start.png");
        startRoll = new ImageIcon("images/startroll.png");
        levelIcon = new ImageIcon("images/level.png");
        levelRoll = new ImageIcon("images/levelroll.png");
        creditsIcon = new ImageIcon("images/credits.png");
        creditsRoll = new ImageIcon("images/creditsRoll.png");
        instructIcon = new ImageIcon("images/howto.png");
        instructRoll = new ImageIcon("images/howtoroll.png");
        titleIcon = new ImageIcon("images/title.png");
        speedIcon = new ImageIcon("images/speedroll.png");
        speedRoll = new ImageIcon("images/speed.png");
        
        backHolder.setIcon(background);
        title.setIcon(titleIcon);
        
        start = new myButton(startIcon, startRoll);
        instruct = new myButton(instructIcon, instructRoll);
        level = new myButton(levelIcon, levelRoll);
        credits = new myButton(creditsIcon, creditsRoll);
        speed = new myButton(speedIcon, speedRoll);
        
        start.addActionListener(this);
        instruct.addActionListener(this);
        credits.addActionListener(this);
        level.addActionListener(this);
        speed.addActionListener(this);
        
        backHolder.setBounds(0, 0, 800, 690);
        start.setBounds(330, 200, 150, 140);
        instruct.setBounds(20, 390, 150, 140);
        credits.setBounds(220, 390, 150, 140);
        level.setBounds(420, 390, 150, 140);
        title.setBounds(140, -150, 800, 500);
        speed.setBounds(620, 390, 150, 140);
        
        add(title);
        add(level);
        add(credits);
        add(instruct);
        add(start);
        add(speed);
        add(backHolder);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object obj = ae.getSource();
        if(obj == start){
            setVisible(false);
            theGame = new Game(difficulty, this.mjf, setSpeed);
            this.mjf.g = theGame;
            this.mjf.add(this.mjf.g);
        }
        else if(obj == instruct) {
            String inst = "<html><center><u>Objective</u></center>This game is based (in a way) on the concept of the popular game<br>"
                    + "<i>Frogger</i>. The objective of the game is to reach the endzone<br>"
                    + "without being stopped by any of the inferior Big 10 schools.<br>"
                    + "Colliding with one of the inferior schools resets your character<br>"
                    + "to the beginning of the board, where you must start over. Reaching<br>"
                    + "the endzone begins a new level, and causes the Big 10 traffic<br>"
                    + "to move faster or increase in speed.<br><br>"
                    + "<center><u>Moving</u></center> "
                    + "Moving is done by using the arrow keys on the keyboard or the mouse. Pressing an <br>"
                    + "arrow key moves your player in the direction of the arrow key being <br>"
                    + "pressed.</html>";
            JOptionPane.showMessageDialog(startFrame, inst, "Instructions", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(obj == credits) {
            String cred = "<html>This game was created"
                    + "for our Fall 2014 IST 240 course. <br>The instructor for this"
                    + "course is Dr. Fred Fonseca.<br><br>Authors:<br><ul>"
                    + "<li>Theodore Greer</li><li>Ruben Mesta</li>"
                    + "</ul></html>";
            JOptionPane.showMessageDialog(startFrame, cred, "Credits", JOptionPane.INFORMATION_MESSAGE);
        }
        else if(obj == level) {
            String[] options = new String[] {"Easy", "Normal", "Hard"};
            difficulty = JOptionPane.showOptionDialog(null, "Please select a level:", "Level",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }
        else if(obj == speed) {
            String[] options = new String[] {"Slow", "Medium", "Fast"};
            setSpeed = JOptionPane.showOptionDialog(null, "Please select a speed:", "Speed",
            JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        }
    }   
}

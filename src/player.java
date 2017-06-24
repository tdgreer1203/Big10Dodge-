
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Theo
 */
public class player extends JLabel{
    int x;
    int y;
    int numberOfHits;
    int changeX;
    int changeY;
    int myWidth = 76;
    int myHeight = 50;
    ImageIcon myIcon;
    
    public player(){
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        x = 350;
        y = 610;
        myIcon = new ImageIcon("images/psu.png");
        setIcon(myIcon);
        setBounds(x, y, myWidth, myHeight);
    }  
    
    public void movePlayer(){
        if(x + changeX > -5 && x + changeX < 725)
            x += changeX;
        if(y + changeY > -5 && y + changeY < 640)
            y += changeY;
        
        setBounds(x, y, myWidth, myHeight);
    }
    
    public void resetPlayer(){
        x = 350;
        y = 610;
        setBounds(x, y, myWidth, myHeight);
    }
    
    public Rectangle myBounds(){
        return new Rectangle(this.x, this.y, this.myWidth, this.myHeight);
    }
}

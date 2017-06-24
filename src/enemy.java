
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class enemy extends JLabel{
    int x;
    int y;
    int setImage;
    int xMax = 800;
    int yMin = 50;
    int myHeight = 76;
    int myWidth = 76;
    int speed;
    int yMax = 400;
    int changeX = 1;
    int changeY = 1;
    Random r = new Random();
    ImageIcon myIcon;
   
    public enemy(int pic){
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        x = r.nextInt(xMax);
        y = r.nextInt(yMax - yMin + 1) + yMin;
        setImage = r.nextInt(13);
        speed = (r.nextInt(3) + 1);
        myIcon = new ImageIcon("images/" + pic + ".png");
        setIcon(myIcon);
        setBounds(x, y, myWidth, myHeight);
    }  
    
    public Rectangle myBounds(){
        return new Rectangle(this.x, this.y, this.myWidth, this.myHeight);
    }
    
    public void moveEnemy(){
        if(x + changeX < 0)
            changeX = speed;
        if(x + changeX > 724)
            changeX = (speed* -1);
        if(y + changeY < 52)
            changeY = speed;
        if(y + changeY > 518)
            changeY = (speed * -1);
        
        x = x + changeX;
        y = y + changeY;
        setLocation(x, y);
    }
}
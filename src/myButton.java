
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Theo
 */
public class myButton extends JButton{
    
    public myButton(ImageIcon a, ImageIcon b){
        setIcon(a);
        setRolloverIcon(b);
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setRolloverEnabled(true);
    }
}
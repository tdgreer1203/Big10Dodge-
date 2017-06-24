
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Theo
 */
public class myJFrame extends JFrame{
    myJPanel1 mjp1;
    Game g;
    
    public myJFrame(){
        super("Mayhem at Beaver Stadium");
        mjp1 = new myJPanel1(this);
        add(mjp1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 690);
        setResizable(false);
        setVisible(true);
    }
}
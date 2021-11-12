
package flappyguitar;

import java.awt.HeadlessException;
import javax.swing.JFrame;

public class FlappyGuitar extends JFrame{

    public FlappyGuitar(String string) throws HeadlessException {
        super(string);
    }


    public static void main(String[] args) {
        FlappyGuitar guitar = new FlappyGuitar("Flappy Bird");
        
            guitar.setResizable(true);
            guitar.setFocusable(false);
            guitar.setSize(500,700);
            
            Flappy flap = new Flappy();
            
            flap.requestFocus();
            flap.addKeyListener(flap);
            flap.setFocusable(true);
            flap.setFocusTraversalKeysEnabled(false);
            
            guitar.add(flap);
            
            guitar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            guitar.setVisible(true);
    }
    
}

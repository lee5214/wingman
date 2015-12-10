

import vehicle.Player;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyControl extends KeyAdapter{
    private GamePanel Gp;
    public KeyControl(GamePanel gp){
        Gp = gp;
        
    }
    
        @Override
	public void keyPressed(KeyEvent e) {
            
		int keycode = e.getKeyCode();
              //  if(PlayerNum != 1){
	//} else {
                switch (keycode) {
                case KeyEvent.VK_UP:
                        Gp.player1.up = true;
                        break;
                case KeyEvent.VK_LEFT:
                        Gp.player1.left = true;
                        break;
                case KeyEvent.VK_RIGHT:
                        Gp.player1.right = true;
                        break;
                case KeyEvent.VK_DOWN:
                        Gp.player1.down = true;
                        break;
                case KeyEvent.VK_SPACE:
                        Gp.player1.isFire = true;
                        
                        break;
                default:
                        break;
                }
            }//}

        @Override
	public void keyReleased(KeyEvent e) {

		int keycode = e.getKeyCode();
		switch (keycode) {
		case KeyEvent.VK_UP:
			Player.up = false;
			break;
		case KeyEvent.VK_LEFT:
			Player.left = false;
			break;
		case KeyEvent.VK_RIGHT:
			Player.right = false;
			break;
		case KeyEvent.VK_DOWN:
			Player.down = false;
			break;
		case KeyEvent.VK_X:
			//isFire = false;
			break;
		default:
			break;
		}
	}
        @Override
	public void keyTyped(KeyEvent e) {

	}
}

import pub.Constant;
import pub.LoopMusic;
import java.awt.Cursor;

import javax.swing.JFrame;
public class CSWingManGame extends JFrame {


	public CSWingManGame() {
           
		this.setTitle("Cong Li");
		this.setCursor(new Cursor(1));
		this.setSize(Constant.FRAME_WIDTH, Constant.FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		GamePanel gp = new GamePanel();
		this.getContentPane().add(gp);
		this.setVisible(true);
                
		gp.requestFocus();
		gp.StartRun();
                LoopMusic m = new LoopMusic();
                m.run();
	}

	public static void main(String[] args) {
		new CSWingManGame();
	}

}

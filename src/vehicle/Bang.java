package vehicle;

import pub.PlaySound;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import basic.Base;
//import basic.game.pub.Play;
import java.awt.Toolkit;

public class Bang extends Base {
        private boolean live = true;
	private int xpic;
	private boolean isBang = false;

	public boolean isBang() {
		return isBang;
	}

	public void setBang(boolean isBang) {
		this.isBang = isBang;
	}

	public int getXpic() {
		return xpic;
	}

	public void setXpic(int xpic) {
		this.xpic = xpic;
	}

	private Timer timer;

	public Bang() {

		timer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				xpic += 66;
				if (xpic >= 528) {
					timer.stop();
					isBang = true;
				}
			}
		});
		timer.start();
		PlaySound.playBang();
	}

	private JPanel panel;
	private Image bang = new ImageIcon("images/bang.png").getImage();

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}
        private static Toolkit tk = Toolkit.getDefaultToolkit();
        private static Image[] enermyExp = {
			tk.getImage(Bang.class.getClassLoader().getResource(
					"Resources/explosion1_1.png")),
			tk.getImage(Bang.class.getClassLoader().getResource(
					"Resources/explosion1_2.png")),
			tk.getImage(Bang.class.getClassLoader().getResource(
					"Resources/explosion1_3.png")),
			tk.getImage(Bang.class.getClassLoader().getResource(
					"Resources/explosion1_4.png")),
			tk.getImage(Bang.class.getClassLoader().getResource(
					"Resources/explosion1_5.png")),
			tk.getImage(Bang.class.getClassLoader().getResource(
					"Resources/explosion1_6.png")),
			};
int step = 0;
	private static boolean init = false;

	public void draw(Graphics g) {
		if (!init) {
			for (int i = 0; i < enermyExp.length; i++) {
				g.drawImage(enermyExp[i], -100, -100, null);
			}
			init = true;
		}

		if (!live) {
			//tc.explodes.remove(this);
			return;
		}

		if (step == enermyExp.length) {
			live = false;
			step = 0;
			return;
		}

		g.drawImage(enermyExp[step], x - 7, y - 9, null);

		step++;
	}

}

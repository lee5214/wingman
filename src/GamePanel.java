
import vehicle.Enemy;
import vehicle.Bang;
import vehicle.Boss;
import vehicle.Player;
import bullet.PBullet;
import bullet.EBullet;
import bullet.Bullet;
import pub.PlaySound;
import pub.Constant;
import pub.Detect;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import basic.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.URL;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements KeyListener {

    private BufferedImage bimg;
    /**
     * �����
     */
    int PlayerNum;
    private static final long serialVersionUID = 1L;
    public Player player1, player2;
    public Vector<Enemy> enemy01s = new Vector<Enemy>();
    public Vector<PBullet> pbullets = new Vector<PBullet>();
    public Vector<EBullet> ebullets = new Vector<EBullet>();
    public Vector<Bang> bangs = new Vector<Bang>();
    public Boss boss = null;
    public Font font = new Font("", Font.BOLD, 16);
    public Random random = new Random();
    public boolean isFire = false;
    public String info = "XXXXXXXX";
    public static int score1,score2;
    public Timer timer;
    public boolean isStop = false;
Image sea = getSprite("Resources/water.png");

        Island I1, I2, I3;

    public GamePanel() {
        // ��ʼ��player
        setBackground(Color.black);

        Image island1, island2, island3, enemy;
        island1 = getSprite("Resources/island1.png");
        island2 = getSprite("Resources/island2.png");
        island3 = getSprite("Resources/island3.png");
        //myPlane = getSprite("Resources/myplane_1.png");

        //enemiesActive = true;
        //gameOver = false;
        //observer = this;

        I1 = new Island(island1, 100, 100, 10, random);
        I2 = new Island(island2, 200, 400, 10, random);
        I3 = new Island(island3, 300, 200, 10, random);

        //m = new MyPlane(myPlane, 300, 360, 10);


        player1 = new Player(1);
        player2 = new Player(2);
        player1.setWidth(80);
        player1.setHeight(80);
        player1.setX(80);
        player1.setY(80);
        player2.setWidth(80);
        player2.setHeight(80);
        player2.setX(300);
        player2.setY(300);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EnemyShot();
                Boss1Shot();
            }
        });
        timer.start();
        //
        this.addKeyListener(this);;
        //this.addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Dimension d = getSize();
Graphics2D g2 = createGraphics2D(d.width, d.height);
        drawDemo(d.width, d.height, g2);
        g2.dispose();
        g.drawImage(bimg, 0, 0, this);
        this.setBackground(Color.BLACK);
        drawInfo(g);
        player1.draw(g);
        player2.draw(g);

        for (int i = 0; i < pbullets.size(); i++) {
            if (!pbullets.elementAt(i).isUsed()) {
                pbullets.elementAt(i).setPanel(this);
                pbullets.elementAt(i).draw(g);
            }
        }

        for (int i = 0; i < enemy01s.size(); i++) {
            enemy01s.elementAt(i).setPanel(this);
            enemy01s.elementAt(i).draw(g);
        }

        for (int i = 0; i < bangs.size(); i++) {
            bangs.elementAt(i).setPanel(this);
            bangs.elementAt(i).draw(g);
        }

        for (int i = 0; i < ebullets.size(); i++) {
            if (!ebullets.elementAt(i).isUsed()) {
                ebullets.elementAt(i).setPanel(this);
                ebullets.elementAt(i).draw(g);
            }
        }

        if (boss != null) {
            boss.draw(g);
        }

        drawBossInfo(g);
    }

    public void StartRun() {
        new Thread() {
            public void run() {
                int counter = 0;
                while (true) {
                    while (true && !isStop) { 
                    
                        AddEnemy();
                    
                        EnemyMove();
                   
                        player1.move();
                        player2.move();
                       
                        if (player1.isFire && counter % 25 == 0) {
                            Bullet[] bs = player1.shot();
                            for (int i = 0; i < bs.length; i++) {
                                pbullets.add((PBullet) bs[i]);
                               // PlaySound.playShot();
                            }
                            
                        }
                        if (player2.isFire && counter % 25 == 0) {
                            Bullet[] bs = player2.shot();
                            for (int i = 0; i < bs.length; i++) {
                                pbullets.add((PBullet) bs[i]);
                               // PlaySound.playShot();
                            }
                        }
                       
                        PBulletMove();
                       
                        Judge();
                       
                        RemoveBang();
                       
                        RemoveEBullet();
                       
                        EBulletMove();
                       
                        JudgeLife();

                        showBoss1();
                        if (boss != null) {
                            boss.move();
                        }
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        repaint();
                    }
                }
            }
        }.start();
    }

    public class Island {

        Image img;
        int x, y, speed;
        Random gen;

        Island(Image img, int x, int y, int speed, Random gen) {
            this.img = img;
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.gen = gen;
        }

        public void update(int w, int h) {
            y += speed;
            if (y >= h) {
                y = -100;
                x = Math.abs(gen.nextInt() % (w - 30));
            }
        }

        public void draw(Graphics g, ImageObserver obs) {
            g.drawImage(img, x, y, obs);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int keycode = e.getKeyCode();
        //  if(PlayerNum != 1){
        //} else {

        switch (keycode) {
            case KeyEvent.VK_UP:
                Player.up = true;
                break;
            case KeyEvent.VK_LEFT:
                Player.left = true;
                break;
            case KeyEvent.VK_RIGHT:
                Player.right = true;
                break;
            case KeyEvent.VK_DOWN:
                Player.down = true;
                break;
            case KeyEvent.VK_SPACE:
                player1.isFire = true;

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
            case KeyEvent.VK_SPACE:
                player1.isFire = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public Image getSprite(String name) {
        URL url = GamePanel.class.getResource(name);
        Image img = getToolkit().getImage(url);
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(img, 0);
            tracker.waitForID(0);
        } catch (Exception e) {
        }
        return img;
    }

    public void drawBackGroundWithTileImage(int w, int h, Graphics2D g2) {
        int TileWidth = sea.getWidth(this);
        int TileHeight = sea.getHeight(this);

        int NumberX = (int) (w / TileWidth);
        int NumberY = (int) (h / TileHeight);

        Image Buffer = createImage(NumberX * TileWidth, NumberY * TileHeight);
        Graphics BufferG = Buffer.getGraphics();


        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g2.drawImage(sea, j * TileWidth, i * TileHeight + ( TileHeight), TileWidth, TileHeight, this);
            }
        }
        //move += speed;
    }

    public void drawDemo(int w, int h, Graphics2D g2) {

        if (true) {
            drawBackGroundWithTileImage(w, h, g2);
            I1.update(w, h);
            I1.draw(g2, this);

            I2.update(w, h);
            I2.draw(g2, this);

            I3.update(w, h);
            I3.draw(g2, this);

           // m.draw(g2, this);
        }

    }
    public Graphics2D createGraphics2D(int w, int h) {
        Graphics2D g2 = null;
        if (bimg == null || bimg.getWidth() != w || bimg.getHeight() != h) {
            bimg = (BufferedImage) createImage(w, h);
        }
        g2 = bimg.createGraphics();
        g2.setBackground(getBackground());
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2.clearRect(0, 0, w, h);
        return g2;
    }

    // �����ӵ��ƶ�����
    public void PBulletMove() {
        for (int i = 0; i < pbullets.size(); i++) {
            pbullets.elementAt(i).move();
            if (pbullets.elementAt(i).getY() <= 0) {
                pbullets.remove(i);
            }
        }
    }

    public void AddEnemy() {
        if (enemy01s.size() <= 8 && boss == null) {
            Enemy e01 = new Enemy();
            e01.setX(random.nextInt(Constant.FRAME_WIDTH - 100) + 50);
            e01.setY(-30);
            e01.setHeight(30);
            e01.setWidth(30);
            enemy01s.add(e01);
        }
       /* if(enemy01s.size()>=3){
            Enemy e02 = new Enemy();
            e02.setX(-30);
            e02.setY(random.nextInt(Constant.FRAME_HEIGHT - 600) + 50);
            e02.setHeight(30);
            e02.setWidth(30);
            enemy01s.add(e02);
        }*/
    }

    public void EnemyMove() {
        for (int i = 0; i < enemy01s.size(); i++) {
            enemy01s.elementAt(i).move();
//for(int j =0;j<=enemy01s.size()/2;j++){
  //  enemy01s.elementAt(i).move2();

            if (Detect.C3(player1.x, player1.y + 20, 80, 60, enemy01s.elementAt(i))) {
                Bang b = new Bang();
                b.setX(enemy01s.elementAt(i).getX());
                b.setY(enemy01s.elementAt(i).getY());
                bangs.add(b);
                score1 += 10;
                player1.setLife(player1.getLife() - 5);
                enemy01s.remove(i);
            }
            if (Detect.C3(player2.x, player2.y + 20, 80, 60, enemy01s.elementAt(i))) {
                Bang b = new Bang();
                b.setX(enemy01s.elementAt(i).getX());
                b.setY(enemy01s.elementAt(i).getY());
                bangs.add(b);
                score2 += 10;
                player1.setLife(player1.getLife() - 5);
                enemy01s.remove(i);
            //}
        }}
        for (int j = 0; j < enemy01s.size(); j++) {
            if (enemy01s.elementAt(j).getY() >= Constant.FRAME_HEIGHT - 30) {// ���Խ��ɾ��
                enemy01s.remove(j);
            }
        }
    }

    public void Judge() {
        for (int i = 0; i < pbullets.size(); i++) {
            for (int j = 0; j < enemy01s.size(); j++) {
                if (Detect.C1(pbullets.elementAt(i), enemy01s.elementAt(j))
                        && !pbullets.elementAt(i).isUsed()) {
                    enemy01s.elementAt(j).setLife(
                            enemy01s.elementAt(j).getLife()
                            - pbullets.elementAt(i).getHurt());
                    pbullets.elementAt(i).setUsed(true);
                    if (enemy01s.elementAt(j).getLife() <= 0) { 
                        //
                        bangs.add(enemy01s.elementAt(j).getBang()); 
                        enemy01s.remove(j); 
                        score1 += 10;
                        score2 += 10;
                    }
                }
            }
            // 
            if (boss != null) {
                if (Detect.Testing(pbullets.elementAt(i), boss)
                        && !pbullets.elementAt(i).isUsed()) {
                    boss.setLife(boss.getLife()
                            - pbullets.elementAt(i).getHurt());
                    pbullets.elementAt(i).setUsed(true);
                    if (boss.getLife() <= 0) {
                        boss.setLife(0);

                        int result = JOptionPane.showConfirmDialog(this,
                                "You win! Try again?", "Remind", JOptionPane.YES_OPTION);
                        if (result == 0) {
                            reset();
                        } else {
                            System.exit(0);
                        }
                    }
                }
            }

        }
    }


    public void RemoveBang() {
        for (int i = 0; i < bangs.size(); i++) {
            if (bangs.elementAt(i).isBang()) { 
                bangs.remove(i);
            }
        }
    }

    public void drawInfo(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(300, 20, player1.getLife(), 10);
        g.setColor(Color.green);
        g.fillRect(800, 20, player2.getLife(), 10);
        g.setFont(font);
        g.drawString(String.format("%d", player1.getLife()), 250, 30);
        g.drawString(String.format("%d", player2.getLife()), 1100, 30);
        info = "Score1:" + score1 + "";
        g.drawString(info, 100, 30);
        info = "Score2:" + score2 + "";
        g.drawString(info, 600, 30);
    }

    public void drawBossInfo(Graphics g) {
        if (boss != null) {
            g.setColor(Color.red);
            g.fillRect(Constant.FRAME_WIDTH - 30, 30, 10, boss.getLife());
            g.drawString(String.format("%d", boss.getLife()),
                    Constant.FRAME_WIDTH - 40, 20);
        }

    }


    public void EnemyShot() {
        for (int i = 0; i < enemy01s.size(); i++) {
            EBullet[] es = (EBullet[]) enemy01s.elementAt(i).shot();
            for (int j = 0; j < es.length; j++) {
                ebullets.add(es[j]);
            }
        }
    }

    public void RemoveEBullet() {
        for (int i = 0; i < ebullets.size(); i++) {
            if (ebullets.elementAt(i).getY() > Constant.FRAME_HEIGHT
                    || ebullets.elementAt(i).isUsed) {
                ebullets.remove(i);
            }
        }
    }

    // �з��ӵ��ƶ�
    public void EBulletMove() {
        for (int i = 0; i < ebullets.size(); i++) {
            ebullets.elementAt(i).move();
            if (Detect.C1(ebullets.elementAt(i), player1)
                    && (!ebullets.elementAt(i).isUsed())) {
                player1.setLife(player1.getLife()
                        - ebullets.elementAt(i).getHurt());
                ebullets.elementAt(i).setUsed(true);
            }

        }
    }

    // �ж�����
    public void JudgeLife() {
        if (player1.getLife() <= 0) {
            int result = JOptionPane.showConfirmDialog(this, "You lost, try again?", "Remind",
                    JOptionPane.YES_OPTION);
            if (result == 0) {
                reset();

            } else {
                System.exit(0);
            }
        }
    }

    public void showBoss1() {

        if (score1+score2 >= 250 && boss == null) {
            boss = new Boss();
            boss.setX(200);
            boss.setY(20);
            boss.setWidth(100);
            boss.setHeight(100);
        }
    }

    public void Boss1Shot() {
        if (boss != null) {
            EBullet[] bs = (EBullet[]) boss.shot();
            for (int i = 0; i < bs.length; i++) {
                ebullets.add(bs[i]);
            }
        }
    }

    public void reset() {
        ebullets.clear();
        pbullets.clear();
        enemy01s.clear();
        player1.setX(Constant.FRAME_WIDTH / 2 - player1.getWidth() / 2);
        player2.setY(Constant.FRAME_HEIGHT - 20 * player2.getHeight());
        player1.setLife(100);
        player1.setLife(100);
        score1 = 0;
        score2 = 0;
        Player.up = false;
        Player.down = false;
        Player.left = false;
        Player.right = false;
        player1.isFire = false;
        player2.isFire = false;
        boss = null;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package flappyguitar;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author 90553
 */
public class Flappy extends JPanel implements KeyListener,ActionListener{
    private BufferedImage flap,zemin,zemin2,asfalt,background;
    private Random random = new Random();
    private int rant = random.nextInt(3);
    private int zeminX = 0;
    private long zam1 =0;
    private long zam2=0;
    private long zam3=0;
    private long zam4=0;
    private int puan = 0;
    private int [] y = {290,390,490};
    private int [] h = {290,190,90};
    private int [] h2 = {100,200,300};
    private int hiz = 12 ;
    private int birkere = 0;
    private int zeminX2 = 500;
    private int [] objex = {1000,1300,1600};
    private int ObjeX1 = 1000;
    private int ObjeX2 = 1300;
    private int ObjeX3 = 1600;
    private int ObjeY1 = 290;
    private int ObjeY2 = 390;
    private int ObjeY3 = 490;
    private int ObjeH1 = 290;
    private int ObjeH12 = 100;
    private int ObjeH2 = 190;
    private int ObjeH22 = 200;
    private int ObjeH3 = 90;
    private int ObjeH32 = 300;
    private int yuruyenObjehiz = 20;
    private int baslat = 0;
    private Lock lock = new ReentrantLock();
    private int anla = 0;
    private int guitarY = 320;
    Timer timer = new Timer(50,this);
    
    public Flappy() {
        try {
            flap = ImageIO.read(new FileImageInputStream(new File("img/flap.png")));
            zemin = ImageIO.read(new FileImageInputStream(new File("img/zemin.png")));
            zemin2 = ImageIO.read(new FileImageInputStream(new File("img/zemin.png")));
            asfalt = ImageIO.read(new FileImageInputStream(new File("img/asfalt.png")));
            background = ImageIO.read(new FileImageInputStream(new File("img/background.png")));
        } catch (IOException ex) {
            Logger.getLogger(Flappy.class.getName()).log(Level.SEVERE, null, ex);
        }
        timer.start();
        
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        g.drawImage(background,0,0, 500, 580, this);
        g.drawImage(asfalt,0,600, 500, 100, this);
        if(baslat == 0){
            g.setColor(Color.GRAY);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50)); 
            g.drawImage(zemin,0,580, 500, 20, this);
            g.drawImage(zemin,500,580, 500, 20, this);
            g.drawString("Press To Space", 70, 250);

            
        }
        else {
            g.drawImage(zemin,zeminX,580, 500, 20, this);
            g.drawImage(zemin,zeminX2,580, 500, 20, this);
                    g.fillRect(ObjeX1, 0, 100, ObjeH12 );
                    g.fillRect(ObjeX1, ObjeY1, 100, ObjeH1);
                    g.fillRect(ObjeX2, 0, 100, ObjeH22 );
                    g.fillRect(ObjeX2, ObjeY2, 100, ObjeH2);
                    g.fillRect(ObjeX3, 0, 100, ObjeH32);
                    g.fillRect(ObjeX3, ObjeY3, 100, ObjeH3);
                    
                
            
           
            g.setColor(Color.WHITE);
            g.drawString("Puan: "+puan, 10, 15);
            
        }
        g.drawImage(flap,170,guitarY, 70, 50, this);
        
    }
    public void collision(){
        Rectangle Bird = new Rectangle(230,guitarY,40,47);
        zam4 = System.currentTimeMillis();
        if(baslat != 0){
        if(Bird.intersects(new Rectangle(ObjeX1+30, -700, 70, ObjeH12+700)) || Bird.intersects(new Rectangle(ObjeX1+30, ObjeY1, 70, ObjeH1)) || Bird.intersects(new Rectangle(ObjeX2+30, ObjeY2, 70, ObjeH2)) || Bird.intersects(new Rectangle(ObjeX2+30, -700, 70, ObjeH22+700)) || Bird.intersects(new Rectangle(ObjeX3+30, -700, 70, ObjeH32+700)) || Bird.intersects(new Rectangle(ObjeX3+30, ObjeY3, 70, ObjeH3))){
            JOptionPane.showMessageDialog(this, "Game Over Puanınız: "+puan);
             System.exit(0); 
        }}
    }
    @Override
    public void repaint() {
        super.repaint(); 
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
       int d = e.getKeyCode();
       
       if(d == KeyEvent.VK_SPACE) {
           baslat=1;
           zam1 = System.currentTimeMillis();
       }
       
    }
    public void gravity(){
        zam3 = System.currentTimeMillis();
        if(zam3 - zam1 <= 200) {
            guitarY -= 2;
        }
        if(zam3 - zam1 <= 400) {
            guitarY -= 6;
        }
        if(zam3 - zam1 <= 500) {
            guitarY -= 8;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rectangle Bird = new Rectangle(230,guitarY,70,100);
        if(baslat != 0 && new Rectangle(230,guitarY,70,40).intersects(new Rectangle(0,575,500,20))) {
           JOptionPane.showMessageDialog(this, "Game Over Puanınız: "+puan);
            System.out.println("Burdan");
           System.exit(0); 
        }
        collision();

        if( Bird.intersects(new Rectangle(ObjeX1+100,ObjeH12,1,100))){
            if(anla == 0){
                puan++;
                anla = 1;
                if(puan % 2 == 0) {
                     hiz++;
                }
            }
        }
        if(Bird.intersects(new Rectangle(ObjeX2+100,ObjeH22,1,100))){
            if(anla == 1){
                puan++;
                anla = 2;
                if(puan % 2 == 0) {
                     hiz++;
                }
            }
        }
        if(Bird.intersects(new Rectangle(ObjeX3+100,ObjeH32,1,100))){
            if(anla == 2){
                puan++;
                anla = 0;
                if(puan % 2 == 0) {
                     hiz++;
                }
            }
        }
        
        if(ObjeX1 >= -100) {
            ObjeX1 -= hiz;
        }
        else {
            ObjeX1=900;
            int a = random.nextInt(3);
            ObjeY1 = 0;
            ObjeH1 = 0;
            ObjeH12 = 0;
            ObjeY1 += y[a];
            ObjeH1 += h[a];
            ObjeH12 += h2[a];
        }
        if(ObjeX2 >= -100) {
           ObjeX2 -= hiz;
           
        }
        else {
            ObjeX2=900;
            int a = random.nextInt(3);
            ObjeY2 = 0;
            ObjeH2 = 0;
            ObjeH22 = 0;
            ObjeY2 += y[a];
            ObjeH2 += h[a];
            ObjeH22 += h2[a];
        }
        if(ObjeX3 >= -100) {
            ObjeX3 -= hiz;
        }
        else {
            ObjeX3=900;
            int a = random.nextInt(3);
            ObjeY3 = 0;
            ObjeH3 = 0;
            ObjeH32 = 0;
            ObjeY3 += y[a];
            ObjeH3 += h[a];
            ObjeH32 += h2[a];
        }
        if(zeminX >= -499) {
            zeminX -=hiz;
        }
        else {
            zeminX = 490;
        }
        if(zeminX2 >= -499) {
            zeminX2 -=hiz;
        }
        else
        {
            zeminX2 = 490;
        }
       if(baslat != 0) {
           zam2 = System.currentTimeMillis();
           
           if(zam2-zam1 >= 250){
               if(zam2-zam1>=1000){
                   guitarY += 15;
               }
               else if(zam2-zam1>=700){
                   guitarY += 12;
               }
               else if(zam2-zam1>=500){
                   guitarY += 9;
               }
               else if(zam2-zam1>=400){
                   guitarY += 8;
               }
               else if(zam2-zam1>=300){
                   guitarY +=7;
               }
               else if(zam2-zam1>=200){
                   guitarY += 6;
               }
               else if(zam2-zam1>=100){
                   guitarY += 5;

               }
                
           }
                
       }
        gravity();
        repaint();
    }
    
}

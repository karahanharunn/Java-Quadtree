package deneme;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Quadtree extends JFrame implements MouseListener , ActionListener{
    int b=1 ,a2=0 , b2=0 ,a1,b1;
    public static int sınır_sag=512 , sınır_sol=0 , sınır_alt=0 , sınır_ust=512 , gir=0,x2=0,y2=0,renk_degistir=0 ,i=1; 
    public static int son_x=0 , son_y=0,son_r=0,cap_degisimi=0 ,eski_cember=0; 
    int sonuc = 0,sonuc2=0;
    public static Node root; 
    int cember=100; 
   static int [] dizi_x=new int[64];
   static int [] dizi_y=new int[64];
    
     JFrame frame;
    JPanel panel;
    JButton button;
    JTextField txtField;
    JPasswordField passField;
    JLabel label;
    public Quadtree() {
         super("Quadtree");
        Container con=getContentPane();
        con.setLayout(new GridLayout());
        addMouseListener(this); //Mouse ekledik
         // Paneli oluşturuyorum
        panel = new JPanel();

        // Nesneleri oluşturuyorum
        button = new JButton("Cember Butonu");
        Button buyut = new Button("+");
        Button kucult = new Button("-");
         Button sonlandır = new Button("Sonlandır");
        txtField = new JTextField(10);
       
        // Nesneleri Panele ekliyorum
        panel.add(button);
        panel.add(txtField);
        panel.add(buyut);
        panel.add(kucult);
         panel.add(sonlandır);
        button.addActionListener(this);
        buyut.addActionListener(this);
        kucult.addActionListener(this);
        sonlandır.addActionListener(this);
        this.add(panel,BorderLayout.SOUTH);
      
        //paneli Frame e ekliyorum
        add(panel); //---&gt;&gt;&gt;frame.add(panel) ile aynı anlamı taşır


    }
     public void actionPerformed(ActionEvent e){
        Quadtree ekle2 = new Quadtree();
        if(e.getActionCommand().equals("Cember Butonu")){
           gir=1;
           
        }
  
       
        if(e.getActionCommand().equals("+")){
             cember=cember+10;
             cap_degisimi=1 ; 
        
            }
            else if(e.getActionCommand().equals("-")){
       
               cember=cember-10 ; 
               cap_degisimi=1 ; 
            }
            else if(e.getActionCommand().equals("Sonlandır")){
           gir=0 ;
           Quadtree.renk_degistir=0;
           Quadtree.i=1;
           cember=100;
           Quadtree cagir = new Quadtree();
           cagir.preorder(root);
            }
      
      
        repaint();
        
    
    }
   
    private Random rnd = new Random();
    public int x1 = 0, y1 = 0;

    public void preorder(Node root) {
    if(root !=  null) {
        //Bu Kod satırı hem ağacı rekürsif olarak gezmeye hemde nokta çemberin içindemi kontrolünü yapmaya yarıyor.
      if(root.x!=0 && root.y!=0)
            
        preorder(root.left);
        preorder(root.right);
        preorder(root.rightalt);
        preorder(root.leftalt);
        
        sonuc=(int) Math.pow(Math.abs(root.y-Quadtree.y2), 2) + (int) Math.pow(Math.abs(root.x-Quadtree.x2), 2);
        if(sonuc<Math.pow(cember,2))
        {    System.out.println("girildi renk değiştir");
             Quadtree.renk_degistir=1 ; 
             dizi_x[Quadtree.i]=root.x;dizi_y[Quadtree.i++]=root.y;
             System.out.println(Quadtree.dizi_x[Quadtree.i-1]+" " + Quadtree.dizi_y[Quadtree.i-1]);
        }
  
    }

  }
    public void insert(int x, int y) {
    sınır_sag=512 ; sınır_sol=0 ; sınır_alt=60 ; sınır_ust=572 ;  
 
    System.out.println(x + " " + y);
        Node newNode = new Node(x, y);
        if (root == null) {
                root = newNode;  
            System.out.println("girildi0");
    
        } else {
            Node current = root;
            Node parent = null;

            while (true) {
                
                parent = current;
                
                if (x < current.x && y < current.y) {
                    
                    if(current.x<sınır_sag)
                    sınır_sag=current.x ; 
                    if(current.y<sınır_ust)
                        sınır_ust = current.y ; 
                    System.out.println("girildi1");
                    current = current.left;

                    if (current == null) {
                        parent.left = newNode;
                       
                  
                        return ;
                    }
                }
                else if (x < current.x && y > current.y) {
                  
                    if(current.x<sınır_sag)
                    sınır_sag=current.x ; 
                    if(current.y>sınır_alt)
                    sınır_alt = current.y ;
                    System.out.println("girildi2");
                    current = current.leftalt;

                    if (current == null) {
                        parent.leftalt = newNode;
                      
                        
                        return;
                    }

                } else if (x > current.x && y > current.y) {
                   
                    if(current.x>sınır_sol)
                    sınır_sol=current.x ; 
                    if(current.y>sınır_alt)
                    sınır_alt = current.y ; 
                    System.out.println("girildi3");
                    current = current.rightalt;
                    if (current == null) {
                        parent.rightalt = newNode;
                   
                
                        return;
                    }

                } else if (x > current.x && y < current.y) {
                    
                    if(current.x>sınır_sol)
                    sınır_sol=current.x ; 
                    if(current.y<sınır_ust)
                    sınır_ust = current.y ;
                    System.out.println("girildi4");
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                 
                        return;
                    }

                }
            }

        }
    }

    public void paint(Graphics G) {
          if(Quadtree.gir==1 && cap_degisimi==1)
        {    G.setColor(Color.WHITE);
            System.out.println("girildipaint");
            G.drawOval(x2-(eski_cember/2), y2-(eski_cember/2),eski_cember, eski_cember);
            cap_degisimi=0;
            
        }
          
          if(Quadtree.gir==1 && x2!=0)
        {    G.setColor(Color.BLUE);
            G.drawOval(x2-(cember/2), y2-(cember/2),cember, cember);
            eski_cember=cember;
            G.setColor(Color.BLACK);
       
          
        }
              G.drawLine(0, 60, 512, 60);
        //Paint ekranı çizim işlemleri
        G.fillOval(x1-3, y1-3, 6, 6);
        G.setColor(Color.RED);
        System.out.println(Quadtree.sınır_alt + " : "+ Quadtree.sınır_ust);
        G.drawLine(x1, Quadtree.sınır_alt, x1, Quadtree.sınır_ust);
        G.drawLine(Quadtree.sınır_sol, y1, Quadtree.sınır_sag, y1);
          while(Quadtree.renk_degistir==1 && Quadtree.i>0)
          {   G.setColor(Color.BLUE);
             
              G.fillOval(Quadtree.dizi_x[Quadtree.i]-3, Quadtree.dizi_y[Quadtree.i]-3,6 , 6);
        
               System.out.println("girildirenkdeğistir"+Quadtree.i+" "+Quadtree.dizi_x[Quadtree.i]+"  "+Quadtree.dizi_y[Quadtree.i]);
                   Quadtree.i--;
            
          }
        renk_degistir=0;
    
       
    }
   

    @Override
    public void mouseClicked(MouseEvent e) {
        Quadtree ekle = new Quadtree();
        
  
       if (e.getButton() == e.BUTTON1 && gir==0)  {
            x1 = e.getX();//  X kordinatını alır
            y1 = e.getY();// Y kordinatını alır
            repaint();//Ekranı Yeniler
            ekle.insert(x1, y1);
            
        }
        
        else if (e.getButton() == e.BUTTON3)
        { 
        x1 = (int)(Math.random()*512);
        y1 = (int)(60+Math.random()*572);
        System.out.println(x1+"   "+y1);
        ekle.insert(x1,y1);
        
        repaint();
        }
        if (e.getButton() == e.BUTTON1 && gir==1 )  {
            Quadtree.x2 = e.getX();//  X kordinatını alır
            Quadtree.y2 = e.getY();// Y kordinatını alır
            repaint();//Ekranı Yeniler
           
    

        }
    }

    @Override
    public void mousePressed(MouseEvent me) {

    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {

    }

    @Override
    public void mouseExited(MouseEvent me) {

    }
}

class Node {

    int x;
    int y;
    Node left;
    Node right;
    Node leftalt;
    Node rightalt;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        left = null;
        right = null;
        leftalt = null;
        rightalt = null;
    }
}

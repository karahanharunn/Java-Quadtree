/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deneme;


import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JFrame;




public class BizimPencere extends JFrame{

    public BizimPencere(){
    Quadtree agac = new Quadtree();
     agac.setSize(512, 572);
     agac.setBackground(Color.BLUE);
     agac.setVisible(true);
     agac.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
    
    public static void main(String[] args) {
    BizimPencere Cagir=new BizimPencere();
      
    }
    
}

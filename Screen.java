import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Image; 
import java.util.ListIterator;
import java.util.Iterator;
import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.swing.Icon;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;

 
public class Screen extends JPanel implements ActionListener {
    
    private Map<Item,Integer> store = new HashMap<Item,Integer>();
    private Map<Item,Integer> cart = new TreeMap<Item,Integer>();
    
    private JButton AddButton,RemoveButton;
    
    private JTextField Name, Price;
    
    private BufferedImage PhotoImage;
    
    private int pencil;
    private int Packbag;
    private int Computer;
    private int Pen;
    private int iphone;
    
    Item store1;
    Item store2;
    Item store3;
    Item store4;
    Item store5;
    Item a;
    

    private int total;
    private double weight2;
    public Screen() {
        this.setLayout(null);
        this.setFocusable(true);
        
        try {
                PhotoImage = ImageIO.read(new File("Back.jpg"));
        } catch (Exception e) {
                e.printStackTrace();
        }
        
        pencil = 34;
        Packbag = 4;
        Computer = 5;
        Pen = 21;
        iphone = 34;
        
        store1 = new Item("Pencil",68,"Pencil.png",0.1);
        store2 = new Item("Packbag",250,"Packbag.png",1);
        store3 = new Item("Mac Pro",2999,"MacBook.png",4.2);
        store4 = new Item("Pen",10,"Pen.png",0.4);
        store5 = new Item("iphoneX",999,"iphoneX.png",2.3);
        
        store.put(store1,pencil);
        store.put(store2,Packbag);
        store.put(store3,Computer);
        store.put(store4,Pen);
        store.put(store5,iphone);
        
        //Add an Item
        AddButton  = new JButton("Add an Item");
        AddButton.setBounds(490, 500, 100, 30);
        AddButton.addActionListener(this);
        this.add(AddButton);
        //Remove an Item
        RemoveButton = new JButton("Remove an Item");
        RemoveButton.setBounds(600, 500, 130, 30);
        RemoveButton.addActionListener(this);
        this.add(RemoveButton);
        // input name
        Name = new JTextField(50);
        Name.setBounds(490, 550,200, 40);
		Name.setText("Input the Item's Name");
        this.add(Name);
        //input price
        Price = new JTextField(50);
        Price.setBounds(490, 600,200, 40);
		Price.setText("Input the Item's Price");
        this.add(Price);
	}
 
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(1250,700);
    }
 
    public void paintComponent(Graphics g) {
        //Backgroud
        g.drawImage(PhotoImage, 0, 0, null);
        //Store
        g.setColor(Color.blue);
        Font fontLarge = new Font("Courier New",Font.BOLD,27);
		Font fontSmall = new Font("Courier New",Font.BOLD,11);
		Font font1 = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font1);
        int xStringPos = 60;
		int yStringPos = 75;
		
		Iterator<Item> it = store.keySet().iterator();
		while(it.hasNext()){
		    Item a = it.next();
		    a.drawImage(g,xStringPos,yStringPos);
			g.drawString(a.toString() + store.get(a) + " left in stock",xStringPos+70,yStringPos+17);
			yStringPos += 70;
			}
		
		//Shopping Cart
		int xStringPos2 = 760;
		int yStringPos2 = 75;
		
		Iterator<Item> it2 = cart.keySet().iterator();
		while(it2.hasNext()){
		    Item b = it2.next();
		    b.drawImage(g,xStringPos2,yStringPos2);
			g.drawString(b.toString() + cart.get(b) + " in cart",xStringPos2+70,yStringPos2+17);
			yStringPos2 += 70;
			if(cart.get(b) == 0){
			    cart.remove(b);
			}
		}
		
		total = 0;
		weight2 = 0;
		
		Iterator<Item> i = cart.keySet().iterator();
		while(i.hasNext()){
		    Item v = i.next();
		    int money = v.getPrice() * cart.get(v);
		    double weight = v.getWeight() * cart.get(v);
		    total += money;
		    weight2 += weight;
		}
		
		g.setFont(fontLarge);
		g.setColor(Color.red);
		g.drawString("1 Pound = 15$",740,500);
		g.drawString("The total weight is "+ weight2+" pounds",740,540);
		double ac = weight2*15;
		g.drawString("The shipping cost is "+ ac+" $",740,570);
		total += ac;
		g.drawString("The Total Price: " + total + " $", 740,660);
		g.setColor(Color.white);
		g.setFont(font1);
		if(total >= 3000 ){
		    g.drawString("you are not rich enough to afford these items :) be happy", 50,630);
		}
		
		
		
		
		
		
		
    }
	
	public void actionPerformed(ActionEvent e) 
	{
	    if(e.getSource() == AddButton){
	        System.out.println("Add Button");
	        
	        boolean check = true;
	        //Iterator<Item> it = store.keySet().iterator();
		    //while(it.hasNext()){
		        //a = it.next();
		        //if(Name.getText().compareTo(a.getName())==0 && Integer.parseInt(Price.getText()) == a.getPrice()){
		            //System.out.println(Integer.parseInt(Price.getText()));
		            Item a = new Item(Name.getText(),Integer.parseInt(Price.getText()));
		            //for(Item each : cart.keySet()){
		                if(cart.containsKey(a)){
		                    if(store.get(a) == 0){
		                        check = false;
		                    }
		                    else{
		                    cart.put(a,cart.get(a)+1);
		                    store.put(a,store.get(a)-1);
		                    check = false;
		                    }
		                }
		            //}
		            if(check){
		                cart.put(a,1);
		                store.put(a,store.get(a)-1);
		            //}
		        }
		    //}
		        
		    
	    }
	    if(e.getSource() == RemoveButton){
	        System.out.println("Remove Button");
	        
	        //Iterator<Item> it2 = cart.keySet().iterator();
	        boolean check = true;
		    //while(it2.hasNext()){
		        //a = it2.next();
		        //if(Name.getText().compareTo(a.getName())==0 && Integer.parseInt(Price.getText()) == a.getPrice()){
		            //for(Item each : cart.keySet()){
		                Item a = new Item(Name.getText(),Integer.parseInt(Price.getText()));
		                if(store.containsKey(a)){
		                
		                    store.put(a,store.get(a)+1);
		                    cart.put(a,cart.get(a)-1);
		                    check = false;
		                     
		                }
		            
		            if(check){
		                store.put(a,1);
		                cart.put(a,cart.get(a)-1);
		            }
		        
		    
		    Iterator<Item> i = cart.keySet().iterator();
		    while(i.hasNext()){
		        Item v = i.next();
		        if(cart.get(v) == 0)
		            cart.remove(v);
		    }
	}
	    

		
		
		repaint();
	} 	
}

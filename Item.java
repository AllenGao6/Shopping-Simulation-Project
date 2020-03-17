import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.net.URL;

import java.awt.image.BufferedImage;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Item implements Comparable<Item>{

    private String name;
    private int price;
    private String imageFile;
    private BufferedImage PhotoImage;
    private double weight;
    
    public Item(String name,int price){
        this.name = name;
        this.price = price;
        if(name.equals("Pencil"))
        {
            weight = 0.1;
            try {
                PhotoImage = ImageIO.read(new File("Pencil.png"));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(name.equals("Packbag"))
        {
            weight = 1;
            try {
                PhotoImage = ImageIO.read(new File("Packbag.png"));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(name.equals("Mac Pro"))
        {
            weight = 4.2;
            try {
                PhotoImage = ImageIO.read(new File("MacBook.png"));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(name.equals("Pen"))
        {
            weight = 0.4;
            try {
                PhotoImage = ImageIO.read(new File("Pen.png"));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(name.equals("iphoneX"))
        {
            weight = 2.3;
            try {
                PhotoImage = ImageIO.read(new File("iphoneX.png"));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public Item(String name, int price, String imageFile,double weight){
        this.name = name;
        this.price = price;
        this.imageFile = imageFile;
        this.weight = weight;
        //import image
        try {
                PhotoImage = ImageIO.read(new File(imageFile));
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
    
    public String getName(){
        return name;
    }
    
    public double getWeight(){
        return weight;
    }   
    
    public int getPrice(){
        return price;
    }
    @Override
    public int compareTo(Item o){
        if(name.equals(o.getName()) && price == o.getPrice()){
            return 0;
        }
        else if(!name.equals(o.getName())){
            return price - o.getPrice();
        }
        else{
            return name.compareTo(o.getName());
        }
    
    }
    
    public boolean equals(Object o){
        Item a = (Item) o;
        if(name.equals(a.getName()) && price == a.getPrice()){
            return true;
        }
        else
        return false;
    }
    
    public int hashCode(){
        int hashCode = name.hashCode();
        hashCode = hashCode * 31 + price; 
        return hashCode;
    }
    public void drawImage(Graphics g, int x, int y)
    {
        //g.setColor(Color.red);
        g.drawImage(PhotoImage, x, y, null);
       
    }
    
    public String toString(){
        return "Name: " + name + "| Price: $" + price + " | Weight : " + weight + "Pounds | ";
    
    }
}
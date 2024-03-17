import java.io.Serializable;

import javax.swing.ImageIcon;

abstract public class Equipment implements Serializable{
    String type;
    String name;
    int price;
    double attack;
    double defence;
    double health;
    double speed;
    ImageIcon image;

    // Helper method to format double values
    private double formatDouble(double value) {
        return Double.parseDouble(String.format("%.1f", value));
    }

    // getters and setters
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }      
    public int getPrice() {
        return price;
    }   
    public double getAttack() {
        return formatDouble(attack);
    }
    public double getDefence() {
        return formatDouble(defence);
    }
    public double getHealth() {
        return formatDouble(health);
    }   
    public double getSpeed() {
        return formatDouble(speed);
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setAttack(double attack) {
        this.attack = formatDouble(attack);
    }
    public void setDefence(double defence) {
        this.defence = formatDouble(defence);
    }
    public void setHealth(double health) {
        this.health = formatDouble(health);
    }
    public void setSpeed(double speed) {
        this.speed = formatDouble(speed);
    }
}
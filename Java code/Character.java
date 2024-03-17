
import java.io.Serializable;

import javax.swing.ImageIcon;

abstract public class Character implements Serializable, Cloneable {
    private String type;
    private String name;
    private int price;
    private double attack;
    private double defence;
    private double health;
    private double speed;
    private Armour armour;
    private Artefact artefact;
    private String category;
    protected int defencePriority;
    protected int attackPriority;
    protected ImageIcon image;

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
    public String getCategory() {
        return category;
    }
    public int getDefencePriority() {
        return defencePriority;
    }
    public int getAttackPriority() {
        return attackPriority;
    }
    public Armour getArmour(){
        return armour;
    }
    public Artefact getArtefact(){
        return artefact;
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
    public void setArmour(Armour armour){
        this.armour = armour;
    }
    public void setArtefact(Artefact artefact){
        this.artefact = artefact;
    }
    public void setCategory(String category){
        this.category = category;
    }

    // Function get the character copies
    @Override
    public Character clone() {
        try {
            return (Character) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Function to equip a character
    public void equip(Equipment equipment) {
        if(equipment.getType().equals("Armour")){
            if (this.armour==null){
                this.armour = (Armour)equipment;
                attack += equipment.attack;
                defence += equipment.defence;
                health += equipment.health;
                speed += equipment.speed;
                price += (int) (0.2*equipment.price);
            }
            else {
                // If the character is already equipped with an armour
                attack = equipment.attack-this.armour.attack;
                defence += equipment.defence-this.armour.defence;
                health += equipment.health-this.armour.health;
                speed += equipment.speed-this.armour.speed;
                price += (int) (0.2*equipment.price-this.armour.price);
            }
        }else{
            if (this.artefact==null){
                this.artefact = (Artefact)equipment;
                attack += equipment.attack;
                defence += equipment.defence;
                health += equipment.health;
                speed += equipment.speed;
                price += (int) (0.2*equipment.price);
            }
            else {
                // If the character is already equipped with an artefact 
                attack = equipment.attack-this.artefact.attack;
                defence += equipment.defence-this.artefact.defence;
                health += equipment.health-this.artefact.health;
                speed += equipment.speed-this.artefact.speed;
                price += (int) (0.2*equipment.price-this.artefact.price);
            }
        }
    }

    // Function to attack 
    public void attack(Character defender) {
        double damage = 0.5 * attack - 0.1 * defender.defence;
        double newHealth = Math.max(0, defender.health - damage);
        defender.setHealth(newHealth);
    }
}

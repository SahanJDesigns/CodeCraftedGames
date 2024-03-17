import javax.swing.ImageIcon;

public class Mage extends Character {
    public Mage(String name, int price, int attack, int defence, int health, int speed) {
        this.setType("Mage");
        this.setName(name);
        this.setPrice(price);
        this.setAttack(attack);
        this.setDefence(defence);
        this.setHealth(health);
        this.setSpeed(speed);
        this.attackPriority =2;
        this.defencePriority = 1;
    }

    @Override
    public Mage clone() {
        return (Mage) super.clone();
    }
}
class Warlock extends Mage{
    Warlock(){
        super("Warlock", 100, 12, 7, 10, 12);
        this.setCategory("Marshlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Mage\\Warlock.png"));
    }
}

class Illusionist extends Mage{
    Illusionist(){
        super("Illusionist", 120, 13, 8, 12, 4);
        this.setCategory("Mystics");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Mage\\Illusionist.png"));
    }
}

class Enchanter extends Mage{
    Enchanter(){
        super("Enchanter", 160, 16, 10, 13, 16);
        this.setCategory("Highlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Mage\\Enchanter.png"));
    }
}

class Conjurer extends Mage{
    Conjurer(){
        super("Conjurer", 195, 18, 15, 14, 12);
        this.setCategory("Highlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Mage\\Conjurer.png"));
    }
}

class  Eldritch extends Mage{
    Eldritch(){
        super(" Eldritch", 270, 19, 17, 18, 14);
        this.setCategory("Mystics");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Mage\\Eldritch.png"));
    }
}
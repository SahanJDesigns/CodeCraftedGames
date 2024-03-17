import javax.swing.ImageIcon;

public class Archer extends Character {
    Archer(String name, int price, int attack, int defence, int health, int speed) {
        this.setType("Archer");
        this.setName(name);
        this.setPrice(price);
        this.setAttack(attack);
        this.setDefence(defence);
        this.setHealth(health);
        this.setSpeed(speed);
        this.attackPriority = 5;
        this.defencePriority = 3;
    }
    @Override
    public Archer clone() {
            return (Archer) super.clone();
    }
}

class Shooter extends Archer{
    Shooter(){
        super("Shooter",80,11,4,6,9);
        this.setCategory("Highlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Archer\\Shooter.png"));
    }
}

class Ranger extends Archer{
    Ranger(){
        super("Ranger",115,14,5,8,10);
        this.setCategory("Highlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Archer\\Ranger.png"));
    }
}

class Sunfire extends Archer{
    Sunfire(){
        super("Sunfire",160,15,5,7,14);
        this.setCategory("Sunchild");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Archer\\Sunfire.png"));
    }
}

class Zing extends Archer{
    Zing(){
        super("Zing",200,16,9,11,14);
        this.setCategory("Sunchild");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Archer\\Zing.png"));
    }
}

class Saggitarius extends Archer{
    Saggitarius(){
        super("Saggitarius",230,18,7,12,17);
        this.setCategory("Mystics");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Archer\\Sagittarius.png"));
    }
}
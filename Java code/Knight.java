import javax.swing.ImageIcon;

public class Knight extends Character {
    public Knight(String name, int price, int attack, int defence, int health, int speed) {
        this.setType("Knight");
        this.setName(name);
        this.setPrice(price);
        this.setAttack(attack);
        this.setDefence(defence);
        this.setHealth(health);
        this.setSpeed(speed);
        this.attackPriority = 4;
        this.defencePriority = 2;
    }

    @Override
    public Knight clone() {
        return (Knight) super.clone();
    }
}

class Squire extends Knight{
    Squire() {
        super("Squire", 85, 8, 9, 7, 8);
        this.setCategory("Marshlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Knight\\Squire.png"));
    }
}

class Cavalier extends Knight{
    Cavalier() {
        super("Cavalier", 110, 10, 12, 7, 10);
        this.setCategory("Highlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Knight\\Cavalier.png"));
    }
}

class Templar extends Knight{
    Templar() {
        super("Templar", 155, 14, 16, 12, 12);
        this.setCategory("Sunchild");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Knight\\Templar.png"));
    }
}

class Zoro extends Knight{
    Zoro() {
        super("Zoro", 180, 17, 16, 13, 14);
        this.setCategory("Highlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Knight\\Zoro.png"));
    }
}

class Swiftblade extends Knight{
    Swiftblade() {
        super("Swiftblade", 250, 18, 20, 17, 13);
        this.setCategory("Marshlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Knight\\Swiftblade.png"));
    }
}
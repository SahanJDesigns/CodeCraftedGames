import javax.swing.ImageIcon;

public class MythicalCreature extends Character {
    public MythicalCreature(String name, int price, int attack, int defence, int health, int speed) {
        this.setType("MythicalCreature");
        this.setName(name);
        this.setPrice(price);
        this.setAttack(attack);
        this.setDefence(defence);
        this.setHealth(health);
        this.setSpeed(speed);
        this.attackPriority = 3;
        this.defencePriority = 4;
    }

    @Override
    public MythicalCreature clone() {
            return (MythicalCreature) super.clone();
    }
}

class Dragon extends MythicalCreature{
    Dragon(){
        super("Dragon", 120, 12, 14, 15, 8);
        this.setCategory("Sunchild");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\MythicalCreatures\\Dragon.png"));
    }
}

class Basilisk extends MythicalCreature{
    Basilisk(){
        super("Basilisk", 165, 15, 11, 10, 12);
        this.setCategory("Marshlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\MythicalCreatures\\Basilisk.png"));
    }
}

class Hydra extends MythicalCreature{
    Hydra(){
        super("Hydra", 205, 12, 16, 15, 11);
        this.setCategory("Marshlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\MythicalCreatures\\Hydra.png"));
    }
}

class Phoenix extends MythicalCreature{
    Phoenix(){
        super("Phoenix", 275, 17, 13, 17, 19);
        this.setCategory("Sunchild");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\MythicalCreatures\\Phoenix.png"));
    }
}

class Pegasus extends MythicalCreature{
    Pegasus(){
        super("Pegasus", 340, 14, 18, 20, 20);
        this.setCategory("Mystics");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\MythicalCreatures\\Pegasus.png"));
    }
}


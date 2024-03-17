import javax.swing.ImageIcon;

public class Artefact extends Equipment {
    Artefact(String name, int price, int attack, double defence, double health, double speed) {
        this.type = "Artefact";
        this.name = name;
        this.price = price;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.speed = speed;
    }
}

class Excalibur extends Artefact{
    Excalibur(){
        super("Excalibur",150,2,0,0,0);
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Artefacts\\Excalibur.jpg"));
    }
}

class Amulet extends Artefact{
    Amulet(){
        super("Amulet", 200,1,-1,1,1);
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Artefacts\\Amulet.jpg"));
    }
}

class Crystal extends Artefact{
    Crystal(){
        super("Crystal", 210,2,1,-1,-1);
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Artefacts\\Crystal.jpg"));
    }
}

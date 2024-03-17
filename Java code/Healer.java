import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Healer extends Character {
    public Healer(String name, int price, int attack, int defence, int health, int speed) {
        this.setType("Healer");
        this.setName(name);
        this.setPrice(price);
        this.setAttack(attack);
        this.setDefence(defence);
        this.setHealth(health);
        this.setSpeed(speed);
        this.attackPriority = 1;
        this.defencePriority = 5;
    }

    // Function to heal the character with the minimum health of an army
    public Character heal(ArrayList<Character> characterList) {
        double minHealth = characterList.get(0).getHealth();
        int minHealthCharacterIndex = 0;

        for (int i = 1; i < characterList.size(); i++) {
            Character character = characterList.get(i);
            if (character.getHealth() < minHealth) {
                minHealthCharacterIndex = i;
                minHealth = character.getHealth();
            }
        }

        Character minHealthCharacter = characterList.get(minHealthCharacterIndex);

        String formattedResult = String.format("%.1f", minHealthCharacter.getHealth() + 0.1 * this.getAttack());
        minHealthCharacter.setHealth(Double.parseDouble(formattedResult));

        characterList.set(minHealthCharacterIndex, minHealthCharacter);

        return minHealthCharacter;
    }

    // Function to heal a given character
    public void heal(Character character) {
        String formattedResult = String.format("%.1f", character.getHealth() + 0.1 * this.getAttack());
        character.setHealth(Double.parseDouble(formattedResult));
    }
}

class Soother extends Healer{
    Soother(){
        super("Soother", 95, 10, 8, 9, 6);
        this.setCategory("Sunchild");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Healer\\Soother.png"));
    }
}

class Medic extends Healer{
    Medic(){
        super("Medic", 125, 12, 9, 10, 7);
        this.setCategory("Highlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Healer\\Medic.png"));
    }
}

class Alchemist extends Healer{
    Alchemist(){
        super("Alchemist", 150, 13, 13, 13, 13);
        this.setCategory("Marshlander");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Healer\\Alchemist.png"));
    }
}

class Saint extends Healer{
    Saint(){
        super("Saint", 200, 16, 14, 17, 9);
        this.setCategory("Mystics");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Healer\\Saint.png"));
    }
}

class Lightbringer extends Healer{
    Lightbringer(){
        super("Lightbringer", 260, 17, 15, 19, 12);
        this.setCategory("Sunchild");
        image = UI.resize(300,250,new ImageIcon("Resources\\Characters\\Healer\\Lightbringer.png"));
    }
}

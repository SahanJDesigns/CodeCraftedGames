import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

abstract public class Homeland implements Serializable {
    String name;
    ImageIcon image;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Function to update the status of a character according to the homeland
    public  void AdaptTOHomeland(Character character){

    }

    // Function to get the bonus status for a character according to the homeland
    public void getbonus(Character attacker,Character reciver) {

    }

    // Function to select the hommeground
    public static void  selectHomeGround(){
        UI.clearFrame();
        UI.setBanneredBackgroundImage();

        Homeland[] homelands = {new Hillcrest(),new Marshland(),new Desert(),new Arcane()  };
        int i=1;
        for(Homeland homeland:homelands){

            JLabel homelandImg = new JLabel(homeland.image);
            homelandImg.setBounds(((1920 - 300) / 8) * (i-1) + 100 * (i)+60, 200 ,300,250);
            JButton select = new StyledButton(homeland.getName());
            select.setBounds(((1920 - 300) / 8) * (i-1) + 100 * (i)+120, 500 ,200,50);
            select.addActionListener(e->{
                UI.player.setHomeland(homeland);
                Database.saveuser(UI.player.castToUser());
                UI.profile();
            });
            i++;
            UI.frame.add(select);
            UI.frame.add(homelandImg);
            UI.frame.revalidate();
            UI.frame.repaint();
        }
    }
}


// Creating subclasses for homelands

class Hillcrest extends Homeland {
    public Hillcrest(){
        name="Hillcrest";
        image = UI.resize(300,250,new ImageIcon("Resources/homeland/Hillcrest.jpg"));
    }

    @Override
    public  void AdaptTOHomeland(Character character) {
        if ("Highlander".equals(character.getCategory())) {
            character.setAttack(character.getAttack() + 1);
            character.setDefence(character.getDefence() + 1);
        }
        if ("Marshlander".equals(character.getCategory()) || "Sunchild".equals(character.getCategory())) {
            character.setSpeed(character.getSpeed() - 1);
        }
    }

    public void getbonus(Character attacker,Character receiver) {
        if(attacker.getCategory().equals("Highlander")){
            if (!attacker.getType().equals("Healer")){
                Character attackerCopy = attacker.clone();
                attackerCopy.setAttack(Double.parseDouble(String.format("%.1f", attacker.getAttack() * 0.2)));
                attackerCopy.attack(receiver);
            }
            else {
                Character attackerCopy = attacker.clone();
                attackerCopy.setAttack(Double.parseDouble(String.format("%.1f", attacker.getAttack() * 0.2)));
                ((Healer)attackerCopy).heal(receiver);
            }
        }
    }
}

class Marshland extends Homeland{
    public Marshland(){
        name="Marshland";
        image = UI.resize(300,250,new ImageIcon("Resources/homeland/Marshland.jpg"));
    }

    @Override
    public void AdaptTOHomeland(Character character) {
        if ("Marshlander".equals(character.getCategory())) {
            character.setDefence(character.getDefence() + 2);
        }
        else if ("Sunchild".equals(character.getCategory())) {
            character.setAttack(character.getAttack() - 1);
        }else if ("Mystic".equals(character.getCategory())){
            character.setSpeed(character.getSpeed() -1);
        }
    }
}
class Desert extends Homeland{
    public Desert(){
        name="Desert";
        image = UI.resize(300,250,new ImageIcon("Resources/homeland/Desert.jpg"));
    }
    @Override
    public void AdaptTOHomeland(Character character) {
        if ("Marshlander".equals(character.getCategory())) {
            character.setHealth(character.getHealth() - 1);
        }
        if ("Sunchild".equals(character.getCategory())) {
            character.setAttack(character.getAttack() + 1);
        }
    }
}

class Arcane extends Homeland{
    public Arcane(){
        name="Arcane";
        image = UI.resize(300,250,new ImageIcon("Resources/homeland/Arcane.jpg"));
    }
    @Override
    public void AdaptTOHomeland(Character character) {
        if ("Mystic".equals(character.getCategory())) {
            character.setAttack(character.getAttack() + 2);
        }
        if ("Marshlander".equals(character.getCategory()) || "Highlander".equals(character.getCategory())) {
            character.setSpeed(character.getSpeed() - 1);
            character.setDefence(character.getDefence() - 1);
        }
    }

    public void getbonus(Character attacker,Character reciver) {
        if(attacker.getCategory().equals("Mystic")){
            String formattedResult = String.format("%.1f", attacker.getHealth() * 1.1);
            attacker.setHealth(Double.parseDouble(formattedResult));
        }
    }
}
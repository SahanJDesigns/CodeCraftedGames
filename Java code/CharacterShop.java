import javax.swing.*;
import java.awt.*;

public class CharacterShop {
    public static void CharacterMenu(String character){
        UI.clearFrame();
        UI.setBanneredBackgroundImage();

        final Character[] Archer = {new Shooter(),new Ranger(),new Sunfire(),new Zing(),new Saggitarius()};

        final Character[] Knight = {new Squire(),new Cavalier(),new Templar(),new Zoro(),new Swiftblade()};
    
        final Character[] Mage = {new Warlock(),new Illusionist(),new Enchanter(),new Conjurer(),new Eldritch()};

        final Character[] Healer = {new Soother(),new Medic(),new Alchemist(),new Saint(),new Lightbringer()};
        
        final Character[] MythicalCreature = {new Dragon(),new Basilisk(),new Hydra(),new Phoenix(),new Pegasus()};
        
        int buttonCount = 1;

        switch (character) {
            case "Archer":
                for (Character archer : Archer) {
                    addButtonToEquipMenu(archer, buttonCount);
                    buttonCount++;
                }
                break;
            case "Knight":
                for (Character knight : Knight) {
                    addButtonToEquipMenu(knight, buttonCount);
                    buttonCount++;
                }
                break;
            case "Mage":
                for (Character mage : Mage) {
                    addButtonToEquipMenu(mage, buttonCount);
                    buttonCount++;
                }
                break;
            case "Healer":
                for (Character healer : Healer) {
                    addButtonToEquipMenu(healer, buttonCount);
                    buttonCount++;
                }
                break;
            case "MythicalCreature":
                for (Character mythicalCreature : MythicalCreature) {
                    addButtonToEquipMenu(mythicalCreature, buttonCount);
                    buttonCount++;
                }
                break;
            default:
                break;
        }
        
        JButton backButton = new StyledButton("Back");
        backButton.setBounds(600, 640, 300, 50);

        backButton.addActionListener(e-> UI.profile());

        UI.frame.add(backButton);
        UI.frame.revalidate();
        UI.frame.repaint();
    }

    private static void addButtonToEquipMenu(Character character,int Count) {
        JButton button = new StyledButton(character.getName());
        
        button.addActionListener(e -> {
            UI.player.buyCharacter(character);
        });

        JLabel image = new JLabel(character.image);
        String[] labels = {"Health", "Attack: ", "Defence: ", "Speed: ", "Price: "};
        
        String[] infoValues = {
            labels[0] + character.getHealth(),
            labels[1] + character.getAttack(),
            labels[2] + character.getDefence(),
            labels[3] + character.getSpeed(),
            labels[4] + character.getPrice()
        };

        int XBounds = ((1500 - 300) / 7) * (Count - 1) + 50 * Count + 150;
        for (int j = 0; j < 5; j++) {
            JTextArea label = new JTextArea(infoValues[j]);
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            label.setEditable(false);
            label.setOpaque(false);
            label.setBounds(XBounds, 400 + j * 25, 200, 25);
            label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            UI.frame.add(label);
        }

        image.setBounds(XBounds, 100, 200, 300);
        button.setBounds(XBounds, 550, 200, 50);
        UI.frame.add(button);
        UI.frame.add(image);
    }
}

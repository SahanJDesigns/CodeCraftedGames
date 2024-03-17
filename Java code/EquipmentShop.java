import javax.swing.*;
import java.awt.*;

public class EquipmentShop {
    public static void EquipmentMenu(String character){
        UI.clearFrame();
        UI.setBanneredBackgroundImage();

        final Equipment[] items = {new Chainmail(),new Regalia(),new Fleece(),new Excalibur(),new Amulet(),new Crystal()};
        int buttonCount = 1;

        for (Equipment item : items) {
            addButtonToEquipMenu(item,buttonCount,character);
            buttonCount++;
        }

        JButton backButton = new StyledButton("Back");
        backButton.setBounds(600, 640, 300, 50);
        backButton.addActionListener(e-> UI.profile());
        UI.frame.add(backButton);
        UI.frame.revalidate();
        UI.frame.repaint();
    }
    
    private static void addButtonToEquipMenu(Equipment item,int Count,String Character) {
        JButton button = new StyledButton(item.getName());
        
        button.addActionListener(e -> {
            UI.player.buyEquipment(Character, item);
        });

        JLabel image = new JLabel(item.image);
        String[] labels = {"Health", "Attack: ", "Defence: ", "Speed: ", "Price: "};

        String[] infoValues = {
            labels[0] + item.getHealth(),
            labels[1] + item.getAttack(),
            labels[2] + item.getDefence(),
            labels[3] + item.getSpeed(),
            labels[4] + item.getPrice()
        };

        for (int j = 0; j < 5; j++) {
            JTextArea label = new JTextArea(infoValues[j]);
            label.setFont(new Font("Arial", Font.PLAIN, 20));
            label.setEditable(false);
            label.setOpaque(false);
            label.setBounds(((1500 - 300) / 7) * (Count - 1) + 50 * Count+150, 400 + j * 25, 200, 25);
            label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            UI.frame.add(label);
        }

        int XBound = ((1500 - 300) / 7) * (Count - 1) + 50 * Count + 60;
        image.setBounds(XBound, 100, 200, 300);
        button.setBounds(XBound, 550, 200, 50);
        UI.frame.add(button);
        UI.frame.add(image);
    }
}

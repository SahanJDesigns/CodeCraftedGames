import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class StyledButton extends JButton {
    private final Color defaultBg = new Color(175, 225, 175);
    private final Color hoverBg = new Color(170, 255, 0);
    private final Color pressBg = new Color(80, 200, 120);
    private Clip hoverSound;

    public StyledButton(String text) {
        super(text);
        setBackground(defaultBg);
        Font originalFont = getFont();
        Map<TextAttribute, Object> fontAttributes = new HashMap<>(originalFont.getAttributes());
        String fontType = "Segoe UI Emoji";
        fontAttributes.put(TextAttribute.FAMILY, fontType);
        int fontSize = 20;
        fontAttributes.put(TextAttribute.SIZE, fontSize);
        setFont(new Font(fontAttributes));

        // Add event listeners
        addMouseListener(new ButtonMouseListener());
    }

    private class ButtonMouseListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            setBackground(hoverBg);
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Resources/hover.wav"));
                hoverSound = AudioSystem.getClip();
                hoverSound.open(audioInputStream);
                hoverSound.start();
                Thread.sleep(200);
                hoverSound.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setBackground(defaultBg);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            setBackground(pressBg);
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Resources\\click.wav"));
                Clip clickSound = AudioSystem.getClip();
                clickSound.open(audioInputStream);
                clickSound.start();
                Thread.sleep(200);
                hoverSound.stop();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            setBackground(defaultBg);
        }
    }

}

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class UI {
    public static JFrame frame;
    public static Player player;
    private static boolean isMusicPlaying;

    public static ImageIcon resize(int width,int height,ImageIcon image){
        Image scaledImage = image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
    
    static void initComponents() {
        clearFrame();
        setBackgroundImage();

        try {
            playBackgroundMusic();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }

        JButton loadProfileButton = new StyledButton("Load Profile");
        JButton newProfileButton = new StyledButton("New Profile");
        JButton exitButton = new StyledButton("Exit");
        
        loadProfileButton.addActionListener(e -> Player.login());
        newProfileButton.addActionListener(e -> Player.createProfile());
        exitButton.addActionListener(e -> System.exit(0));

        loadProfileButton.setBounds(600, 500, 300, 50);
        newProfileButton.setBounds(600, 570, 300, 50);
        exitButton.setBounds(600, 640, 300, 50);

        frame.add(loadProfileButton);
        frame.add(newProfileButton);
        frame.add(exitButton);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Main Menu");
        frame.setSize(1920, 1080);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void setBackgroundImage() {
        ImageIcon imageIcon = new ImageIcon("resources/mainBackground.jpg");
        JLabel backgroundLabel = new JLabel(resize(1550, 850,imageIcon));
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        frame.setContentPane(backgroundLabel);
    }

    public static void setBanneredBackgroundImage(){
        ImageIcon imageIcon = new ImageIcon("Resources/banneredBackground.png");
        JLabel backgroundLabel = new JLabel(resize(1550, 850,imageIcon));
        backgroundLabel.setBounds(0, 0, 1920, 1080);
        frame.setContentPane(backgroundLabel);
    }

    public static void profile() {
        clearFrame();
        setBanneredBackgroundImage();
        int innerWidth = frame.getContentPane().getWidth();

        JTextArea nameText = new JTextArea("Name : "+player.getName());
        nameText.setBounds(300,30,300,100);
        nameText.setOpaque(false);
        nameText.setEditable(false);
        nameText.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        frame.add(nameText);

        JTextArea userIDText = new JTextArea("userID : "+player.getUserID());
        userIDText.setBounds(300,70,300,100);
        userIDText.setOpaque(false);
        userIDText.setEditable(false);
        userIDText.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        frame.add(userIDText);

        JButton changeNameBtn = new StyledButton("Change");
        changeNameBtn.setBounds(160,15,120,50);   
        changeNameBtn.addActionListener(e-> player.changeName());
        frame.add(changeNameBtn);

        JTextArea XPText = new JTextArea("\uD83C\uDFAF : "+player.getXP());
        XPText.setBounds(600,30,200,100);
        XPText.setOpaque(false);
        XPText.setEditable(false);
        XPText.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        frame.add(XPText);

        JTextArea loot = new JTextArea("💰 : "+player.getLoot());
        loot.setBounds(800,30,200,100);
        loot.setOpaque(false);
        loot.setEditable(false);
        loot.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        frame.add(loot);

        JTextArea homeland = new JTextArea("🌋 : "+player.getHomeland().getName());
        homeland.setBounds(1020,30,230,100);
        homeland.setOpaque(false);
        homeland.setEditable(false);
        homeland.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 32));
        frame.add(homeland);

        JButton ChangeHomeground = new StyledButton("Change");
        ChangeHomeground.setBounds(1250,15,120,50);
        
        ChangeHomeground.addActionListener(e-> Homeland.selectHomeGround());

        frame.add(ChangeHomeground);

        ArrayList<Character> characters = player.getCharacters();
        String[] labels = {"💓 : ", "👊 : ", "⚔ : ", "🔥 : " , "🪙 : ", "🛡️ : ", "🔱 : "};

        for (int i = 0; i < 5; ++i) {
            if (characters.get(i) != null) {
                String [] infoValues = new String[7];
                infoValues[0] = labels[0] + characters.get(i).getHealth();
                infoValues[1] = labels[1] + characters.get(i).getAttack();
                infoValues[2] = labels[2] + characters.get(i).getDefence();
                infoValues[3] = labels[3] + characters.get(i).getSpeed();
                infoValues[4] = labels[4] + characters.get(i).getPrice();

                if (characters.get(i).getArmour() != null){
                    infoValues[5] = labels[5] + characters.get(i).getArmour().getName();
                }
                else{
                    infoValues[5] = labels[5] + "No Armour";
                }
                if (characters.get(i).getArtefact() != null){
                    infoValues[6] = labels[6] + characters.get(i).getArtefact().getName();
                }
                else{
                    infoValues[6] = labels[6] + "No Artefact";
                }

                int XBounds = ((innerWidth - 300) / 8) * i + 50 * (i + 1) + 100;
                for (int j = 0; j < 5; j++) {
                    JTextArea label = new JTextArea(infoValues[j]);
                    label.setFont(new Font("Arial", Font.PLAIN, 20));
                    label.setEditable(false);
                    label.setOpaque(false);
                    label.setBounds(XBounds, 400 + j * 25, 90, 25);
                    label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
                    frame.add(label);
                }

                for (int j = 5; j < 7; j++){
                    JTextArea label = new JTextArea(infoValues[j]);
                    label.setFont(new Font("Arial", Font.PLAIN, 20));
                    label.setEditable(false);
                    label.setOpaque(false);
                    label.setBounds(XBounds, 400 + j * 25, 200, 25);
                    label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
                    frame.add(label);
                }

                JLabel ImageLabel = new JLabel(characters.get(i).image);
                ImageLabel.setBounds(XBounds, 180, 200, 200);
                frame.add(ImageLabel);
            }
        }

        // Adding buttons to interact
        String[] buttonText = {"Sell", "Buy", "Equip"};
        String[] characterTypes = {"Archer", "Knight", "Healer", "Mage", "MythicalCreature"};
    
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                JButton button = new StyledButton(buttonText[j]);
                button.setBounds(((innerWidth - 300) / 8) * i + 50 * (i + 1)+190,  400 + j * 40, 115, 30);
    
                int finalI = i;
                
                    if (j == 0) {
                        button.addActionListener(e -> {
                            player.sellCharacter(characters.get(finalI));
                            profile();
                        });
                    } else if (j == 1) {
                        button.addActionListener(e -> CharacterShop.CharacterMenu(characterTypes[finalI]));
                    } else {
                        button.addActionListener(e -> EquipmentShop.EquipmentMenu(characterTypes[finalI]));
                    }

                frame.add(button);
                
            }
            JTextArea label = new JTextArea(characterTypes[i]);
                    label.setFont(new Font("Arial", Font.PLAIN, 20));
                    label.setEditable(false);
                    label.setOpaque(false);
                    label.setBounds(((innerWidth - 300) / 8) * i + 50 * (i + 1)+150, 150, 90, 25);
                    label.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
                    frame.add(label);
        }
    
        JButton playButton = new StyledButton("Play");
        playButton.setBounds(650,600,200,50);
        playButton.addActionListener(e-> Battle.play(new ArrayList<>()));
        frame.add(playButton);


        frame.revalidate();
        frame.repaint();
    }
    
    private static void playBackgroundMusic() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        if (!isMusicPlaying) {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Resources/Bgsounds.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            isMusicPlaying = true;
        }
    }

    public static void clearFrame() {
        frame.getContentPane().removeAll();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
             frame = new JFrame();
            initComponents();
        });
    }
}
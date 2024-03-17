import javax.swing.*;
import java.awt.*;

public class Player extends User {
    // private constructor to call when login
    protected Player(int loot, String username, String name,int XP) {
        super(loot, username, name,XP);
    }

    // User-Interface for login
    static void login(){
        UI.clearFrame();
        UI.setBackgroundImage();
        JTextField textField = new JTextField(20);
        JButton submitButton = new StyledButton("Submit");
        JButton backButton = new StyledButton("Back");

        submitButton.addActionListener(e -> {
            String userInput = textField.getText();
            UI.player = processLogin(userInput);
            if (UI.player == null) {
                JOptionPane.showMessageDialog(UI.frame, "Incorrect Username: " + userInput);
            } else {
                UI.profile();
            }
        });
        backButton.addActionListener(e-> UI.initComponents());
        
        textField.setBounds(600, 500, 300, 50);
        submitButton.setBounds(600, 570, 300, 50);
        backButton.setBounds(600, 640, 300, 50);

        UI.frame.add(textField);
        UI.frame.add(submitButton);
        UI.frame.add(backButton);

        UI.frame.revalidate();
        UI.frame.repaint();
    }

    public static Player processLogin(String username) {
        User user = Database.authenticationLogin(username);
        if(user != null){
            return user.castToPlayer();
        }else{
            System.out.println("Login failed. User not found.");
        }
        return null;
    }

    public static void createProfile() {
        UI.clearFrame();
        UI.setBackgroundImage();

        Font labelFont = new Font("Arial", Font.BOLD, 20);

        TextField usernameField = new TextField("Enter your username");
        usernameField.setForeground(Color.GRAY);
        usernameField.setBounds(600, 500, 300, 50);
        UI.frame.add(usernameField);

        TextField nameField = new TextField("Enter your name");
        nameField.setForeground(Color.GRAY);
        nameField.setBounds(600, 570, 300, 50);
        UI.frame.add(nameField);

        JButton createProfileButton = new StyledButton("Create Profile");
        createProfileButton.setBounds(600, 640, 300, 50);
        createProfileButton.setFont(labelFont);
        createProfileButton.setForeground(Color.BLACK);

        createProfileButton.addActionListener(e -> {
            String username = usernameField.getText();
            String name = nameField.getText();
            if (username.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(UI.frame, "Please fill all the fields");
                return;
            }

            if (!Database.isUniqueUsername(username)) {
                JOptionPane.showMessageDialog(UI.frame, "Username already taken. Try another.");
                return;
            }

            User newUser = new User(500, username, name,0);
            newUser.setHomeland(new Hillcrest());
            newUser.setUserID(Database.getNewUserID());
            Database.saveuser(newUser);
            UI.player = Player.processLogin(username);
            UI.profile();
        });

        UI.frame.add(createProfileButton);
        UI.frame.setVisible(true);
    }

    private int getFutureExpenses(){
        int futureExpenseAmount = 0;

        if (!this.hasArcher()) {
            futureExpenseAmount += 80;
        }
        if (!this.hasHealer()) {
            futureExpenseAmount += 95;
        }
        if (!this.hasKnight()) {
            futureExpenseAmount += 85;
        }
        if (!this.hasMage()) {
            futureExpenseAmount += 100;
        }
        if (!this.hasMythicalCreature()) {
            futureExpenseAmount += 120;
        }

        return futureExpenseAmount;
    }
    
    private boolean canBuy(Character character) {
        int futureExpenseAmount = 0;

        if (!this.hasArcher() && !character.getType().equals("Archer")) {
            futureExpenseAmount += 80;
        }
        if (!this.hasHealer() && !character.getType().equals("Healer")) {
            futureExpenseAmount += 95;
        }
        if (!this.hasKnight() && !character.getType().equals("Knight")) {
            futureExpenseAmount += 85;
        }
        if (!this.hasMage() && !character.getType().equals("Mage")) {
            futureExpenseAmount += 100;
        }
        if (!this.hasMythicalCreature() && !character.getType().equals("MythicalCreature")) {
            futureExpenseAmount += 120;
        }

        return (0 <= this.getLoot() - character.getPrice() - futureExpenseAmount);
    }
    
    private boolean canBuy(Equipment equipment) {
        int futureExpenseAmount = getFutureExpenses();

        return (0 <= this.getLoot() - equipment.getPrice() - futureExpenseAmount);
    }

    private boolean canSell(Character character) {
        int futureExpenseAmount = getFutureExpenses();
        switch (character.getType()) {
            case "Archer" -> futureExpenseAmount += 80;
            case "Healer" -> futureExpenseAmount += 95;
            case "Knight" -> futureExpenseAmount += 85;
            case "Mage" -> futureExpenseAmount += 100;
            case "MythicalCreature" -> futureExpenseAmount += 120;
        }
        return (0 < this.getLoot() + character.getPrice() * 0.9 - futureExpenseAmount);
    }

    public void buyCharacter(Character character) {
        String characterType = character.getType();
        switch (characterType) {
            case "Archer":
                buyCharacterOfType(character, hasArcher(), "Archer");
                Database.saveuser(this.castToUser());
                break;
            case "Mage":
                buyCharacterOfType(character, hasMage(), "Mage");
                Database.saveuser(this.castToUser());
                break;
            case "Healer":
                buyCharacterOfType(character, hasHealer(), "Healer");
                Database.saveuser(this.castToUser());
                break;
            case "MythicalCreature":
                buyCharacterOfType(character, hasMythicalCreature(), "Mythical Creature");
                Database.saveuser(this.castToUser());
                break;
            case "Knight":
                buyCharacterOfType(character, hasKnight(), "Knight");
                Database.saveuser(this.castToUser());
                break;
            default:
                System.out.println("Invalid character type: " + characterType);
        }
    }

    private void buyCharacterOfType(Character character, boolean hasCharacter, String characterTypeName) {
        if (!hasCharacter) {
            if (canBuy(character)) {
                this.setLoot(this.getLoot()-character.getPrice());
                switch (characterTypeName) {
                    case "Archer":
                        this.setArcher((Archer) character);
                        break;
                    case "Mage":
                        this.setMage((Mage) character);
                        break;
                    case "Healer":
                        this.setHealer((Healer) character);
                        break;
                    case "Mythical Creature":
                        this.setMythicalCreature((MythicalCreature) character);
                        break;
                    case "Knight":
                        this.setKnight((Knight) character);
                        break;
                    default:
                        System.out.println("Invalid character type: " + characterTypeName);
                        return;
                }
                JOptionPane.showMessageDialog(UI.frame, "Bought successfully!");
            } else {
                JOptionPane.showMessageDialog(UI.frame, "You will not have enough money to complete the army. Choose a different character.");
            }
        } else {
            JOptionPane.showMessageDialog(UI.frame, "You already have a " + characterTypeName + ". You need to sell the " + characterTypeName + " first.");
        }
    }

    public void sellCharacter(Character character){
        if(character != null){
            if(canSell(character)){
                this.setCharacter(character.getType(),null);
                setLoot((int)(getLoot()+ 0.9*character.getPrice()));
                Database.saveuser(this.castToUser());
            }else{
                JOptionPane.showMessageDialog(UI.frame, "You do not have enough money to regain characters");
            }
        }else{
            JOptionPane.showMessageDialog(UI.frame, "You do not have a character to sell");
        }
    }

    public void buyEquipment(String characterType,Equipment equipment){

        boolean hasCharacter = (getCharacter(characterType)== null);
        Equipment currentArmour;
        Equipment currentArtefact;

        if(hasCharacter){
            JOptionPane.showMessageDialog(UI.frame, "You need a "+characterType+" to add equipments");
            return;
        }else{
            currentArmour = getCharacter(characterType).getArmour();
            currentArtefact = getCharacter(characterType).getArtefact();
        }

        if((currentArmour!= null && currentArmour.getName().equals(equipment.getName()))||
                (currentArtefact!= null && currentArtefact.getName().equals(equipment.getName()))){
                JOptionPane.showMessageDialog(UI.frame, "You already have a " + equipment.getName());
        }else if (canBuy(equipment)) {
            this.setLoot(this.getLoot()-equipment.getPrice());
            this.getCharacter(characterType).equip(equipment);
            Database.saveuser(this.castToUser());
            JOptionPane.showMessageDialog(UI.frame,characterType+" equipped with"+equipment.getName());
        }else{
            JOptionPane.showMessageDialog(UI.frame,"You will not have enough money to complete your army");
        }

    }

    public void changeName(){
        UI.clearFrame();
        UI.setBanneredBackgroundImage();
        JTextField textField = new JTextField(20);
        JButton submitButton = new StyledButton("Submit");
        JButton backButton = new StyledButton("Back");
        submitButton.addActionListener(e -> {
            String userInput = textField.getText();
            Database.saveuser(this.castToUser());
            this.setName(userInput);
            JOptionPane.showMessageDialog(UI.frame, "New name: " + this.getName());
            UI.profile();
        });

        backButton.addActionListener(e-> UI.profile());

        textField.setBounds(600, 500, 300, 50);
        submitButton.setBounds(600, 570, 300, 50);
        backButton.setBounds(600, 640, 300, 50);

        UI.frame.add(textField);
        UI.frame.add(submitButton);
        UI.frame.add(backButton);

        UI.frame.revalidate();
        UI.frame.repaint();
    }

        // Casting the status to the user
        public User castToUser(){
            User user = new User(this.getLoot(),this.getUsername(),this.getName(),this.getXP());
            user.setUserID(this.getUserID());
            user.setArcher(this.getArcher());
            user.setHealer(this.getHealer());
            user.setKnight(this.getKnight());
            user.setMage(this.getMage());
            user.setMythicalCreature(this.getMythicalCreature());
            user.setHomeland(this.getHomeland());
            return user;
        }
}

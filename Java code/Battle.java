import java.awt.Font;
import java.util.ArrayList;
import java.io.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Battle {

    private static final int MAX_TURNS = 10;
    public String attackLog = ""; // Creating a log to store the battle information

    // Method to check whether the character is dead
    public static boolean isDead(Character character) {
        return character.getHealth() <= 0.0;
    }

    // Method to update the status of the players and the battle log after the battle
    public void updateStatus(User winner, User loser){
        winner.setXP(winner.getXP()+1);
        winner.setLoot((int) (winner.getLoot() + (loser.getLoot() * 0.1)));
        loser.setLoot((int) (loser.getLoot() * 0.9));
        
        this.attackLog += "\n***********************************************************************************\n"
                + "\t\t" + winner.getName()+" Won!" +
                "\n***********************************************************************************\n" ;
        this.attackLog += loser.getName() + "\tXP: "+loser.getXP()+"\tGold Coins :"+loser.getLoot()+"\n";
        this.attackLog += winner.getName() + "\tXP: "+winner.getXP()+"\tGold Coins :"+winner.getLoot()+"\n\n";

        try{
            // Writing the battle log into a file
            FileWriter writer = new FileWriter("Battle Log.txt");
            writer.write(attackLog);
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // Function to sort the characters by speed
    public static  void  sortCharactersBySpeed(ArrayList<Character> characterList) {
        int n = characterList.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (characterList.get(j).getSpeed() < characterList.get(j+1).getSpeed()) {
                    Character temp = characterList.get(j);
                    characterList.set(j, characterList.get(j + 1));
                    characterList.set(j + 1, temp);
                    swapped = true;
                }else if(characterList.get(j).getSpeed() == characterList.get(j+1).getSpeed() && characterList.get(j).getAttackPriority() < characterList.get(j+1).getAttackPriority()){
                    Character temp = characterList.get(j);
                    characterList.set(j, characterList.get(j + 1));
                    characterList.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    // Function to sort the characters by defence
    public static void sortCharactersByDefence(ArrayList<Character> characterList) {
        int n = characterList.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (characterList.get(j).getDefence() > characterList.get(j+1).getDefence()) {
                    Character temp = characterList.get(j);
                    characterList.set(j, characterList.get(j + 1));
                    characterList.set(j + 1, temp);
                    swapped = true;
                }else if(characterList.get(j).getDefence() == characterList.get(j+1).getDefence() && characterList.get(j).getDefencePriority() < characterList.get(j+1).getDefencePriority()){
                    Character temp = characterList.get(j);
                    characterList.set(j, characterList.get(j + 1));
                    characterList.set(j + 1, temp);
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    // Method to battle and find the winner
    public User combat(User player, User opponent) {

        this.attackLog += "\n" + "***********************************************************************************\n"
                + "\t\t" + player.getName() + " VS "+opponent.getName()+"\n"
                + "***********************************************************************************\n\n";

        // Creating a list of characters of the player and the opponent
        ArrayList<Character> playerCharacters = player.getCharactersCopies();
        ArrayList<Character> opponentCharacters = opponent.getCharactersCopies();

        // Updating the characters according to the homeland of the opponent
        for (Character character : playerCharacters) {
            opponent.getHomeland().AdaptTOHomeland(character);
        }
        for (Character character : opponentCharacters) {
            opponent.getHomeland().AdaptTOHomeland(character);
        }

        int i = 0; // Pointer to the index of player character
        int j = 0; // Pointer to the index of opponent character
        int playerTurns = 1;
        while (playerTurns <= MAX_TURNS) {
            // Resetting i to zero to go through as a loop when the end of the list is reached
            if (i >= playerCharacters.size()) {
                i = 0;
            }


            // Attacking the opponent by the player
            sortCharactersBySpeed(playerCharacters);
            sortCharactersByDefence(opponentCharacters);

            Character attacker = playerCharacters.get(i);
            Character receiver = opponentCharacters.get(0);

            this.attackLog += "Turn "+ (playerTurns)+ ": " + player.getName()+"\n";

            if (!"Healer".equals(attacker.getType())) {
                attacker.attack(receiver); // Attacking
                opponent.getHomeland().getbonus(attacker,receiver); // Checking for bonuses for the given homeland

                this.attackLog += attacker.getName()+" of "+player.getName()+" attacks "+receiver.getName()+" of "+opponent.getName()+"\n";
                this.attackLog += opponent.getName()+"'s "+receiver.getName()+"'s health: "+receiver.getHealth()+"\n";
                this.attackLog += player.getName()+"'s "+attacker.getName()+"'s health: "+attacker.getHealth()+"\n";

                if (isDead(receiver)) {
                    this.attackLog += opponent.getName()+"'s " + receiver.getName() + " died! \n";

                    // Updating the value of j to correctly update the index of the next attacker of the opponent
                    sortCharactersBySpeed(opponentCharacters);
                    if (opponentCharacters.indexOf(receiver) < j){
                        j--;
                    }

                    // If a character dies, removing him/her from the army
                    opponentCharacters.remove(receiver);
                }

                this.attackLog += "\n";

            } else {
                // If the character is a healer, healing the character with the lowest health
                Character minHealthCharacter = ((Healer)attacker).heal(playerCharacters);
                opponent.getHomeland().getbonus(attacker,minHealthCharacter); // Checking for bonuses 

                this.attackLog += attacker.getName()+" of "+player.getName()+" heals "+ minHealthCharacter.getName() + " of "+player.getName()+"\n";
                this.attackLog += player.getName()+"'s "+minHealthCharacter.getName()+"'s health: "+minHealthCharacter.getHealth()+"\n";
                this.attackLog += "\n";
            }

            if (playerCharacters.size()==0){
                // If Player's army is dead returning Opponent as the winner after updating the loot and XP
                updateStatus(opponent, player);

                System.out.println(attackLog);

                return opponent;
            }

            else if (opponentCharacters.size()==0) {
                // If Opponent's army is dead returning the Player as the winner after updating the loot and XP
                updateStatus(player, opponent);

                System.out.println(attackLog);

                return player;
            }

            // Resetting i to zero to go through as a loop when the end of the list is reached
            if (j >= opponentCharacters.size()) {
                j = 0;
            }

            i++;


            // Attacking the player by the opponent

            sortCharactersBySpeed(opponentCharacters);
            sortCharactersByDefence(playerCharacters);

            attacker = opponentCharacters.get(j);
            receiver = playerCharacters.get(0);

            this.attackLog += "Turn " + (playerTurns) + ": "+ opponent.getName() + "\n";

            if (!"Healer".equals(attacker.getType())) {
                attacker.attack(receiver); // Attacking
                opponent.getHomeland().getbonus(attacker,receiver); // Checking for bonuses

                this.attackLog += attacker.getName()+" of "+opponent.getName()+" attacks "+receiver.getName()+" of "+player.getName()+"\n";
                this.attackLog += player.getName()+"'s "+receiver.getName()+"'s health: "+receiver.getHealth()+"\n";
                this.attackLog += opponent.getName()+"'s "+attacker.getName()+"'s health: "+attacker.getHealth()+"\n";

                if (isDead(receiver)) {
                    this.attackLog += player.getName()+"'s " + receiver.getName() + " died! \n";

                    // Decreasing index of i to correctly update the index of the next attacker
                    sortCharactersBySpeed(playerCharacters);
                    if (playerCharacters.indexOf(receiver) < i){
                        i--;
                    }

                    // If a character dies, removing him/her from the army
                    playerCharacters.remove(receiver);
                }
                this.attackLog += "\n";
            } else {
                // If the character is a healer
                Character minHealthCharacter = ((Healer) attacker).heal(opponentCharacters);
                opponent.getHomeland().getbonus(attacker,minHealthCharacter);  // Checking for bonuses according to the homeland

                this.attackLog += attacker.getName()+" of "+opponent.getName()+" heals "+ minHealthCharacter.getName() +" of "+opponent.getName()+"\n";
                this.attackLog += opponent.getName()+"'s "+minHealthCharacter.getName()+"'s health: "+minHealthCharacter.getHealth()+"\n";
                this.attackLog += "\n";
            }

            j++;
            playerTurns++;

            if (playerCharacters.size()==0){
                // If Player's army is dead returning the Opponent as the winner after updating the loot and XP
                updateStatus(opponent, player);

                System.out.println(attackLog);

                return opponent;
            }
            else if (opponentCharacters.size() == 0) {
                // If Opponent's army is dead returning the Player as the winner after updating the loot and XP
                updateStatus(player, opponent);

                System.out.println(attackLog);

                return player;
            }
        }

        this.attackLog +=  "\n***********************************************************************************\n"
                + "\t\t   Draw!\n"
                +  "***********************************************************************************\n";
        this.attackLog += "\n";
        this.attackLog += player.getName() + "\tXP: "+player.getXP()+"\tGold Coins :"+player.getLoot()+"\n";
        this.attackLog += opponent.getName() + "\tXP: "+opponent.getXP()+"\tGold Coins :"+opponent.getLoot()+"\n";

        System.out.println(attackLog);

        try{
            // Writing the battle log into a file
            FileWriter writer = new FileWriter("Battle Log.txt");
            writer.write(attackLog);
            writer.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return null; // Returning NULL in case of a draw
    }

    // Function to skip players when selecting the opponent
    public static void play(ArrayList<String> skippedUsers){
        if(UI.player.getCharactersCount() == 5){
            int iterations = 0;
            User opponent = Database.getRandomUser();
            while (opponent.getCharactersCount() != 5 || opponent.getUsername().equals(UI.player.getUsername()) || skippedUsers.contains(opponent.getUsername())) {
                opponent = Database.getRandomUser();
                iterations++;
                if(iterations>30){
                    JOptionPane.showMessageDialog(UI.frame,"Failed to find an opponent");
                    UI.profile();
                    return;
                }
            }
            processBattle(opponent,skippedUsers);
        }else{
            JOptionPane.showMessageDialog(UI.frame,"You Have to buy all 5 charactors");
        }
    }

    // Function to process the battle
    public static void processBattle(User opponent,ArrayList<String> skippedUsers) {
        UI.clearFrame();
        UI.setBanneredBackgroundImage();

        // Displaying the opponent's info
        JLabel opponentLabel = new JLabel("Opponent: " + opponent.getName()+" ,   XP: "+opponent.getXP());
        opponentLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        opponentLabel.setBounds(580, 200, 500, 50);
        UI.frame.add(opponentLabel);

        ArrayList<Character> characterList = opponent.getCharacters();
        String army = "";
        for (Character character:characterList) {
            army += character.getName() + ", ";
        }
        army = army.substring(0, army.length()-2);

        JLabel armyLabel = new JLabel("Opponent Army: "+army);
        armyLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 24));
        armyLabel.setBounds(450, 250, 1000, 50);
        UI.frame.add(armyLabel);

        // Adding a button to confirm the battle
        JButton confirmPlayButton = new StyledButton("Confirm Play");
        confirmPlayButton.setBounds(600, 360, 300, 50);

        confirmPlayButton.addActionListener(e -> {
            Battle battle = new Battle();
            User winner = battle.combat(UI.player, opponent);

            // Creating a window to show the battle log
            JTextArea logTextArea = new JTextArea(40, 40);
            logTextArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(logTextArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);            
            logTextArea.setText(battle.attackLog);
            logTextArea.setLineWrap(true);
            logTextArea.setOpaque(false);

            if(winner != null){
                if (winner.getName().equals(UI.player.getName())) {
                    JOptionPane.showMessageDialog(UI.frame, scrollPane, "Battle Log", JOptionPane.PLAIN_MESSAGE);
                } else if (winner.getName().equals(opponent.getName())) {
                    JOptionPane.showMessageDialog(UI.frame, scrollPane, "Battle Log", JOptionPane.PLAIN_MESSAGE);
                }
            }else{
                // If the battle is a draw
                JOptionPane.showMessageDialog(UI.frame, scrollPane, "Battle Log", JOptionPane.PLAIN_MESSAGE);
            }

            // Saving the user stats in the database
            Database.saveuser(opponent);
            Database.saveuser(UI.player.castToUser());
            UI.profile();
        });

        JButton skipBattleButton = new StyledButton("Skip Battle");
        skipBattleButton.setBounds(600, 420, 300, 50);

        skipBattleButton.addActionListener(e->{
            skippedUsers.add(opponent.getUsername());
            play(skippedUsers);
        });

        JButton backButton = new StyledButton("Return");
        backButton.setBounds(600, 480, 300, 50);
        backButton.addActionListener(e->UI.profile());
        
        UI.frame.add(backButton);
        UI.frame.add(skipBattleButton);
        UI.frame.add(confirmPlayButton);
        UI.frame.revalidate();
        UI.frame.repaint();
    }
}

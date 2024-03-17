import java.io.*;
import java.util.ArrayList;

class User implements Serializable {
    private int userID;
    private String username;
    private String name;
    private int loot;
    private Archer archer;
    private Knight knight;
    private Mage mage;
    private Healer healer;
    private MythicalCreature mythicalCreature;
    private Homeland homeland;
    private int XP;
    
    User(int loot, String username, String name,int XP) {
        this.loot = loot;
        this.name = name;
        this.username = username;
        this.XP = XP;
    }

    // getters and setters
    public int getUserID() {
        return userID;
    }
    public String getName() {
        return name;
    }
    public String getUsername() {
        return username;
    }    
    public Archer getArcher() {
        return archer;
    }
    public Knight getKnight() {
        return knight;
    }
    public Mage getMage() {
        return mage;
    }
    public Healer getHealer() {
        return healer;
    }
    public MythicalCreature getMythicalCreature() {
        return mythicalCreature;
    }
    public int getLoot() {
        return loot;
    }   
    public Homeland getHomeland() {
        return homeland;
    }
    public int getXP() {
        return XP;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLoot(int loot) {
        this.loot = loot;
    }
    public void setArcher(Archer archer) {
        this.archer = archer;
    }
    public void setKnight(Knight knight) {
        this.knight = knight;
    }
    public void setMage(Mage mage) {
        this.mage = mage;
    }
    public void setHealer(Healer healer) {
        this.healer = healer;
    }
    public void setMythicalCreature(MythicalCreature mythicalCreature) {
        this.mythicalCreature = mythicalCreature;
    }
    public void setHomeland(Homeland homeland) {
        this.homeland = homeland;
    }  
    public void setXP(int xP) {
        XP = xP;
    }

    // Function to return the characters as a list
    public ArrayList<Character> getCharacters() {
        ArrayList<Character> characterList = new ArrayList<>();
        String[] characterTypes = {"Archer","Knight","Healer","Mage","MythicalCreature"};

        for(String characterType:characterTypes){
            characterList.add(this.getCharacter(characterType));
        }

        return characterList;
    }

    // Function to return a list of copy of characters
    public ArrayList<Character> getCharactersCopies() {
        ArrayList<Character> characterList = new ArrayList<>();
        String[] characterTypes = {"Archer","Knight","Healer","Mage","MythicalCreature"};

        for(String characterType:characterTypes){
            characterList.add(this.getCharacter(characterType).clone());
        }

        return characterList;
    }

    // Function to get a character
    public Character getCharacter(String characterType){
        return switch (characterType) {
            case "Archer" -> archer;
            case "Knight" -> knight;
            case "Mage" -> mage;
            case "Healer" -> healer;
            case "MythicalCreature" -> mythicalCreature;
            default -> null;
        };
    }

    // Function to set a character
    public void setCharacter(String characterType, Character newCharacter) {
        switch (characterType) {
            case "Archer":
                archer = (Archer)newCharacter;
                break;
            case "Knight":
                knight = (Knight)newCharacter;
                break;
            case "Mage":
                mage = (Mage)newCharacter;
                break;
            case "Healer":
                healer = (Healer)newCharacter;
                break;
            case "MythicalCreature":
                mythicalCreature = (MythicalCreature)newCharacter;
                break;
            default:
                System.out.println("Invalid Character type");
                break;
        }
    }
    
    // Functions to check whether the given character is available
    public boolean hasArcher() {
        return this.archer != null;
    }
    public boolean hasHealer() {
        return this.healer != null;
    }
    public boolean hasKnight() {
        return this.knight != null;
    }
    public boolean hasMage() {
        return this.mage != null;
    }
    public boolean hasMythicalCreature() {
        return this.mythicalCreature != null;
    }

    public int getCharactersCount() {
        int count = 0;
        if (this.hasArcher()) {
            count++;
        }
        if (this.hasHealer()) {
            count++;
        }
        if (this.hasKnight()) {
            count++;
        }
        if (this.hasMage()) {
            count++;
        }
        if (this.hasMythicalCreature()) {
            count++;
        }
        return count;
    }

    public Player castToPlayer(){
        Player player = new Player(this.getLoot(), this.getUsername(), this.getName(),this.getXP());
        player.setUserID(this.getUserID());
        player.setArcher(this.getArcher());
        player.setHealer(this.getHealer());
        player.setKnight(this.getKnight());
        player.setMage(this.getMage());
        player.setMythicalCreature(this.getMythicalCreature());
        player.setHomeland(this.getHomeland());
        return player;
    }
    
}


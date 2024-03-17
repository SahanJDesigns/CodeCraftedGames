class whitewolf{
    public static void main(String[] arg){
        User user = new User(215, "whitewolf", "GeraltofRivia", 32);
        user.setUserID(Database.getNewUserID());
        user.setHomeland(new Marshland());
        Archer archer = new Ranger();
        Equipment chainmail = new Chainmail();
        archer.equip(chainmail);
        user.setArcher(archer);

        Knight knight = new Squire();
        user.setKnight(knight);

        Mage mage = new Warlock();
        user.setMage(mage);

        Healer healer = new Medic();
        Equipment amulet = new Amulet();
        healer.equip(amulet);
        user.setHealer(healer);

        MythicalCreature mythicalCreature = new Dragon();
        user.setMythicalCreature(mythicalCreature);
        Database.saveuser(user);
}
}
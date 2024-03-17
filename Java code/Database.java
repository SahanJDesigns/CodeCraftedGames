import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Database {
    // Function to save a user
    public static void saveuser(User user){
        HashMap<String,User> database = loadDatabase();
        database.put(user.getUsername(),user);
        saveDatabase(database);
    }

    // Function to get a random user
    public static User getRandomUser() {
        HashMap<String, User> database = loadDatabase();
        ArrayList<String> keysList = new ArrayList<>(database.keySet());
        Random random = new Random();
        String randomKey = keysList.get(random.nextInt(keysList.size()));
        return database.get(randomKey);
    }

    // Function to authenticate the login
    public static User authenticationLogin(String username) {
        try {
            HashMap<String, User> database = loadDatabase();
            return database.get(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Function to check whether the user name is unique
    public static boolean isUniqueUsername(String username) {
        HashMap<String, User> database = loadDatabase();
    
        for (HashMap.Entry<String, User> entry : database.entrySet()) {
            User user = entry.getValue();
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
    
        return true;
    }

    // Function to save the hashmap with users
    private static void saveDatabase(HashMap<String, User> users) {
        try (ObjectOutputStream database = new ObjectOutputStream(new FileOutputStream("Database.ser"))) {
            database.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Function to load the hashmap with users
    @SuppressWarnings("unchecked")
    private static HashMap<String, User> loadDatabase() {
        HashMap<String, User> users = new HashMap<>();
        try (ObjectInputStream database = new ObjectInputStream(new FileInputStream("Database.ser"))) {
            users = (HashMap<String, User>) database.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Function to get a new user id
    public static int getNewUserID() {
        File idFile = new File("UserID.ser");

        try {
            if (idFile.length() == 0) {
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("UserID.ser"))) {
                    objectOutputStream.writeObject(1);
                }
                return 1;
            }
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(idFile))) {
                int lastUserID = (int) objectInputStream.readObject();
                try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("UserID.ser"))) {
                    objectOutputStream.writeObject(lastUserID + 1);
                }
                return lastUserID + 1;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return -1;
    }
}


package User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Authentication {
    private Map<String, User> userMap;
    private Scanner scanner; // Add scanner here

    public Authentication() {
        this.userMap = readFromFile();
        this.scanner = new Scanner(System.in); // Initialize scanner
    }

    public void register(String username, String password) {
        User newUser = new User(username, password);
        userMap.put(username, newUser);
        saveToFile();
    }

    public boolean login(String username, String password) {
        User user = userMap.get(username);
        return user != null && user.equals(new User(username, password));
    }

    public void rename(String oldUsername, String newUsername) {
        User user = userMap.remove(oldUsername);
        if (user != null) {
            userMap.put(newUsername, user);
            saveToFile();
        }
    }

    public void repassword(String username, String newPassword) {
        User user = userMap.get(username);
        if (user != null) {
            userMap.put(username, new User(username, newPassword));
            saveToFile();
        }
    }

    public void displayUsers() {
        for (User user : userMap.values()) {
            System.out.println(user);
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\MSI 12VE\\OneDrive\\Desktop\\Authentication\\User\\user_data.txt"))) {
            for (User user : userMap.values()) {
                writer.println(user.getUsername() + "/" + user.getPassword());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, User> readFromFile() {
        Map<String, User> users = new HashMap<>();
        try (Scanner scanner = new Scanner(new File("C:\\Users\\MSI 12VE\\OneDrive\\Desktop\\Authentication\\User\\user_data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("/");
                if (parts.length == 2) {
                    users.put(parts[0], new User(parts[0], parts[1]));
                }
            }
        } catch (FileNotFoundException e) {
            // Ignore if the file doesn't exist yet
        }
        return users;
    }

    public void changeInformation(String username, String password) {
        User user = userMap.get(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful! What information would you like to change?");
            System.out.println("1. Change username");
            System.out.println("2. Change password");
            System.out.println("3. Change both");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter new username: ");
                    String newUsername = scanner.nextLine();
                    user.setUsername(newUsername);
                    saveToFile();
                    System.out.println("Username changed successfully!");
                    break;

                case 2:
                    System.out.print("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    user.setPassword(newPassword);
                    saveToFile();
                    System.out.println("Password changed successfully!");
                    break;

                case 3:
                    System.out.print("Enter new username: ");
                    String updatedUsername = scanner.nextLine();
                    System.out.print("Enter new password: ");
                    String updatedPassword = scanner.nextLine();
                    user.setUsername(updatedUsername);
                    user.setPassword(updatedPassword);
                    saveToFile();
                    System.out.println("Username and password changed successfully!");
                    break;

                default:
                    System.out.println("Invalid choice. No changes made.");
            }
        } else {
            System.out.println("Login failed. Invalid username or password.");
        }
    }

    public void closeScanner() {
        scanner.close(); // Close scanner when done
    }
}

import java.util.*;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import qa.QAProgram;
import qa.ForgotPassword;

// class LoginCredential {
//     String name;
//     String Id;

//     public static void signIn() {

//     }
// }

class PasswordManager {
    private static final String PasswordFile = "password.txt";
    // private static final int maxPasswordLength = 8;
    static String regex = "^(?=.*[a-z])(?=."
            + "*[A-Z])(?=.*\\d)"
            + "(?=.*[-+_!@#$%^&*., ?]).+$";

    private static final Pattern PasswordPattern = Pattern.compile(regex);

    private static final String pass_file = "password.txt";

    @SuppressWarnings("static-access")
    public static void main(String[] args) throws IOException {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);

        // LoginCredential obj = new LoginCredential();
        // obj.signIn();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String name = sc.nextLine();
        System.out.println("Enter you User Id");
        String Id = sc.nextLine();

        QAProgram qaProgram = new QAProgram();
        qaProgram.main(null);

        String password;
        do {
            System.out.println(
                    "Enter a password (uppercase letters, one lowercase letter, one digit, one symbol, and 8 characters):");
            password = scanner.nextLine();
            if (!isValidPassword(password)) {
                System.out.println("Password does not meet the criteria. Please try again.");
            }
        } while (!isValidPassword(password));

        String confirmedPassword;
        do {
            System.out.println("Confirm password:");
            confirmedPassword = scanner.nextLine();
        } while (!password.equals(confirmedPassword));

        savePassword(name, Id, password);

        System.out.println("Enter password to login:");
        String loginPassword = scanner.nextLine();
        if (checkPassword(loginPassword)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid password.");
        }
    }

    private static boolean isValidPassword(String password) {
        Matcher matcher = PasswordPattern.matcher(password);
        return matcher.matches();
    }

    // private static void savePassword(String password) {
    // try (PrintWriter writer = new PrintWriter(new FileWriter(PasswordFile))) {
    // writer.println(writer);
    // } catch (IOException e) {
    // System.out.println("Error saving password: " + e.getMessage());
    // }
    // }

    // Subhechchha
    private static void savePassword(String name, String Id, String password) throws IOException {
        // Open the file in append mode
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(pass_file, true))) {
            // Append the entered data to the file
            writer.write(name + "," + Id + "," + password);
            writer.newLine(); // Add a new line after each entry
        }
    }

    private static boolean checkPassword(String loginPassword) {
        try (Scanner fileScanner = new Scanner(PasswordManager.class.getResourceAsStream(PasswordFile))) {
            if (fileScanner.hasNextLine()) {
                String savedPassword = fileScanner.nextLine();
                return savedPassword.equals(loginPassword);
            }
        } catch (Exception e) {
            System.out.println("Error reading password file: " + e.getMessage());
        }
        return false;

    }
}

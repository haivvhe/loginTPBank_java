
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginTienPhong {

    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        LoginTienPhong login = new LoginTienPhong();
        login.Start();
    }

    public void menu() {
        System.out.println("=========Login Tien Phong Bank’s Ebank===========");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
    }

    int getInputInt(String message, int min, int max) {
        int number;
        while (true) {
            try {
                System.out.print(message);
                number = Integer.parseInt(scan.nextLine());
                if (number < min || number > max) {
                    System.out.println("Out of range !!");
                } else {
                    return number;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input must be an integer number!");
            }
        }
    }

    public void Start() {
        while (true) {
            menu();
            int input = getInputInt("Please choose one option: ", 1, 3);
            switch (input) {
                case 1:
                    Locale vi_Locale = new Locale("vi", "VN");
                    ResourceBundle vi_bundle = ResourceBundle.getBundle("language_vi_VN", vi_Locale);
                    loginSytem(vi_bundle);
                    break;
                case 2:
                    Locale en_Locale = new Locale("en", "US");
                    ResourceBundle en_bundle = ResourceBundle.getBundle("language_en_US", en_Locale);
                    loginSytem(en_bundle);
                    break;
                case 3:
                    return;
            }
        }
    }

    public void loginSytem(ResourceBundle bundle) {
        checkPhone(bundle);
        checkPassword(bundle);
        checkCaptcha(bundle);
    }

    public void checkPhone(ResourceBundle bundle) {
        while (true) {
            System.out.print(bundle.getString("account")); // in van ban duoc gan cho khoa account
            String phonenumber = scan.nextLine();
            if (phonenumber.matches("^[0-9]{10}$")) {
                break;
            } else {
                System.out.println(bundle.getString("check.account"));
            }
        }
    }

    public void checkPassword(ResourceBundle bundle) {

        while (true) {
            int check1 = 0;
            int check2 = 0;
            System.out.print(bundle.getString("password"));

            String password = scan.nextLine();
            // int size = password.length();
            if (password.length() >= 8 && password.length() <= 31 && password.matches("[a-zA-Z0-9]*")) {
                for (int i = 0; i < password.length(); i++) {
                    char x = password.charAt(i);
                    if (Character.isDigit(x)) {
                        check1 = 1;
                    }
                    if (Character.isLetter(x)) {
                        check2 = 1;
                    }

                }
                if (check1 == 1 && check2 == 1) {
                    return;

                } else {
                    System.out.println(bundle.getString("wrong.password"));
                }
            } else {

                System.out.println(bundle.getString("wrong.password"));
            }
        }

    }

    public String randomCaptcha() {
        String s = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random r = new Random();
        String captcha = "";
        for (int i = 0; i < 5; i++) {
            int index = r.nextInt(s.length());
            captcha = captcha + s.charAt(index);
        }
        return captcha;
    }

    public void checkCaptcha(ResourceBundle bundle) {
        String captcha = randomCaptcha();
        System.out.println(bundle.getString("captcha") + captcha);
        while (true) {
            System.out.print(bundle.getString("captchaInput"));
            String input = scan.nextLine();
            if (captcha.contains(input)) {
                System.out.println(bundle.getString("loginSuccess"));
                break;
            } else {
                System.out.println(bundle.getString("wrong.captcha"));
            }

        }
    }

}

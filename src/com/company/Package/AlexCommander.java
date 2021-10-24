package com.company.Package;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class AlexCommander {

    File file = new File("/");

    public void run () throws IOException {
        System.out.println("Hello world !");
        localeMessage();
        fileManager();
    }

    private void fileManager() throws IOException {

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.print("Текущая директория: " + file.getAbsolutePath() + "\n");
            fileMessage();
                String userInput = scanner.next();
            switch (userInput) {
                case "..":
                        file = new File(file.getParent());
                        break;
                case "exit":
                    exit = true;
                    break;
                case "cd":
                        file = new File(file.getAbsolutePath() + "/" + scanner.next());
                        break;

                default:
                    for (File name : file.listFiles()) {
                        if (name.getName().equals(userInput)) {
                            readFile(file.getAbsolutePath()+ "\\" + userInput);
                        }
                    }
                    break;
            }

        }
    };
    private void readFile(String name) throws IOException {
        System.out.println("-----Содержимое файла "+ name + " :");
            BufferedReader in = new BufferedReader(new FileReader(name));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(line);
                }
        System.out.println("-----Конец содержимого файла "+ name);
    };

    private void fileMessage() {
        for (File name : file.listFiles()) {
            if (name.isDirectory()) {
                System.out.println(name.getName());
            }
        }
        for (File name : file.listFiles()) {
            if (name.isFile()) {
                System.out.println(name.getName());
            }
        }
    };
    private void localeMessage() {
        Locale currentLocale = null;
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            System.out.println("Please enter your languege: EN, DE, RU");
            String userLocale = scanner.nextLine();
            switch (userLocale) {
                case "EN":
                    currentLocale = new Locale("en", "EN");
                    exit = true;
                    break;
                case "DE":
                    currentLocale = new Locale("de", "DE");
                    exit = true;
                    break;

                case "RU":
                    currentLocale = new Locale("ru", "RU");
                    exit = true;
                    break;
                default:
                    System.out.println("Спецом накосячил ? Начинаем заново !");
            }
        }
        ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        System.out.println(bundle.getString("greetingMessage"));

    };

}

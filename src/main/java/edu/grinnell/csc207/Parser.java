package edu.grinnell.csc207;

import java.util.List;
import java.util.Scanner;

/**
 * Parser
 */
public class Parser {
    private Scanner scanner;

    /**
     * instantiate parser
     * 
     * @param scanner
     */
    public Parser(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * get a list of string and return one of them based on user input (not case
     * sensitive)
     * 
     * @param actions
     * @return instruction
     */
    public String act(List<String> actions) {
        System.out.print("Possible options: ");
        for (int iter = 0; iter < actions.size(); iter++) {
            System.out.print("<" + actions.get(iter) + "> ");
        }
        System.out.println("");
        String result;
        while (true) {
            result = scanner.nextLine();
            for (int iter = 0; iter < actions.size(); iter++) {
                if (result.toLowerCase().equals(actions.get(iter).toLowerCase())) {
                    return actions.get(iter);
                }
            }
            System.out.println("Invalid option!");
            System.out.print("Possible options: ");
            for (int iter = 0; iter < actions.size(); iter++) {
                System.out.print("<" + actions.get(iter) + "> ");
            }
            System.out.println("");
        }
    }

}

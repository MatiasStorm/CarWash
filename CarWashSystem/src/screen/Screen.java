package screen;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import app.Input;

public class Screen{
    Input input = new Input();
    Scanner scan = new Scanner(System.in);

	public String askForWashCardId() {
        String idPattern = "[0-9]{4}";
        String message = "Enter WashCard id (4-numbers): ";
        String id = input.getInput(message, idPattern);
		return id;
    }
    
    private void printMenu(String menuName, ArrayList items){
        System.out.printf("\n---- %s Menu ----\n", menuName);
        for (int i = 0; i < items.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, items.get(i));
        }
    }

    public Object displayMenu(String menuName, ArrayList items) {
        printMenu(menuName, items);
        String message = String.format("Pick an option (1-%d): ", items.size());
        String pattern = String.format("[1-%d]", items.size());
        int index = Integer.parseInt(input.getInput(message, pattern));
        return items.get(index - 1);
    }

    public void displayError(String message){
        System.out.println("\nError: " + message);
        System.out.print("Press enter to return to main menu.");
        scan.nextLine();
    }   

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayCarWash(){
        System.out.println("\nWashing car...");
        try{
            TimeUnit.MILLISECONDS.sleep(500);
        }
        catch (InterruptedException e){
            return;
        }
        System.out.println("All done!");
    }

    public char yesNoOption(String question){
        String message = String.format("%s [Y/n]? ", question);
        String pattern = "Y|y|N|n";
        String answer = input.getInput(message, pattern);
        return answer.toLowerCase().charAt(0);
    }

    public void displayObject(String message, Object o){
        System.out.printf("\n---- %s ----\n", message);
        System.out.println(o);
        System.out.print("\nPress enter to return to main menu.");
        scan.nextLine();
    }

    public int askForAmount(String message, int min, int max){
        System.out.printf("\n---- %s ----\n", message);
        
        String inputMessage = String.format("Specify amount (%d - %d): ", min, max);
        String pattern = "[1-9]{1}|[1-9]{1}[0-9]{1,2}|1000";
        int amount = Integer.parseInt(input.getInput(inputMessage, pattern));
        return amount;
    }



}
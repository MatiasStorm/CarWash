package screen;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Screen{
    Scanner scan = new Scanner(System.in);

	public String askForWashCardId() {
        String idPattern = "[0-9]{4}";
        System.out.print("Enter WashCard id (4-numbers): ");
        String id = scan.nextLine();
        while(!id.matches(idPattern)){
            System.out.println("Invalid ID!");
            System.out.print("Enter WashCard id (4-numbers): ");
            id = scan.nextLine();
        }
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
        System.out.printf("Pick an option (1-%d): ", items.size());

        int index = -1;
        
        while(index < 1 || index > items.size()){
            if(scan.hasNextInt()){
                index = scan.nextInt();
            }
            if(index < 1 || index > items.size()){
                printMenu(menuName, items);
                System.out.printf("\nYou have to pick a number between 1 and %d.\n", items.size());
                System.out.printf("Pick an option (1-%d): ", items.size());
                scan.nextLine();
            }
        }
        scan.nextLine();
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
        System.out.printf("%s [Y/n]? ", question);
        String answer = scan.nextLine();
        while(!answer.equals("y") && !answer.equals("n")){
            System.out.println("\nYou have to type either 'y' or 'n'.");
            System.out.printf("%s [Y/n]? ", question);
            answer = scan.nextLine();
        }
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
        System.out.printf("Specify amount (%d - %d): ", min, max);
        int amount = min - 1;
        while(min > amount || amount > max){
            if(scan.hasNextInt()){
                amount = scan.nextInt();
            }
            if(min > amount || amount > max){
                System.out.println("Invalid amount.");
                System.out.printf("Specify amount (%d - %d): ", min, max);
            }
        }
        scan.nextLine();
        return amount;
    }



}
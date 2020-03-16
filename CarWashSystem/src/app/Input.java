package app;

import java.util.Scanner;

public class Input{
    Scanner scan = new Scanner(System.in);

    public String getInput(String message, String pattern){
        String input;
        do{
            System.out.print(message);
            input = scan.next();
        } while(!input.matches(pattern));
        scan.nextLine();
        return input;
    }

}


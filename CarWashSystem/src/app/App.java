package app;

import washmachine.WashMachine;

public class App {
    public static void main(String[] args) throws Exception {
        WashMachine washMachine = new WashMachine();
        String userMode;
        try{
            userMode = args[0].toLowerCase();
        } catch (ArrayIndexOutOfBoundsException e){
            userMode = "user";
        }
        washMachine.run(userMode);
    }
}

package washmachine;

import java.util.ArrayList;

import app.WashCard;
import screen.Screen;


public class WashMachine{
    Screen screen = new Screen();
    ArrayList<WashType> washTypes = WashTypeFactory.getWashTypes(); 
    ArrayList<WashMachineOption> mainMenuOptions = new ArrayList<WashMachineOption>();
    ArrayList<WashMachineOption> adminMenuOptions = new ArrayList<WashMachineOption>();
    Database database = new Database();
    WashCard washCard;
    boolean inUserLoop = false;
    boolean isOn = true;

    public void run(String userMode) {
        loadMenuOptions();
        System.out.println(userMode);
        if(userMode.equals("admin")){
            adminLoop();
        }
        else{
            userLoop();
        }
    }

    public void userLoop() {
        while(this.isOn){
            this.inUserLoop = true;
            String washCardId = screen.askForWashCardId();

            // this.washCard = new WashCard(washCardId);
            this.washCard = database.getWashCard(washCardId);
            
            while(this.inUserLoop){
                WashMachineOption option = (WashMachineOption)screen.displayMenu("Main", this.mainMenuOptions);
                option.execute(this);
            }
        }
    }

    public void adminLoop() {
        while(this.isOn){
            WashMachineOption option = (WashMachineOption)screen.displayMenu("Main", this.adminMenuOptions);
            option.execute(this);
        }
    }

    public void loadMenuOptions() {
        this.mainMenuOptions.add(new WashCarOption());
        this.mainMenuOptions.add(new RechargeCardOption());
        this.mainMenuOptions.add(new CheckFundsOption());
        this.mainMenuOptions.add(new ExpelCardOption());

        this.adminMenuOptions.add(new ShowAllDataOption());
        this.adminMenuOptions.add(new WashBreakdownOption());
    }

    public void showAllData(){
        this.screen.displayObject("All Data", this.database.getAllData());
    }

    public void showWashBreakdown(){
        this.screen.displayObject("Wash Breakdown", this.database.getWashBreakdown());
    }

    public void washCar() {
        WashType selectedWash = (WashType) this.screen.displayMenu("Wash", this.washTypes);
        
        double cardBalance = this.washCard.getBalance();
        if(selectedWash.getPrice() > cardBalance){
            String errorMessage = String.format("Insufficient funds! You only have %.2f kr. left on your card.", cardBalance);
            this.screen.displayError(errorMessage);
        }
        else {
            char yesNo = this.screen.yesNoOption("Do you wan't a receipt?");
            this.screen.displayCarWash();
            if(yesNo == 'y'){
                this.screen.displayObject("Receipt", selectedWash);
            }
            this.washCard.withdraw(selectedWash.getPrice());
            this.database.logWash(this.washCard, this.washTypes.indexOf(selectedWash));
        }
        
    }

    public void rechargeCard() {
        int amount = this.screen.askForAmount("Insert amount", 0, 1000);
        this.washCard.addAmount((double) amount);
        this.database.logBalance(this.washCard);
        this.checkAmountLeftOnCard();
    }

    public void checkAmountLeftOnCard() {
        this.screen.displayObject("WashCard", this.washCard);
    }

    public void expelCard() {
        this.inUserLoop = false;
        this.screen.displayMessage("\nGoodbye!\n\n");
    }
}
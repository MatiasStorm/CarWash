package washmachine;

abstract class WashMachineOption{
    protected String name;

    public String toString() {
        return this.name;
    }
    
    abstract public void execute(WashMachine washMachine);
}

class WashCarOption extends WashMachineOption {

    public WashCarOption(){
        this.name = "Wash Car";
    }

    public void execute(WashMachine washMachine) {
        washMachine.washCar();
    }
}

class RechargeCardOption extends WashMachineOption{

    public RechargeCardOption(){
        this.name = "Recharge Card";
    }

    public void execute(WashMachine washMachine) {
        washMachine.rechargeCard();
    }
}

class CheckFundsOption extends WashMachineOption{
    
    public CheckFundsOption(){
        this.name = "Check amount left on card";
    }

    public void execute(WashMachine washMachine) {
        washMachine.checkAmountLeftOnCard();
    }
}

class ExpelCardOption extends WashMachineOption{
    
    public ExpelCardOption(){
        this.name = "Expel Card";
    }

    public void execute(WashMachine washMachine) {
        washMachine.expelCard();
    }
}

class ShowAllDataOption extends WashMachineOption{
    
    public ShowAllDataOption(){
        this.name = "Show All Data";
    }

    public void execute(WashMachine washMachine) {
        washMachine.showAllData();
    }
}

class WashBreakdownOption extends WashMachineOption{
    
    public WashBreakdownOption(){
        this.name = "Wash Breakdown";
    }

    public void execute(WashMachine washMachine) {
        washMachine.showWashBreakdown();
    }
}



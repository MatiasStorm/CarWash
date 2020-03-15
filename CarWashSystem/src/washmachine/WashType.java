package washmachine;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

class WashTypeFactory{
    public static ArrayList<WashType> getWashTypes(){
        ArrayList<WashType> washTypes = new ArrayList<>();
        washTypes.add(new EconomyWash());
        washTypes.add(new StandardWash());
        washTypes.add(new DeLuxeWash());
        return washTypes;
    }

    public static int getNumberOfWashTypes(){
        return WashTypeFactory.getWashTypes().size();
    }

    public static ArrayList<String> getWashTypeNames(){
        ArrayList<String> names = new ArrayList<>();
        for(WashType type : WashTypeFactory.getWashTypes()){
            names.add(type.name);
        }
        return names;
    }
}

abstract class WashType{
    double price;
    String name ;

    public String toString(){
        return String.format("%s: %.2f kr.", name, getPrice());
    }

    public double getPrice(){
        return this.price;
    }
}

abstract class EarlyBirdDiscount extends WashType{
    LocalTime experyTime = LocalTime.of(14, 0, 0);
    double discountPercentage = 0.2;

    private boolean isDiscountValid(){
        LocalTime now = LocalTime.now();
        String dayOfWeek = LocalDate.now().getDayOfWeek().toString();
        return now.isBefore(experyTime) && dayOfWeek != "SATURDAY" && dayOfWeek != "SUNDAY"; 
    }

    @Override
    public double getPrice(){
        if(isDiscountValid()){
            return this.price - (this.price * this.discountPercentage);
        }
        return this.price;
    }

    @Override
    public String toString(){
        if(isDiscountValid()){
            return String.format("%s: %.2f kr. (Earlybird Discount 20%% Off, Normal Price: %.2f)", this.name, getPrice(), this.price);
        }
        return super.toString();
    }

}

class EconomyWash extends EarlyBirdDiscount{
    public EconomyWash(){
        this.price = 50;
        this.name = "Economy";
    }
}

class StandardWash extends EarlyBirdDiscount{
    public StandardWash(){
        this.price = 80;
        this.name = "Standard";
    }
}

class DeLuxeWash extends WashType{
    public DeLuxeWash(){
        this.price = 120;
        this.name = "De Luxe";
    }
}
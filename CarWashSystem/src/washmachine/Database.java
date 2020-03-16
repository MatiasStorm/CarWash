package washmachine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import app.WashCard;


class Database{
    final static String delim = ",";
    final private String dataFileName = "bin/data/WashCardData.txt";
    private ArrayList<WashDataElement> washData;
    private int numberOfWashTypes = WashTypeFactory.getNumberOfWashTypes();

    public Database(){
        readWashCardDataFile();
    }

    private WashCard createNewWashCard(String id){
        WashCard washCard = new WashCard(id);
        ArrayList<Integer> boughtWashes = new ArrayList<>(Collections.nCopies(numberOfWashTypes, 0));
        washData.add(new WashDataElement(id, washCard.getBalance(), boughtWashes));
        return washCard;
    }

    private void readWashCardDataFile(){
        try{
            washData = new ArrayList<>();
            File dataFile = new File(dataFileName);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }

            String line;
            BufferedReader in = new BufferedReader(new FileReader(dataFileName));
            line = in.readLine();
            while (line != null){
                readWashCardDataLine(line);
                line = in.readLine();
            }
            in.close();

        } catch (final IOException e) {
            System.out.println("An error occured.");
            e.printStackTrace();
        }
    }


    private void readWashCardDataLine(String line){
        String[] lineArr = line.split(delim);
        String id = lineArr[0];
        double balance = Double.parseDouble(lineArr[1]);
        ArrayList<Integer> boughtWashes = new ArrayList<>();
        for(int i = 2; i < this.numberOfWashTypes + 2; i++){
            boughtWashes.add(Integer.parseInt(lineArr[i]));
        }
        washData.add(new WashDataElement(id, balance, boughtWashes));
    }

    private void saveData(){
        try{
            FileOutputStream fileOut = new FileOutputStream(dataFileName);
            for(WashDataElement elem : washData){
                fileOut.write(elem.toString().getBytes());
            }
            fileOut.close();

        } catch (IOException e){
            System.out.println("An error occured.");
            e.printStackTrace();
            System.exit(-1);
        }
    }


    public WashCard getWashCard(String id){
        readWashCardDataFile();
        int index = washData.indexOf(new WashDataElement(id));
        
        if(index == -1) {
            return createNewWashCard(id);
        }

        WashDataElement data = washData.get(index);
        return new WashCard(data.id, data.balance);
    }


    public void logWash(WashCard washCard, int index){
        WashDataElement dataElement = washData.get(washData.indexOf(new WashDataElement(washCard.getId())));
        dataElement.incrementWashType(index);
        logBalance(washCard);
        dataElement.balance = washCard.getBalance();
        saveData();
    }
    
    public void logBalance(WashCard washCard){
        WashDataElement dataElement = washData.get(washData.indexOf(new WashDataElement(washCard.getId())));
        dataElement.balance = washCard.getBalance();
        saveData();
    }

    public String getAllData(){
        String result = "Id,Balance";
        ArrayList<String> washTypeNames = WashTypeFactory.getWashTypeNames();

        for(String name : washTypeNames){
            result += "," + name;
        }
        result += "\n";

        for(WashDataElement element : washData){
            result += element;
        }

        return result;
    }

    public String getWashBreakdown(){
        String result = "";
        ArrayList<String> washTypeNames = WashTypeFactory.getWashTypeNames();
        String name;
        int totalWashes = 0;
        for(int i = 0 ; i < washTypeNames.size(); i++){
            name = washTypeNames.get(i);
            int subTotal = 0;
            for(WashDataElement element : washData){
                subTotal += element.boughtWashes.get(i);
            }
            result += name + ": " + subTotal + "\n";
            totalWashes += subTotal;
        }
        result += "Total Washes: " + totalWashes;
        return result;
    }

    private class WashDataElement{
        String id;
        double balance;
        ArrayList<Integer> boughtWashes;

        public WashDataElement(String id){
            this.id = id;
        }

        public WashDataElement(String id, double balance, ArrayList<Integer> boughtWashes){
            this.id = id;
            this.balance = balance;
            this.boughtWashes = boughtWashes;
        }

        public String toString(){
            String result = id + Database.delim + balance;
            for(int boughtWash: boughtWashes){
                result += Database.delim + boughtWash;
            }
            return result + "\n";
        }

        public void incrementWashType(int index){
            boughtWashes.set(index, boughtWashes.get(index) + 1);
        }

        @Override
        public boolean equals(Object other){
            if(other == null) return false;
            WashDataElement otherWashDataElement = (WashDataElement) other;
            return this.id.equals(otherWashDataElement.id);
        }

        @Override
        public int hashCode(){
            return Integer.parseInt(this.id);
        }
    }
}
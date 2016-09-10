package com.company;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {
    public static String SEPARATOR = "@";
    public static final String COLON = ":";

    /*
     * Complete the function below.
     *
 	 * Note: The questions in this test build upon each other. We recommend you
	 * copy your solutions to your text editor of choice before proceeding to
	 * the next question as you will not be able to revisit previous questions.
	 */

    static String generateTransactions(String input) {
        String portfolio = input.split(":")[0];
        String benchmark = input.split(":")[1];

        // Create TreeMap for all holdings
        String[] portfolioParts = portfolio.split(SEPARATOR);
        int numberOfStockInPortfolio = portfolioParts.length;

        TreeMap<String, Stock> portfolios = new TreeMap<>();

        for(int i = 0; i < numberOfStockInPortfolio; i ++){
            String[] info = portfolioParts[i].split(",");
            portfolios.put(info[0], new Stock(info[0], info[1], info[2]));
        }

        // Create TreeMap for all benchmarks
        String[] benchmarkParts = benchmark.split(SEPARATOR);
        int numberOfStockInBenchmark = benchmarkParts.length;

        TreeMap<String, Stock> benchmarks = new TreeMap<>();

        for(int i = 0; i < numberOfStockInBenchmark; i ++){
            String[] info = benchmarkParts[i].split(",");
            benchmarks.put(info[0], new Stock(info[0], info[1], info[2], info[3]));
        }

        // Calculate value and NAV for holdings
        double  holdingNAV = 0;

        //Map<String, double[]> valueH = new TreeMap<>(); // store value and NAV for each stock

        for(Map.Entry<String, Stock> entry : benchmarks.entrySet()){
            holdingNAV += portfolios.get(entry.getKey()).getQuantity() * entry.getValue().getPrice();
        }

        /*
        for(Map.Entry<String, Stock> entry : benchmarks.entrySet()){
            double holdingValue = portfolios.get(entry.getKey()).getQuantity() * entry.getValue().getPrice();
            double percNAV = holdingValue / holdingNAV * 100;
            valueH.put(entry.getKey(), new double[]{holdingValue, percNAV});
        }
        */

        // Calculate NAV for benchmarks
        double  benNAV = 0;

        //Map<String, double[]> valueB = new TreeMap<>(); // store value and NAV for each stock

        for(Map.Entry<String, Stock> entry : benchmarks.entrySet()){
            benNAV += entry.getValue().getQuantity() * entry.getValue().getPrice();
        }

        //Calculate NAV ratio
        double ratio = holdingNAV / benNAV;

        // Calculate target quantity
        for(Map.Entry<String, Stock> entry : benchmarks.entrySet()) {
            double originalQ = entry.getValue().getQuantity();
            double targetQ = originalQ * ratio;
            entry.getValue().setQuantity(targetQ);
        }

        /*
        // Print result
        for(Map.Entry<String, Stock> entry : benchmarks.entrySet()){
            String last = benchmarks.lastKey();
            if(entry.getKey().equals(last)){
                System.out.print("[" + portfolios.get(entry.getKey()) + ", " +
                        String.format("%.2f", entry.getValue().getPrice()) + ", " +
                        String.format("%.2f", value.get(entry.getKey())[0]) +
                        ", " + String.format("%.2f", value.get(entry.getKey())[1]) + "]");
            } else {
                System.out.print("[" + portfolios.get(entry.getKey()) + ", " +
                        String.format("%.2f", entry.getValue().getPrice()) + ", " +
                        String.format("%.2f", value.get(entry.getKey())[0]) +
                        ", " + String.format("%.2f", value.get(entry.getKey())[1]) + "], ");
            }

        }
        */

        // In case the number of stocks in benchmark and in portfolio are different
        List<Transaction> transactions = new ArrayList<>();
        for(Map.Entry<String, Stock> entry : portfolios.entrySet()){
            String ticker = entry.getKey();
            if(benchmarks.containsKey(ticker)){
                transactions.add(new Transaction(entry.getValue(), benchmarks.get(ticker)));
            }
        }


        //Print out result
        for(int i = 0; i < transactions.size() - 1; i ++){
            System.out.print(transactions.get(i) + ", ");
        }

        System.out.print(transactions.get(transactions.size() - 1));


        return "";

    }

    public static class Stock implements Comparable<Stock>{
        private String ticker;
        private String name;
        private double quantity;
        private double price;

        public Stock(){
            this.ticker = "";
            this.name = "";
            this.quantity = 0;
            this.price = 0;
        }

        public Stock(String ticker, String name, String quantity){
            this.ticker = ticker;
            this.name = name;
            this.quantity = Integer.parseInt(quantity);
            this.price = -1;
        }

        public Stock(String ticker, String name, String quantity, String price){
            this.ticker = ticker;
            this.name = name;
            this.quantity = Integer.parseInt(quantity);
            this.price = Double.parseDouble(price);
        }

        public void setTicker(String ticker){
            this.ticker = ticker;
        }

        public void setName(String name){
            this.name = name;
        }

        public void setQuantity(Double quantity){
            this.quantity = quantity;
        }

        public void setPrice(String price){
            this.price = Double.parseDouble(price);
        }

        public String getTicker(){
            return ticker;
        }

        public String getName(){
            return name;
        }

        public double getQuantity(){
            return quantity;
        }

        public double getPrice(){
            return price;
        }

        public String toString(){
            if(this.price < 0){
                return ticker + ", " + name + ", " + String.format("%.0f", quantity);
            } else {
                return ticker + ", " + name + ", " + String.format("%.0f", quantity)
                        + ", " + String.format("%.2f", price);
            }

        }


        public int compareTo(Stock other){
            int result = this.ticker.compareTo(other.ticker);
            return result;
        }
    }

    public static class Transaction{
        private Stock stock1;
        private Stock stock2;

        public Transaction(Stock stock1, Stock stock2){
            this.stock1 = stock1;
            this.stock2 = stock2;
        }

        private double difference(){
            return stock2.getQuantity() - stock1.getQuantity();
        }

        public String toString(){
            double difference = this.difference();
            if(difference > 0){
                return "[BUY, " + stock1.getTicker() + ", " + String.format("%.2f", difference) + "]";
            } else {
                return "[SELL, " + stock1.getTicker() + ", " + String.format("%.2f", 0 - difference) + "]";
            }
        }
    }


    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        String res;
        String _input;
        try {
            _input = in.nextLine();
        } catch (Exception e) {
            _input = null;
        }
        res = generateTransactions(_input);
        System.out.println(res);
    }
}

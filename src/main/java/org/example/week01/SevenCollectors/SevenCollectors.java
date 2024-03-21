package main.java.org.example.week01.SevenCollectors;

import java.util.List;
import java.util.stream.Collectors;


class Transaction {
    private int id;
    private double amount;
    private String currency;

    public Transaction(int id, double amount, String currency) {
        this.id = id;
        this.amount = amount;
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                '}';
    }
}

public class SevenCollectors {

    public static void main(String[] args) {
        List<Transaction> transactions = List.of(
                new Transaction(1, 100.0, "USD"),
                new Transaction(2, 150.0, "EUR"),
                new Transaction(3, 200.0, "USD"),
                new Transaction(4, 75.0, "GBP"),
                new Transaction(5, 120.0, "EUR"),
                new Transaction(6, 300.0, "GBP")
        );

        // Calculate the total sum of all transaction amounts
        double totalSum = transactions.stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
        System.out.println("Total sum of all transactions: " + totalSum);

        // Group transactions by currency and calculate sum for each currency
        transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCurrency))
                .forEach((currency, transactionList) -> {
                    double sum = transactionList.stream()
                            .mapToDouble(Transaction::getAmount)
                            .sum();
                    System.out.println("Sum of transactions in " + currency + ": " + sum);
                });

        // Find the highest transaction amount
        Transaction highestTransaction = transactions.stream()
                .collect(Collectors.maxBy((t1, t2) -> Double.compare(t1.getAmount(), t2.getAmount())))
                .orElseThrow();
        System.out.println("Highest transaction: " + highestTransaction);

        // Find the average transaction amount
        double averageTransaction = transactions.stream()
                .collect(Collectors.averagingDouble(Transaction::getAmount));
        System.out.println("Average transaction: " + averageTransaction);

    }
}


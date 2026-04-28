package src.streams.transaction;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TransactionManager {
    public static void main(String[] args) {
       /*
       idiomatic Java solution to process a list of transaction records and sum all APPROVED transactions per vendor (grouped by vendorId).
       */
        List<Transaction> transactions = Arrays.asList(
                new Transaction("T001", "CITI", "V001", new java.math.BigDecimal("100.95"), "Approved", java.time.LocalDateTime.now()),
                new Transaction("T002", "ANZ", "V002", new java.math.BigDecimal("980.10"), "Pending", java.time.LocalDateTime.now()),
                new Transaction("T003", "CITI", "V001", new java.math.BigDecimal("1090.00"), "Approved", java.time.LocalDateTime.now()),
                new Transaction("T004", "HSBC", "V004", new java.math.BigDecimal("305.65"), "Failed", java.time.LocalDateTime.now()),
                new Transaction("T005", "AXIS", "V003", new java.math.BigDecimal("250.05"), "Approved", java.time.LocalDateTime.now()),
                new Transaction("T006", "AMAZON", "V005", new java.math.BigDecimal("999.99"), "Failed", java.time.LocalDateTime.now()),
                new Transaction("T007", "AXIS", "V003", new java.math.BigDecimal("709.98"), "Approved", java.time.LocalDateTime.now()),
                new Transaction("T008", "AMAZON", "V005", new java.math.BigDecimal("450.50"), "Approved", java.time.LocalDateTime.now()),
                null,
                new Transaction("T009", "GOKART", null, new java.math.BigDecimal("120.55"), "Approved", java.time.LocalDateTime.now())
        );

        Map<String, BigDecimal> sumApprovedByVendor = transactions.stream()
                .filter(Objects::nonNull)
                .filter(t -> "Approved".equalsIgnoreCase(t.getTransactionStatus()))
                .collect(Collectors.groupingBy(Transaction::getVendorName,
                                Collectors.reducing(BigDecimal.ZERO,
                                        Transaction::getSettlementAmount,
                                        BigDecimal::add)));

        sumApprovedByVendor.forEach((vendorName, totalAmount) ->
                System.out.println("Vendor Name: " + vendorName + ", Total Approved Amount: " + totalAmount));

        Map<String, BigDecimal> sumApprovedByVendorHandleNulls = transactions.stream()
                // 1. Filter out null objects
                .filter(Objects::nonNull)
                // 2. Filter for Approved status
                .filter(tx -> "Approved".equalsIgnoreCase(tx.getTransactionStatus()))
                // 3. Required vendorId
                .filter(tx ->tx.getVendorId() != null)
                // 4. Group and sum
                .collect(Collectors.groupingBy(Transaction::getVendorName,
                        Collectors.reducing(BigDecimal.ZERO,
                                Transaction::getSettlementAmount,
                                BigDecimal::add)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        java.util.LinkedHashMap::new
                ));

        System.out.println("\nVendors sorted by total approved amount (descending):");
        sumApprovedByVendorHandleNulls.forEach((vendorName, totalAmount) ->
                System.out.println("Vendor Name: " + vendorName + ", Total Approved Amount: " + totalAmount));




    }

}

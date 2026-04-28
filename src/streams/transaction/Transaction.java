package src.streams.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private String vendorName;
    private String vendorId;
    //Monetary values should never use double due to precision errors. BigDecimal is the preferred type for financial calculations.
    private BigDecimal settlementAmount;
    private String transactionStatus;
    private LocalDateTime settlementTime;

    public Transaction(String transactionId, String vendorName, String vendorId, BigDecimal settlementAmount, String transactionStatus, LocalDateTime settlementTime) {
        this.transactionId = transactionId;
        this.vendorName = vendorName;
        this.vendorId = vendorId;
        this.settlementAmount = settlementAmount;
        this.transactionStatus = transactionStatus;
        this.settlementTime = settlementTime;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public LocalDateTime getSettlementTime() {
        return settlementTime;
    }

    public void setSettlementTime(LocalDateTime settlementTime) {
        this.settlementTime = settlementTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", vendorId='" + vendorId + '\'' +
                ", settlementAmount=" + settlementAmount +
                ", transactionStatus='" + transactionStatus + '\'' +
                ", settlementTime=" + settlementTime +
                '}';
    }
}

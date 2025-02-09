package com.example.bankproject;


public class Client extends Person {

    private String AccountNumber;
    private String PIN;
    private String ClientName;
    private String PhoneNumber;
    private DebitCard debitCard;
    private SavingsAccount savingsAccount;

    public Client(String username, String password, String clientName, String phoneNumber, float accountBalance, float balance) {
        super(username, password);
        this.AccountNumber = username;
        this.PIN = password;
        this.ClientName = clientName;
        this.PhoneNumber = phoneNumber;
        this.debitCard = new DebitCard(accountBalance);
        this.savingsAccount = new SavingsAccount(balance);
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.AccountNumber = accountNumber;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        this.ClientName = clientName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPin(String pin) {
        this.PIN = pin;
    }

    public DebitCard getDebitCard() {
        return debitCard;
    }

    public SavingsAccount getSavingsAccount()
    {
        return savingsAccount;
    }

    @Override
    public String toString() {
        return "Client: " + ClientName + ", Account Number: " + AccountNumber;
    }
}

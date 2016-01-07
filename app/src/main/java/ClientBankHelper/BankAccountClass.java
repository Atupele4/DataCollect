package ClientBankHelper;

/**
 * Created by mboyaa on 1/7/2016.
 */
public class BankAccountClass {

    public String BankName;
    public String AccountNumber;
    public String AccountType;
    public String ClientID;

    public BankAccountClass(){
        BankName = "";
        AccountNumber = "";
        AccountType = "";
        ClientID = "";
    }

    public String getBantName(){
        return BankName;
    }

    public String getAccountNumber(){
        return AccountNumber;
    }

    public String getAccountType(){
        return AccountType;
    }

    public String getClientID(){
        return ClientID;
    }
}

package GoFootball;

import java.math.BigInteger;
import java.util.UUID;
/**
 *  Stores the various information of a User Wallet
 */
public class eWallet
{
    /**
     *Class Attributes
     */
    private double currentBalance;
    private String walletID;

    /**
     * Assign the user's wallet to a default value and generate a unique ID for the wallet.
     */
    public eWallet() {
        currentBalance = 500;
        walletID = String.format("%015d",
                new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
    }

    /**
     * eWallet parametrize Constructor takes user given Money
     * @param givenMoney , Double
     */

    public eWallet(final double givenMoney) {
        setCurrentBalance(givenMoney);
        walletID = String.format("%015d",
                new BigInteger(UUID.randomUUID().toString().replace("-", ""), 16));
    }
    /**
     * this method sets a value to the wallet Balance
     * @param givenMoney , Double
     */
    public boolean setCurrentBalance(double givenMoney)
    {
        if(givenMoney <= 0){
            System.out.println("INVALID INPUT : Money cannot be negative!");
            return false;
        }
        currentBalance = givenMoney;
        return true;
    }

    // Getters
    /**
     * this method return the wallent balance
     * @return currentBalance
     */

    public double getCurrentBalance(){ return currentBalance; }
    /**
     * this method return the wallent ID
     * @return walletID
     */
    public String getTransactionID(){ return walletID.substring(0,14); }
}

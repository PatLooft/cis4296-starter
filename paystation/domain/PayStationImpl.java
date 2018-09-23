package paystation.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */
public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    
    private int lastPurchase;

    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5: break;
            case 10: break;
            case 25: break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        insertedSoFar += coinValue;
        timeBought = insertedSoFar / 5 * 2;
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        lastPurchase = timeBought;
        reset();
        return r;
    }

    @Override
    public void cancel() {
        reset();
    }
    
    private int empty() {
    	int temp = lastPurchase;
    	lastPurchase = 0;
    	insertedSoFar = 0;
    	return temp;
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
    }




//    @Test
//    public void testEmpty() throws IllegalCoinException {
//        addPayment(5);
//        addPayment(10);
//        assertEquals(15, empty());
//    }
}

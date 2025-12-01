package library.service;

import library.exceptions.AlreadyBorrowedException;
import library.exceptions.ItemNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages loans (checkouts/checkins).
 * Uses ArrayList internally but demonstrates array usage as well.
 */
public class LoanManager {
    private List<Loan> loans = new ArrayList<>();

    public void checkOut(String callNumber, String patronCode) throws AlreadyBorrowedException {
        // if an active loan exists for callNumber and not returned -> already borrowed
        for (Loan loan : loans) {
            if (loan.getCallNumber().equalsIgnoreCase(callNumber) && !loan.isReturned()) {
                throw new AlreadyBorrowedException("Item " + callNumber + " is already borrowed.");
            }
        }
        loans.add(new Loan(callNumber, patronCode));
    }

    public void checkIn(String callNumber) throws ItemNotFoundException {
        for (Loan loan : loans) {
            if (loan.getCallNumber().equalsIgnoreCase(callNumber) && !loan.isReturned()) {
                loan.setReturned(true);
                return;
            }
        }
        throw new ItemNotFoundException("No active loan found for call number: " + callNumber);
    }

    public Loan findActiveLoan(String callNumber) {
        for (Loan loan : loans) {
            if (loan.getCallNumber().equalsIgnoreCase(callNumber) && !loan.isReturned()) {
                return loan;
            }
        }
        return null;
    }

    public Loan[] asArray() {
        // return an array of loans (demonstrate arrays)
        return loans.toArray(new Loan[0]);
    }

    public List<Loan> getAllLoans() { return new ArrayList<>(loans); }
}

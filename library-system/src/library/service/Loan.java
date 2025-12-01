package library.service;

import java.time.LocalDate;

public class Loan {
    private String callNumber;
    private String patronCode;
    private LocalDate checkoutDate;
    private boolean returned;

    public Loan(String callNumber, String patronCode) {
        this.callNumber = callNumber;
        this.patronCode = patronCode;
        this.checkoutDate = LocalDate.now();
        this.returned = false;
    }

    public String getCallNumber() { return callNumber; }
    public String getPatronCode() { return patronCode; }
    public LocalDate getCheckoutDate() { return checkoutDate; }
    public boolean isReturned() { return returned; }
    public void setReturned(boolean returned) { this.returned = returned; }

    @Override
    public String toString() {
        return String.format("Loan(call#: %s, patron: %s, checked: %s, returned: %s)",
                callNumber, patronCode, checkoutDate, returned);
    }
}

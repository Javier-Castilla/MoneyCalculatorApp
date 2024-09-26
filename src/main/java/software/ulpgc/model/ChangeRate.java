package software.ulpgc.model;

import java.time.LocalDate;

public class ChangeRate {
    private final LocalDate date;
    private final double rate;
    private final Currency from_currency;
    private final Currency to_currency;

    public ChangeRate(LocalDate date, double rate, Currency from_currency, Currency to_currency) {
        this.date = date;
        this.rate = rate;
        this.from_currency = from_currency;
        this.to_currency = to_currency;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getRate() {
        return rate;
    }

    public Currency getFrom_currency() {
        return from_currency;
    }

    public Currency getTo_currency() {
        return to_currency;
    }

    @Override
    public String toString() {
        return "ChangeRate{" +
                "date=" + date +
                ", rate=" + rate +
                ", from_currency=" + from_currency +
                ", to_currency=" + to_currency +
                '}';
    }
}

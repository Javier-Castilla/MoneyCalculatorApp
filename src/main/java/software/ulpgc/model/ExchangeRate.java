package software.ulpgc.model;

import java.time.LocalDate;

public record ExchangeRate(LocalDate date, double rate, Currency from_currency, Currency to_currency) {}
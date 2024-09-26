package software.ulpgc.model;

public class Currency {
    private final String name;
    private final String symbol;
    private final Country country;
    private final String code;

    public Currency(String name, String symbol, Country country, String code) {
        this.name = name;
        this.symbol = symbol;
        this.country = country;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public Country getCountry() {
        return country;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", country=" + country +
                ", code='" + code + '\'' +
                '}';
    }
}

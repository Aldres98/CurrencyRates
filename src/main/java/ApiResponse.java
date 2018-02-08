/**
 * Created by Aldres on 08.02.2018.
 */
public class ApiResponse {

    private String base;
    private RateObject rates;

    public String getBase() {
        return base;
    }

    public ApiResponse(String currencyFrom, String currencyTo) {
    }

    public void setBase(String base) {
        this.base = base;
    }

    public RateObject getRates() {
        return rates;
    }

    public void setRates(RateObject rates) {
        this.rates = rates;
    }
}


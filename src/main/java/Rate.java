import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Aldres on 08.02.2018.
 */
public class Rate {
    private String[] listOfCurrencies =
            {"AUD", "GBP", "KRW", "SEK",
                    "BGN", "HKD", "MXN", "SGD",
                    "BRL", "HRK", "MYR", "THB",
                    "CAD", "HUF", "NOK", "TRY",
                    "CHF", "IDR", "NZD", "USD",
                    "CNY", "ILS", "PHP", "ZAR",
                    "CZK", "INR", "PLN", "EUR",
                    "DKK", "JPY", "RON", "RUB"};

    private double currencyRate;
    private String currencyFrom;
    private String currencyTo;

    public void start() {
        inputData();
        getResult();

    }

    private void inputData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isValid = false;

        do {
            try {
                System.out.println("Enter from currency: ");
                currencyFrom = reader.readLine().toUpperCase();
                System.out.println("Enter to currency: ");
                currencyTo = reader.readLine().toUpperCase();
            } catch (IOException e) {
                System.out.print("IOException: " + e.getMessage());
            }

            if (isValid()) {
                isValid = true;
            } else {
                System.out.println("There's no such currency found");
                System.out.println("Try again! \n");
            }
        } while (!isValid);
    }

    private void getResult() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future future = service.submit(new Runnable() {

            @Override
            public void run() {
                currencyRate = new RateCache().getCurrencyRates(currencyFrom, currencyTo);

            }
        });
        while (!future.isDone()) {
            System.out.print(".");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                System.err.println("InterruptedException");
            }
        }
        System.out.format("%n%s -> %s : %.2f%n", currencyFrom, currencyTo, currencyRate);
        service.shutdown();
    }

    private Boolean isValid() {
        int count = 0;
        if (currencyFrom.equals(currencyTo)) {
            return false;
        }

        for (String currency : listOfCurrencies) {
            if (currency.equals(currencyFrom) || currency.equals(currencyTo)) {
                count++;
            }
        }
        return count == 2;
    }

}

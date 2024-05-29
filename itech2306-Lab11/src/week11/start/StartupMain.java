package week11.start;

import java.util.ArrayList;
import week11.domain.Country;
import week11.ui.ParcelFrame;

public class StartupMain {
    public static void main(String[] args) {
        // Set up data about countries we can ship parcels to, stating the base cost.
        ArrayList<Country> shippingInfo = new ArrayList<>();
        shippingInfo.add(new Country("Australia", 5.00));
        shippingInfo.add(new Country("New Zealand", 7.50));
        shippingInfo.add(new Country("Indonesia", 8.25));
        shippingInfo.add(new Country("Malaysia", 9.30));
        shippingInfo.add(new Country("India", 10.00));
        shippingInfo.add(new Country("China", 10.00));
        shippingInfo.add(new Country("Nepal", 11.45));
        shippingInfo.add(new Country("Sri Lanka", 8.75));
        shippingInfo.add(new Country("USA", 13.85));
        shippingInfo.add(new Country("Brazil", 12.70));
        shippingInfo.add(new Country("Chile", 13.15));
        shippingInfo.add(new Country("UK", 12.50));

        // Create and show the main frame
        new ParcelFrame(shippingInfo);
    }
}
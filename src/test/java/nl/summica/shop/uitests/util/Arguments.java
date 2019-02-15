package nl.summica.shop.uitests.util;

public interface Arguments {

    String SERVER_URL = "https://localhost:9002";

    interface Site {
        String TEST = "/?site=test";
        String SHOP = "/?site=shop";
        String BREEMES = "/?site=breemes";
        String ELEKTRES = "/?site=elektres";
        String ESELEKTRO = "/?site=eselektro";

        String[] ALL_SITES = {TEST, SHOP, BREEMES, ELEKTRES, ESELEKTRO};
    }

    interface User {
        String LOGIN = "shop.customer.1@foo.bar";
        String PASSWORD = "1234";
    }
}

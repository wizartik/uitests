package nl.summica.shop.uitests;

import nl.summica.shop.uitests.pageObjects.LoginLogoutPage;
import nl.summica.shop.uitests.pageObjects.UserPage;
import nl.summica.shop.uitests.util.ShopParametrizedTest;
import nl.summica.shop.uitests.util.JsErrorsChecker;
import nl.summica.shop.uitests.util.selenium.TestedPageContext;

public class LoginLogoutTest {

    @ShopParametrizedTest
    void LoginLogoutTest(TestedPageContext ctx) {
        ctx.openRootPage();
        LoginLogoutPage loginPage = new LoginLogoutPage();
        loginPage.goToLoginPage();
        loginPage.login(ctx.getUserLogin(), ctx.getUserPassword());
        loginPage.checkLoggedIn();

        UserPage userPage = new UserPage();
        userPage.goToPersonalDetailsPage();
        userPage.goToEmailPage();

        loginPage.logout();
        loginPage.checkLoggedOut();

        JsErrorsChecker.checkJSErrors();
    }
}

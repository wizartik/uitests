package nl.summica.shop.uitests.pageObjects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Selenide.$;

public class LoginLogoutPage {

    private SelenideElement loginInput;
    private SelenideElement passwordInput;
    private SelenideElement submitButton;

    public LoginLogoutPage() {
        this.loginInput = $("#j_username");
        this.passwordInput = $("#j_password");
        this.submitButton = $("#loginForm [type=submit]");
    }

    public SelenideElement getLoginInput() {
        return loginInput;
    }

    public void setLoginInput(SelenideElement loginInput) {
        this.loginInput = loginInput;
    }

    public SelenideElement getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(SelenideElement passwordInput) {
        this.passwordInput = passwordInput;
    }

    public SelenideElement getSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(SelenideElement submitButton) {
        this.submitButton = submitButton;
    }

    public void login(String login, String password) {
        loginInput.setValue(login);
        passwordInput.setValue(password);
        submitButton.click();
    }

    public void checkLoggedIn() {
        $(".logged_in.js-logged_in").should(exist);
    }

    public void checkLoggedOut() {
        $(".logged_in.js-logged_in").should(not(exist));
    }

    public void goToLoginPage(){
        $(By.linkText("Sign in")).click();
    }

    public void logout() {
        $(By.linkText("Sign Out")).click();
    }
}

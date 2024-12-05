package tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginRegPage;

import java.util.Random;

public class RegistrationTests extends ApplicationManager {

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginRegPage(getDriver()).typeRegistrationForm("frodo_baggins_" + i + "@gmail.com",
                "Password123!");
        Assert.assertTrue(new ContactsPage(getDriver()).isSignOutPresent());


    }
    @Test
    public void registrationNegativeTest_wrongPassword() {
        int i = new Random().nextInt(1000);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginRegPage(getDriver()).typeRegistrationForm("frodo_baggins_" + i + "@gmail.com",
                "Password123");
        new LoginRegPage(getDriver()).closeAlert();
        Assert.assertTrue(new LoginRegPage(getDriver()).validateErrorMessageLogin("Registration failed"));

    }
    @Test
    public void registrationNegativeTest_wrongEmail() {
        int i = new Random().nextInt(1000);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginRegPage(getDriver()).typeRegistrationForm("frodo_baggins_" + i + "@gmailcom",
                "Password123!");
        new LoginRegPage(getDriver()).closeAlert();
        Assert.assertTrue(new LoginRegPage(getDriver()).validateErrorMessageLogin("Registration failed"));

    }
}



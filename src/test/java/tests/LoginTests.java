package tests;

import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginRegPage;

import java.util.Random;

public class LoginTests extends ApplicationManager {

    private String email, password;

    @BeforeMethod
    public void registration(){
        int i = new Random().nextInt(1000);
        email = "frodo_baggins_" + i + "@gmail.com";
        password = "Password123!";
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginRegPage(getDriver()).typeRegistrationForm(email, password);
        new ContactsPage(getDriver()).clickBtnSignOut();


    }
    @Test
    public void loginPositiveTest(){
        UserDto user = new UserDto(email, password);
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginRegPage(getDriver()).typeLoginForm(user);
        Assert.assertTrue(new ContactsPage(getDriver()).isSignOutPresent());





    }
}
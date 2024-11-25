package tests;

import manager.ApplicationManager;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginRegPage;

public class RegistrationTests extends ApplicationManager {
    @Test
    public void registrationPositiveTest(){

        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginRegPage(getDriver()).typeLoginForm("email", "password");




    }

}

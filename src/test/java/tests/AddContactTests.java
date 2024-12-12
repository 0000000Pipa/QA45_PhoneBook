package tests;

import dto.ContactDtoLombok;
import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.AddPage;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginRegPage;

public class AddContactTests extends ApplicationManager {

    SoftAssert softAssert = new SoftAssert();
    UserDto user = new UserDto("qa_mail@mail.com", "Qwerty123!");
    AddPage addPage;


    @BeforeMethod
    public void login(){
        new HomePage(getDriver()).clickBtnLoginHeader();
        new LoginRegPage(getDriver()).typeLoginForm(user);
        new ContactsPage(getDriver()).clickBtnAdd();
        AddPage addPage = new AddPage(getDriver());
    }

    @Test(invocationCount = 1)
    public void addNewContactPositiveTest(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("Name")
                .lastName("Last name")
                .phone("1234567890")
                .email("lastname@gmail.com")
                .address("address st 1")
                .description("description")
                .build();
        addPage.typeContactForm(contact);
        Assert.assertTrue(new ContactsPage(getDriver()).validateLastElementContactList(contact));
    }

    @Test
    public void addNewContactNegativeTest_emptyName(){
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("")
                .lastName("Last name")
                .phone("1234567890")
                .email("lastname@gmail.com")
                .address("address st 1")
                .description("description")
                .build();
        addPage.typeContactForm(contact);
       Assert.assertFalse(addPage.validateUrlContacts());
    }

    @Test
    public void addNewContactNegativeTest_wrongPhone() {
        ContactDtoLombok contact = ContactDtoLombok.builder()
                .name("name 123")
                .lastName("Last name")
                .phone("1qwerrtty")
                .email("lastname@gmail.com")
                .address("address st 1")
                .description("description")
                .build();
        addPage.typeContactForm(contact);
        String message = addPage.closeAlertAndReturnText();
        System.out.println(message);
        softAssert.assertTrue(message.contains("Phone number must contain only digits! And length min 10, max 15!"));
        System.out.println("code after assert");
        softAssert.assertFalse(addPage.validateUrlContacts());
        softAssert.assertAll();
    }



}
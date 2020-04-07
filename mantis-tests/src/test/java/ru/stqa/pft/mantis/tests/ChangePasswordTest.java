package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import java.util.List;

public class ChangePasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void changePassword() {
        String admin = "administrator";
        String passwordAdmin = "root";
        app.changePassword().login(admin, passwordAdmin);
        app.changePassword().manageUser();
        List<UserData> list = app.changePassword().getUsersListWithoutAdmin();
        String email = list.iterator().next().getEmail();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 6000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.changePassword().finish(confirmationLink, "test");
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}

package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.mode.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.mode.DataHelper.cleanDataBase;


public class LoginTest {

    @Test
    void shouldLogInAndSelectCode() {
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        loginPage.validLogin(DataHelper.getAuthInfo());
        val verificationPage = new VerificationPage();
        verificationPage.validVerify(DataHelper.getVerificationCode(DataHelper.getAuthInfo()));
        val dashboardPage = new DashboardPage();
        dashboardPage.dashboardPage();
        DataHelper.cleanDataBase();
    }
}

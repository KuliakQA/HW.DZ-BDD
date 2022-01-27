package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DataHelper.*;

public class MoneyTransferTest {
    DashboardPage dashboardPage;

    @BeforeEach
    void setUpAll() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyCardNr1() {
        var card1BalanceStart = dashboardPage.getCard1Balance();
        var card2BalanceStart = dashboardPage.getCard2Balance();
        int amount = 10_000;

        var transfer = dashboardPage.card1Button();
        transfer.transferFromCardToCard(amount, getCard2Number());
        var card1BalanceResult = card1BalanceStart + amount;
        var card2BalanceResult = card2BalanceStart - amount;

        assertEquals(card1BalanceResult, dashboardPage.getCard1Balance());
        assertEquals(card2BalanceResult, dashboardPage.getCard2Balance());
    }

    @Test
    void shouldTransferMoneyCardNr2() {
        var card1BalanceStart = dashboardPage.getCard1Balance();
        var card2BalanceStart = dashboardPage.getCard2Balance();
        int amount = 20_000;

        var transfer = dashboardPage.card2Button();
        transfer.transferFromCardToCard(amount, getCard1Number());
        var card1BalanceResult = card1BalanceStart - amount;
        var card2BalanceResult = card2BalanceStart + amount;

        assertEquals(card1BalanceResult, dashboardPage.getCard1Balance());
        assertEquals(card2BalanceResult, dashboardPage.getCard2Balance());
    }

}
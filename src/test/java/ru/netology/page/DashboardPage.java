package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement card1 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement card2 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    private ElementsCollection cards = $$(".list__item");
    private String balanceStart = "баланс: ";
    private String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public TransferPage card1Button() {
        card1.click();
        return new TransferPage();
    }

    public TransferPage card2Button() {
        card2.click();
        return new TransferPage();
    }

    public int getCard1Balance() {
        var balance = cards.first().text();
        return extractBalance(balance);
    }

    public int getCard2Balance() {
        var balance = cards.last().text();
        return extractBalance(balance);
    }

    public int extractBalance(String balance) {
        var start = balance.indexOf(balanceStart);
        var finish = balance.indexOf(balanceFinish);
        var value = balance.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}

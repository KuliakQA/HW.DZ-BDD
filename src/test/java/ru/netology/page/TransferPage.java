package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement titleText = $(byText("Пополнение карты"));
    private SelenideElement sum = $("[data-test-id=amount] input");
    private SelenideElement fromWhichCard = $("[data-test-id='from'] input");
    private SelenideElement replenishButton = $("[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");


    public void transferFromCardToCard(int amount, DataHelper.CardInfo from) {
        sum.setValue(String.valueOf(amount));
        fromWhichCard.setValue(String.valueOf(from));
        replenishButton.click();
        new DashboardPage();
    }

    public void getErrorLimit() {
        $(byText("Сумма превышает допустимый лимит!"))
                .shouldBe(Condition.visible);
    }
}


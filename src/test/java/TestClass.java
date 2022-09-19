import bankProducts.BankProduct;
import bankProducts.Currency;
import bankProducts.TypeOfBalanceChange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestClass {

    BankProduct debitCard = new BankProduct.BankProductBuilder(Currency.USD, 1000, "DebitCard")
            .build();

    BankProduct creditCard = new BankProduct.BankProductBuilder(Currency.RUB, 1000, "CreditCard")
            .build();

    BankProduct deposit = new BankProduct.BankProductBuilder(Currency.EUR, 100000, "Deposit")
            .build();

    @Test
    public void createDebitCardTest() {
        BankProduct debitCard = new BankProduct.BankProductBuilder(Currency.USD, 1000, "FirstDebitCard")
                .build();
        Assertions.assertEquals(Currency.USD, debitCard.getCurrency(), "Currency should be USD");
        Assertions.assertEquals(1000, debitCard.requestBalance(), "Balance should be 1000");
        Assertions.assertEquals("FirstDebitCard", debitCard.getProductName(), "Card name should be 'FirstDebitCard'");
    }

    @Test
    public void createCreditCardTest() {

        BankProduct creditCard = new BankProduct.BankProductBuilder(Currency.RUB, 100000, "FirstCreditCard")
                .build();
                creditCard.setInterestRate(25.5);
                creditCard.setDebt(creditCard.requestBalance());
        Assertions.assertEquals(Currency.RUB, creditCard.getCurrency(), "Currency should be RUB");
        Assertions.assertEquals(100000, creditCard.requestBalance(), "Balance should be 100000");
        Assertions.assertEquals("FirstCreditCard", creditCard.getProductName(), "Card name should be 'FirstCreditCard'");
        Assertions.assertEquals(25.5 , creditCard.getInterestRate(), "Interest rate should be 25");
        Assertions.assertEquals(100000 , creditCard.getDebt(), "Interest rate should be 100000");
    }

    @Test
    public void createDepositTest() {
        BankProduct deposit = new BankProduct.BankProductBuilder(Currency.EUR, 100000, "FirstDeposit")
                .build();
        Assertions.assertEquals(Currency.EUR, deposit.getCurrency(), "Currency should be EUR");
        Assertions.assertEquals(100000, deposit.requestBalance(), "Balance should be 100000");
        Assertions.assertEquals("FirstDeposit", deposit.getProductName(), "Card name should be 'FirstDeposit'");
    }

    @Test
    public void withdrawMoneySameCurrencyTest() {
        Assertions.assertNotEquals(debitCard.getBalance(), debitCard.changeBalance(TypeOfBalanceChange.WITHDRAW_MONEY, Currency.USD, 100));
    }

    @Test
    public void withdrawMoneyOtherCurrencyTest() {
        Assertions.assertNotEquals(debitCard.getBalance(), debitCard.changeBalance(TypeOfBalanceChange.WITHDRAW_MONEY, Currency.EUR, 100));
    }

    @Test
    public void depositMoneySameCurrencyTest() {
        Assertions.assertNotEquals(debitCard.getBalance(), debitCard.changeBalance(TypeOfBalanceChange.DEPOSIT_MONEY, Currency.USD, 100));
    }

    @Test
    public void depositMoneyOtherCurrencyTest() {
        Assertions.assertNotEquals(debitCard.getBalance(), debitCard.changeBalance(TypeOfBalanceChange.DEPOSIT_MONEY, Currency.EUR, 100));
    }

    @Test
    public void depositMoneySameCurrencyToDepositTest() {
        Assertions.assertNotEquals(deposit.getBalance(), deposit.changeBalance(TypeOfBalanceChange.DEPOSIT_MONEY, Currency.USD, 100));
    }

    @Test
    public void depositMoneyOtherCurrencyToDepositTest() {
        Assertions.assertNotEquals(deposit.getBalance(), deposit.changeBalance(TypeOfBalanceChange.DEPOSIT_MONEY, Currency.EUR, 100));
    }


    @Test
    public void requestCardBalanceTest() {
        Assertions.assertEquals(debitCard.getBalance(), debitCard.requestBalance());
    }

    @Test
    public void requestDepositBalanceTest() {
        Assertions.assertEquals(deposit.getBalance(), deposit.requestBalance());
    }

    @Test
    public void requestDebtTest() {
        creditCard.setDebt(creditCard.requestBalance());
        creditCard.requestDebt();
        creditCard.changeBalance(TypeOfBalanceChange.WITHDRAW_MONEY, Currency.RUB, 100);
        creditCard.requestDebt();
    }

    @Test
    public void closeDepositTest() {
        deposit.closeDeposit(deposit);
    }
}

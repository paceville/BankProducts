package bankProducts;

public interface Changeable {

    long changeBalance(TypeOfBalanceChange typeOfBalanceChange, Currency currency, long amountOfMoney);
}

package bankProducts;

public class BankProduct implements Changeable, RequestBalance, CloseDeposit, RequestDebt {
    public Currency currency;
   // public BigDecimal balance;
    public long balance;
    public String productName;

    //only for credit card
    public double interestRate;
    public long debt;

    public Currency getCurrency() {
        return currency;
    }

    public long getBalance() {
        return balance;
    }

    public String getProductName() {
        return productName;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public long getDebt() {
        return debt;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public void setDebt(long debt) {
        this.debt = debt;
    }

    public BankProduct(Currency currency, long balance, String cardName) {
        this.currency = currency;
        this.balance = balance;
        this.productName = cardName;
    }

    public static class BankProductBuilder {
        private Currency currency;
        private long balance;
        private String cardName;
        double interestRate;

        public BankProductBuilder(Currency currency, long balance, String cardName) {
            this.currency = currency;
            this.balance = balance;
            this.cardName = cardName;
        }

//        public BankProductBuilder interestRate(BankProductBuilder bankProductBuilder, double interestRate) {
//            this.interestRate = interestRate;
//            return this;
//        }

        public BankProduct build() {
            return new BankProduct(this.currency, this.balance, this.cardName);
        }
    }

    @Override
    public long changeBalance(TypeOfBalanceChange typeOfBalanceChange, Currency currency, long amountOfMoney) {

        if (!currency.equals(this.currency)) {
            System.out.println("Attention! You deposit money in a currency other than your account currency. " +
                    "The deposited amount will be converted into the currency of your account at the bank's current exchange rate.");

            //following conversion is just for example
            if (this.currency.equals(Currency.EUR) && (currency.equals(Currency.USD))) {
                amountOfMoney -= 10;
            } else if (this.currency.equals(Currency.USD) && (currency.equals(Currency.EUR))) {
                amountOfMoney += 10;
            } else if (this.currency.equals(Currency.USD) && (currency.equals(Currency.RUB))) {
                amountOfMoney *= 5;
            } else if (this.currency.equals(Currency.RUB) && (currency.equals(Currency.USD))) {
                amountOfMoney /= 5;
            } else if (this.currency.equals(Currency.EUR) && (currency.equals(Currency.RUB))) {
                amountOfMoney *= 6;
            } else if (this.currency.equals(Currency.RUB) && (currency.equals(Currency.EUR))) {
                amountOfMoney /= 6;
            }
        }
        if (typeOfBalanceChange.equals(TypeOfBalanceChange.DEPOSIT_MONEY)) {
            this.balance += amountOfMoney;
            System.out.println("Success! You deposited " + amountOfMoney + " into your account. Your current balance is: " + requestBalance());
        } else if (typeOfBalanceChange.equals(TypeOfBalanceChange.WITHDRAW_MONEY)) {
            if (amountOfMoney > this.balance) {
                System.out.println("Oops! You don't enough money in your account. Your current balance is: " + this.balance);
            } else {
                this.balance -= amountOfMoney;
                System.out.println("Success! You withdrew " + amountOfMoney + " from your account. Your current balance is: " + this.balance);
            }
        }
        return this.balance;
    }

    @Override
    public long requestBalance() {
        System.out.println("Your current balance is: " + this.balance);
        return this.balance;
    }

    @Override
    public void closeDeposit(BankProduct deposit) {
        deposit = null;
        System.gc();
        System.out.println("Your deposit is successfully closed");
    }

    @Override
    public void requestDebt() {
        System.out.println("Your current debt is: " + this.debt);
    }

    /**
     * this method hepls to check that required parameters are not empty
     * @return
     */
    public boolean doQualityCheck() {
        return (currency != null && productName != null);
    }
}

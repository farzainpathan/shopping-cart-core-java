package shoppingcart;

import java.math.BigDecimal;
import java.util.Objects;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static shoppingcart.Constants.PRECISION;

public class Price {

  private final BigDecimal unitPrice;
  private final Tax tax;

  public Price(BigDecimal unitPrice, Tax tax) {
    this.unitPrice = unitPrice;
    this.tax = tax;
  }

  public BigDecimal getPriceWithTax() {
    BigDecimal taxAmount = getTaxAmountOnUnitPrice();
    BigDecimal priceWithTax = unitPrice.add(taxAmount);
    return priceWithTax.setScale(PRECISION, ROUND_HALF_UP);
  }

  BigDecimal getUnitPrice() {
    return unitPrice;
  }

  BigDecimal getTaxAmountOnUnitPrice() {
    return unitPrice.multiply(tax.getTaxPercentage()).divide(new BigDecimal("100"));
  }

  @Override
  public int hashCode() {
    return Objects.hash(unitPrice, tax);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Price price = (Price) o;
    return unitPrice.equals(price.unitPrice) && tax.equals(price.tax);
  }
}

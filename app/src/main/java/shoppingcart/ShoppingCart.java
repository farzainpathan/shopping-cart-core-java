package shoppingcart;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import static java.math.BigDecimal.ROUND_HALF_UP;
import static shoppingcart.Constants.PRECISION;

public class ShoppingCart {

  private final List<ShoppingCartItem> shoppingCartItems;
  private BigDecimal totalPrice;
  private BigDecimal totalTaxAmount;

  public ShoppingCart() {
    this.shoppingCartItems = new LinkedList<>();
    this.totalPrice = new BigDecimal("0.0");
  }

  public void addProduct(Product product, int quantity) {
    for (ShoppingCartItem item : shoppingCartItems) {
      if (item.isSameProduct(product)) {
        item.increaseQuantityBy(quantity);
        calculateTotalPrice();
        return;
      }
    }
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, quantity);
    shoppingCartItems.add(shoppingCartItem);
    calculateTotalPrice();
  }

  private void calculateTotalPrice() {
    this.totalPrice = new BigDecimal("0.0");
    this.totalTaxAmount = new BigDecimal("0.0");

    for (ShoppingCartItem item : shoppingCartItems) {
      this.totalTaxAmount = totalTaxAmount.add(item.getTaxAmountOnItem());

      BigDecimal priceWithTax = item.getItemPriceWithTax();
      totalPrice = totalPrice.add(priceWithTax);
    }
    this.totalTaxAmount = roundOffUpToTwoDecimalPlaces(this.totalTaxAmount);
    this.totalPrice = roundOffUpToTwoDecimalPlaces(totalPrice);
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public BigDecimal getTotalTaxOnCart() {
    return this.totalTaxAmount;
  }

  private BigDecimal roundOffUpToTwoDecimalPlaces(BigDecimal amount) {
    return amount.setScale(PRECISION, ROUND_HALF_UP);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ShoppingCart that = (ShoppingCart) o;
    return shoppingCartItems.equals(that.shoppingCartItems) && totalPrice.equals(that.totalPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(shoppingCartItems, totalPrice);
  }
}

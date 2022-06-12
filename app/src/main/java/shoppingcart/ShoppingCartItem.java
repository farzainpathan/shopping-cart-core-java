package shoppingcart;

import java.math.BigDecimal;
import java.util.Objects;

public class ShoppingCartItem {

  private final Product product;
  private Integer quantity;

  public ShoppingCartItem(Product product, Integer quantity) {
    this.product = product;
    this.quantity = quantity;
  }

  BigDecimal getItemPriceWithTax() {
    return this.product.getProductPriceWithTax().multiply(new BigDecimal(this.quantity));
  }

  BigDecimal getTaxAmountOnItem() {
    return this.product.getTaxAmountOnProduct().multiply(new BigDecimal(this.quantity));
  }

  boolean isSameProduct(Product product) {
    return this.product.equals(product);
  }

  void increaseQuantityBy(Integer quantity) {
    this.quantity = this.quantity + quantity;
  }

  @Override
  public int hashCode() {
    return Objects.hash(product, quantity);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ShoppingCartItem that = (ShoppingCartItem) o;
    return product.equals(that.product) && quantity.equals(that.quantity);
  }
}

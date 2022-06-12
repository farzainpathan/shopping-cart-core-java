package shoppingcart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static shoppingcart.Constants.DOVE_SOAP;
import static shoppingcart.Constants.SALES;

class ShoppingCartItemTest {

  @Test
  @Order(1)
  @DisplayName("testEquals")
  void test1() {
    // Given
    BigDecimal salesPercentage = new BigDecimal("12.5");
    Tax salesTax = new Tax(SALES, salesPercentage);
    BigDecimal unitPrice = new BigDecimal("39.99");
    Price price = new Price(unitPrice, salesTax);
    Product product = new Product("Dove Soap", price, DOVE_SOAP);
    ShoppingCartItem actualShoppingCartItem = new ShoppingCartItem(product, 2);
    ShoppingCartItem expectedShoppingCartItem = new ShoppingCartItem(product, 2);
    // When
    boolean result = actualShoppingCartItem.equals(expectedShoppingCartItem);
    // Then
    assertThat(result).isTrue();
  }

  @Test
  @Order(2)
  @DisplayName("getItemPriceWithTax")
  void test2() {
    // Given
    BigDecimal salesPercentage = new BigDecimal("12.5");
    Tax salesTax = new Tax(SALES, salesPercentage);
    BigDecimal unitPrice = new BigDecimal("39.99");
    Price price = new Price(unitPrice, salesTax);
    Product product = new Product("Dove Soap", price, DOVE_SOAP);
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 2);
    // When
    BigDecimal itemPriceWithTax = shoppingCartItem.getItemPriceWithTax();
    // Then
    assertThat(itemPriceWithTax).isEqualTo("89.98");
  }

  @Test
  @Order(3)
  @DisplayName("getTaxAmountOnItem")
  void test3() {
    // Given
    BigDecimal salesPercentage = new BigDecimal("12.5");
    Tax salesTax = new Tax(SALES, salesPercentage);
    BigDecimal unitPrice = new BigDecimal("39.99");
    Price price = new Price(unitPrice, salesTax);
    Product product = new Product("Dove Soap", price, DOVE_SOAP);
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 2);
    // When
    BigDecimal taxAmountOnItem = shoppingCartItem.getTaxAmountOnItem();
    // Then
    assertThat(taxAmountOnItem).isEqualTo("9.99750");
  }

  @Test
  @Order(4)
  @DisplayName("isSameProduct")
  void test4() {
    // Given
    BigDecimal salesPercentage = new BigDecimal("12.5");
    Tax salesTax = new Tax(SALES, salesPercentage);
    BigDecimal unitPrice = new BigDecimal("39.99");
    Price price = new Price(unitPrice, salesTax);
    Product product1 = new Product("Dove Soap", price, DOVE_SOAP);
    Product product2 = new Product("Dove Soap", price, DOVE_SOAP);
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product1, 2);
    // When
    boolean result = shoppingCartItem.isSameProduct(product2);
    // Then
    assertThat(result).isTrue();
  }

  @Test
  @Order(5)
  @DisplayName("increaseQuantityBy")
  void test5() {
    // Given
    BigDecimal salesPercentage = new BigDecimal("12.5");
    Tax salesTax = new Tax(SALES, salesPercentage);
    BigDecimal unitPrice = new BigDecimal("39.99");
    Price price = new Price(unitPrice, salesTax);
    Product product = new Product("Dove Soap", price, DOVE_SOAP);
    ShoppingCartItem shoppingCartItem = new ShoppingCartItem(product, 2);
    // When
    BigDecimal beforeIncreasingQuantity = shoppingCartItem.getItemPriceWithTax();
    shoppingCartItem.increaseQuantityBy(10);
    BigDecimal afterIncreasingQuantity = shoppingCartItem.getItemPriceWithTax();
    // Then
    assertThat(beforeIncreasingQuantity).isEqualTo("89.98");
    assertThat(afterIncreasingQuantity).isEqualTo("539.88");
  }
}

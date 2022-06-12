package shoppingcart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static shoppingcart.Constants.*;

public class ShoppingCartTest {

  @Test
  @Order(1)
  @DisplayName("Should add 5 soaps to the cart")
  void test1() {
    // Given
    Product doveSoap = getDoveSoapProductWithTax();
    ShoppingCart shoppingCart = new ShoppingCart();
    ShoppingCart expectedShoppingCartWithFiveDove =
        getExpectedShoppingCartWithGivenQuantityAndProduct(5);
    // When
    shoppingCart.addProduct(doveSoap, 5);
    // Then
    assertThat(shoppingCart).isEqualTo(expectedShoppingCartWithFiveDove);
    assertThat(shoppingCart.getTotalPrice()).isEqualTo("224.95");
  }

  @Test
  @Order(2)
  @DisplayName("Should add 8 soaps to the cart")
  void test2() {
    // Given
    Product doveSoap = getDoveSoapProductWithTax();
    ShoppingCart shoppingCart = new ShoppingCart();
    ShoppingCart expectedShoppingCartWithFiveDove =
        getExpectedShoppingCartWithGivenQuantityAndProduct(8);
    // When
    shoppingCart.addProduct(doveSoap, 5);
    shoppingCart.addProduct(doveSoap, 3);
    // Then
    assertThat(shoppingCart).isEqualTo(expectedShoppingCartWithFiveDove);
    assertThat(shoppingCart.getTotalPrice()).isEqualTo("359.92");
    assertThat(shoppingCart.getTotalTaxOnCart()).isEqualTo("39.99");
  }

  @Test
  @Order(3)
  @DisplayName("Should add 2 dove soaps and 2 axe deo to shopping cart")
  void test3() {
    // Given
    Product doveSoap = getDoveSoapProductWithTax();
    Product axeDeo = getAxeDeoProductWithTax();
    ShoppingCart cartWithTwoDoveAndTwoAxe = new ShoppingCart();
    ShoppingCart expectedCartWithTwoDoveAndTwoAxe = getExpectedCartWithTwoDovesAndTwoAxe();
    // When
    cartWithTwoDoveAndTwoAxe.addProduct(doveSoap, 2);
    cartWithTwoDoveAndTwoAxe.addProduct(axeDeo, 2);
    // Then
    assertThat(cartWithTwoDoveAndTwoAxe).isEqualTo(expectedCartWithTwoDoveAndTwoAxe);
    assertThat(cartWithTwoDoveAndTwoAxe.getTotalPrice()).isEqualTo("314.96");
    assertThat(cartWithTwoDoveAndTwoAxe.getTotalTaxOnCart()).isEqualTo("35.00");
  }

  @Test
  @Order(4)
  @DisplayName("Should check equality of 2 equal carts")
  void test4() {
    // Given
    Product doveSoap = getDoveSoapProductWithTax();
    ShoppingCart shoppingCart = new ShoppingCart();
    ShoppingCart expectedShoppingCart = getExpectedShoppingCartWithGivenQuantityAndProduct(5);
    // When
    shoppingCart.addProduct(doveSoap, 5);
    // Then
    assertThat(shoppingCart.equals(expectedShoppingCart)).isTrue();
  }

  @Test
  @Order(5)
  @DisplayName("Should check total price of a cart")
  void test5() {
    // Given
    Product doveSoap = getDoveSoapProductWithTax();
    ShoppingCart shoppingCart = new ShoppingCart();
    // When
    shoppingCart.addProduct(doveSoap, 5);
    // Then
    assertThat(shoppingCart.getTotalPrice()).isEqualTo("224.95");
  }

  // Helper Functions
  private ShoppingCart getExpectedCartWithTwoDovesAndTwoAxe() {
    ShoppingCart expectedCartWithTwoDove = getExpectedShoppingCartWithGivenQuantityAndProduct(2);
    expectedCartWithTwoDove.addProduct(getAxeDeoProductWithTax(), 2);
    return expectedCartWithTwoDove;
  }

  private ShoppingCart getExpectedShoppingCartWithGivenQuantityAndProduct(int productQuantity) {
    ShoppingCart expectedShoppingCartWithFiveDove = new ShoppingCart();
    expectedShoppingCartWithFiveDove.addProduct(getDoveSoapProduct(getTax()), productQuantity);
    return expectedShoppingCartWithFiveDove;
  }

  private Product getDoveSoapProductWithTax() {
    return getDoveSoapProduct(new Tax(SALES, new BigDecimal(TAX_PERCENTAGE)));
  }

  private Product getAxeDeoProductWithTax() {
    return getAxeDeoProduct(new Tax(SALES, new BigDecimal(TAX_PERCENTAGE)));
  }

  private Product getAxeDeoProduct(Tax tax) {
    return new Product("Axe Deo", new Price(new BigDecimal("99.99"), tax), AXE_DEO);
  }

  private Product getDoveSoapProduct(Tax tax) {
    return new Product("Dove Soap", new Price(new BigDecimal("39.99"), tax), DOVE_SOAP);
  }

  private Tax getTax() {
    return new Tax(SALES, new BigDecimal(TAX_PERCENTAGE));
  }
}

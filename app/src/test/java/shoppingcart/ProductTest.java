package shoppingcart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static shoppingcart.Constants.DOVE_SOAP;
import static shoppingcart.Constants.SALES;

class ProductTest {
  @Test
  @DisplayName("shouldTestEquals")
  void test1() {
    // Given
    BigDecimal salesPercentage = new BigDecimal("12.5");
    Tax salesTax = new Tax(SALES, salesPercentage);
    BigDecimal unitPrice = new BigDecimal("39.99");
    Price price = new Price(unitPrice, salesTax);
    String productName = "Dove Soap";
    Product actualProduct = new Product(productName, price, DOVE_SOAP);
    Product expectedProduct = new Product(productName, price, DOVE_SOAP);
    // When
    boolean result = actualProduct.equals(expectedProduct);
    // Then
    assertThat(result).isTrue();
  }

  @Test
  @DisplayName("shouldGetUnitPrice")
  void test2() {
    // Given
    BigDecimal salesPercentage = new BigDecimal("12.5");
    Tax salesTax = new Tax(SALES, salesPercentage);
    BigDecimal unitPrice = new BigDecimal("39.99");
    Price price = new Price(unitPrice, salesTax);
    String productName = "Dove Soap";
    Product actualProduct = new Product(productName, price, DOVE_SOAP);
    Product expectedProduct = new Product(productName, price, DOVE_SOAP);
    BigDecimal expectedUnitPrice = new BigDecimal("39.99");
    // When
    BigDecimal resultUnitPrice = actualProduct.getUnitPrice();
    // Then
    assertThat(resultUnitPrice).isEqualTo(expectedUnitPrice);
  }
}

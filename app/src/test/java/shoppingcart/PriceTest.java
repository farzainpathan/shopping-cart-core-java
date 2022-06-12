package shoppingcart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static shoppingcart.Constants.SALES;
import static shoppingcart.Constants.TAX_PERCENTAGE;

class PriceTest {

  @Test
  @Order(1)
  @DisplayName("shouldTestEquals")
  void test1() {
    // Given
    BigDecimal salesPercentage = new BigDecimal(TAX_PERCENTAGE);
    Tax salesTax = new Tax(SALES, salesPercentage);
    BigDecimal unitPrice = new BigDecimal("100.00");
    Price price = new Price(unitPrice, salesTax);
    Price expectedPrice = new Price(unitPrice, salesTax);
    // When
    boolean result = price.equals(expectedPrice);
    // Then
    assertThat(result).isTrue();
  }

  @Test
  @Order(2)
  @DisplayName("shouldTestGetPriceWithTax")
  void test2() {
    // Given
    BigDecimal salesPercentage = new BigDecimal(TAX_PERCENTAGE);
    Tax salesTax = new Tax(SALES, salesPercentage);
    BigDecimal unitPrice = new BigDecimal("100.00");
    Price price = new Price(unitPrice, salesTax);
    BigDecimal expectedPrice = new BigDecimal("112.50");
    // When
    BigDecimal priceWithTax = price.getPriceWithTax();
    // Assert
    assertThat(priceWithTax).isEqualTo(expectedPrice);
  }
}

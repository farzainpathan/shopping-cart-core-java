package shoppingcart;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static shoppingcart.Constants.SALES;

class TaxTest {

  @Test
  @Order(1)
  @DisplayName("shouldTesGetTaxPercentage")
  void test1() {
    // Given
    BigDecimal taxPercentage = new BigDecimal("12.5");
    Tax tax = new Tax(SALES, taxPercentage);
    BigDecimal expectedTaxPercentage = new BigDecimal("12.5");
    // When
    BigDecimal percentage = tax.getTaxPercentage();
    // Then
    assertThat(percentage).isEqualTo(expectedTaxPercentage);
  }

  @Test
  @Order(2)
  @DisplayName("shouldTestEquals")
  void test2() {
    // Given
    BigDecimal taxPercentage = new BigDecimal("12.5");
    Tax expectedTest = new Tax(SALES, taxPercentage);
    // When
    Tax tax = new Tax(SALES, taxPercentage);
    // Then
    assertThat(tax).isEqualTo(expectedTest);
  }
}

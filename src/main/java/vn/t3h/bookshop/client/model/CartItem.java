package vn.t3h.bookshop.client.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entity class đại diện cho một item trong giỏ hàng
 */
@Entity
@Table(name = "cart_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    /**
     * Tính tổng tiền của item (đã tính discount)
     */
    public BigDecimal getSubtotal() {
        BigDecimal quantityDecimal = BigDecimal.valueOf(quantity);
        BigDecimal discountDecimal = BigDecimal.valueOf(product.getDiscountValue());
        return unitPrice.multiply(quantityDecimal)
                .multiply(BigDecimal.ONE.subtract(discountDecimal));
    }
}

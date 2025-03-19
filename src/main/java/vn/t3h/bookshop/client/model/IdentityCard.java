package vn.t3h.bookshop.client.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entity class đại diện cho thông tin CMND/CCCD của người dùng
 */
@Entity
@Table(name = "identity_cards")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdentityCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number", nullable = false, unique = true)
    private String cardNumber;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "issue_place")
    private String issuePlace;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}

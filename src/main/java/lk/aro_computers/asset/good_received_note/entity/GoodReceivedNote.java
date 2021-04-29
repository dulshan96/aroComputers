package lk.aro_computers.asset.good_received_note.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.aro_computers.asset.good_received_note.entity.enums.GoodReceivedNoteState;
import lk.aro_computers.asset.ledger.entity.Ledger;
import lk.aro_computers.asset.purchase_order.entity.PurchaseOrder;
import lk.aro_computers.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("GoodReceivedNote")
public class GoodReceivedNote extends AuditEntity {
    private String remark;

    @Column( precision = 10, scale = 2 )
    private BigDecimal totalAmount;

    @Enumerated( EnumType.STRING )
    private GoodReceivedNoteState goodReceivedNoteState;

    @ManyToOne
    private PurchaseOrder purchaseOrder;

    @OneToMany( mappedBy = "goodReceivedNote")
    private List< Ledger > ledgers;


}

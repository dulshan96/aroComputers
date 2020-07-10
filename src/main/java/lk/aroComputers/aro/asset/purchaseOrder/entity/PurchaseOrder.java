package lk.aroComputers.aro.asset.purchaseOrder.entity;

import com.fasterxml.jackson.annotation.JsonFilter;

import lk.aroComputers.aro.asset.purchaseOrder.entity.Enum.PurchaseOrderStatus;
import lk.aroComputers.aro.asset.supplier.entity.Supplier;
import lk.aroComputers.aro.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("PurchaseOrder")
public class PurchaseOrder extends AuditEntity {

    private String remark;

    @Column(nullable = false, unique = true)
    private String code;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus purchaseOrderStatus;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.PERSIST)
    private List<PurchaseOrderItem> purchaseOrderItems;

    @ManyToOne
    private Supplier supplier;

   /* @OneToMany(mappedBy = "purchaseOrder")
    private List<Payment> payments;*/
}

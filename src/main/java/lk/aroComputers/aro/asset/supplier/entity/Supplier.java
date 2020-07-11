package lk.aroComputers.aro.asset.supplier.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import lk.aroComputers.aro.asset.purchaseOrder.entity.PurchaseOrder;
import lk.aroComputers.aro.asset.supplier.entity.Enum.ItemSupplierStatus;
import lk.aroComputers.aro.util.audit.AuditEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("Supplier")
public class Supplier extends AuditEntity {

    @Size(min = 5, message = "Your Company name cannot be accepted")
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @Size(min = 2, message = "Your BRN cannot be accepted")
    private String brn;

    @Size(max = 10, min = 9, message = "Mobile number length should be contained 10 and 9")
    private String contactOne;
    private String contactTwo;

    @Column(unique = true)
    private String email;

    @Column(columnDefinition = "VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NULL", length = 255)
    private String address;

    @OneToMany(mappedBy = "supplier")
    private List<PurchaseOrder> purchaseOrders;

    @OneToMany(mappedBy = "supplier")
    private List<SupplierItem> supplierItems;

    @Enumerated(EnumType.STRING)
    private ItemSupplierStatus itemSupplierStatus;

}
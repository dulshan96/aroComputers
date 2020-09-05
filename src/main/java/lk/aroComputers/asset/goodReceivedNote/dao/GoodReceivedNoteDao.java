package lk.aroComputers.asset.goodReceivedNote.dao;

import lk.aroComputers.asset.PurchaseOrder.entity.PurchaseOrder;
import lk.aroComputers.asset.goodReceivedNote.entity.GoodReceivedNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodReceivedNoteDao extends JpaRepository< GoodReceivedNote, Integer> {
    GoodReceivedNote findByPurchaseOrder(PurchaseOrder purchaseOrder);
}

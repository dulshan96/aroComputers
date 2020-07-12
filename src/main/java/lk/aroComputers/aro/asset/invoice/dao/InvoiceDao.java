package lk.aroComputers.aro.asset.invoice.dao;


import lk.aroComputers.aro.asset.invoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceDao extends JpaRepository<Invoice,Integer> {
}

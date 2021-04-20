package lk.aro_computers.asset.brand.dao;


;
import lk.aro_computers.asset.brand.entity.Brand;
import lk.aro_computers.asset.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandDao extends JpaRepository<Brand, Integer> {
    Brand findByName(String nic);
}

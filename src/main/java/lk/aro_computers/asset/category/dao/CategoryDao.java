package lk.aro_computers.asset.category.dao;

import lk.aro_computers.asset.category.entity.Category;
import lk.aro_computers.asset.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

    Category findByName(String nic);
}

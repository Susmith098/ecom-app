package com.susmith.ecomapp.Repository;


import com.susmith.ecomapp.Entity.Order;
import com.susmith.ecomapp.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    @Query("SELECT SUM(o.totalAmount) FROM Order o")
    Double calculateTotalSalesAmount();

}

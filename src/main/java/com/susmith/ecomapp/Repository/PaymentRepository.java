package com.susmith.ecomapp.Repository;


import com.susmith.ecomapp.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    public Payment findByOrderId(String orderId);
}

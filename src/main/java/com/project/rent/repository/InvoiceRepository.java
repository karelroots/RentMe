package com.project.rent.repository;

import com.project.rent.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    Invoice findInvoiceByPayerId(int id);
    Invoice findInvoiceByItemName(String name);
    Invoice findInvoiceById(int id);
}

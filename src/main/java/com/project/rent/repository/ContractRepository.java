package com.project.rent.repository;

import com.project.rent.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {
    List<Contract> findAllByUserId(int id);
    List<Contract> findAllByOwnerId(int id);
    Contract findByContractID(int id);

}

package com.project.rent.repository;

import com.project.rent.model.ContractOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractOfferRepository extends JpaRepository<ContractOffer, Integer> {
    List<ContractOffer> findAllByUserId(int id);
    List<ContractOffer> findAllByOwnerId(int id);
    ContractOffer findByContractOfferID(int id);
}

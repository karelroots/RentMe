package com.project.rent.service;

import com.project.rent.model.*;
import com.project.rent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service("rentService")
public class RentService {
    private final OfferRepository offerRepository;
    private final WishRepository wishRepository;
    private final UserRepository userRepository;
    private final ContractRepository contractRepository;
    private final ContractOfferRepository contractOfferRepository;
    private final InvoiceRepository invoiceRepository;

    @Autowired
    public RentService(@Autowired OfferRepository offerRepository, @Autowired WishRepository wishRepository,
                       @Autowired UserRepository userRepository, @Autowired ContractRepository contractRepository,
                       @Autowired ContractOfferRepository contractOfferRepository, @Autowired InvoiceRepository invoiceRepository) {
        this.offerRepository = offerRepository;
        this.wishRepository = wishRepository;
        this.userRepository = userRepository;
        this.contractRepository = contractRepository;
        this.contractOfferRepository = contractOfferRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public Offer findOfferById(int id) {
        return offerRepository.findOfferById(id);
    }

    public Wish findWishById(int id) {
        return wishRepository.findWishById(id);
    }

    public Contract findContractById(int id) { return contractRepository.findContractById(id);}

    public ContractOffer findContractOfferById(int id) { return contractOfferRepository.findContractOfferById(id); }

    public Invoice findInvoiceById(int id) { return invoiceRepository.findInvoiceById(id); }

    public void saveOffer(Offer offer) {
        offerRepository.save(offer);
    }

    public void saveWish(Wish wish) {
        wishRepository.save(wish);
    }

    public void saveContract(Contract contract) { contractRepository.save(contract); }

    public void saveContractOffer(ContractOffer offer) { contractOfferRepository.save(offer); }

    public void saveInvoice(Invoice invoice) { invoiceRepository.save(invoice);
    }

    public void removeContract(Contract contract) {
        contractRepository.delete(contract);
    }

    public void removeContractOffer(ContractOffer contractOffer) {
        contractOfferRepository.delete(contractOffer);
    }

    public void removeOffer(Offer offer) {
        offerRepository.delete(offer);
    }

    public void removeWish(Wish wish) {
        wishRepository.delete(wish);
    }

    public List<Offer> getOffers(String query) {
        List<Offer> offers = query != null ? getOffersContaining(query) : offerRepository.findAll();

        for (Offer offer : offers) {
            // lisame pakkumisele kasutaja id-le vastava kasutajanime
            User user = userRepository.findById(offer.getUserId());
            if (user != null) {
                offer.setUserName(user.getUsername());
            }
        }
        return offers;
    }

    public List<Wish> getWishes(String query) {
        List<Wish> wishes = query != null ? getWishesContaining(query) : wishRepository.findAll();

        for (Wish wish : wishes) {
            // lisame soovile kasutaja id-le vastava kasutajanime
            User user = userRepository.findById(wish.getUserId());
            if (user != null) {
                wish.setUserName(user.getUsername());
            }
        }

        return wishes;
    }

    public List<Offer> getUserOffersList(int id) {
        List<Offer> offers = offerRepository.findAll();
        List<Offer> userOffers = new ArrayList<>();

        for(Offer offer:offers) {
            if(offer.getUserId() == id) {
                userOffers.add(offer);
            }
        }

        return userOffers;
    }

    public List<Wish> getUserWishesList(int id) {
        List<Wish> wishes = wishRepository.findAll();
        List<Wish> userWishes = new ArrayList<>();

        for(Wish wish:wishes) {
            if(wish.getUserId() == id) {
                userWishes.add(wish);
            }
        }

        return userWishes;
    }

    public List<Invoice> getUserInvoiceList(int id) {
        List<Invoice> invoices = invoiceRepository.findAll();
        List<Invoice> userInvoices = new ArrayList<>();

        for(Invoice invoice:invoices) {
            if(invoice.getPayerId() == id) {
                userInvoices.add(invoice);
            }
        }
        return userInvoices;
    }

    public List<Contract> getUserOwnerContractList(int id) {
        return contractRepository.findAllByOwnerId(id);
    }

    public List<Contract> getUserRentContractList(int id) {
        return contractRepository.findAllByUserId(id);
    }

    public List<ContractOffer> getUserOwnerContractOfferList(int id) {
        return contractOfferRepository.findAllByOwnerId(id);
    }

    public List<ContractOffer> getUserRentContractOfferList(int id) {
        return contractOfferRepository.findAllByUserId(id);
    }

    private List<Offer> getOffersContaining(String searchQuery) {
        List<Offer> allOffers = offerRepository.findAll();
        return allOffers.stream()
                        .filter(offer -> offer.getItemName().toLowerCase()
                                              .contains(searchQuery.toLowerCase()))
                        .collect(toList());
    }

    private List<Wish> getWishesContaining(String searchQuery) {
        List<Wish> allWishes = wishRepository.findAll();
        return allWishes.stream()
                        .filter(wish -> wish.getItemName().toLowerCase()
                                            .contains(searchQuery.toLowerCase()))
                        .collect(toList());
    }
}

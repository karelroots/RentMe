package com.rent.rent;

import com.project.rent.model.*;
import com.project.rent.repository.*;
import com.project.rent.service.RentService;
import com.project.rent.service.UserService;
import com.rent.rent.utils.TestConsts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RentApplicationTests {

	private RentService rentService;
	private UserService userService;
	@Mock
	private OfferRepository offerRepository;
	@Mock
	private WishRepository wishRepository;
	@Mock
	private UserRepository userRepository;
	@Mock
	private InvoiceRepository invoiceRepository;
	@Mock
	private ContractOfferRepository contractOfferRepository;
	@Mock
	private ContractRepository contractRepository;

	@Before
	public void setUp() {
		rentService = new RentService(offerRepository, wishRepository, userRepository, contractRepository,
									  contractOfferRepository, invoiceRepository);
		userService = new UserService(userRepository, null, null);
	}

	@Test
	// TDD Cycle 1
	public void givenValidSearchQuery_whenPerformingRentOfferSearch_thenOnlyOffersMatchingSearchQueryReturned() {
		// Assign
		String searchQuery = "banana";

		doReturn(TestConsts.ALL_OFFERS).when(offerRepository).findAll();

		// Act
		List<Offer> filteredOffers = rentService.getOffers(searchQuery);

		// Assert
		assertTrue(
				TestConsts.BANANA_OFFERS.stream()
										.allMatch(offer1 ->
														  filteredOffers.stream().anyMatch(
																  offer2 ->
																		  offer1.getItemName()
																				.equals(offer2.getItemName()))));
		assertTrue(
				filteredOffers.stream()
							  .noneMatch(offer1 ->
												 TestConsts.WITHOUT_BANANA_OFFERS.stream().anyMatch(
														 offer2 ->
																 offer1.getItemName()
																	   .equals(offer2.getItemName()))));
	}

	@Test
	// TDD Cycle 2
	public void givenValidSearchQuery_whenPerformingRentWishSearch_thenOnlyOffersMatchingSearchQueryReturned() {
		// Assign
		String searchQuery = "VACUUM";

		doReturn(TestConsts.ALL_WISHES).when(wishRepository).findAll();

		// Act
		List<Wish> filteredWishes = rentService.getWishes(searchQuery);

		// Assert
		assertTrue(
				TestConsts.VACUUM_WISHES.stream()
										.allMatch(wish1 ->
														  filteredWishes.stream().anyMatch(
																  wish2 ->
																		  wish1.getItemName()
																			   .equals(wish2.getItemName()))));
		assertTrue(
				filteredWishes.stream()
							  .noneMatch(wish1 ->
												 TestConsts.WITHOUT_VACUUM_WISHES.stream().anyMatch(
														 wish2 -> wish1.getItemName()
																	   .equals(wish2.getItemName()))));
	}

	@Test
	// TDD Cycle 3
	public void givenValidSearchQuery_whenGetUsersCalled_thenOnlyUsersMatchingSearchQueryReturned() {
		// Assign
		String searchQuery = "test@rent.me";

		doReturn(TestConsts.ALL_USERS).when(userRepository).findAll();

		// Act
		List<User> users = userService.getUsers(searchQuery);

		// Assert
		assertTrue(
				TestConsts.FILTERED_USERS.stream()
										 .allMatch(user1 ->
														   users.stream().anyMatch(
																   user2 ->
																		   user1.getEmail()
																				.equals(user2.getEmail()))));
		assertTrue(
				users.stream()
					 .noneMatch(user1 ->
										TestConsts.OTHER_USERS.stream().anyMatch(
												user2 -> user1.getEmail()
															  .equals(user2.getEmail()))));
	}

	@Test
	// Coverage test 1
	public void givenValidId_whenFindOfferByIdCalled_thenRepositoryCalledOnce() {
		// Assign
		doReturn(TestConsts.BANANA1).when(offerRepository).findOfferById(TestConsts.BANANA1_ID);
		// Act
		Offer offer = rentService.findOfferById(TestConsts.BANANA1_ID);
		// Assert
		verify(offerRepository, times(1)).findOfferById(anyInt());
		verifyNoMoreInteractions(offerRepository);
		assertEquals(TestConsts.BANANA1, offer);
	}

	@Test
	// Coverage test 2
	public void givenValidId_whenFindWishByIdCalled_thenRepositoryCalledOnce() {
		// Assign
		doReturn(TestConsts.VACUUM2).when(wishRepository).findWishById(TestConsts.VACUUM2_ID);
		// Act
		Wish wish = rentService.findWishById(TestConsts.VACUUM2_ID);
		// Assert
		verify(wishRepository, times(1)).findWishById(anyInt());
		verifyNoMoreInteractions(wishRepository);
		assertEquals(TestConsts.VACUUM2, wish);
	}

	@Test
	// Coverage test 3
	public void givenValidId_whenFindContractByIdCalled_thenRepositoryCalledOnce() {
		// Assign
		doReturn(TestConsts.EXAMPLE_CONTRACT).when(contractRepository).findContractById(TestConsts.CONTRACT_ID);
		// Act
		Contract contract = rentService.findContractById(TestConsts.CONTRACT_ID);
		// Assert
		verify(contractRepository, times(1)).findContractById(anyInt());
		verifyNoMoreInteractions(contractRepository);
		assertEquals(TestConsts.EXAMPLE_CONTRACT, contract);
	}

	@Test
	// Coverage test 4
	public void givenValidId_whenFindContractOfferById_thenRepositoryCalledOnce() {
		// Assign
		doReturn(TestConsts.EXAMPLE_CONTRACT_OFFER).when(contractOfferRepository)
												   .findContractOfferById(TestConsts.CONTRACT_OFFER_ID);
		// Act
		ContractOffer contractOffer = rentService.findContractOfferById(TestConsts.CONTRACT_OFFER_ID);
		// Assert
		verify(contractOfferRepository, times(1)).findContractOfferById(anyInt());
		verifyNoMoreInteractions(contractOfferRepository);
		assertEquals(TestConsts.EXAMPLE_CONTRACT_OFFER, contractOffer);
	}

	@Test
	// Coverage test 5
	public void givenValidId_whenFindInvoiceByIdCalled_thenRepositoryCalledOnce() {
		// Assign
		doReturn(TestConsts.EXAMPLE_INVOICE).when(invoiceRepository).findInvoiceById(TestConsts.INVOICE_ID);
		// Act
		Invoice invoice = rentService.findInvoiceById(TestConsts.INVOICE_ID);
		// Assert
		verify(invoiceRepository, times(1)).findInvoiceById(anyInt());
		verifyNoMoreInteractions(invoiceRepository);
		assertEquals(TestConsts.EXAMPLE_INVOICE, invoice);
	}

	@Test
	// Coverage test 6
	public void givenNoQuery_whenGetOffersCalled_thenAllOffersReturned() {
		// Assign
		doReturn(TestConsts.ALL_OFFERS).when(offerRepository).findAll();
		// Act
		List<Offer> offers = rentService.getOffers(null);
		// Assert
		verify(offerRepository, times(1)).findAll();
		verifyNoMoreInteractions(offerRepository);
		assertTrue(offers.containsAll(TestConsts.ALL_OFFERS));
	}

	@Test
	// Coverage test 7
	public void givenValidQueryAndValidUser_whenGetOffersCalled_thenOffersReturnedContainUsername() {
		// Assign
		String bananaQuery = "banana";
		doReturn(TestConsts.ALL_OFFERS).when(offerRepository).findAll();
		doReturn(TestConsts.USER1).when(userRepository).findById(TestConsts.USER1_ID);
		// Act
		List<Offer> offers = rentService.getOffers(bananaQuery);
		// Assert
		verify(offerRepository, times(1)).findAll();
		verifyNoMoreInteractions(offerRepository);
		verify(userRepository, times(4)).findById(anyInt());
		verifyNoMoreInteractions(userRepository);
		assertTrue(offers.stream()
						 .allMatch(offer -> offer.getUserName().equals(TestConsts.USER1_USERNAME)));
	}

	@Test
	// Coverage test 8
	public void givenValidUserId_whenGetUserOffersListCalled_thenUserOffersReturned() {
		// Assign
		doReturn(TestConsts.ALL_OFFERS).when(offerRepository).findAll();
		// Act
		List<Offer> offers = rentService.getUserOffersList(TestConsts.USER1_ID);
		// Assert
		verify(offerRepository, times(1)).findAll();
		verifyNoMoreInteractions(offerRepository);
		assertTrue(offers.stream()
						 .allMatch(offer -> offer.getUserId() == TestConsts.USER1_ID));
	}

	@Test
	// Coverage test 9
	public void givenValidUserId_whenGetUserWishesListCalled_thenUserWishesReturned() {
		// Assign
		doReturn(TestConsts.ALL_WISHES).when(wishRepository).findAll();
		// Act
		List<Wish> wishes = rentService.getUserWishesList(TestConsts.USER1_ID);
		// Assert
		verify(wishRepository, times(1)).findAll();
		verifyNoMoreInteractions(wishRepository);
		assertTrue(wishes.stream()
						 .allMatch(wish -> wish.getUserId() == TestConsts.USER1_ID));
	}

	@Test
	// Coverage test 10
	public void givenUserWithNoInvoices_whenGetUserInvoiceListCalled_thenEmptyListReturned() {
		// Assign
		doReturn(TestConsts.ALL_INVOICES).when(invoiceRepository).findAll();
		// Act
		List<Invoice> invoices = rentService.getUserInvoiceList(TestConsts.USER1_ID);
		// Assert
		verify(invoiceRepository, times(1)).findAll();
		verifyNoMoreInteractions(invoiceRepository);
		assertTrue(invoices.isEmpty());
	}

}

package com.rent.rent;

import com.project.rent.model.Offer;
import com.project.rent.model.User;
import com.project.rent.model.Wish;
import com.project.rent.repository.OfferRepository;
import com.project.rent.repository.UserRepository;
import com.project.rent.repository.WishRepository;
import com.project.rent.service.RentService;
import com.project.rent.service.UserService;
import com.rent.rent.utils.TestConsts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

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

	@Before
	public void setUp() {
		rentService = new RentService(offerRepository, wishRepository, userRepository, null, null, null);
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

}

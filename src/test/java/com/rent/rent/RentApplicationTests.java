package com.rent.rent;

import com.project.rent.model.Offer;
import com.project.rent.repository.OfferRepository;
import com.project.rent.service.RentService;
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
	@Mock
	private OfferRepository offerRepository;

	@Before
	public void setUp() {
		rentService = new RentService(offerRepository, null, null, null, null, null);
	}

	@Test
	public void givenValidSearchQuery_whenPerformingRentOfferSearch_thenOnlyOffersMatchingSearchQueryReturned() {
		// Assign
		String searchQuery = "banana";

		doReturn(TestConsts.ALL_OFFERS).when(offerRepository).findAll();

		// Act
		List<Offer> filteredOffers = rentService.getOffersContaining(searchQuery);

		// Assert
		assertTrue(
				filteredOffers.stream().allMatch(offer1 ->
														 TestConsts.BANANA_OFFERS.stream().anyMatch(offer2 ->
																											offer1.getItemName()
																												  .equals(offer2.getItemName()))));
		assertTrue(
				filteredOffers.stream().noneMatch(offer1 ->
														  TestConsts.OTHER_OFFERS.stream().anyMatch(
																  offer2 -> offer1.getItemName()
																				  .equals(offer2.getItemName()))));
	}

}

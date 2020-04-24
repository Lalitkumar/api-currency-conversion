package com.barclaycard.currency.service;

import com.barclaycard.currency.model.CurrencyDetails;
import com.barclaycard.currency.repository.CurrencyRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class NoteCalculatorServiceTest {

    private NoteCalculatorService noteCalculatorService;
    private CurrencyDetails currencyDetails;
    private List<CurrencyDetails> currencyDetailList = new ArrayList<>();
    private List<String> currencyList = new ArrayList<>();

    @Mock
    CurrencyRepository currencyRepository;

    @Before
    public void init(){
        noteCalculatorService = new NoteCalculatorService(currencyRepository);
    }

    @Test
    public void testNoteCalculator(){
        whenCurrencyDetailsAreProvided();
        andServiceIsInvoked();
        thenCurrencyDetailsShouldReturn();
    }

    @Test
    public void testGetCurrencyList(){
        whenServiceIsInvoked();
        thenListShouldReturn();
    }

    private void whenCurrencyDetailsAreProvided(){
        currencyDetails = new CurrencyDetails();
        currencyDetails.setAmount(BigDecimal.valueOf(100.01));
        currencyDetails.setCurrency("INR");
    }
    private void andServiceIsInvoked(){
        currencyDetailList = noteCalculatorService.calculateNotes(currencyDetails);
    }
    private void thenCurrencyDetailsShouldReturn(){
        Assert.assertNotNull(currencyDetailList);
    }

    private void whenServiceIsInvoked(){
        currencyList = noteCalculatorService.getCurrencyList();
    }
    private void thenListShouldReturn(){
        Assert.assertFalse(currencyList.isEmpty());
        Assert.assertTrue(currencyDetailList.size()==2);
    }

}

package com.barclaycard.currency.service;

import com.barclaycard.currency.model.CurrencyDetails;
import com.barclaycard.currency.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoteCalculatorService{

    private CurrencyRepository currencyRepository;

    @Autowired
    public NoteCalculatorService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public List<String> getCurrencyList(){
        return currencyRepository.findDistinctCurrency();
    }

    public List<Integer> getCurrencyMappings(final String currency){
        return currencyRepository.findDenomByCurrency(currency);
    }

    public List<CurrencyDetails> calculateNotes(final CurrencyDetails currencyDetails){
        final List<CurrencyDetails> currencyDetailsList = new ArrayList<>();
        int amount = getRoundedAmount(currencyDetails.getAmount());
        List<Integer> notes = getCurrencyMappings(currencyDetails.getCurrency());
        int[] noteCounter = new int[notes.size()];
        for (int i = 0; i < notes.size(); i++) {
            if (amount >= notes.get(i)) {
                noteCounter[i] = amount / notes.get(i);
                amount = amount - noteCounter[i] * notes.get(i);
            }

            if (noteCounter[i] != 0) {
                CurrencyDetails details = new CurrencyDetails();
                System.out.println(notes.get(i) + " : "+ noteCounter[i]);
                details.setNoteValue(String.valueOf(notes.get(i)));
                details.setNoteCount(noteCounter[i]);
                currencyDetailsList.add(details);
            }
        }
        final String decimalAmt = getDecimalAmount(currencyDetails.getAmount());
        if(! decimalAmt.equals("0.0")){
            CurrencyDetails cDetails = new CurrencyDetails();
            getDecimalAmount(currencyDetails.getAmount());
            cDetails.setNoteValue(decimalAmt);
            cDetails.setNoteCount(1);
            currencyDetailsList.add(cDetails);
        }
        return currencyDetailsList;
    }

    private int getRoundedAmount(final BigDecimal amount){
        final String strAmt = String.valueOf(amount);
        return Integer.parseInt(strAmt.split("\\.")[0]);
    }

    private String getDecimalAmount(final BigDecimal amount){
        final String strAmt = String.valueOf(amount);
        if(strAmt.contains(".")) {
            return "0." + strAmt.split("\\.")[1];
        }
        return "0.0";
    }

}

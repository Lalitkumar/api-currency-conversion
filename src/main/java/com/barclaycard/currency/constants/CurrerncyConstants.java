package com.barclaycard.currency.constants;

import java.util.HashMap;
import java.util.Map;

public class CurrerncyConstants {

    CurrerncyConstants(){
        throw new UnsupportedOperationException("Operation Not Supported");
    }

    public static final Map<String, int[]> currencyNoteMapper = new HashMap<>(3);

    static {
        currencyNoteMapper.put("INR", new int[]{2000,500,100,50,20,10,5,2,1});
        currencyNoteMapper.put("EUR", new int[]{500,200,100,50,25,15,5,1});
        currencyNoteMapper.put("USD", new int[]{2000,500,100,50,30,10,5,2,1});
    }
}

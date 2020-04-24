package com.barclaycard.currency.validator;


import com.barclaycard.currency.model.CurrencyDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class CurrencyValidator implements Validator {

    public boolean supports(Class<?> paramClass) {
        return CurrencyDetails.class.equals(paramClass);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "amount", "valid.amount");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currency", "valid.currency");

    }
}

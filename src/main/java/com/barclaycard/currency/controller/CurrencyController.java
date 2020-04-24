package com.barclaycard.currency.controller;

import com.barclaycard.currency.model.CurrencyDetails;
import com.barclaycard.currency.service.NoteCalculatorService;
import com.barclaycard.currency.validator.CurrencyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CurrencyController {

	@Autowired
	private NoteCalculatorService noteCalculatorService;

	@Autowired
	private CurrencyValidator currencyValidator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(currencyValidator);
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("currency", new CurrencyDetails());
		model.addAttribute("currencyList", noteCalculatorService.getCurrencyList());
		return "homepage";
	}
	
	@PostMapping("/calculateNotes")
	public @ResponseBody List<CurrencyDetails> calculateNotes(@Validated final CurrencyDetails currencyDetails) {
		System.out.println("Entered in calculateNotes() method");
		System.out.println("Amount: " + currencyDetails.getAmount() + " | Currency: "+currencyDetails.getCurrency());
		return noteCalculatorService.calculateNotes(currencyDetails);
	}

}
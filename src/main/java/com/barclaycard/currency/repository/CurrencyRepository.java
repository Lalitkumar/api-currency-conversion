package com.barclaycard.currency.repository;

import com.barclaycard.currency.model.CurrencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<CurrencyEntity, Long> {
    @Query(value = "SELECT DISTINCT currency FROM CURRENCY_MAPPINGS", nativeQuery = true)
    public List<String> findDistinctCurrency();

    @Query(value = "SELECT denomination FROM CURRENCY_MAPPINGS where currency = ?1", nativeQuery = true)
    public List<Integer> findDenomByCurrency(String currency);
}

package com.orbisfn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.orbisfn.entity.Funds;

@Repository
public interface FundRepository extends CrudRepository<Funds, Long> {

	@Query("select ticker from funds")
	public List<String> getAllTickers();
	
	@Query("select f from funds f where f.ticker=?1")
	public Funds getFundInfo(String ticker);
}

package resourceUtilization.StockMarketListener.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import resourceUtilization.StockMarketListener.model.*;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;

@Repository
public interface ThresholdRepository extends MongoRepository<Threshold, String> {
	// Find thresholds by teleId
    List<Threshold> findByTeleId(String teleId);
    
    @Query("{ 'ticker': ?0, 'price': { $gte: ?1, $lte: ?2 } }")
    List<Threshold> findByTickerAndPriceRange(String ticker, Double minPrice, Double maxPrice);
}
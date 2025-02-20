package resourceUtilization.StockMarketListener.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import resourceUtilization.StockMarketListener.model.*;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByTeleId(String teleId);
}
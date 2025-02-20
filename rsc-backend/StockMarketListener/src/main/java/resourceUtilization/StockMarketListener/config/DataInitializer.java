package resourceUtilization.StockMarketListener.config;
import resourceUtilization.StockMarketListener.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(String... args) {
        // Create the 'thresholds' collection if it doesn't exist
        if (!mongoTemplate.collectionExists(Threshold.class)) {
            mongoTemplate.createCollection(Threshold.class);
            System.out.println("Collection 'thresholds' created!");
        }
        
        if (!mongoTemplate.collectionExists(User.class)) {
            mongoTemplate.createCollection(User.class);
            System.out.println("Collection 'users' created!");
        }

        // Create the compound index on 'ticker' and 'price'
        createCompoundIndex("ticker", "price");
        // teleId is actually the chatId of the dm for the registered user
        createIndex("teleId");
        
        
    }

    private void createCompoundIndex(String col1, String col2) {
    	try {
    		   // Get IndexOperations for the Threshold class
            IndexOperations indexOps = mongoTemplate.indexOps(Threshold.class);

            // Create a compound index on 'ticker' (ascending) and 'price' (ascending)
            indexOps.ensureIndex(new Index().on(col1, Sort.Direction.ASC).on(col2, Sort.Direction.ASC));
            System.out.println("Compound index on 'ticker' and 'price' created!");
    	}
    	catch (Exception e) {
    		System.out.println("Error while creating compound index on 'ticker' and 'price': " + e.getMessage());
    	}
        
    }
    
    private void createIndex(String col) {
    	try {
    		mongoTemplate.indexOps(Threshold.class)
            .ensureIndex(new Index().on(col, org.springframework.data.domain.Sort.Direction.ASC));
    		System.out.println("Index on 'teleId' created");
    	}
    	catch(Exception e) {
    		System.out.println("Error while creating index on 'teleId': " + e.getMessage());
    	}
    	  
    }
}
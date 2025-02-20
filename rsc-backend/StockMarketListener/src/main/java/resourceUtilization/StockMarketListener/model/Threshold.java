package resourceUtilization.StockMarketListener.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "thresholds")
public class Threshold extends Record{

    @Id
    private String id;

    @Field("ticker")
    private String ticker;

    @Field("price")
    private Double price;
    
    @Indexed
    @Field("teleId")
    private String teleId;

    public Threshold() {
    }
    
    // Getters and Setters
    public Threshold(String ticker, Double price, String teleId) {
        this.ticker = ticker;
        this.price = price;
        this.teleId = teleId;
    }
    
    public String getId() {
    	return this.id;
    }
    
    public String getTicker() {
    	return this.ticker;
    }
    public Double getPrice() {
    	return this.price;
    }
    public String getTeleId() {
    	return this.teleId;
    }
    
    public void setTicker(String ticker) {
    	this.ticker = ticker;
    }
    
    public void setPrice(Double price) {
    	this.price = price;
    }
    
    public void setTeleId(String teleId) {
    	this.teleId = teleId;
    }
    // getters and setters
}

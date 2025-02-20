package resourceUtilization.StockMarketListener.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "users")
public class User extends Record{

    @Id
    private String userId;

    @Indexed(unique = true)
    @Field("teleId")
    private String teleId;

    @Field("teleUsername")
    private String teleUsername;

    public User() {
    }
    
    public User(String userId) {
    	this.userId = userId;
    	this.teleId = null;
        this.teleUsername = null;
    }

    public User(String userId, String teleId, String teleUsername) {
        this.userId = userId;
        this.teleId = teleId;
        this.teleUsername = teleUsername;
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTeleId() {
        return teleId;
    }

    public void setTeleId(String teleId) {
        this.teleId = teleId;
    }

    public String getTeleUsername() {
        return teleUsername;
    }

    public void setTeleUsername(String teleUsername) {
        this.teleUsername = teleUsername;
    }
}
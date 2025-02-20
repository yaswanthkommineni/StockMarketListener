package resourceUtilization.StockMarketListener.controller;
import java.util.List;
import resourceUtilization.StockMarketListener.model.Record;

public class ResponseBody {
	String message;
	List <Record> results;
	
	public ResponseBody(String message,List<Record> results) {
		this.message = message;
		this.results = results;
	}
	
	// Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Record> getResults() {
        return results;
    }

    public void setResults(List<Record> results) {
        this.results = results;
    }
}


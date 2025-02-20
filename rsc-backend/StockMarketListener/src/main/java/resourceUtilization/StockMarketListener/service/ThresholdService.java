package resourceUtilization.StockMarketListener.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resourceUtilization.StockMarketListener.model.*;
import resourceUtilization.StockMarketListener.repository.*;
import resourceUtilization.StockMarketListener.exceptions.*;

import java.util.Optional;
import java.util.List;

@Service
public class ThresholdService {
    @Autowired
    private ThresholdRepository thresholdRepository;

    public List<Threshold> getAllThresholds() {
        return thresholdRepository.findAll();
    }

    public Threshold getThresholdById(String id) {
    	
    	Optional <Threshold> result = thresholdRepository.findById(id);
    	
    	if(result.isPresent()) {
    		return result.get();
    	}
    	throw new ResourceNotFoundException("No resource found with the given Id");
    }

    public Threshold createThreshold(Threshold threshold) {
        return thresholdRepository.save(threshold);
    }

    public Threshold updateThreshold(String id, Threshold threshold) {
        Threshold existingThreshold = getThresholdById(id);
        existingThreshold.setTicker(threshold.getTicker());
        existingThreshold.setPrice(threshold.getPrice());
        existingThreshold.setTeleId(threshold.getTeleId());
        return thresholdRepository.save(existingThreshold);
    }

    public void deleteThreshold(String id) {
        thresholdRepository.deleteById(id);
    }
    
    // Get thresholds by teleId
    public List<Threshold> getThresholdsByTeleId(String teleId) {
        return thresholdRepository.findByTeleId(teleId);
    }

    // Get thresholds by ticker and price range
    public List<Threshold> getThresholdsByTickerAndPriceRange(String ticker, Double minPrice, Double maxPrice) {
        return thresholdRepository.findByTickerAndPriceRange(ticker, minPrice, maxPrice);
    }
}

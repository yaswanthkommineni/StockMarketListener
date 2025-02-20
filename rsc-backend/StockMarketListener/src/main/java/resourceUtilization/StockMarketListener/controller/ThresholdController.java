package resourceUtilization.StockMarketListener.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resourceUtilization.StockMarketListener.service.*;
import resourceUtilization.StockMarketListener.model.*;
import resourceUtilization.StockMarketListener.model.Record;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;

import resourceUtilization.StockMarketListener.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@RestController
@RequestMapping("/api/thresholds")
public class ThresholdController {
    @Autowired
    private ThresholdService thresholdService;

    @GetMapping
    public ResponseEntity<ResponseBody> getAllThresholds() {
    	try {
    		List <Threshold> results = thresholdService.getAllThresholds();
    		return ResponseEntity.status(HttpStatus.FOUND).body(
    			new ResponseBody("success", new ArrayList<Record>(results))
    		);
    	}
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }

    @GetMapping("/teleId/{teleId}")
    public ResponseEntity<ResponseBody> getThresholdsByTeleId(@PathVariable("teleId") String teleId) {
    	try {
    		List<Threshold> results = thresholdService.getThresholdsByTeleId(teleId);
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
        			new ResponseBody("success", new ArrayList<Record>(results))
        		);
    	}
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }
    
    @GetMapping("/ticker/{ticker}")
    public ResponseEntity<ResponseBody> getThresholdsByTickerAndPriceRange(
            @PathVariable("ticker") String ticker,
            @RequestParam("minPrice") Double minPrice,
            @RequestParam("maxPrice") Double maxPrice) {
    	try {
    		 List<Threshold> results = thresholdService.getThresholdsByTickerAndPriceRange(ticker, minPrice, maxPrice);
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
        			new ResponseBody("success", new ArrayList<Record>(results))
        		);
    	}
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> getThresholdById(@PathVariable("id") String id) {
    	try {
    		Threshold result = thresholdService.getThresholdById(id);
    		return ResponseEntity.status(HttpStatus.FOUND).body(
    			new ResponseBody("success", new ArrayList<Record>(Arrays.asList(result)))
    		);
    	}
    	catch (ResourceNotFoundException  e) {
            // If the ticker already exists, send a custom error message
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        		new ResponseBody("success", new ArrayList <Record> ())
        	);
        }
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }

    @PostMapping
    public ResponseEntity<ResponseBody> createThreshold(@RequestBody Threshold threshold) {
    	try {
    		Threshold createdThreshold = thresholdService.createThreshold(threshold);
    		return ResponseEntity.status(HttpStatus.CREATED).body(
    			new ResponseBody("success", new ArrayList<Record>(Arrays.asList(createdThreshold)))
    		);
    	}
        catch (DuplicateKeyException e) {
            // If the ticker already exists, send a custom error message
        	return ResponseEntity.status(HttpStatus.CONFLICT).body(
        		new ResponseBody("duplicateKey", new ArrayList <Record> ())
        	);
        }
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> updateThreshold(@PathVariable("id") String id, @RequestBody Threshold threshold) {
    	try {
    		Threshold result = thresholdService.updateThreshold(id, threshold);
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
    			new ResponseBody("success", new ArrayList<Record>(Arrays.asList(result)))
    		);
    	}
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteThreshold(@PathVariable("id") String id) {
    	try {
    		thresholdService.deleteThreshold(id);
    		return ResponseEntity.status(HttpStatus.ACCEPTED).body(
    			new ResponseBody("success", new ArrayList<Record>())
    		);
    	}
    	catch (Exception e) {
            // Handle any other exception (e.g., database issues)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
            	new ResponseBody("error", new ArrayList <Record> ())
            );
        }
    }

}
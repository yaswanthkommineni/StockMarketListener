package resourceUtilization.StockMarketListener.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resourceUtilization.StockMarketListener.model.*;
import resourceUtilization.StockMarketListener.repository.*;
import resourceUtilization.StockMarketListener.exceptions.*;

import java.util.Optional;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String userId) {
    	
    	Optional <User> result = userRepository.findById(userId);
    	
    	if(result.isPresent()) {
    		return result.get();
    	}
    	
    	throw new ResourceNotFoundException("No resource found with the given userId");
    }

    public User getUserByTeleId(String teleId) {
        return userRepository.findByTeleId(teleId);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String userId, User userDetails) {
        User existingUser = getUserById(userId);
        existingUser.setTeleId(userDetails.getTeleId());
        existingUser.setTeleUsername(userDetails.getTeleUsername());
        return userRepository.save(existingUser);
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}

package it.si2001.springMVC.service;

import it.si2001.springMVC.model.User;
import it.si2001.springMVC.repository.UserRepository;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public List<User> getCustomers()   {
        return userRepository.findCustomer();
    }

    @Transactional
    public User getUserByMail(String email){
        return userRepository.findByEMail(email);
    }

    @Transactional
    public User getUser(int id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public void deleteUser(int id){
        userRepository.deleteById(id);
    }

    @Transactional
    public void saveUser(User user){
        userRepository.save(user);
    }
}

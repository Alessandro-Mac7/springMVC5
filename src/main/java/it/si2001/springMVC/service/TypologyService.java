package it.si2001.springMVC.service;

import it.si2001.springMVC.model.Typology;
import it.si2001.springMVC.repository.TypologyRepository;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypologyService {

    @Autowired
    private TypologyRepository typologyRepository;

    @Transactional
    public List<Typology> getAll(){
        return typologyRepository.findAll();
    }

    @Transactional
    public Typology get(int id) throws ResourceNotFoundException {
        return typologyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public Typology getType(String type) {
        return typologyRepository.findByType(type);
    }

    @Transactional
    public void delete(int id){
        typologyRepository.deleteById(id);
    }

    @Transactional
    public void save(Typology typology){
        typologyRepository.saveAndFlush(typology);
    }

}

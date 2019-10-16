package it.si2001.springMVC.service;

import it.si2001.springMVC.model.Category;
import it.si2001.springMVC.repository.CategoryRepository;
import it.si2001.springMVC.util.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    @Transactional
    public Category get(int id) throws ResourceNotFoundException {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public void delete(int id){
        categoryRepository.deleteById(id);
    }

    @Transactional
    public void save(Category category){
        categoryRepository.save(category);
    }
}

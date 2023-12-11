package com.patika.vet.api;

import com.patika.vet.business.abstracts.AnimalService;
import com.patika.vet.entity.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/animals")
public class AnimalController {

    @Autowired
    private AnimalService animalService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> findAll(){
        return animalService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animal getById(@PathVariable("id") Long id){
        return animalService.getById(id);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> getByName(@PathVariable("name") String name){
        return this.animalService.findByName(name);
    }

    @GetMapping("/customer/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<Animal> getByCustomerName(@PathVariable("name") String customerName){
        return this.animalService.findAllByCustomerName(customerName);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Animal save(@RequestBody Animal animal){
        return animalService.save(animal);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Animal update(@PathVariable("id") Long id,@RequestBody Animal newAnimal){
        return animalService.update(id,newAnimal);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        animalService.delete(id);
    }

}

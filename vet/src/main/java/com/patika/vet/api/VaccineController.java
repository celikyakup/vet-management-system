package com.patika.vet.api;

import com.patika.vet.business.abstracts.IVaccineService;
import com.patika.vet.entity.Vaccine;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
public class VaccineController {
    @Autowired
    private IVaccineService vaccineService;


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> findAll(){
        return vaccineService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vaccine getById(@PathVariable("id") Long id){
        return this.vaccineService.getById(id);
    }

    @GetMapping("/animal/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine> getByAnimalId(@PathVariable("id") Long animalId){
        return this.vaccineService.findByAnimalId(animalId);
    }
    @GetMapping("/date")
    public List<Vaccine> getByProtectionDate(@RequestParam("start-date") LocalDate startDate,@RequestParam("end-date")LocalDate endDate){
        return this.vaccineService.findByProtectionDate(startDate,endDate);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Vaccine save(@RequestBody Vaccine vaccine){
        return this.vaccineService.save(vaccine);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Vaccine update(@PathVariable("id") Long id ,Vaccine vaccine){
        return this.vaccineService.update(id,vaccine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        this.vaccineService.delete(id);
    }
}

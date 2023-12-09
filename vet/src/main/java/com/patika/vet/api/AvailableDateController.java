package com.patika.vet.api;

import com.patika.vet.business.abstracts.IAvailableDateService;
import com.patika.vet.business.abstracts.IDoctorService;
import com.patika.vet.entity.AvailableDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/dates")
public class AvailableDateController {
    @Autowired
    private IAvailableDateService dateService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AvailableDate> findAll(){
        return this.dateService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDate getById(@PathVariable("id") Long id){
        return this.dateService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public AvailableDate save(@RequestBody AvailableDate date){
        return this.dateService.save(date);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AvailableDate update(@PathVariable Long id,@RequestBody AvailableDate date){
        return this.dateService.update(id,date);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        this.dateService.delete(id);
    }
}

package com.patika.vet.api;

import com.patika.vet.business.abstracts.IDoctorService;
import com.patika.vet.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctors")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor getById(@PathVariable("id") Long id){
        return this.doctorService.findById(id);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Doctor> getAll(){
        return this.doctorService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Doctor save(@RequestBody Doctor doctor){
        return this.doctorService.save(doctor);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Doctor update(@PathVariable("id") Long id,@RequestBody Doctor doctor){
        return this.doctorService.update(id,doctor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id){
        this.doctorService.delete(id);
    }
}

package com.whoiscan.resttest.controller;

import com.whoiscan.resttest.entity.Developer;
import com.whoiscan.resttest.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.whoiscan.resttest.model.Result;
import com.whoiscan.resttest.payload.DeveloperRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DeveloperController {
    @Autowired
    DeveloperRepository developerRepository;

    @GetMapping("/developers")
    public HttpEntity<List<Developer>> getDevelopers() {
        List<Developer> developers = developerRepository.findAll();
        if (developers.size() == 0) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(developers);
    }

    @GetMapping("/developer/{id}")
    public HttpEntity<Developer> getDeveloper(@PathVariable Integer id) {
        Optional<Developer> optional = developerRepository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/developer/create", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST)
    public Developer createDeveloper(@RequestBody DeveloperRequest developerRequest) {
        Developer developer = new Developer();
        developer.setName(developerRequest.getName());
        developer.setOffice(developerRequest.getOffice());
        developer.setSalary(developerRequest.getSalary());
        return developerRepository.save(developer);
    }

    @PutMapping("/developer/{id}")
    public Result editDeveloper(@PathVariable Integer id, @RequestBody DeveloperRequest developerRequest) {
        Result result = new Result();
        Optional<Developer> optional = developerRepository.findById(id);
        if (optional.isPresent()) {
            Developer developer = optional.get();
            developer.setName(developerRequest.getName());
            developer.setOffice(developerRequest.getOffice());
            developer.setSalary(developerRequest.getSalary());
            Developer savedDeveloper = developerRepository.save(developer);
            if (savedDeveloper != null) {
                result.setSuccess(true);
                result.setMessage("Successfully edited!");
            } else {
                result.setSuccess(false);
                result.setMessage("Error editing");
            }
        } else {
            result.setSuccess(false);
            result.setMessage("Developer not found");
        }
        return result;
    }

    @DeleteMapping("/developer/delete/{id}")
    public Result deleteDeveloper(@PathVariable Integer id) {
        Result result = new Result();
        developerRepository.deleteById(id);
        Developer deletedDeveloper = developerRepository.getOne(id);
        if (deletedDeveloper != null) {
            result.setSuccess(true);
            result.setMessage("Successfully deleted!");
        } else {
            result.setSuccess(false);
            result.setMessage("Error deleting");
        }
        return result;
    }
}

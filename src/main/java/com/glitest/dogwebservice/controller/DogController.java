package com.glitest.dogwebservice.controller;

import com.glitest.dogwebservice.dto.DogResponse;
import com.glitest.dogwebservice.dto.MyDogDTO;
import com.glitest.dogwebservice.exception.type.BadRequestError;
import com.glitest.dogwebservice.service.DogService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dog")
public class DogController {

//    Singleton Pattern
//    Karena menggunakan Bean dimana pada prinsip Bean juga menggunakan prinsip
//    bahwa penggunaan class hanya menggunakan 1 instance
    private final DogService dogService;

    @ResponseBody
    @Operation(summary = "For fetch all data from https://dog.ceo/dog-api/documentation/")
    @GetMapping(value = "/all/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllDogData() {
        String dogDtoList = dogService.fetchAllDogData();
        return new ResponseEntity<>(dogDtoList, HttpStatus.OK);
    }

    @ResponseBody
    @Operation(summary = "For fetch all data from https://dog.ceo/dog-api/documentation/ with rules in tech test question")
    @GetMapping(value = "/{subBreed}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSubDogData(@PathVariable String subBreed) {
        if(subBreed == null || subBreed.isEmpty()) {
            throw new BadRequestError("Bad Request subBreed",
                    "sub breed null or empty");
        }
        DogResponse data = dogService.fetchDogDataBySub(subBreed);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @ResponseBody
    @Operation(summary = "For save data as CRUD Operation")
    @PostMapping(value = "/mydog/save",
        consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<MyDogDTO> createMyDog(@RequestBody MyDogDTO request) {
        if(request.getName() == null || request.getName().isEmpty()) {
            throw new BadRequestError("Bad Request Create MyDog",
                    "Name My Dog Is Empty");
        }
        if(request.getType() == null || request.getType().isEmpty()) {
            throw new BadRequestError("Bad Request Create MyDog",
                    "Dog Type is Empty");
        }
        if(request.getSubType() == null || request.getSubType().isEmpty()) {
            throw new BadRequestError("Bad Request Create MyDog",
                    "Dog Sub Type is Empty");
        }
        MyDogDTO myDog = dogService.createMyDog(request);
        return new ResponseEntity<>(myDog, HttpStatus.OK);
    }

    @ResponseBody
    @Operation(summary = "For Get data as CRUD Operation")
    @GetMapping(value = "/mydog/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getSubDogData(@PathVariable Long id) {
        if(id == null) {
            throw new BadRequestError("Bad Request subBreed",
                    "Null Id");
        }
        MyDogDTO data = dogService.getById(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @ResponseBody
    @Operation(summary = "For Delete data as CRUD Operation")
    @DeleteMapping(value = "/mydog/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteMyDogData(@PathVariable Long id) {
        if(id == null) {
            throw new BadRequestError("Bad Request subBreed",
                    "Null Id");
        }
        boolean data = dogService.deleteMyDog(id);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}

package com.glitest.dogwebservice.service;

import com.glitest.dogwebservice.dto.DogResponse;
import com.glitest.dogwebservice.dto.MyDogDTO;
import com.glitest.dogwebservice.exception.type.InternalServerError;
import com.glitest.dogwebservice.exception.type.NotFoundException;
import com.glitest.dogwebservice.model.MyDog;
import com.glitest.dogwebservice.repository.MyDogRepository;
import com.google.gson.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;

@Service
@AllArgsConstructor
public class DogService {

    private final MyDogRepository myDogRepository;

    public String fetchAllDogData() {

        String url = "https://dog.ceo/api/breeds/list/all";

        try {
            Unirest.setTimeouts(5000, 5000);
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();
            System.out.println(response.getStatus());

            if(response.getStatus() == HttpStatus.SC_OK) {
                String resultData = response.getBody().getObject().getJSONObject("message").toString();
                JsonParser parser = new JsonParser();
                JsonObject jsonObject = (JsonObject) parser.parse(resultData);
                return jsonObject.toString();
            } else {
                throw new InternalServerError("Fail To fetch data",
                        "Http Status not ok(200)");
            }
        } catch (UnirestException e) {
            throw new InternalServerError("Internal Server Error", e.getMessage());
        }
    }

    public DogResponse fetchDogDataBySub(String subBreed) {

        String url = "https://dog.ceo/api/breed/"+ subBreed +"/list";

        try {
            Unirest.setTimeouts(2000, 2000);
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();

            if(response.getStatus() == HttpStatus.SC_OK) {
                JSONArray resultData = response.getBody().getObject().getJSONArray("message");
                Map<String, List<String>> map = new HashMap<>();
                if(subBreed.equals("sheepdog")) {
                    resultData.forEach((data) -> {
                        String nameObject = subBreed + "-" + data.toString();
                        List<String> value = new ArrayList<>();
                        map.put(nameObject, value);
                    });
                    return new DogResponse("Success", map);
                } else if(subBreed.equals("terrier")) {
                    resultData.forEach((data) -> {
                        String nameObject = subBreed + "-" + data.toString();
                        List<String> value = fetchAllImageDog(subBreed, data.toString());
                        map.put(nameObject, value);
                    });
                    return new DogResponse("Success", map);
                }
                return new DogResponse("Success", resultData);
            } else if(response.getStatus() == HttpStatus.SC_NOT_FOUND) {
                throw new NotFoundException("Fail To fetch data",
                        "Dog Not Found" + subBreed);
            } else {
                throw new InternalServerError("Fail To fetch data",
                        "Http Status not ok(200)");
            }
        } catch (Exception e) {
            throw new InternalServerError("Internal Server Error", e.getMessage());
        }
    }

    public List<String> fetchAllImageDog(String dogName, String type) {
        String url = "https://dog.ceo/api/breed/"+ dogName+ "/" + type+ "/images";
        List<String> dogImageData = new ArrayList<>();
        try {
            HttpResponse<JsonNode> response = Unirest.get(url).asJson();

            if(response.getStatus() == HttpStatus.SC_OK) {
                JSONArray resultData = response.getBody().getObject().getJSONArray("message");
                int index = 0;
                for (Object data : resultData) {
                    if (index == 4) {
                        break;
                    }
                    dogImageData.add(data.toString());
                    index++;
                }
                return dogImageData;
            } else {
                throw new InternalServerError("Fail To fetch data",
                        "Http Status not ok(200)");
            }
        } catch (Exception e) {
            throw new InternalServerError("Internal Server Error", e.getMessage());
        }
    }

    public MyDogDTO createMyDog(MyDogDTO myDog) {
        MyDog myDog1 = new MyDog();
        myDog1.setName(myDog.getName());
        myDog1.setType(myDog.getType());
        myDog1.setSubType(myDog.getSubType());
        myDog1.setImage(myDog.getImage());
        MyDog save = myDogRepository.save(myDog1);

        return new MyDogDTO(
                save.getName(),
                save.getType(),
                save.getSubType(),
                save.getImage()
        );
    }

    public MyDogDTO getById(Long id) {
        try {
            MyDog data = myDogRepository.getById(id);
            return new MyDogDTO(
                    data.getName(),
                    data.getType(),
                    data.getSubType(),
                    data.getImage()
            );
        }catch (EntityNotFoundException e) {
            throw new NotFoundException("Not Found data", "Can't Find data with that id");
        }
    }

    public boolean deleteMyDog(Long id) {
        try {
            MyDog data = myDogRepository.getById(id);
            System.out.println(data);
            myDogRepository.delete(data);
            return true;
        }catch (EntityNotFoundException e) {
            throw new NotFoundException("Not Found data", "Can't Find data with that id");
        } catch (Exception e) {
            throw new NotFoundException("Can't Delete", "Can't Find data for delte");
        }
    }


}

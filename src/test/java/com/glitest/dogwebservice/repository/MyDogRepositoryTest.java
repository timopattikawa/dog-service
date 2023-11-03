package com.glitest.dogwebservice.repository;

import com.glitest.dogwebservice.model.MyDog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class MyDogRepositoryTest {

    @Autowired
    private MyDogRepository mydogRepository;

    private MyDog myDog;

    @BeforeEach
    public void setUpStudent() {
        myDog = MyDog.builder()
                .name("Snowee")
                .type("terrier")
                .subType("american")
                .image("https://images.dog.ceo/breeds/terrier-american/n02093428_10164.jpg")
                .build();
    }

    @Test
    @DisplayName("JUnit test for save MyDog operation")
    public void givenMyDog_CreateDog_SuccessToCreate() {
        MyDog myDog1 = mydogRepository.save(myDog);
        assertThat(myDog1).isNotNull();
        assertThat(myDog1.getId()).isNotNull();
        assertThat(myDog1.getName()).isEqualTo(myDog.getName());
    }

    @Test
    @DisplayName("JUnit test for Get MyDog operation")
    public void givenId_whenGetDog_SuccessToGEt() {
        MyDog myDog2 = MyDog.builder()
                .name("Snowaa")
                .type("terrier")
                .subType("american")
                .image("https://images.dog.ceo/breeds/terrier-american/n02093428_10164.jpg")
                .build();
        mydogRepository.save(myDog2);
        Optional<MyDog> doggyData = mydogRepository.findById(myDog2.getId());
        assertThat(doggyData.isPresent()).isEqualTo(true);
        assertThat(doggyData.get().getId()).isNotNull();
    }

    @Test
    @DisplayName("JUnit test for Delete MyDog operation")
    public void givenDog_whenDelete_SuccessToDelete() {
        MyDog myDog3 = MyDog.builder()
                .name("Canon")
                .type("terrier")
                .subType("australian")
                .image("https://images.dog.ceo/breeds/terrier-american/n02093428_10164.jpg")
                .build();
        MyDog fromRepo = mydogRepository.save(myDog3);
        mydogRepository.delete(myDog3);
        Optional<MyDog> doggyData = mydogRepository.findById(fromRepo.getId());
        assertThat(doggyData.isPresent()).isEqualTo(false);
    }
}
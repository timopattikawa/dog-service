package com.glitest.dogwebservice.service;

import com.glitest.dogwebservice.dto.MyDogDTO;
import com.glitest.dogwebservice.model.MyDog;
import com.glitest.dogwebservice.repository.MyDogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
// Harus Mengubah scope pada h2 menjadi test
@ExtendWith(MockitoExtension.class)
class DogServiceTest {

    @Mock
    private MyDogRepository myDogRepository;
    @InjectMocks
    private DogService myDogService;

    @Test
    public void GivenDog_SaveDog_SuccessToSave() {
        MyDog myDog = MyDog.builder()
                .name("Snowee")
                .type("terrier")
                .subType("american")
                .image("https://images.dog.ceo/breeds/terrier-american/n02093428_10164.jpg")
                .build();

        MyDogDTO myDogDTO = MyDogDTO.builder()
                .name("Snowee")
                .type("terrier")
                .subType("american")
                .image("https://images.dog.ceo/breeds/terrier-american/n02093428_10164.jpg")
                .build();

        when(myDogRepository.save(Mockito.any(MyDog.class))).thenReturn(myDog);
        MyDogDTO response = myDogService.createMyDog(myDogDTO);
        assertThat(response).isNotNull();
    }

//    @Test
//    public void GivenID_GetDog_SuccessToGet() {
//        MyDog myDog = MyDog.builder()
//                .name("Snowee")
//                .type("terrier")
//                .subType("american")
//                .image("https://images.dog.ceo/breeds/terrier-american/n02093428_10164.jpg")
//                .build();
//
//        MyDogDTO myDogDTO = MyDogDTO.builder()
//                .name("Snowee")
//                .type("terrier")
//                .subType("american")
//                .image("https://images.dog.ceo/breeds/terrier-american/n02093428_10164.jpg")
//                .build();
//
//        when(myDogRepository.getById(1L)).thenReturn(myDog);
//        MyDogDTO response = myDogService.createMyDog(myDogDTO);
//        assertThat(response).isNotNull();
//    }

}
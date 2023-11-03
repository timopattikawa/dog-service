package com.glitest.dogwebservice.repository;

import com.glitest.dogwebservice.model.MyDog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyDogRepository extends JpaRepository<MyDog, Long> {
}

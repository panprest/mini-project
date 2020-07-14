package com.app.latihan.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.app.latihan.mongo.model.BookingModel;


@Repository
public interface MongoRepo extends MongoRepository<BookingModel, String> {

}

package com.example.config.client.controller;

import java.util.List;

public interface  CustomerRepository {
	List<Customer> findAll();

	Customer findOne(Long id);
}

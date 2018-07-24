package com.example.readinglist;

import org.springframework.stereotype.Service;

@Service
public class AddressService {
    public Address findByLastName(String schema) {
        return new Address("1","2","3","4","5","6");
    }
}

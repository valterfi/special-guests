package com.valterfi.specialguests.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valterfi.specialguests.model.SpecialGuest;
import com.valterfi.specialguests.repository.SpecialGuestRepository;

@Service
public class SpecialGuestService {

	@Autowired
	private SpecialGuestRepository specialGuestRepository;

	public List<SpecialGuest> listAll() {
        return (List<SpecialGuest>) specialGuestRepository.findAll();
    }

    public void save(SpecialGuest product) {
    	specialGuestRepository.save(product);
    }

    public SpecialGuest get(Integer id) {
    	return specialGuestRepository.findById(id).get();
    }

    public void delete(Integer id) {
    	specialGuestRepository.deleteById(id);
    }



}

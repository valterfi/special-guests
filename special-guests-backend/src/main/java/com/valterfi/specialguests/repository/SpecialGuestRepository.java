package com.valterfi.specialguests.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.valterfi.specialguests.model.SpecialGuest;

@Repository
public interface SpecialGuestRepository extends CrudRepository<SpecialGuest, Integer> {

}

package com.foster.pet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foster.pet.dto.OptionDTO;
import com.foster.pet.dto.state.StateFRDTO;
import com.foster.pet.dto.state.StateHRDTO;
import com.foster.pet.dto.state.StatePDTO;
import com.foster.pet.dto.state.StateRDTO;

public interface StateService {

	Page<StateHRDTO> findAll(Pageable pageable);

	List<OptionDTO<Long>> findOptions(Long countryId);

	StateFRDTO findById(Long id);

	StateFRDTO findByName(String name);

	StateRDTO persist(StatePDTO statePDTO);

	StateHRDTO deleteById(Long id);
}

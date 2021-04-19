package com.foster.pet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.foster.pet.dto.OptionDTO;
import com.foster.pet.dto.state.StateFRPDTO;
import com.foster.pet.dto.state.StateHRDTO;
import com.foster.pet.dto.state.StatePDTO;
import com.foster.pet.dto.state.StateRDTO;

public interface StateService {

	Page<StateHRDTO> findAll(Pageable pageable);

	List<OptionDTO<Long>> findOptions(Long countryId);

	StateFRPDTO findById(Long id);

	StateFRPDTO findByName(String name);

	StateRDTO register(StatePDTO statePDTO);

	StateFRPDTO edit(StateFRPDTO stateFRPDTO);

	StateHRDTO remove(Long id);
}

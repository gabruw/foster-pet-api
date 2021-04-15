package com.foster.pet.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDTO<T> implements Serializable {

	private static final long serialVersionUID = -8267287228611361678L;

	private String text;
	private transient T value;
}

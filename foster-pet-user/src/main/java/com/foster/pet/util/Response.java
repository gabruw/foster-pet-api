package com.foster.pet.util;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response<T> {

	private T data;
	private List<String> errors;

	public Response() {
		if (this.errors == null) {
			this.errors = new ArrayList<>();
		}
	}

	public void addError(String error) {
		this.errors.add(error);
	}

	public boolean hasErrors() {
		return !this.errors.isEmpty();
	}
}

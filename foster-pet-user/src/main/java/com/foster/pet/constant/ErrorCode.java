package com.foster.pet.constant;

public enum ErrorCode {

	INTERNAL_SERVER_ERROR("Erro interno do servidor"), INVALID_REQUEST("Requisição inválida"),
	VALIDATION_FAILED("A validação falhou"), PERSON_NOT_FOUND("A pessoa não foi encontrada"),
	PERSON_ALREADY_EXISTS("A pessoa já foi cadastrada"), COMPANY_NOT_FOUND("A empresa não foi encontrada"),
	COMPANY_ALREADY_EXISTS("A empresa já foi cadastrada");

	private final String message;

	ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

package com.foster.pet.constant;

public enum ErrorCode {

	INTERNAL_SERVER_ERROR("Erro interno do servidor"), INVALID_REQUEST("Requisição inválida"),
	VALIDATION_FAILED("A validação falhou"), TOKEN_INVALID("O token enviado é inválido"),
	TOKEN_TYPE_INVALID("O tipo do token é inválido"), TOKEN_EMPTY("O token está vazio"),
	PERSON_NOT_FOUND("A pessoa não foi encontrada"), PERSON_ALREADY_EXISTS("A pessoa já foi cadastrada"),
	COMPANY_NOT_FOUND("A empresa não foi encontrada"), COMPANY_ALREADY_EXISTS("A empresa já foi cadastrada"),
	AUTHENTICATION_NOT_FOUND("A autenticação não foi encontrada"),
	AUTHENTICATION_ALREADY_EXISTS("A autenticação já foi cadastrada"),
	ACCESS_DENIED("Acesso negado. Você deve estar autenticado no sistema para acessar o serviço solicitado."),
	CITY_NOT_FOUND("A cidade não foi encontrada."), STATE_NOT_FOUND("A estado não foi encontrada.");

	private final String message;

	ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

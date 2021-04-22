package com.foster.pet.animal.constant;

public enum ErrorCode {

	// Misc.
	INTERNAL_SERVER_ERROR("Erro interno do servidor"), INVALID_REQUEST("Requisição inválida"),
	VALIDATION_FAILED("A validação falhou"),
	ACCESS_DENIED("Acesso negado. Você deve estar autenticado no sistema para acessar o serviço solicitado."),

	// Token
	TOKEN_INVALID("O token enviado é inválido"), TOKEN_TYPE_INVALID("O tipo do token é inválido"),
	TOKEN_EMPTY("O token está vazio"),

	// Not Found
	ANIMAL_NOT_FOUND("Animal não foi encontrado"),

	// Already Exists
	ANIMAL_ALREADY_EXISTS("Animal já foi cadastrado");

	private final String message;

	ErrorCode(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

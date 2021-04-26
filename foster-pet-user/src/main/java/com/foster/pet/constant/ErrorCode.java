package com.foster.pet.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// Misc.
	INTERNAL_SERVER_ERROR("Erro interno do servidor"), INVALID_REQUEST("Requisição inválida"),
	VALIDATION_FAILED("A validação falhou"),
	ACCESS_DENIED("Acesso negado. Você deve estar autenticado no sistema para acessar o serviço solicitado"),

	// Token
	TOKEN_INVALID("O token enviado é inválido"), TOKEN_TYPE_INVALID("O tipo do token é inválido"),
	TOKEN_EMPTY("O token está vazio"),
	
	// Password
	WRONG_PASSWORD("A senha está incorreta"),

	// Not Found
	PERSON_NOT_FOUND("A pessoa não foi encontrada"), COMPANY_NOT_FOUND("A empresa não foi encontrada"),
	CITY_NOT_FOUND("A cidade não foi encontrada"), STATE_NOT_FOUND("O estado não foi encontrado"),
	COUNTRY_NOT_FOUND("O país não foi encontrado"), ADDRESS_NOT_FOUND("O endereço não foi encontrado"),
	AUTHENTICATION_NOT_FOUND("A autenticação não foi encontrada"),

	// Already Exists
	PERSON_ALREADY_EXISTS("A pessoa já foi cadastrada"), COMPANY_ALREADY_EXISTS("A empresa já foi cadastrada"),
	CITY_ALREADY_EXISTS("A cidade já foi cadastrada"), STATE_ALREADY_EXISTS("O estado já foi cadastrado"),
	COUNTRY_ALREADY_EXISTS("O país já foi cadastrado"), ADDRESS_ALREADY_EXISTS("O endereço já foi cadastrado"),
	AUTHENTICATION_ALREADY_EXISTS("A autenticação já foi cadastrada"),
	
	// Not Recognized
	USER_TYPE_NOT_RECOGNIZED("O tipo de usuário não foi reconhecido");

	private final String message;
}

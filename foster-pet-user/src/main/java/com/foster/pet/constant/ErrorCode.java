package com.foster.pet.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

	// Misc.
	INVALID_REQUEST("Requisição inválida"),
	VALIDATION_FAILED("A validação falhou"),
	INTERNAL_SERVER_ERROR("Erro interno do servidor"),
	ACCESS_DENIED("Acesso negado. Você deve estar autenticado no sistema para acessar o serviço solicitado"),

	// Token
	TOKEN_EMPTY("O token está vazio"),
	TOKEN_INVALID("O token enviado é inválido"),
	TOKEN_TYPE_INVALID("O tipo do token é inválido"),
	
	// Password
	WRONG_PASSWORD("A senha está incorreta"),

	// Not Found
	CITY_NOT_FOUND("A cidade não foi encontrada"),
	STATE_NOT_FOUND("O estado não foi encontrado"),
	COUNTRY_NOT_FOUND("O país não foi encontrado"),
	PERSON_NOT_FOUND("A pessoa não foi encontrada"),
	COMPANY_NOT_FOUND("A empresa não foi encontrada"),
	ADDRESS_NOT_FOUND("O endereço não foi encontrado"),
	AUTHENTICATION_NOT_FOUND("A autenticação não foi encontrada"),
	
	// Not Changed
	CITY_NOT_CHANGED("Os dados da cidade não foram alterados"),
	STATE_NOT_CHANGED("Os dados do estado não foram alterados"),
	COUNTRY_NOT_CHANGED("Os dados do país não foram alterados"),
	PERSON_NOT_CHANGED("Os dados da pessoa não foram alterados"),
	COMPANY_NOT_CHANGED("Os dados da empresa não foram alterados"),
	ADDRESS_NOT_CHANGED("Os dados do endereço não foram alterados"),
	AUTHENTICATION_NOT_CHANGED("Os dados da autenticação não foram alterados"),

	// Already Exists
	CITY_ALREADY_EXISTS("A cidade já foi cadastrada"),
	STATE_ALREADY_EXISTS("O estado já foi cadastrado"),
	COUNTRY_ALREADY_EXISTS("O país já foi cadastrado"),
	PERSON_ALREADY_EXISTS("A pessoa já foi cadastrada"),
	COMPANY_ALREADY_EXISTS("A empresa já foi cadastrada"),
	ADDRESS_ALREADY_EXISTS("O endereço já foi cadastrado"),
	AUTHENTICATION_ALREADY_EXISTS("A autenticação já foi cadastrada"),
	
	// Not Recognized
	USER_TYPE_NOT_RECOGNIZED("O tipo de usuário não foi reconhecido");

	private final String message;
}

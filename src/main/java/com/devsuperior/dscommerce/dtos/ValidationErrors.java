package com.devsuperior.dscommerce.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationErrors extends CustomError {

	private List<FieldMessage> errors= new ArrayList<>();

	public ValidationErrors(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		//Single message for each field
		errors.removeIf(x->x.getName().equals(fieldName));
		errors.add(new FieldMessage(fieldName,message));

	}
}

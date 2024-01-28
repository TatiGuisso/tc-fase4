package com.grupo16.tcfase4.exception;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class ExceptionJsonUnitTest {
	
	@Test
	void exceptionJson() {
		
		SystemBaseException baseException = mock(SystemBaseException.class);
		
		when(baseException.getCode()).thenReturn("Test Code");
		when(baseException.getMessage()).thenReturn("Test Message");
		
		ExceptionJson exception = new ExceptionJson(baseException);
		assertEquals(baseException.getCode(), exception.getCode());
		assertEquals(baseException.getMessage(), exception.getMessage());
	}

}

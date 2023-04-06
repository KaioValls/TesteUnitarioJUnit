package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Funcionario;

class BonusServiceTest {

	BonusService service;
	
	@BeforeEach
	public void init() {
		service = new BonusService();
	}
	
	
	@Test
	void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {

		try {			
			service.calcularBonus(new Funcionario("Kaio", LocalDate.now(), new BigDecimal("25000")));
			fail("Nao deu a exception");
		} catch (Exception e) {
			assertEquals("Funcionário com salario maior do que R$10000 não pode receber Bonus", e.getMessage());
		}
	}

	@Test
	void bonusDeveriaSer10PorCentoDoSalario() {
		BigDecimal bonus = service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("2500")));
		assertEquals(new BigDecimal("250.00"), bonus);
	}

	@Test
	void bonusDeveriaSerDezPorCentoParaSalarioDeExatamente10000() {
		BigDecimal bonus = service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("10000")));
		assertEquals(new BigDecimal("1000.00"), bonus);
	}

}

package test.backend.konto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Date;

import org.junit.jupiter.api.Test;

import com.example.swe_finanzmanager.backend.konten.Konto;
import com.example.swe_finanzmanager.backend.konten.Transaktion;
import com.example.swe_finanzmanager.backend.nutzer.Nutzer;

public class TestTransaktion {
	@Test
	public void testSetAndGetObsolet() {
		// normaler Konstrukter anstatt factory
		Transaktion testTransaktion = new Transaktion(new BigDecimal("2323.0"), mock(Date.class), mock(Nutzer.class), mock(Konto.class),
				"Beschreibung", "titel", "834329");

		Konto testKonto = mock(Konto.class);
		// now zielKonto is not the testKonto
		assertNotEquals(testTransaktion.getZielKonto(), testKonto);
		// now we set zielKonto to TestKonto
		testTransaktion.setZielKonto(testKonto);
		assertEquals(testTransaktion.getZielKonto(), testKonto);
		// test booleans
		assertFalse(testTransaktion.isAusgefuehrt());
		assertFalse(testTransaktion.isObsolet());
		// change obsolet
		testTransaktion.setObsolet(true);
		assertTrue(testTransaktion.isObsolet());
	}

}

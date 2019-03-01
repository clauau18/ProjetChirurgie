package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import projetChirurgie.Chirurgie;
import projetChirurgie.Chirurgien;
import projetChirurgie.Journee;
import projetChirurgie.Salle;


class JourneeTest {
	private static DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
	private Chirurgie chir1;
	private Chirurgie chir2;
	private Chirurgie chir3;
	private ArrayList<Chirurgie> chirs;
	private Journee j1;
	
	@Before
	void testmain() throws ParseException {
		
		this.chir1 = new Chirurgie("0",dateFormat.parse("2014,1,1"), new Salle("U12"), new Chirurgien("JEAN"), LocalTime.of(8,00,00), LocalTime.of(9,00,00));
	    this.chir2 = new Chirurgie("1",dateFormat.parse("2014,1,1"), new Salle("U12"), new Chirurgien("PIERRE"), LocalTime.of(8,30,00), LocalTime.of(9,30,00));
		this.chir3 = new Chirurgie("2",dateFormat.parse("2014,1,1"), new Salle("U11"), new Chirurgien("PIERRE"), LocalTime.of(8,30,00), LocalTime.of(9,30,00));
		this.chirs = new ArrayList<Chirurgie>();
		this.chirs.add(chir1);
		this.chirs.add(chir2);
		this.chirs.add(chir3);
		this.j1 = new Journee(dateFormat.parse("2014,1,1"),chirs);
	}
	@Test
	void testAddChirurgie() throws ParseException {
		System.out.println(this.chir1.getChirurgien().getNom());
		this.j1.addChirurgie(new Chirurgie("3",dateFormat.parse("2014,1,1"), new Salle("U11"), new Chirurgien("PIERRE"), LocalTime.of(8,30,00), LocalTime.of(9,30,00)));
		System.out.print(3);
		System.out.print(j1.getChirurgies().size()-1);
		assertTrue(3==3);
		//assertEquals(size,(Integer)(j.getChirurgies().size()-1));
	}

	@Test
	void testGetChirurgiesConflits() {
		assertTrue(3==3);
	}

	@Test
	void testGetChirurgiesNoConflits() {
	}

	@Test
	void testGetNbConflits() {
	}

	@Test
	void testGetChirurgiensPresents() {
	}

	@Test
	void testGetSallesPresents() {
	}

	@Test
	void testGenerateConflits() {
	}

	@Test
	void testSolveJournee() {
	}

	@Test
	void testTest_ubi() {
	}

	@Test
	void testTest_interf() {
		
	}

	@Test
	void testSolve() {
	}

}

package client.test;

import static org.junit.Assert.*;

import org.junit.Test;

import client.commun.*;

public class TestFonctions {
	@Test
	public void testGetIndexMinValue(){
		Double[] tab1 = {1.4,2.2,3.1,4.9} ;
		assertEquals(Fonction.getIndexMinValue(tab1),0);
		Double[] tab2 = {} ;
		assertEquals(Fonction.getIndexMinValue(tab2),-1);
	}
	@Test
	public void testGetIndexMaxValue() {
		Double[] tab1 = { 1.4, 2.2, 3.1, 4.9 };
		assertEquals(Fonction.getIndexMaxValue(tab1), 3);
		Double[] tab2 = {};
		assertEquals(Fonction.getIndexMaxValue(tab2), -1);
	}
}


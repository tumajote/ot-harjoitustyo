package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {

        String vastaus = kortti.toString();

        assertEquals("saldo: 10.0", vastaus);
    }
    @Test
    public void konstruktoriAsettaaSaldonOikein2() {

        

        assertEquals(kortti.saldo(), 1000);
    }

    @Test
    public void rahaaLadataanOikein() {
        kortti.lataaRahaa(50);

        String vastaus = kortti.toString();

        assertEquals("saldo: 10.50", vastaus);
    }
    @Test
    public void rahaaLadataanOikein2() {
        kortti.lataaRahaa(5555);

        String vastaus = kortti.toString();

        assertEquals("saldo: 65.55", vastaus);
    }
    @Test
    public void rahaaOtetaanOikein() {
        kortti.otaRahaa(555);

        String vastaus = kortti.toString();

        assertEquals("saldo: 4.45", vastaus);
    }
     @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(1555);

        String vastaus = kortti.toString();

        assertEquals("saldo: 10.0", vastaus);
    }
    
    @Test
    public void palauttaaTrueJosRahaaTarpeeksi() {
        

        assertEquals(true,kortti.otaRahaa(1000));
    }
    
    @Test
    public void palauttaaFalseJosRahaaEiTarpeeksi() {
        

        assertEquals(false,kortti.otaRahaa(1001));
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tmjterho
 */
public class KassapaateTest {
    Kassapaate kassapaate;
    Maksukortti kortti;
    
    
    @Before
    public void setUp() {
    this.kassapaate = new Kassapaate();
    this.kortti = new Maksukortti(1000);
    }
    @Test
    public void saldoOikein(){
        assertEquals(100000, kassapaate.kassassaRahaa());
                
    }
    @Test
    public void myydytEdullisetOikein(){
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
                
    }
    @Test
    public void myydytMaukkaatOikein(){
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
                
    }
    @Test
    public void rahaaPalautuuOikeinEdullisesta(){
        
        assertEquals(1,kassapaate.syoEdullisesti(241));
                
    }
    @Test
    public void rahaaPalautuuOikeinMauukkaasta(){
        
        assertEquals(1,kassapaate.syoMaukkaasti(401));
                
    }
     @Test
    public void saldoLisaantyOikeinEdullisesta(){
        kassapaate.syoEdullisesti(240);
        kassapaate.syoEdullisesti(240);
        kassapaate.syoEdullisesti(239);
        assertEquals(2,kassapaate.edullisiaLounaitaMyyty());
                
    }
    @Test
    public void saldoLisaantyOikeinMaukkaasta(){
        kassapaate.syoMaukkaasti(400);
        kassapaate.syoMaukkaasti(400);
        kassapaate.syoMaukkaasti(399);
        assertEquals(2,kassapaate.maukkaitaLounaitaMyyty());
                
    }
    @Test
    public void palautetaanOikeinJosEiRiitaEdullisesta(){    
        assertEquals(123,kassapaate.syoEdullisesti(123));
    }
    @Test
    public void palautetaanOikeinJosEiRiitaMaukkaasta(){
        assertEquals(123,kassapaate.syoMaukkaasti(123));
    }
    @Test
    public void kassaOikeinJosEiRiitaEdullisesta(){    
        kassapaate.syoEdullisesti(123);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test
    public void kassaOikeinJosEiRiitaMaukkaasta(){
        kassapaate.syoMaukkaasti(123);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test
    public void myytyOikeinJosEiRiitaEdullisesta(){
        kassapaate.syoEdullisesti(123);
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
                
    }
    @Test
    public void myytyOikeinJosEiRiitaMaukkaasta(){
        kassapaate.syoMaukkaasti(123);
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void  palautuuTrueJosKorttiOstoOnnistuuEdullisesta(){
        assertEquals(true,kassapaate.syoEdullisesti(kortti));
    }
    @Test
    public void  palautuuTrueJosKorttiOstoOnnistuuMaukkaasta(){
        assertEquals(true,kassapaate.syoMaukkaasti(kortti));
    }
    @Test
    public void  palautuuFalseJosKorttiOstoEiOnnistuEdullisesta(){
        kortti.otaRahaa(999);
        assertEquals(false,kassapaate.syoEdullisesti(kortti));
    }
    @Test
    public void  palautuuFalseJosKorttiOstoEiOnnistuMaukkaasta(){
        kortti.otaRahaa(999);
        assertEquals(false,kassapaate.syoMaukkaasti(kortti));
    }
    @Test
    public void  kortinSaldoEiMuutuJosKorttiOstoEiOnnistuEdullisesta(){
        kortti.otaRahaa(999);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(1,kortti.saldo());
    }
    @Test
    public void  kortinSaldoEiMuutuJosKorttiOstoEiOnnistuMaukkaasta(){
        kortti.otaRahaa(999);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(1,kortti.saldo());
    }
    @Test
    public void  myytyEiMuutuJosKorttiOstoEiOnnistuEdullisesta(){
        kortti.otaRahaa(999);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void  myytySaldoEiMuutuJosKorttiOstoEiOnnistuMaukkaasta(){
        kortti.otaRahaa(999);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
    }
    
   @Test
    public void  myytyOikeinKorttiOstoEdullisesta(){
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(3,kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void  myytyOikeinKorttiOstoMaukkaasta(){
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(2,kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void  kassanSaldoEiMuutuKorttiOstollaMaukkaasta(){
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test
    public void  kassanSaldoEiMuutuKorttiOstollaEdullisesta(){
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test
    public void  kortilleRahaaLadattaessKortinSaldoMuuttuuOikein(){
        kassapaate.lataaRahaaKortille(kortti, 123);
        assertEquals(1123, kortti.saldo());
    }
    @Test
    public void  kortilleRahaaLadattaessKassanSaldoMuuttuuOikein(){
        kassapaate.lataaRahaaKortille(kortti, 123);
        assertEquals(100123, kassapaate.kassassaRahaa());
    }
    @Test
    public void  kortilleRahaaLadattaessaEiPositiivinenSummaKassanSaldoMuuttuuOikein(){
        kassapaate.lataaRahaaKortille(kortti, -1);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }  
    @Test
    public void  kortilleRahaaLadattaessaEiPositiivinenSummaKortinSaldoMuuttuuOikein(){
        kassapaate.lataaRahaaKortille(kortti, -3);
        assertEquals(1000, kortti.saldo());
    
  
    }
    
   }

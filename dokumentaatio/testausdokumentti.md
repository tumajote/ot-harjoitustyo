# Testausdokumentti

Sovellusta on testattu automatisoiduin yksikkötestein JUnitilla. Kuvanmuokkauksen toimivuuden testaus ja järjestelmätason testaus on toteutettu manuaalisesti.

## Yksikkö- ja integraatiotestaus

### sovelluslogiikka

Automatisoidut JUnit testit testaavat monipuolisesti pakkauksessa [sip.domain](https://github.com/tumajote/ot-harjoitustyo/tree/master/SimpleImageProcessor/src/main/java/sip/domain) sijaitsevaa sovelluslogiikkaa ja sen integraatiota [sip.domain.methods](https://github.com/tumajote/ot-harjoitustyo/tree/master/SimpleImageProcessor/src/main/java/sip/domain/methods) pakkauksessa sijaitsevien kuvanmuokkaus metodien ja pakkauksessa [sip.fileio.FileIo](https://github.com/tumajote/ot-harjoitustyo/tree/master/SimpleImageProcessor/src/main/java/sip/fileio) sijaitsevien tiedostonkäsittely metodien kanssa. 

Käytännössä testit käyttävät test.jpg tiedostoa. Ne testaavat, että sovelluksen toteuttamat muokkaustoimenpiteet muokkaavat kyseistä kuvatiedosta samalla tavalla kuin openCV-kirjaston tarjoamat metodit ja vertaavat sovelluksen ja kirjastometodien tuottamia matriiseja. Koska, sovellus toteuttaa kuvanmuokkauksen ImageData-luokan ja metodien yhteispelinä testaavat testit samalla niiden integraation. FileIo-luokan testit käyttävät myös test.jpg tiedostoa lataamisen ja tallentamisen testaamiseen. Koska, FileIo toimii myös käytännössä ImageData-luokan kautta tulee niiden integraatio myös samalla testattua.

### Testauskattavuus

![testikattavuus](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testikattavuus.png)

Käyttöliittymää lukuunottamatta sovelluksen testauksen rivikattavuus on 97% ja haarautumakattavuus 84%. Puuttuvat haarat ovat yksiselitteisiä gettereitä.

## Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti. 

### Asennus ja konfigurointi

Sovellus on haettu ja sitä on testattu [käyttöohjeen](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md) mukaisella tavalla muun muassa käsittelemällä erilaisia kuvia lukuisilla eri tavoilla ja tallentamalla niitä erilaisiin osoitteisiin Linux-ympäristössä.

### Toiminnallisuudet

[Vaatimusmäärittelydokumentissa](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md) ja käyttöohjeessa mainitut toiminnallisuudet on käyty läpi. 

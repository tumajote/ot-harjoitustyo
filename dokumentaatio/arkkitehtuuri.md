## Rakenne

Ohjelman rakenne noudattaa kerrosarkkitehtuuria. Koodin pakkausrakenne on seuraavanlainen:

<img src="https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/Packets.png">

Pakkaus _sip.gui_ sisältää JavaFX käyttöliittymän _sip.domain_ sovelluslogiikan, _sip.domain.methods_ kuvanmuokkaukseen käytetyt metodit ja _sip.fileIo_ levyltä lataamiseen ja sinne tallentamiseen liittyvän koodin.

## Käyttöliittymä 
Käyttöliittymä on toteutettu kokonaan luokassa [sip.gui.LaunchApplication](https://github.com/tumajote/ot-harjoitustyo/blob/master/SimpleImageProcessor/src/main/java/sip/gui/LaunchApplication.java) ja se koostuu pääosin yhdestä näkymästä, joka sisältää kaikki sovelluksen toimintaan liittyvät kontrollit. Tiedostojen avaamisen ja tallentamisen yhteydessä avautuvat omat tiedoston valitsemiseen liittyvät näkymät. 

Käyttöliittymä perustuu yhdelle javaFx:n Scene-oliolle, jonka päälle on rakennettu layout käyttäen Borderpane-oliota. Borderpanen keskelle on sijoitettu ImageView-olio, johon käsiteltävä kuva tulee näkyviin. Kuvan histogrammi, mitat sekä kuvanmuokkaukseen käytetyt kontrollit tuleva Bordepanen vasempaan laitaan aseteltuna VBox-olioon.  

Käyttöliittymä on eristetty sovelluslogiikasta ja kaikki toiminnallisuus tapahtuu kutsumalla ImageData- tai FileIo-olioiden metodeja kontrolleilla annetuilla parametreillä.

## Sovelluslogiikka

Sovelluksen sovelluslogiikka perustuu [ImageData-luokalle](https://github.com/tumajote/ot-harjoitustyo/blob/master/SimpleImageProcessor/src/main/java/sip/domain/ImageData.java), jonne käsiteltävä kuva tallentuu kuvatiedoston matriisiesityksenä (openCV Mat-olio). Imagedata olio saa matriisin [FileIo-luokalta](https://github.com/tumajote/ot-harjoitustyo/blob/master/SimpleImageProcessor/src/main/java/sip/fileio/FileIo.java), jolle annetaan polku kuvatiedostoon (joko jpeg tai png). FileIo- luokka muuntaa kuvan matriisiksi ja asettaa sen Imagedata kuvan muuttujaksi. ImageData päivittää kyseisen kuvan graafiseen käyttöliittymään käyttäen [ImageUtils-luokan](https://github.com/tumajote/ot-harjoitustyo/blob/master/SimpleImageProcessor/src/main/java/sip/domain/ImageUtils.java) matToImage-metodia. Imagedata huolehtii myös kuvan ominaisuuksien kuten mittojen ja histogrammin päivittämisestä graafiseen käyttöliittymään. 

Toiminnallisista kokonaisuuksista vastaa rajapinnan [_method_](https://github.com/tumajote/ot-harjoitustyo/blob/master/SimpleImageProcessor/src/main/java/sip/domain/methods/Method.java) toteuttavat luokat, jotka sijaitsevat pakkauksessa [sip.domain.methods](https://github.com/tumajote/ot-harjoitustyo/tree/master/SimpleImageProcessor/src/main/java/sip/domain/methods). Metodit sisältävät erilaisia tapoja muokata kuvaa ja tallentavat kyseisellä metodilla tehdyn muokkauksen parametrit. Imagedata ja sen sisältämät metodit siis pitävät yllä muokattavan kuvan tilaa ja mahdollistavat paluun alkuasetuksiin tai jonkin metodin tekemän muokkausen nollaamiseen. sip.domain.methods pakkauksessa olevien metodien on tarkoitus toteuttaa Method rajapinta, jonka avulla on helppo luoda uusia kuvanmuokkausmetodeja. Kuvan pikseliarvojen muokkaus ja kuvan mittasuhteiden muokkaus vaativat tilan säilyttämisen kannalta hieman erilaisia toimenpiteitä. Siksi kuvan mittasuhteisiin liittyvät muutokset tallentuvat Imagedatan ylläpitämään transformedMat-muuttujaan ja pikseliarvot taas processedMat-muuttujaan. originalMat-muuttuja taas pitää yllä kuvan alkuperäistä tilaa. 

Imagedatan ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

![pakkaus/luokkakaavio_SIP](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/Sip_overview.png)

## Tietojen pysyväistallennus

Kuvatiedoston lataamisesta ja tallentamisesta levylle huolehtii fileIo-luokka. Tuettavat kuvatiedostot ovat jpg ja dng.

## Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka parin päätoiminnallisuuden osalta sekvenssikaaviona. 

#### kuvan lataaminen sovellukseen
Load image nappia painaessa avautuu valintaikkuna, josta voi valita kuvatiedoston levyltä. Nappia painessa siirtyy sovelluksen kontrolli seuraavasti

![Load image toiminnallisuus](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/Load%20image.png)

Käyttäjän painaessa load image nappia tapahtumakäsittelijä kutsuu esiin Filechooser-ikkunan, joka palauttaa käyttäjän valitseman tiedoston. Graafinen käyttöliittymä kutsuu FileIO-luokan staattista metodia, joka muuntaa annetun tiedoston Mat-matriisiksi ja toimittaa sen ImageData luokalle. Imagedata luokka muuntaa matriisiin JavaFx:n näytettäväksi sopivaksi Image-olioksi, luo kuvasta histogrammin ja mitat sekä palauttaa ne graafisen käyttöliittymään käyttäjän nähtäviksi.

#### kuvan kontrastin muokkaaminen
Käyttäjän asettaessa contrast-liukurin arvoksi 2 siirtyy sovelluksen kontrolli seuraavasti. 

![Processing image toiminnallisuus](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/Processing.png)

Graafinen käyttöliittymä antaa arvon 2 ImageData-oliolle ja Imagedata kutsuu BrightnessAndContrast olion metodia, joka asetttaa kontrastia säätelevän parametrin (alpha) kakkoseksi. Tämän jälkeen graafinen käyttöliittymä kutsuu ImageData_olion process()-metodia, joka käsittelee kuvan kaikilla metodeilla ja niiden ylläptimällä parametreilla. Tämä tehdään siksi, että eri metodit muokkaavat samoja pikseliarvoja joten niiden yhdistelmä täytyy aina laskea alkuperäisille pikseleille. Toisin sanoen muutokset eivät ole kumulatiivisia vaan ne joudutaan aina laskemaan uudelleen. Tämän jälkeen ImageData-olio päivittää kuvan ja sen histogrammin ja palauttaa ne graafiselle käyttöjärjestelmälle näytettäviksi.

#### muut kuvanmuokkaus metodit

Käytännössä kaikki kuvanmuokkausmetodit noudattavat samanlaista kaavaa kuin kontrastin säätö. Pienen poikkeuksen tekee Rotate metodi, jonka tekemät muutokset tallentuvat eri muuttujaan kuin muiden metodien. Tämä siksi, että kuvan kokoon ja muotoon kohdistuvat muutokset ovat kumulatiivisia joten niiden aikaisempi tila vaikuttaa seuraavaan.

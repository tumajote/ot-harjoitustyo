## Rakenne

Ohjelman rakenne noudattaa kerrosarkkitehtuuria. Koodin pakkausrakenne on seuraavanlainen:

<img src="https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/Packets.png">

Pakkaus _sip.gui_ sisältää JavaFX käyttöliittymän _sip.domain_ sovelluslogiikan, _sip.domain.methods_ kuvanmuokkaukseen käytetyt metodit ja _sip.fileIo_ levyltä lataamiseen ja sinne tallentamiseen liittyvän koodi.

## Käyttöliittymä 
Käyttöliittymä koostuu Scene-oliosta, jonka päälle on rakennettu layout käyttäen Borderpane-oliota. Borderpanen keskelle on sijoitettu ImageView-olio, johon käsiteltävä kuva tulee näkyviin. Kuvan histogrammi, mitat sekä kuvanmuokkaukseen käytetyt kontrollit tuleva Bordepanen vasempaan laitaan.  

Käyttöliittymä on eristetty sovelluslogiikasta ja kaikki toiminnallisuus tapahtuu kutsumalla ImageData- tai FileIo-olioiden metodeja kontrolleilla annetuilla parametreillä.

## Sovelluslogiikka

Sovelluksen sovelluslogiikka perustuu ImageData-luokalle, jonne käsiteltävä kuva tallentuu kuvatiedoston matriisiesityksenä (openCV Mat-olio). Imagedata olio saa matriisin FileIo luokalta, jolle annetaan polku kuvatiedostoon (joko jpeg tai png). FileIo luokka muuntaa kuvan matriisiksi ja asettaa sen Imagedata kuvan muuttujaksi. ImageData päivittää kyseisen kuvan graafiseen käyttöliittymään käyttäen ImageUtils luokan metodeja. Imagedata myös päivittää kuvan ominaisuudet kuten mitat lukuina ja valoarvot histogrammina graafiseen käyttöliittymään. 

Toiminnallisista kokonaisuuksista vastaa rajapinnan _method_ toteuttavat luokat (pakkauksessa sip.domain.methods). Metodit sisältävät erilaisia tapoja muokata kuvaa. Imagedata ja sen sisältämät metodit pitävät yllä muokattavan kuvan tilaa ja mahdollistavat paluun alkuasetuksiin tai jonkin metodin muokkausen poistamiseen. sip.domain.methods pakkauksessa olevien metodien on tarkoitus toteuttaa Method rajapinta, jonka avulla on helppo luoda uusia kuvanmuokkausmetodeja. Kuvan pikseliarvojen muokkaus ja kuvan mittasuhteiden muokkaus vaativat tilan säilyttämisen kannalta hieman erilaisia toimenpiteitä. Siksi kuvan mittasuhteisiin liittyvät muutokset tallentuvat transformedMat-muuttujaan ja pikseliarvot taas processedMat-muuttujaan. originalMat-muuttuja taas pitää yllä kuvan alkuperäistä tilaa. 

Imagedatan ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

![pakkaus/luokkakaavio_SIP](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/Sip_overview.png)

## Tietojen pysyväistallennus

Kuvatiedoston lataamisesta ja tallentamisesta levylle huolehtii fileIo-luokka. Tuettavat kuvatiedostot ovat jpeg ja dng.

## Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka päätoiminnallisuuden osalta sekvenssikaaviona.

#### kuvan lataaminen sovellukseen
Load image nappia painaessa avautuu valintaikkuna, josta voi valita kuvatiedoston levyltä. Nappia painessa siirtyy sovelluksen kontrolli seuraavasti

![Load image toiminnallisuus](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/Load%20image.png)

Käyttäjän painaessa load image nappia tapahtumakäsittelijä kutsuu esiin Filechooser-ikkunan, joka palauttaa käyttäjän valitseman tiedoston. Graafinen käyttöliittymä kutsuu FileIO-luokan staattista metodia, joka muuntaa annetun tiedoston Mat-matriisiksi ja toimittaa sen ImageData luokalle. Imagedata luokka muuntaa matriisiin JavaFx:n näytettäväksi sopivaksi Image-olioksi, luo kuvasta histogrammin ja mitat sekä palauttaa ne graafisen käyttöliittymään käyttäjän nähtäviksi.



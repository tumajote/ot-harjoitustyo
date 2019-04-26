## Rakenne

Ohjelman rakenne noudattaa kerrosarkkitehtuuria. Koodin pakkausrakenne on seuraavanlainen:

<img src="https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/Packets.png">

Pakkaus _sip.gui_ sisältää JavaFX käyttöliittymän _sip.domain_ ja _sip.domain.methods_ sovelluslogiikan ja _fileIo_ levyltä lataamiseen ja sinne tallentamiseen liittyvän koodi.

## Sovelluslogiikka

Sovelluksen sovelluslogiikka perustuu ImageData-luokalle, jonne käsiteltävä kuva tallentuu kuvatiedoston matriisiesityksenä (openCV Mat-olio). Imagedata olio saa matriisin FileIo luokalta, jolle annetaan polku kuvatiedostoon (joko jpeg tai png). FileIo luokka muuntaa kuvan matriisiksi ja asettaa sen Imagedata kuvan muuttujaksi. ImageData päivittää kyseisen kuvan graafiseen käyttöliittymään käyttäen ImageUtils luokan metodeja. Imagedata myös päivittää kuvan ominaisuudet kuten mitat lukuina ja valoarvot histogrammina graafiseen käyttöliittymään. 

Toiminnallisista kokonaisuuksista vastaa rajapinnan metodit toteuttavat luokat (pakkauksessa sip.domain.methods). Metodit sisältävät erilaisia tapoja muokata kuvaa ja ne voidaan antaa ImageDatalle toteutettaviksi. Imagedata pitää yllä muokattavan kuvan tilaa ja mahdollistaa paluun alkuasetuksiin. sip.domain.methods pakkauksessa olevien metodien on tarkoitus toteuttaa Method rajapinta, jonka avulla on helppo luoda uusia kuvanmuokkausmetodeja. Kuvan pikseliarvojen muokkaus ja kuvan mittasuhteiden muokkaus vaativat kuitenkin imagedata luokalta hieman erilaisia toimenpiteitä, jotta kuvan tilaa voidaan pitää yllä. Siksi kuvan mittasuhteita muuttavat metodit käyttävät Imagedata luokan useTransformingMethod() metodia ja kuvan valoarvoja muokkaavat metodit useProcessingMethod() metodia.

Imagedatan ja ohjelman muiden osien suhdetta kuvaava luokka/pakkauskaavio:

![pakkaus/luokkakaavio_SIP](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/Sip_overview.png)

## Tietojen pysyväistallennus

Kuvatiedoston lataamisesta ja tallentamisesta levylle huolehtii fileIo-luokka. Tuettavat kuvatiedostot ovat jpeg ja dng.

### Päätoiminnallisuudet

Kuvataan seuraavaksi sovelluksen toimintalogiikka päätoiminnallisuuden osalta sekvenssikaaviona.

#### kuvan lataaminen sovellukseen
Load image nappia painaessa avautuu valintaikkuna, josta voi valita kuvatiedoston levyltä. Nappia painessa siirtyy sovelluksen kontrolli seuraavasti

![Load image toiminnallisuus](https://github.com/tumajote/ot-harjoitustyo/blob/master/dokumentaatio/load_image.png)

Käyttäjän painaessa load image nappia tapahtumakäsittelijä kutsuu esiin Filechooser-ikkunan, joka palauttaa käyttäjän valitseman tiedoston. Graafinen käyttöliittymä luo FileLoader olion, joka muuntaa annetun tiedoston Mat-matriisiksi ja toimittaa sen ImageData luokalle. Imagedata luokka muuntaa matriisiin JavaFx:n näytettäväksi sopivaksi Image-olioksi, luo kuvasta histogrammin ja mitat sekä palauttaa ne graafisen käyttöliittymään käyttäjän nähtäviksi.



package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto epakelpoVarasto;
    Varasto varastoAlkusaldolla;
    Varasto varastoSuurellaSaldolla;
    Varasto varastoMiinusSaldolla;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        epakelpoVarasto = new Varasto(-1);
        varastoAlkusaldolla = new Varasto(10, 5);
        varastoSuurellaSaldolla = new Varasto(10, 20);
        varastoMiinusSaldolla = new Varasto(-10, -10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoEpakelvonVaraston() {                
        assertEquals(0, epakelpoVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoAlkusaldolla() {
        assertEquals(10, varastoAlkusaldolla.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void konstruktoriLuoSuurellasaldolla() {
        assertEquals(10, varastoSuurellaSaldolla.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, varastoSuurellaSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoMiinussaldolla() {
        assertEquals(0, varastoMiinusSaldolla.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varastoMiinusSaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonMiinusMaara() {
        double alkusaldo = varastoAlkusaldolla.getSaldo();
        varastoAlkusaldolla.lisaaVarastoon(-10);
        assertEquals(alkusaldo, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaaVarastoonYlisuuriMaara() {
        double tilavuus = varastoAlkusaldolla.getTilavuus();
        varastoAlkusaldolla.lisaaVarastoon(2*tilavuus);
        assertEquals(tilavuus, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaMiinusMaara() {        
        assertEquals(0, varastoAlkusaldolla.otaVarastosta(-10), vertailuTarkkuus);
    }

    @Test
    public void otaVarastoTyhjaksi() {
        double alkusaldo = varastoAlkusaldolla.getSaldo();
        assertEquals(alkusaldo, varastoAlkusaldolla.otaVarastosta(2*alkusaldo), vertailuTarkkuus);
        assertEquals(0, varastoAlkusaldolla.getSaldo(), vertailuTarkkuus);
    }

    @Test 
    public void merkkijonoEsitysToimii() {
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varastoAlkusaldolla.toString());
    }
}
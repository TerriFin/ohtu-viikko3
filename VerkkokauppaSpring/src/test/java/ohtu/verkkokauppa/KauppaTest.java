package ohtu.verkkokauppa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author samisaukkonen
 */
public class KauppaTest {

    Pankki pankki;
    Viitegeneraattori generaattori;
    Varasto varasto;

    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        generaattori = mock(Viitegeneraattori.class);
        when(generaattori.uusi()).thenReturn(42);
        
        varasto = mock(Varasto.class);
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "juusto", 3));
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "kala", 4));

        k = new Kauppa(varasto, pankki, generaattori);
    }

    @Test
    public void tilimaksuTapahtuuOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Pekka", "12345");
        
        verify(pankki).tilisiirto(eq("Pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void tilimaksuTapahtuuKahdenEriOstoksenJalkeenOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Pekka", "12345");
        
        verify(pankki).tilisiirto(eq("Pekka"), anyInt(), eq("12345"), anyString(), eq(8));
    }
    
    @Test
    public void tilimaksuTapahtuuKahdenSamanOstoksenJalkeenOikeallaAsiakkaallaTilinumerollaJaSummalla() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("Pekka", "12345");
        
        verify(pankki).tilisiirto(eq("Pekka"), anyInt(), eq("12345"), anyString(), eq(10));
    }
    
    @Test
    public void tilimaksuOikeaVaarallaJaOikeallaTuotteella() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(3);
        k.tilimaksu("Pekka", "12345");
        
        verify(pankki).tilisiirto(eq("Pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void aloitaAsioinninKutsuminenNollaaOstoskorin() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Pekka", "12345");
        
        verify(pankki).tilisiirto(eq("Pekka"), anyInt(), eq("12345"), anyString(), eq(5));
    }
    
    @Test
    public void kauppaPyytaaUudenViitteenJokaiselleMaksulle() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Perttu", "432432");
        
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Pekka", "12345");
        
        verify(pankki, times(2)).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
    }
    
    @Test
    public void ostoskoristaVoiPoistaaOstoksia() {
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        
        k.poistaKorista(1);
        k.poistaKorista(2);
        
        k.tilimaksu("Pekka", "12345");
        
        verify(pankki).tilisiirto(eq("Pekka"), anyInt(), eq("12345"), anyString(), eq(10));

    }
}

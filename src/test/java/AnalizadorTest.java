import com.example.demoapp.entities.Analizador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


public class AnalizadorTest {
    Analizador analizador = new Analizador();


    @Test
    public void test1Mutante(){
        List<String> dna = Arrays.asList(
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        );

        boolean resultado=analizador.isMutant(dna);
        Assertions.assertTrue(resultado);
    }

    @Test
    public void test2Mutante(){
        List<String> dna = Arrays.asList(
                "AAAA",
                "CCCC",
                "TCAG",
                "GGTC"
        );

        boolean resultado=analizador.isMutant(dna);
        Assertions.assertTrue(resultado);
    }

    @Test
    public void test3Mutante(){
        List<String> dna = Arrays.asList(
                "TGAC",
                "AGCC",
                "TGAC",
                "GGTC"
        );

        boolean resultado=analizador.isMutant(dna);
        Assertions.assertTrue(resultado);
    }

    @Test
    public void test1NoMutante(){
        List<String> dna = Arrays.asList(
                "TGAC",
                "ATCC",
                "TAAC",
                "GGTC"
        );

        boolean resultado=analizador.isMutant(dna);
        Assertions.assertFalse(resultado);
    }

    @Test
    public void test2NoMutante(){
        List<String> dna = Arrays.asList(
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        );

        boolean resultado=analizador.isMutant(dna);
        Assertions.assertFalse(resultado);
    }

    @Test
    public void testMutanteColumnas(){
        List<String> dna = Arrays.asList(
                "ATGCGA",
                "AAGTGC",
                "ATGTGG",
                "AGAAGG",
                "CTCCTG",
                "TCACTG"
        );


        boolean resultado=analizador.isMutant(dna);
        Assertions.assertTrue(resultado);
    }

    @Test
    public void testMatrizNxM() {
        List<String> dna = Arrays.asList("ATG", "CAG", "TA");


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            analizador.isMutant(dna);
        });
    }

    @Test
    public void testMatriznull() {
        List<String> dna = null;


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            analizador.isMutant(dna);
        });
    }

    @Test
    public void testCaracterNoValido() {
        List<String> dna = Arrays.asList(
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGXAGG",
                "CCCCTA",
                "TCACTG"
        );


        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            analizador.isMutant(dna);
        });
    }
}

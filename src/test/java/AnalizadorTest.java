
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.demoapp.controllers.AdnController;
import com.example.demoapp.entities.Adn;
import com.example.demoapp.entities.AdnRequest;
import com.example.demoapp.services.AdnServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.demoapp.entities.Analizador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = AdnController.class)
@AutoConfigureMockMvc
public class AnalizadorTest {
    Analizador analizador = new Analizador();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AdnServiceImpl servicio;

    @Test
    public void testSaveAdnAsMutant() throws Exception {
        // Crear el ADN de prueba
        AdnRequest adnRequest = new AdnRequest();
        adnRequest.setDna(Arrays.asList("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"));

        // Configurar la simulación del servicio
        Adn adn = new Adn();
        adn.setMutant(true);
        when(servicio.findByAdnValue(anyString())).thenReturn(Optional.empty());
        when(servicio.saveAdn(anyList())).thenReturn(adn);

        // Realizar la solicitud y verificar la respuesta
        mockMvc.perform(post("/api/v1/adn/mutant/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(adnRequest)))
                .andExpect(status().isOk());
    }
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
    public void testMutanteDiagonalInvertida(){
        List<String> dna = Arrays.asList(
                "ATGCGA",
                "CTGGTC",
                "TTGTGT",
                "AGAGGA",
                "CCCCGA",
                "TCACTT"
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

package com.example.demoapp.entities;


import java.util.List;

public class Analizador {


    public Analizador(){};


    public boolean isMutant(List<String> dna) {

        if (dna==null || dna.size() == 0) {
            throw new IllegalArgumentException("La lista no puede ser vacia");
        }

        String caracteresPermitidos = "ATCG";
        for (String secuencia : dna) {
            if(secuencia.length()!=dna.size()){
                throw new IllegalArgumentException("La matriz debe ser de NxN");
            }

            for (char c : secuencia.toCharArray()) {
                if (caracteresPermitidos.indexOf(c) == -1) {

                    throw new IllegalArgumentException("La secuencia de ADN contiene caracteres no válidos: " + c);
                }
            }
        }

        int n = dna.size();
        int cont = 0;
        char secuenciaEncon='0';



        for (int i = 0; i < n; i++) {
            /*
            if (dna.get(i).contains("AAAA") || dna.get(i).contains("TTTT") || dna.get(i).contains("CCCC") || dna.get(i).contains("GGGG")) {
                cont++;
            }

             */
            for (int f = 0; f < n; f++) {

                if ((f + 3) < n && dna.get(i).charAt(f) != secuenciaEncon && dna.get(i).charAt(f) == dna.get(i).charAt(f + 3) && dna.get(i).charAt(f) == dna.get(i).charAt(f + 2) && dna.get(i).charAt(f) == dna.get(i).charAt(f + 1)) {
                    secuenciaEncon=dna.get(i).charAt(f);
                    cont++;
                    System.out.println("Fila de "+secuenciaEncon+" en: "+i+";"+f);
                    if(cont>1){return true;}
                }
                if ((f + 3) < n && dna.get(f).charAt(i) != secuenciaEncon && dna.get(f).charAt(i) == dna.get(f + 3).charAt(i) && dna.get(f).charAt(i) == dna.get(f + 2).charAt(i) && dna.get(f).charAt(i) == dna.get(f + 1).charAt(i)) {
                    secuenciaEncon=dna.get(f).charAt(i);
                    cont++;
                    System.out.println("Columna de "+secuenciaEncon+" en: "+f+";"+i);
                    if(cont>1){return true;}
                }
                if ((i + 3) < n && (f + 3) < n && dna.get(f).charAt(i) != secuenciaEncon && dna.get(f).charAt(i) == dna.get(f + 3).charAt(i + 3) && dna.get(f).charAt(i) == dna.get(f + 2).charAt(i + 2) && dna.get(f).charAt(i) == dna.get(f + 1).charAt(i + 1)) {
                    secuenciaEncon=dna.get(f).charAt(i);
                    cont++;
                    System.out.println("Diagonal de "+secuenciaEncon+" en: "+f+";"+i);
                    if(cont>1){return true;}
                }

                if ((i - 3) >= 0 && (f + 3) < n && dna.get(f).charAt(i) != secuenciaEncon && dna.get(f).charAt(i) == dna.get(f + 3).charAt(i - 3) && dna.get(f).charAt(i) == dna.get(f + 2).charAt(i - 2) && dna.get(f).charAt(i) == dna.get(f + 1).charAt(i - 1)) {
                    secuenciaEncon=dna.get(f).charAt(i);
                    cont++;
                    System.out.println("Diagonal invertida de "+secuenciaEncon+" en: "+f+";"+i);
                    if(cont>1){return true;}
                }

            }

        }


            return false;

    }

}

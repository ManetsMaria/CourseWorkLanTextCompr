package compress.lz;

import compress.Compress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LZ78 implements Compress {

    List<String> diccionario;

    public LZ78() {
        diccionario = new ArrayList<String>();
        diccionario.add(null);
    }


    public List<Character> codificar(List<Character> listaNumeros) {
        String codif = "";
        List<Character> listaC = new ArrayList<Character>();
        String letra = "";
        boolean brea = false;
        int posicion = 0;
        for (int i = 0; i < listaNumeros.size(); i++) {
            int num = listaNumeros.get(i);
            letra += (char) num;

            if (!diccionario.contains(letra)) {
                diccionario.add(letra);
                codif += 0;
                listaC.add('0');
                codif += letra;
                char paso = letra.charAt(0);
                listaC.add(paso);
                letra = "";

            } else {

                while (diccionario.contains(letra)) {

                    i++;

                    if (i >= listaNumeros.size()) {

                        brea = true;


                        if (!diccionario.contains(letra)) {
                            listaC.add('0');
                            char paso2 = letra.charAt(0);
                            listaC.add(paso2);
                        } else {
                            int index = diccionario.indexOf(letra);
                            String paso3 = "" + index;
                            for (int j = 0; j < paso3.length(); j++) {
                                listaC.add(paso3.charAt(j));
                            }

                        }
                        System.out.println("BREAK!");
                        break;
                    }
                    posicion = diccionario.indexOf(letra);
                    num = listaNumeros.get(i);
                    letra += (char) num;


                }

                if (brea) {
                    break;
                }

                i++;

                diccionario.add(letra);
                letra = "" + letra.charAt(letra.length() - 1);
                codif += posicion;

                String numero = "" + posicion;
                for (int j = 0; j < numero.length(); j++) {
                    listaC.add(numero.charAt(j));
                }


                codif += letra;
                char paso = letra.charAt(0);
                listaC.add(paso);

                letra = "";
                i--;

            }
        }

        System.out.println(codif);

        return listaC;
    }


    public String compress(String text) {
        List<Character> code = codificar(text.chars().mapToObj(c -> (char) c).collect(Collectors.toList()));

        char[] codigo = new char[code.size()];
        StringBuilder dec = new StringBuilder();
        for (int j = 0; j < code.size(); j++) {
            //System.out.print(j+":'"+code.get(j)+"'   ");
            codigo[j] = code.get(j);

            dec.append(codigo[j]);
        }
        return dec.toString();
    }
}
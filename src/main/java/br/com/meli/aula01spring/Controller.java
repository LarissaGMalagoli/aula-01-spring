package br.com.meli.aula01spring;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

    }

    public final static String toRoman(int number) {
        int l =  map.floorKey(number);
        if ( number == l ) {
            return map.get(number);
        }
        return map.get(l) + toRoman(number-l);
    }


    public static String conversorMorse(String[] code,
                                      String morseCode)
    {

        Map<String, Character> morseTraducao
                = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            morseTraducao.put(code[i], (char)('a' + i));
        }

        String[] array = morseCode.split(" ");

        String resultado="";
        for (int i = 0; i < array.length; i++) {
            resultado+=morseTraducao.get(array[i]);
        }

        return resultado;
    }

    @RequestMapping("/ed1/{p}")
    @ResponseBody
    public String endpoint1(@PathVariable String p) {
        //return "Nosso primeiro endpoint";
        //return "{'id': '1', 'nome': 'teste'}";
        int num = Integer.parseInt(p);


        return "recebido: " + p + " retornando:" + (String)(toRoman(num));
    }

    @RequestMapping("/ed2/{p}")
    @ResponseBody
    public String endpoint2(@PathVariable String p) {
        //return "Nosso primeiro endpoint";
        //return "{'id': '1', 'nome': 'teste'}"

        // Morse code by indexing
        String[] code
                = { ".-",   "-...", "-.-.", "-..",  ".",
                "..-.", "--.",  "....", "..",   ".---",
                "-.-",  ".-..", "--",   "-.",   "---",
                ".--.", "--.-", ".-.",  "...",  "-",
                "..-",  "...-", ".--",  "-..-", "-.--",
                "--..", "|" };

        // Given Strings
        String morseCode = "... -.-. .... --- --- .-..";

        System.out.println();

        // English to morse code

        return "recebido: " + p + " retornando:" + (conversorMorse(code, p));
    }

}

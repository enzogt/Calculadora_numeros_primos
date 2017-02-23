package es.enzogt.primeraparactica;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class NumeroPrimo {

    private String patron;
    private final int limiteDigitos = 5;
    private List<Integer> arrayPrimos = new ArrayList<Integer>();
    private boolean unoEsPrimo = false;

    public NumeroPrimo() {
        //Se ocupa la posición 0 del array.
        arrayPrimos.add(1);
    }

    public void setUnoEsPrimo (boolean entrada){
        unoEsPrimo = entrada;
    }

    public void setPatron (String patronEntrada) {
        patron = patronEntrada;
    }

    public boolean esNumero (String entrada) {
        return entrada.matches("[-+]?\\d*\\.?\\d+");
    }

    public boolean esValido (String entrada) {
        return entrada.length() <= limiteDigitos && Integer.valueOf(entrada) > 0;
        //(entrada.replace("0", "")).length() == 0
    }

    public String ponerResultado (String entrada) {

        return patron.replace("{0}", entrada).replace("{1}", calcularPrimo(entrada));
    }

    private String calcularPrimo (String entrada){
        int tope = Integer.valueOf(entrada);
        int contador = 0;
        int numero = 0;
        int primoBuscado;

        //Si el número primo aún no ha sido encontrado, se busca.
        if (tope >= arrayPrimos.size()) {
            //Se parte del último primo encontrado para no gastar CPU.
            numero = arrayPrimos.get(arrayPrimos.size() - 1);
            contador = arrayPrimos.size() - 1;

            while (contador < tope) {

                numero++;

                if (esPrimo(numero)) {
                    contador++;
                    arrayPrimos.add(numero);
                }
            }
        }

        //Dependiendo del valor del CheckBox del menú, se tratará la primitividad del número 1.
        primoBuscado = unoEsPrimo ? tope - 1 : tope;

        //Se devuelve el número primo buscado.
        return String.valueOf(arrayPrimos.get(primoBuscado));
    }

    private boolean esPrimo (int numero) {

        //Se descarta si es par y además no es 2.
        if (numero % 2 == 0 && numero != 2)
            return false;

        //Se comprueban los números impares hasta el tope como máximo.
        for(int i = 3; i*i <= numero; i += 2)
            if(numero % i == 0)
                return false;

        return true;
    }

}

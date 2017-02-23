package es.enzogt.primeraparactica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity implements View.OnClickListener{

    EditText input;
    Button boton;
    TextView resultado;

    NumeroPrimo NumeroPrimo;

    String valor;
    String textoToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Inicializo la clase de los numeros primos y le paso el patron de respuesta.
        NumeroPrimo = new NumeroPrimo();
        NumeroPrimo.setPatron(getString(R.string.textoPatronRespuesta));

        input = (EditText)findViewById(R.id.txtEntrada);
        boton = (Button)findViewById(R.id.btnCalcular);
        resultado = (TextView)findViewById(R.id.lblResultado);

        textoToast = getString(R.string.textoUnoEsPrimo);

        boton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_principal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.opcionUnoEsPrimo) {

            boolean unoSeConsideraPrimo = !item.isChecked();

            item.setChecked(unoSeConsideraPrimo);
            NumeroPrimo.setUnoEsPrimo(unoSeConsideraPrimo);

            Toast.makeText(
                    getApplicationContext(),
                    textoToast.replace("{0}", (unoSeConsideraPrimo ? "Se" : "No se")),
                    Toast.LENGTH_SHORT
            ).show();
        }
        return true;
    }

    public void onClick (View view){

        valor = input.getText().toString();
        valor = valor.trim();

        if(NumeroPrimo.esNumero(valor)){

            if (NumeroPrimo.esValido(valor)){

                resultado.setText(NumeroPrimo.ponerResultado(valor));

            } else {
                resultado.setText(getString(R.string.textoMayorLimite));
            }

        } else {
            resultado.setText(getString(R.string.textoNoNumero));
        }
    }

}

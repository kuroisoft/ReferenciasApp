/*------------------------------------------------------------------------------------------
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2017    HORA: 10-11 HRS
:*
:*                             Clase del activity_wikipedia.
:*
:*  Archivo     : WikipediaActivity.java
:*  Autor       : Ernesto Daniel Barajas Contreras  14130713
:*                Jose Luis Chavez Alvarado         14130787
:*                Gustavo Alonzo Gonzales Benavente 14130959
:*  Fecha       : 22/Septiembre/2017
:*  Compilador  : Android Studio 2.3.3
:*  Descripción : Esta aplicación recauda los datos de un libre, pagina web, y de un video
:*                en la web, y crea su ficha bibliográfica en base a las normas APA.
:*
:*------------------------------------------------------------------------------------------*/
//-----------------------------------------------------------------------------------------------
package com.bash.app.referenciasapp;
//-----------------------------------------------------------------------------------------------
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//-----------------------------------------------------------------------------------------------
public class WikipediaActivity extends AppCompatActivity implements View.OnClickListener {
//-----------------------------------------------------------------------------------------------
    //Estas son referencias a los EditText's en nuestro archivo xml
    private EditText _txtNombreArticulo, _txtUrl;
    private EditText _txtDia, _txtAnio, _txtMes;

    //Estos son referencias a los Botones en nuestro archivo xml
    private Button _btnCreaFicha, _btnLimpiaCampos;
//-----------------------------------------------------------------------------------------------
    //Método onCreate que se llama cada vez que se muestra esta pantalla
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wikipedia);
        iniciaControles(); //referenciamos a los controles de nuestra aplicacion grafica
        creaEventos(); //Asignamos a los eventos correspondients de nuestros controles (botones y editText's)
    }
//-----------------------------------------------------------------------------------------------
    //Metodo que inicializa los controles de la interfaz grafica para su posterior uso
    private void iniciaControles() {
        _txtNombreArticulo = (EditText) findViewById(R.id.txtWikNombreArticulo);
        _txtUrl = (EditText) findViewById(R.id.txtWikUrl);
        _txtDia = (EditText) findViewById(R.id.txtWikDia);
        _txtAnio = (EditText) findViewById(R.id.txtWikAnio);
        _txtMes = (EditText) findViewById(R.id.txtWikMes);

        _btnCreaFicha = (Button) findViewById(R.id.btnWikCreaFicha);
        _btnLimpiaCampos = (Button) findViewById(R.id.btnWikBorraDatos);

    }
//-----------------------------------------------------------------------------------------------
    //Metodo para la creacion de listeners (eventos) que reaccionaran a la interaccion con el usuario
    private void creaEventos() {
        _btnCreaFicha.setOnClickListener(this);
        _btnLimpiaCampos.setOnClickListener(this);
    }
//-----------------------------------------------------------------------------------------------
    //Evento generado por android estudio en el que llegaran todos los
    //listeners click de la aplicacion
    @Override
    public void onClick(View view) {
        //difetenciaremos el boton que llama al evento onClick segun el id de la propia vista.
        switch (view.getId()) {
            case R.id.btnWikCreaFicha:
                //Iniciaremos una nueva actividad, en este caso la
                //FichaActivity para mostrar le resultado

                //Primero obtenemos los datos de los campos.
                String nombre = _txtNombreArticulo.getText().toString();
                String url = _txtUrl.getText().toString();
                String dia = _txtDia.getText().toString();
                String mes = _txtMes.getText().toString();
                String anio = _txtAnio.getText().toString();

                //Despues validamos que todos los datos esten completos.
                boolean isComplete = nombre.length() * url.length() *
                        dia.length() * mes.length() * anio.length() > 0;

                if (isComplete) {
                    //Después de comprobar que se llenaron todos los campos
                    //procedemos a hacer el formato de la ficha.
                    String ficha = nombre + ". (s.f.). En Wikipedia. Recuperado el " +
                            dia + " de " + mes + " de " + anio + " de " + url;

                    Intent intent = new Intent(getApplicationContext(), FichaActivity.class);
                    intent.putExtra("ficha", ficha);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            getResources().getText(R.string.validarCampos),
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnWikBorraDatos: //Limpiaremos todos los EditText
                limpiaCampos();
                break;
        }
    }
//-----------------------------------------------------------------------------------------------
    //Metodo que limpia todos los inputs del activity
    private void limpiaCampos() {
        _txtNombreArticulo.setText("");
        _txtUrl.setText("");
        _txtDia.setText("");
        _txtAnio.setText("");
        _txtMes.setText("");

        Toast.makeText(getApplicationContext(),
                getResources().getText(R.string.camposVacios),
                Toast.LENGTH_SHORT).show();
    }
//----------------------------------------------------------------------------------------------
}

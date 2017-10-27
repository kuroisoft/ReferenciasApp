/*------------------------------------------------------------------------------------------
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2017    HORA: 10-11 HRS
:*
:*                             Clase del activity_libro.
:*
:*  Archivo     : LibroActivity.java
:*  Autor       : Ernesto Daniel Barajas Contreras  14130713
:*                Jose Luis Chavez Alvarado         14130787
:*                Gustavo Alonzo Gonzales Benavente 14130959
:*  Fecha       : 24/Septiempre/2017
:*  Compilador  : Android Studio 2.3.3
:*  Descripción : Esta aplicación recauda los datos de un libro, pagina web, y de un video
:*                en la web, y crea su ficha bibliográfica en base a las normas APA.
:*
:*------------------------------------------------------------------------------------------*/
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
public class LibroActivity extends AppCompatActivity implements View.OnClickListener {
//-----------------------------------------------------------------------------------------------
    //Estas son referencias a los EditText's en nuestro archivo xml
    private EditText _txtNombreAutor, _txtApellidoAutor, _txtTitulo;
    private EditText _txtAnio, _txtCiudad, _txtPais, _txtEditoria;

    //Estos son referencias a los Botones en nuestro archivo xml
    private Button _btnCreaFicha, _btnLimpiaCampos;

//-----------------------------------------------------------------------------------------------
    //Método onCreate que se ejecuta cada vez que se muestra la pantalla
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);
        iniciaControles(); //referenciamos a los controles de nuestra aplicacion grafica
        creaEventos(); //Asignamos a los eventos correspondients de nuestros controles
    }
//-----------------------------------------------------------------------------------------------
    //Metodo que inicializa los controles de la interfaz grafica para su posterior uso
    private void iniciaControles() {
        _txtNombreAutor = (EditText) findViewById(R.id.txtLbrNombreAutor);
        _txtApellidoAutor = (EditText) findViewById(R.id.txtLbrAPellidosAutor);
        _txtTitulo = (EditText) findViewById(R.id.txtLbrTitulo);
        _txtAnio = (EditText) findViewById(R.id.txtLbrAnio);
        _txtCiudad = (EditText) findViewById(R.id.txtLbrCiudad);
        _txtPais = (EditText) findViewById(R.id.txtLbrPais);
        _txtEditoria = (EditText) findViewById(R.id.txtLbrEditoria);

        _btnCreaFicha = (Button) findViewById(R.id.btnLbrCreaFicha);
        _btnLimpiaCampos = (Button) findViewById(R.id.btnLbrBorraDatos);

    }
//-----------------------------------------------------------------------------------------------
    //Metodo para la creacion de listeners (eventos) que reaccionaran a la
    // interaccion con el usuario
    private void creaEventos() {
        _btnCreaFicha.setOnClickListener(this);
        _btnLimpiaCampos.setOnClickListener(this);
    }
//-----------------------------------------------------------------------------------------------
    //Evento generado por android estudio en el que llegaran todos los
    // listeners click de la aplicacion
    @Override
    public void onClick(View view) {
        //Distinguimos el boton que llama al evento onClick segun el id de la propia vista.
        switch (view.getId()){
        //Iniciaremos una nueva actividad, en este caso la FichaActivity para mostrar le resultado
            case R.id.btnLbrCreaFicha:
                //Primero obtenemos los datos de los textView
                String nombreAutor = _txtNombreAutor.getText().toString();
                String apellidoAutor =  _txtApellidoAutor.getText().toString();
                String titulo = _txtTitulo.getText().toString();
                String anio = _txtAnio.getText().toString();
                String ciudad = _txtCiudad.getText().toString();
                String pais = _txtPais.getText().toString();
                String editorial = _txtEditoria.getText().toString();

                //Después obtenemos si estan todos los campos llenos.
                boolean isComplete = nombreAutor.length() *
                        apellidoAutor.length() * titulo.length() *
                        anio.length() * ciudad.length() * pais.length() *
                        editorial.length() > 0;

                if(isComplete) {
                    //Comprobando si los datos entán completos,
                    //procedemos a concatenarlos en el formato deseado.
                    String ficha = apellidoAutor + ", " + nombreAutor.toUpperCase().charAt(0) +
                                    ". (" + anio + "). ";

                    ficha += "\"" + titulo + "\", " + ciudad + ", " +
                             pais + ": " + editorial + ".";

                    //Después mandamos el la variable a la invocacion del siguiente activity.
                    Intent intent = new Intent(getApplicationContext(), FichaActivity.class);
                    intent.putExtra("ficha", ficha);
                    startActivity(intent);
                } else {
                    Toast.makeText( getApplicationContext(),
                                    getResources().getText(R.string.validarCampos),
                                    Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnLbrBorraDatos: //Limpiaremos todos los EditText
                limpiaCampos();
                break;
        }
    }
//-----------------------------------------------------------------------------------------------
    //Metodo que limpia todos los inputs del activity
    private void limpiaCampos(){
        _txtNombreAutor.setText("");
        _txtApellidoAutor.setText("");
        _txtTitulo.setText("");
        _txtAnio.setText("");
        _txtCiudad.setText("");
        _txtPais.setText("");
        _txtEditoria.setText("");
        Toast.makeText(getApplicationContext(), getResources().getText(R.string.camposVacios), Toast.LENGTH_SHORT).show();
    }
//-----------------------------------------------------------------------------------------------
}

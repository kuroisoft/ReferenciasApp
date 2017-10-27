/*------------------------------------------------------------------------------------------
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2017    HORA: 10-11 HRS
:*
:*                             Clase del activity_video.
:*
:*  Archivo     : VideoActivity.java
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
public class VideoActivity extends AppCompatActivity implements View.OnClickListener {
//-----------------------------------------------------------------------------------------------
    //Estas son referencias a los EditText's en nuestro archivo xml
    private EditText _txtNombreAutor, _txtApellidoAutor, _txtTitulo;
    private EditText _txtUsuario, _txtUrl, _txtDia, _txtAnio, _txtMes;

    //Estos son referencias a los Botones en nuestro archivo xml
    private Button _btnCreaFicha, _btnLimpiaCampos;
//-----------------------------------------------------------------------------------------------
    //Método onCreate que se llama cada vez que se muestra esta pantalla
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        iniciaControles(); //referenciamos a los controles de nuestra aplicacion grafica
        creaEventos(); //Asignamos a los eventos correspondients de nuestros controles
    }
//-----------------------------------------------------------------------------------------------
    //Metodo que inicializa los controles de la interfaz grafica para su posterior uso
    private void iniciaControles() {
        _txtNombreAutor = (EditText) findViewById(R.id.txtVidNombreAutor);
        _txtApellidoAutor = (EditText) findViewById(R.id.txtVidAPellidosAutor);
        _txtTitulo = (EditText) findViewById(R.id.txtVidTitulo);
        _txtUsuario = (EditText) findViewById(R.id.txtVidUsuario);
        _txtUrl = (EditText) findViewById(R.id.txtVidUrl);
        _txtDia = (EditText) findViewById(R.id.txtVidDia);
        _txtAnio = (EditText) findViewById(R.id.txtVidAnio);
        _txtMes = (EditText) findViewById(R.id.txtVidMes);

        _btnCreaFicha = (Button) findViewById(R.id.btnVidCreaFicha);
        _btnLimpiaCampos = (Button) findViewById(R.id.btnVidBorraDatos);

    }
//-----------------------------------------------------------------------------------------------
    //Metodo para la creacion de listeners (eventos) que reaccionaran a la interaccion con el usuario
    private void creaEventos() {
        _btnCreaFicha.setOnClickListener(this);
        _btnLimpiaCampos.setOnClickListener(this);
    }
//-----------------------------------------------------------------------------------------------
    //Evento generado por android estudio en el que llegaran todos los listeners click de la aplicacion
    @Override
    public void onClick(View view) {
        //difetenciaremos el boton que llama al evento onClick segun el id de la propia vista.
        switch (view.getId()) {
            case R.id.btnVidCreaFicha:
                //Iniciaremos una nueva actividad, en este caso la FichaActivity
                //para mostrar le resultado

                //Primero guardamos todos los valores de los campos.
                String nombreAutor = _txtNombreAutor.getText().toString();
                String apellidoAutor = _txtApellidoAutor.getText().toString();
                String titulo = _txtTitulo.getText().toString();
                String usuario = _txtUsuario.getText().toString();
                String url = _txtUrl.getText().toString();
                String dia = _txtDia.getText().toString();
                String mes = _txtMes.getText().toString();
                String anio = _txtAnio.getText().toString();

                //Después validamos que todos los campos estén llenos.
                boolean isComplete = nombreAutor.length() * apellidoAutor.length() *
                        titulo.length() * usuario.length() * url.length() *
                        dia.length() * mes.length() * anio.length() > 0;

                if (isComplete) {

                    //Ahora validando si todos los campos están llenos,
                    //procedemos a ir haciendo la cadena de la ficha.
                    String ficha = apellidoAutor + ", " + nombreAutor.toUpperCase().charAt(0) +
                                    ". [" + usuario + "]. (" + anio + ", " + mes + ", " +
                                    dia + "). " + titulo +
                                    getResources().getString(R.string.videoRef) + url;

                    Intent intent = new Intent(getApplicationContext(), FichaActivity.class);
                    intent.putExtra("ficha", ficha);
                    startActivity(intent);
                } else {
                    //Si los campos no estám llenos, mostramos un mensaje.
                    Toast.makeText(getApplicationContext(),
                            getResources().getText(R.string.validarCampos),
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnVidBorraDatos: //Limpiaremos todos los EditText
                limpiaCampos();
                break;
        }
    }
//-----------------------------------------------------------------------------------------------
    //Metodo que limpia todos los inputs del activity
    private void limpiaCampos() {
        _txtNombreAutor.setText("");
        _txtApellidoAutor.setText("");
        _txtTitulo.setText("");
        _txtUsuario.setText("");
        _txtUrl.setText("");
        _txtDia.setText("");
        _txtAnio.setText("");
        _txtMes.setText("");

        Toast.makeText(getApplicationContext(),
                getResources().getText(R.string.camposVacios),
                Toast.LENGTH_SHORT).show();
    }
}

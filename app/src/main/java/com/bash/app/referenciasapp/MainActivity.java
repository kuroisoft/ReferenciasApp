/*------------------------------------------------------------------------------------------
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                       TOPICOS AVANZADOS DE PROGRAMACION "B"
:*
:*                   SEMESTRE: ENE-JUN/2017    HORA: 10-11 HRS
:*
:*                             Clase del activity_main.
:*
:*  Archivo     : MainActivity.java
:*  Autor       : Ernesto Daniel Barajas Contreras  14130713
:*                Jose Luis Chavez Alvarado         14130787
:*                Gustavo Alonzo Gonzales Benavente 14130959
:*  Fecha       : 24/Septiembre/2017
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
import android.widget.Toast;
//-----------------------------------------------------------------------------------------------
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//-----------------------------------------------------------------------------------------------
    private Button _btnLibro, _btnWikipedia, _btnYoutube, _btnAcerca, _btnSalir;
//----------------------------------------------------------------------------------------------
    //Método onCreate que se llama cada vez que se muestra esta pantalla
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciaControles(); //referenciamos a los controles de nuestra aplicacion grafica
        creaEventos(); //Asignamos a los eventos correspondients de nuestros controles (botones)
    }
//-----------------------------------------------------------------------------------------------
    //Metodo que inicializa los controles de la interfaz grafica para su posterior uso
    private void iniciaControles(){
        _btnLibro = (Button) findViewById(R.id.btnMainFichaLibro);
        _btnWikipedia = (Button) findViewById(R.id.btnMainFichaPagina);
        _btnYoutube = (Button) findViewById(R.id.btnMainFichaVideo);
        _btnAcerca = (Button) findViewById(R.id.btnMainAcerca);
        _btnSalir = (Button) findViewById(R.id.btnMainSalir);
    }
//-----------------------------------------------------------------------------------------------
    //Metodo para la creacion de listeners (eventos) que reaccionaran a la
    //interaccion con el usuario
    private void creaEventos(){
        _btnLibro.setOnClickListener(this);
        _btnWikipedia.setOnClickListener(this);
        _btnYoutube.setOnClickListener(this);
        _btnAcerca.setOnClickListener(this);
        _btnSalir.setOnClickListener(this);
    }
//-----------------------------------------------------------------------------------------------
    //Evento generado por android estudio en el que llegaran todos los
    //listeners click de la aplicacion
    @Override
    public void onClick(View view) {
        //difetenciaremos el boton que llama al evento onClick segun el id de la propia vista.
        switch (view.getId()) {
            case R.id.btnMainFichaLibro:
                //Iniciaremos una nueva actividad, en este caso el LibroActivity
                startActivity(new Intent(getApplicationContext(), LibroActivity.class));
                break;
            case R.id.btnMainFichaPagina:
                //Iniciaremos una nueva actividad, en este caso el WikipediaActivity
                startActivity(new Intent(getApplicationContext(), WikipediaActivity.class));
                break;
            case R.id.btnMainFichaVideo:
                //Iniciaremos una nueva actividad, en este caso el VideoActivity
                startActivity(new Intent(getApplicationContext(), VideoActivity.class));
                break;
            case R.id.btnMainAcerca:
                acercaDeClick();
                break;
            case R.id.btnMainSalir:
                finish();
                break;
        }
    }
//-----------------------------------------------------------------------------------------------
    //Método que muestra un Toast con la información de la aplicación
    public void acercaDeClick(){
        String version = getResources().getString(R.string.app_version);
        Toast.makeText( getApplicationContext(),
                "MatematicaApp " + version + "\n\n"                                         +
                        "Empresa: Bash\n"                                                   +
                        "Director: Gustavo Alonso González Benavente #14130959\n"           +
                        "Jefe de desarrollo: Ernesto Daniel Barajas Contreras #14130713\n"  +
                        "Jefe de RP: Jose Luis Chavez Alvarado #14130787\n\n"               +
                        "(c) Tecnológico de la Laguna\n"                                    +
                        "Torreón, Coah. México",
                Toast.LENGTH_LONG).show();
    }

}

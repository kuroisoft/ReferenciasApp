
/*------------------------------------------------------------------------------------------
:*                       INSTITUTO TECNOLOGICO DE LA LAGUNA
:*                     INGENIERIA EN SISTEMAS COMPUTACIONALES
:*                     PROGRAMACION DE DISPOSITIVOS MOVILES "A"
:*
:*                   SEMESTRE: AGO-DIC/2017    HORA: 10-11 HRS
:*
:*                             Clase del activity_ficha.
:*
:*  Archivo     : FichaActivity.java
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
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
//-----------------------------------------------------------------------------------------------
public class FichaActivity extends AppCompatActivity {
//-----------------------------------------------------------------------------------------------
    private TextView _txtFichaResultado;
//-----------------------------------------------------------------------------------------------

    //Método onCreate que se ejecuta cada vez que se muestra la pantalla
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha);
        //Obtenemos la referencia al TextView en donde se mostrará la ficha.
        _txtFichaResultado = (TextView) findViewById(R.id.txtFichaResultado);

        //Obtendremos el nombre de la ficha.
        Intent intent = getIntent();
        String ficha = intent.getStringExtra("ficha");

        //Mostramos la ficha en el control.
        _txtFichaResultado.setText(ficha);

        //Asignamos un metodo anonimo al onClickListener del botón, aqui se copiara la ficha al
        //portapapeles del sistema
        findViewById(R.id.btnFichaCopiar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(
                        getApplicationContext().CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText(  "El texto se ha copiado",
                                                        _txtFichaResultado.getText().toString());
                Toast.makeText( getApplicationContext(),
                                getResources().getText(R.string.msjClip),
                                Toast.LENGTH_SHORT).show();
                clipboard.setPrimaryClip(clip);
            }
        });
    }
//-----------------------------------------------------------------------------------------------
}

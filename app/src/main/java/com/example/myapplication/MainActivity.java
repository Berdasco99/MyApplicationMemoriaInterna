package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText et1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String archivos[] = fileList();
        et1 = (EditText)findViewById(R.id.txt_agenda);

        //Este metodo lee el contenido de agenda.txt lo almacena en la variable br que a su vez es leida y almacenada por la String linea la cual es imprimida en el multitext.
        if (ArchivoExiste(archivos, "agenda.txt")){
            try {
                InputStreamReader archivo = new InputStreamReader(openFileInput("agenda.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String lista_tareas ="";
                while (linea != null){
                    lista_tareas = lista_tareas + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                et1.setText(lista_tareas);
            }catch (IOException e){
                Toast.makeText(this, "Error al leer los archivos", Toast.LENGTH_SHORT).show();
            }

        }


    }

    private boolean ArchivoExiste (String archivos[], String nombreArchivo){
        for (int i=0;i<archivos.length;i++){
            if (nombreArchivo.equals(archivos[i])){
                return true;
            }
        }
        return false;
    }


    //Este metodo sirve para guardar lo que escribo en el multitext sin que se borren al cerrar la app.
    public void Guardar (View view){

        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("agenda.txt", Activity.MODE_PRIVATE));
            archivo.write(et1.getText().toString());
            archivo.flush();
            archivo.close();
        }catch (IOException e){
            Toast.makeText(this, "Error al crear el archivo", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Lista de tareas a guardada", Toast.LENGTH_SHORT).show();

    }
}
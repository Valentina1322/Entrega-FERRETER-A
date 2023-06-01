package com.example.fereteria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText txtCedula, txtNombre, txtDireccion, txtTelefono;
    Button btnCrear, btnCargar, btnActualizar, btnEliminar, btnLimpiar, btnPedidosActivity, btnFacturasActivity, btnProductosActivity;
    DatabaseManager databaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCedula = findViewById(R.id.txtCedula);
        txtNombre = findViewById(R.id.txtNombre);
        txtDireccion = findViewById(R.id.txtDireccion);
        txtTelefono = findViewById(R.id.txtTelefono);

        btnCrear = findViewById(R.id.btnCrear);
        btnCargar = findViewById(R.id.btnCargar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnLimpiar = findViewById(R.id.btnLimpiar);

        btnPedidosActivity = findViewById(R.id.btnPedidosActivity);
        btnFacturasActivity = findViewById(R.id.btnFacturasActivity);
        btnProductosActivity= findViewById(R.id.btnProductosActivity);

        databaseManager = new DatabaseManager(this);

        btnPedidosActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PedidosActivity.class);
                startActivity(intent);
            }
        });

        btnFacturasActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FacturaActivity.class);
                startActivity(intent);
            }
        });
        btnProductosActivity.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductosActivity.class);
                startActivity(intent);
            }
        });

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cedula = txtCedula.getText().toString();
                String nombre = txtNombre.getText().toString();
                String direccion = txtDireccion.getText().toString();
                String telefono = txtTelefono.getText().toString();
                long result = databaseManager.insertClient(cedula, nombre, direccion, telefono);
                if (result != -1) {
                    Toast.makeText(getApplicationContext(), "REGISTRO GUARDADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cedula = txtCedula.getText().toString();
                String nombre = txtNombre.getText().toString();
                String direccion = txtDireccion.getText().toString();
                String telefono = txtTelefono.getText().toString();
                int result = databaseManager.updateClient(cedula, nombre, direccion, telefono);
                if (result > 0) {
                    Toast.makeText(getApplicationContext(), "REGISTRO MODIFICADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL MODIFICAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cedula = txtCedula.getText().toString();
                int result = databaseManager.deleteClient(cedula);
                if (result > 0) {
                    Toast.makeText(getApplicationContext(), "REGISTRO ELIMINADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL ELIMINAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = txtCedula.getText().toString();
                Client client = databaseManager.getClientByCedula(cedula);
                if (client != null) {
                    txtNombre.setText(client.getNombre());
                    txtDireccion.setText(client.getDireccion());
                    txtTelefono.setText(client.getTelefono());
                    Toast.makeText(getApplicationContext(), "REGISTRO CARGADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "CLIENTE NO ENCONTRADO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLimpiar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){
                limpiarCampos();
            }
        });
    }
    // Limpiar los campos de texto
    private void limpiarCampos() {
        txtCedula.setText("");
        txtNombre.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
    }
}

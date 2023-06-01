package com.example.fereteria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductosActivity extends AppCompatActivity {

    Button btnCrearProducto, btnBuscarProducto, btnActualizarProducto, btnLimpiarProducto, btnEliminarProducto;
    EditText txtCodProducto, txtValorProducto, txtDescripcionProducto;
    DatabaseManager databaseManager;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        databaseManager = new DatabaseManager(this);

        txtCodProducto = findViewById(R.id.txtCodProducto);
        txtValorProducto = findViewById(R.id.txtValorProducto);
        txtDescripcionProducto = findViewById(R.id.txtDescripcionProducto);

        btnCrearProducto = findViewById(R.id.btnCrearProducto);
        btnBuscarProducto = findViewById(R.id.btnBuscarProducto);
        btnActualizarProducto = findViewById(R.id.btnActualizarProducto);
        btnLimpiarProducto = findViewById(R.id.btnLimpiarProducto);
        btnEliminarProducto = findViewById(R.id.btnEliminarProducto);

        btnCrearProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = txtCodProducto.getText().toString();
                String descripcion = txtDescripcionProducto.getText().toString();
                String valor = txtValorProducto.getText().toString();

                long result = databaseManager.insertProducto(codigo, descripcion, valor);
                if (result != -1) {
                    Toast.makeText(getApplicationContext(), "REGISTRO GUARDADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    limpiarProducto();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnBuscarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = txtCodProducto.getText().toString();
                Producto producto = databaseManager.getProductoById(codigo);
                if (producto != null) {
                    txtDescripcionProducto.setText(producto.getDescripcion());
                    txtValorProducto.setText(producto.getValor());
                    Toast.makeText(getApplicationContext(), "REGISTRO CARGADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "REGISTRO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnActualizarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = txtCodProducto.getText().toString();
                String descripcion = txtDescripcionProducto.getText().toString();
                String valor = txtValorProducto.getText().toString();
                int result = databaseManager.updateProducto(codigo, descripcion, valor);
                if (result > 0) {
                    Toast.makeText(getApplicationContext(), "REGISTRO MODIFICADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    limpiarProducto();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL MODIFICAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnLimpiarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarProducto();
            }
        });

        btnEliminarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = txtCodProducto.getText().toString();

                int result = databaseManager.deleteProducto(codigo);

                if (result > 0) {
                    Toast.makeText(getApplicationContext(), "REGISTRO ELIMINADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    limpiarProducto();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL ELIMINAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void limpiarProducto(){
        txtCodProducto.setText("");
        txtValorProducto.setText("");
        txtDescripcionProducto.setText("");
    }
}

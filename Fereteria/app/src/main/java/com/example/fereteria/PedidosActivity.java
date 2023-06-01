package com.example.fereteria;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PedidosActivity extends AppCompatActivity {
    EditText txtCodPedidoProducto, txtFechaPedido, txtCedulaPedido, txtNombrePedido, txtDireccionPedido, txtTelefonoPedido, txtCodProductoPedido, txtCantproductoPedido, txtValorProductoPedido, txtDescripcionPedido, txtCodFactura;
    Button btnBuscarPedidoOCliente, btnBuscarPedidoProducto, btnClean, btnHacerPedido, btnEliminarPedido, btnLimpiarProductoPedido, btnBuscarPedidoOC;

    DatabaseManager dataBaseManager;
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dataBaseManager = new DatabaseManager(this);

        txtCodPedidoProducto = findViewById(R.id.txtCodPedidoProducto);
        txtCedulaPedido = findViewById(R.id.txtCedulaPedido);
        txtNombrePedido = findViewById(R.id.txtNombrePedido);
        txtDireccionPedido = findViewById(R.id.txtDireccionPedido);
        txtTelefonoPedido = findViewById(R.id.txtTelefonoPedido);
        txtFechaPedido = findViewById(R.id.txtFechaPedido);
        txtDescripcionPedido = findViewById(R.id.txtDescripcionPedido);
        txtCodFactura = findViewById(R.id.txtCodFactura);

        txtTelefonoPedido = findViewById(R.id.txtTelefonoPedido);
        txtCodProductoPedido = findViewById(R.id.txtCodPedidoProducto);
        txtCantproductoPedido = findViewById(R.id.txtCantproductoPedido);
        txtValorProductoPedido = findViewById(R.id.txtValorProductoPedido);

        btnBuscarPedidoOCliente = findViewById(R.id.btnBuscarPedidoOCliente);
        btnBuscarPedidoProducto = findViewById(R.id.btnBuscarPedidoProducto);
        btnClean = findViewById(R.id.btnClean);
        btnHacerPedido = findViewById(R.id.btnHacerPedido);
        btnEliminarPedido = findViewById(R.id.btnEliminarPedido);
        btnLimpiarProductoPedido = findViewById(R.id.btnLimpiarProductoPedido);
        btnBuscarPedidoOC = findViewById(R.id.btnBuscarPedidoOC);

        btnBuscarPedidoOCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtCedulaPedido.getText().toString().isEmpty()) {
                    String cedula = txtCedulaPedido.getText().toString().trim();
                    Client cliente = dataBaseManager.getClientByCedula(cedula);

                    if (cliente != null) {
                        txtCedulaPedido.setText(cliente.getCedula());
                        txtNombrePedido.setText(cliente.getNombre());
                        txtDireccionPedido.setText(cliente.getDireccion());
                        txtTelefonoPedido.setText(cliente.getTelefono());

                        Toast.makeText(getApplicationContext(), "REGISTRO CARGADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "REGISTRO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    if (txtCodPedidoProducto.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "REGISTRO NO ENCONTRADO", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        btnBuscarPedidoOC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codFactura = txtCodFactura.getText().toString();
                Pedido pedido = null;
                Client cliente = null;
                if (txtCodFactura.getText().toString().trim() != null) {
                    pedido = dataBaseManager.getPedidoById(codFactura);
                }else{
                    Toast.makeText(getApplicationContext(), "INGRESE EL NUMERO DE FACTURA", Toast.LENGTH_SHORT).show();
                }
                if (pedido != null) {
                    cliente = dataBaseManager.getClientByCedula(pedido.getCedula());
                }
                int encontrado = 0;
                if (pedido != null && cliente != null) {
                    txtCodFactura.setText(pedido.getCodPedido());
                    txtDescripcionPedido.setText(pedido.getDescripcion());
                    txtFechaPedido.setText(pedido.getFecha());
                    txtCantproductoPedido.setText(pedido.getCantidad());
                    txtCodProductoPedido.setText(pedido.getCodProducto());
                    txtCedulaPedido.setText(pedido.getCedula());

                    txtCedulaPedido.setText(cliente.getCedula());
                    txtNombrePedido.setText(cliente.getNombre());
                    txtDireccionPedido.setText(cliente.getDireccion());
                    txtTelefonoPedido.setText(cliente.getTelefono());

                    Toast.makeText(getApplicationContext(), "REGISTRO CARGADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    encontrado = 1;
                } else {
                    encontrado = 0;
                    if (encontrado == 0) {
                        Toast.makeText(getApplicationContext(), "REGISTRO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnBuscarPedidoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codProducto = txtCodProductoPedido.getText().toString().trim();
                Producto producto = dataBaseManager.getProductoById(codProducto);
                if (producto != null) {
                    txtDescripcionPedido.setText(producto.getDescripcion());
                    Toast.makeText(getApplicationContext(), "REGISTRO CARGADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "REGISTRO NO ENCONTRADO", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHacerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtCodPedidoProducto.getText().toString().trim().isEmpty() && !txtCodProductoPedido.getText().toString().trim().isEmpty() && !txtCedulaPedido.getText().toString().trim().isEmpty()){

                    String codFactura = txtCodFactura.getText().toString().trim();
                    String descripcion = txtDescripcionPedido.getText().toString().trim();
                    String fecha = txtFechaPedido.getText().toString().trim();
                    String cantidad = txtCantproductoPedido.getText().toString().trim();
                    String codProducto = txtCodProductoPedido.getText().toString().trim();
                    String cedula = txtCedulaPedido.getText().toString().trim();
                    long result = dataBaseManager.insertPedido(codFactura, descripcion, fecha, cantidad, codProducto, cedula);
                    if (result != -1) {
                        Toast.makeText(getApplicationContext(), "REGISTRO GUARDADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        limpiarCampos();
                    } else {
                        Toast.makeText(getApplicationContext(), "ERROR AL GUARDAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "HAY CAMPOS VACÍOS", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnLimpiarProductoPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarProductoPedido();
            }
        });

        btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
            }
        });

        btnEliminarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String condFactura = txtCodFactura.getText().toString().trim();
                int result = dataBaseManager.deletePedido(condFactura);
                if (result > 0) {
                    Toast.makeText(getApplicationContext(), "REGISTRO ELIMINADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                } else {
                    Toast.makeText(getApplicationContext(), "ERROR AL ELIMINAR EL REGISTRO", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void limpiarCampos() {
        txtCodFactura.setText("");
        txtCodPedidoProducto.setText("");
        txtCedulaPedido.setText("");
        txtNombrePedido.setText("");
        txtDireccionPedido.setText("");
        txtTelefonoPedido.setText("");
        txtCodProductoPedido.setText("");
        txtCantproductoPedido.setText("");
        txtValorProductoPedido.setText("");
        txtDescripcionPedido.setText("");
        txtFechaPedido.setText("");
    }

    private void limpiarProductoPedido() {
        txtCodProductoPedido.setText("");
        txtCantproductoPedido.setText("");
        txtValorProductoPedido.setText("");
        txtDescripcionPedido.setText("");
    }
}

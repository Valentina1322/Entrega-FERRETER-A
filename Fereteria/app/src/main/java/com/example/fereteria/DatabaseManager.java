package com.example.fereteria;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    //Base de datos de clientes
    //------------------------------------------
    private static final String DATABASE_NAME = "FerreteriaDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "cliente";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CEDULA = "cedula";
    private static final String COLUMN_NOMBRES = "nombres";
    private static final String COLUMN_DIRECCION = "direccion";
    private static final String COLUMN_TELEFONO = "telefono";
    //------------------------------------------


    //base de datos de pedidos
    //------------------------------------------
    private static final String TABLE_PEDIDOS = "pedido";
    private static final String COLUMN_COD_PEDIDO = "codigo_pedido";
    private static final String COLUMN_DESCRIPCION = "descripcion";
    private static final String COLUMN_FECHA = "fecha";
    private static final String COLUMN_CANTIDAD = "cantidad";
    private static final String COLUMN_COD_PRODUCTO = "codigo_producto";
    private static final String COLUMN_CEDULA_PEDIDO = "cedula";

    //base de datos de productos
    //------------------------------------------
    private static final String TABLE_PRODUCTO = "producto";
    private static final String COLUMN_CODIGO_PRODUCTO = "codigo_producto";
    private static final String COLUMN_DESCRIPCION_PRODUCTO = "descripcion_producto";
    private static final String COLUMN_VALOR_PRODUCTO = "valor_poducto";
    //------------------------------------------
    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //CREATE de datos de clientes
    //------------------------------------------
    private static final String CREATE_TABLE_CLIENTES = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CEDULA + " TEXT, " +
                COLUMN_NOMBRES + " TEXT, " +
                COLUMN_DIRECCION + " TEXT, " +
                COLUMN_TELEFONO + " TEXT)";
    //------------------------------------------


    //CREATE de datos de pedidos
    //------------------------------------------
    private static final String CREATE_TABLE_PRODUCTOS = "CREATE TABLE " + TABLE_PRODUCTO + " (" +
        COLUMN_CODIGO_PRODUCTO + " TEXT PRIMARY KEY, " +
        COLUMN_DESCRIPCION_PRODUCTO + " TEXT, " +
        COLUMN_VALOR_PRODUCTO + " TEXT)";
    //------------------------------------------

    //CREATE de datos de pedidos
    //------------------------------------------
    private static final String CREATE_TABLE_PEDIDOS = "CREATE TABLE " + TABLE_PEDIDOS + "(" +
            COLUMN_COD_PEDIDO + " TEXT PRIMARY KEY, " +
            COLUMN_DESCRIPCION + " TEXT, " +
            COLUMN_FECHA + " TEXT, " +
            COLUMN_CANTIDAD + " TEXT, " +
            COLUMN_COD_PRODUCTO + " TEXT, " +
            COLUMN_CEDULA_PEDIDO + " TEXT, " +
            "FOREIGN KEY(" + COLUMN_CODIGO_PRODUCTO + ") REFERENCES " + TABLE_PRODUCTO + "(" + COLUMN_CODIGO_PRODUCTO + "), " +
            "FOREIGN KEY(" + COLUMN_CEDULA_PEDIDO + ") REFERENCES " + TABLE_NAME + "(" + COLUMN_CEDULA + "))";
    //------------------------------------------




    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CLIENTES);
        db.execSQL(CREATE_TABLE_PEDIDOS);
        db.execSQL(CREATE_TABLE_PRODUCTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String dropClienteTableQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropClienteTableQuery);

        String dropProductoTableQuery = "DROP TABLE IF EXISTS " + TABLE_PRODUCTO;
        db.execSQL(dropProductoTableQuery);

        String dropPedidoTableQuery = "DROP TABLE IF EXISTS " + TABLE_PEDIDOS;
        db.execSQL(dropPedidoTableQuery);

        onCreate(db);

    }

    //CRUD de datos de clientes
    //------------------------------------------

    public long insertClient(String cedula, String nombres, String direccion, String telefono) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CEDULA, cedula);
        values.put(COLUMN_NOMBRES, nombres);
        values.put(COLUMN_DIRECCION, direccion);
        values.put(COLUMN_TELEFONO, telefono);
        return db.insert(TABLE_NAME, null, values);
    }

    public int updateClient(String cedula, String nombre, String direccion, String telefono) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CEDULA, cedula);
        values.put(COLUMN_NOMBRES, nombre);
        values.put(COLUMN_DIRECCION, direccion);
        values.put(COLUMN_TELEFONO, telefono);
        return db.update(TABLE_NAME, values, COLUMN_CEDULA + " = ?", new String[]{cedula});
    }

    public int deleteClient(String cedula) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COLUMN_CEDULA + " = ?";
        String[] whereArgs = {cedula};
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }

    public Client getClientByCedula(String cedula) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_CEDULA + " = ?";
        String[] selectionArgs = {cedula};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        Client client = null;
        if (cursor != null && cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nombresIndex = cursor.getColumnIndex(COLUMN_NOMBRES);
            int direccionIndex = cursor.getColumnIndex(COLUMN_DIRECCION);
            int telefonoIndex = cursor.getColumnIndex(COLUMN_TELEFONO);
            if (idIndex != -1 && nombresIndex != -1 && direccionIndex != -1 && telefonoIndex != -1) {
                int id = cursor.getInt(idIndex);
                String nombres = cursor.getString(nombresIndex);
                String direccion = cursor.getString(direccionIndex);
                String telefono = cursor.getString(telefonoIndex);
                client = new Client(cedula, nombres, direccion, telefono);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return client;
    }
    //------------------------------------------

    //CRUD de datos de pedidos
    //------------------------------------------

    public long insertPedido(String codPedido, String descripcion, String fecha, String cantidad, String codProducto, String cedulaPedido) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COD_PEDIDO, codPedido);
        values.put(COLUMN_DESCRIPCION, descripcion);
        values.put(COLUMN_FECHA, fecha);
        values.put(COLUMN_CANTIDAD, cantidad);
        values.put(COLUMN_COD_PRODUCTO, codProducto);
        values.put(COLUMN_CEDULA_PEDIDO, cedulaPedido);
        return db.insert(TABLE_PEDIDOS, null, values);
    }
    public int deletePedido(String codPedido) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COLUMN_COD_PEDIDO + " = ?";
        String[] whereArgs = {codPedido};
        return db.delete(TABLE_PEDIDOS, whereClause, whereArgs);
    }

    public Pedido getPedidoById(String codPedido) {
        SQLiteDatabase db = getReadableDatabase();
        String selection = COLUMN_COD_PEDIDO + " = ?";
        String[] selectionArgs = {codPedido};
        Cursor cursor = db.query(TABLE_PEDIDOS, null, selection, selectionArgs, null, null, null);
        Pedido pedido = null;
        if (cursor != null && cursor.moveToFirst()) {
            int codigoPedidoIndex = cursor.getColumnIndex(COLUMN_COD_PEDIDO);
            int descripcionIndex = cursor.getColumnIndex(COLUMN_DESCRIPCION);
            int fechaIndex = cursor.getColumnIndex(COLUMN_FECHA);
            int cantidadIndex = cursor.getColumnIndex(COLUMN_CANTIDAD);
            int codProductoIndex = cursor.getColumnIndex(COLUMN_COD_PRODUCTO);
            int cedulaPedidoIndex = cursor.getColumnIndex(COLUMN_CEDULA_PEDIDO);
            if (codigoPedidoIndex != -1 && descripcionIndex != -1 && fechaIndex != -1 && cantidadIndex != -1 && codProductoIndex != -1 && cedulaPedidoIndex != -1) {
                String codigoPedido = cursor.getString(codigoPedidoIndex);
                String descripcion = cursor.getString(descripcionIndex);
                String fecha = cursor.getString(fechaIndex);
                String cantidad = cursor.getString(cantidadIndex);
                String codProducto = cursor.getString(codProductoIndex);
                String cedulaPedido = cursor.getString(cedulaPedidoIndex);

                pedido = new Pedido(codigoPedido, descripcion, fecha, cantidad, codProducto, cedulaPedido);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return pedido;
    }
    //------------------------------------------



    //CRUD de datos de productos
    //------------------------------------------

    public long insertProducto(String codigo, String descripcion, String valor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CODIGO_PRODUCTO, codigo);
        values.put(COLUMN_DESCRIPCION_PRODUCTO, descripcion);
        values.put(COLUMN_VALOR_PRODUCTO, valor);
        return db.insert(TABLE_PRODUCTO, null, values);

    }

    public int updateProducto(String codigo, String descripcion, String valor) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DESCRIPCION_PRODUCTO, descripcion);
        values.put(COLUMN_VALOR_PRODUCTO, valor);
        return  db.update(TABLE_PRODUCTO, values, COLUMN_CODIGO_PRODUCTO + " = ?", new String[]{codigo});

    }

    public int deleteProducto(String codigo) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COLUMN_CODIGO_PRODUCTO + " = ?";
        String[] values = {codigo};
        return db.delete(TABLE_PRODUCTO, whereClause, values);

    }

    public Producto getProductoById(String codigo) {
            SQLiteDatabase db = getReadableDatabase();
            //String[] columns = {COLUMN_CODIGO_PRODUCTO, COLUMN_DESCRIPCION_PRODUCTO, COLUMN_VALOR_PRODUCTO};
            String selection = COLUMN_CODIGO_PRODUCTO + " = ?";
            String[] selectionArgs = {codigo};
            Cursor cursor = db.query(TABLE_PRODUCTO, null, selection, selectionArgs, null, null, null);
            Producto producto = null;
            if (cursor != null && cursor.moveToFirst()) {
                int descripcionIndex = cursor.getColumnIndex(COLUMN_DESCRIPCION_PRODUCTO);
                int valorIndex = cursor.getColumnIndex(COLUMN_VALOR_PRODUCTO);

                if (descripcionIndex != -1 && valorIndex != -1){
                    String descripcion = cursor.getString(descripcionIndex);
                    String valor = cursor.getString(valorIndex);
                    producto = new Producto(codigo, descripcion, valor);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            db.close();
            return producto;
    }

}

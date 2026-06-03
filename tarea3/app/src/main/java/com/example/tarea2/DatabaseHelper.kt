package com.example.tarea3

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.Cursor

data class Order(
    val id: Int,
    val burger: Int,
    val wings: Int,
    val fries: Int,
    val hotdog: Int,
    val burrito: Int,
    val salchipapa: Int,
    val milanesa: Int,
    val subtotal: Double,
    val tax: Double,
    val total: Double,
    val delivery: String,
    val payment: String,
    val notes: String,
    val timestamp: String
)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "OrdersDB"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "orders"
        const val COLUMN_ID = "id"
        const val COLUMN_BURGER = "burger_qty"
        const val COLUMN_WINGS = "wings_qty"
        const val COLUMN_FRIES = "fries_qty"
        const val COLUMN_HOTDOG = "hotdog_qty"
        const val COLUMN_BURRITO = "burrito_qty"
        const val COLUMN_SALCHIPAPA = "salchipapa_qty"
        const val COLUMN_MILANESA = "milanesa_qty"
        const val COLUMN_SUBTOTAL = "subtotal"
        const val COLUMN_TAX = "tax"
        const val COLUMN_TOTAL = "total"
        const val COLUMN_DELIVERY = "delivery_type"
        const val COLUMN_PAYMENT = "payment_method"
        const val COLUMN_NOTES = "notes"
        const val COLUMN_TIMESTAMP = "timestamp"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_BURGER INTEGER, " +
                "$COLUMN_WINGS INTEGER, " +
                "$COLUMN_FRIES INTEGER, " +
                "$COLUMN_HOTDOG INTEGER, " +
                "$COLUMN_BURRITO INTEGER, " +
                "$COLUMN_SALCHIPAPA INTEGER, " +
                "$COLUMN_MILANESA INTEGER, " +
                "$COLUMN_SUBTOTAL REAL, " +
                "$COLUMN_TAX REAL, " +
                "$COLUMN_TOTAL REAL, " +
                "$COLUMN_DELIVERY TEXT, " +
                "$COLUMN_PAYMENT TEXT, " +
                "$COLUMN_NOTES TEXT, " +
                "$COLUMN_TIMESTAMP DATETIME DEFAULT CURRENT_TIMESTAMP)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertOrder(
        burger: Int, wings: Int, fries: Int, hotdog: Int, burrito: Int,
        salchipapa: Int, milanesa: Int, subtotal: Double, tax: Double,
        total: Double, delivery: String, payment: String, notes: String
    ): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_BURGER, burger)
        contentValues.put(COLUMN_WINGS, wings)
        contentValues.put(COLUMN_FRIES, fries)
        contentValues.put(COLUMN_HOTDOG, hotdog)
        contentValues.put(COLUMN_BURRITO, burrito)
        contentValues.put(COLUMN_SALCHIPAPA, salchipapa)
        contentValues.put(COLUMN_MILANESA, milanesa)
        contentValues.put(COLUMN_SUBTOTAL, subtotal)
        contentValues.put(COLUMN_TAX, tax)
        contentValues.put(COLUMN_TOTAL, total)
        contentValues.put(COLUMN_DELIVERY, delivery)
        contentValues.put(COLUMN_PAYMENT, payment)
        contentValues.put(COLUMN_NOTES, notes)

        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return result
    }

    fun getAllOrders(): List<Order> {
        val orderList = mutableListOf<Order>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME ORDER BY $COLUMN_TIMESTAMP DESC", null)

        if (cursor.moveToFirst()) {
            do {
                val order = Order(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_BURGER)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_WINGS)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_FRIES)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_HOTDOG)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_BURRITO)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SALCHIPAPA)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_MILANESA)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_SUBTOTAL)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TAX)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DELIVERY)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PAYMENT)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOTES)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIMESTAMP))
                )
                orderList.add(order)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return orderList
    }

    fun deleteOrder(id: Int): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
        return result
    }
}

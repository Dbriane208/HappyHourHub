package daniel.brian.happyhourhub.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CartDB extends SQLiteOpenHelper {
    public CartDB(@Nullable Context context) {
        super(context, "cartProducts.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table cartProducts(name Text not null,price Text not null, image blob not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS cartProducts");
    }

    public boolean insertCocktailData(String name, String price, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("image", image); // Assuming COLUMN_IMAGE is a BLOB column
        long result = db.insert("cartProducts", null, values);

        return result != -1;
    }

    public Cursor getAllCartCocktails(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from cartProducts ",null);
    }

    public int getItemsInCart() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT count(*) FROM cartProducts", null);
        // this will store the counts of items in the cart
        int count = 0;
        if (cursor != null) {
            // handling potential excepts
            try {
                // this line moves the cursor to the first row of the result set.
                // if there is at least one low, it returns true
                if (cursor.moveToFirst()) {
                    // this retrieves the count value from the first column of the result set
                    count = cursor.getInt(0);
                }
            } finally {
                // closes the cursor regardless of whether the exceptions occurred or not
                cursor.close();
            }
        }

        // returns the count of the items
        return count;
    }

    public int getTotalCost(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select sum(price) from cartProducts",null);

        int cost = 0;
        if(cursor != null){
            try{
                if(cursor.moveToFirst()){
                    cost = cursor.getInt(0);
                }
            }
            finally {
               cursor.close();
            }
        }
        return cost;
    }

    public void deleteItemFromCart(String name,String price){
        SQLiteDatabase db  = this.getWritableDatabase();
        db.delete("cartProducts","name = ? and price = ? ",new String[]{name,price});
    }
}


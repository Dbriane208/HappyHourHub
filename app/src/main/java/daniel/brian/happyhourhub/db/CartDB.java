package daniel.brian.happyhourhub.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CartDB extends SQLiteOpenHelper {
    public CartDB(@Nullable Context context) {
        super(context, "cartProducts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table cartProducts(name Text not null,price Text not null,type Text not null, image blob not null)");
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
}

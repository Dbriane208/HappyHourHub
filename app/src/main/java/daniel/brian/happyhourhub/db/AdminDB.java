package daniel.brian.happyhourhub.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Product_Database";


    public AdminDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table Products(name Text not null,price Text not null, description Text not null,type Text not null, image blob not null)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if the table exists
        db.execSQL("DROP TABLE IF EXISTS Products");
    }

    // Code to add new product
    public boolean insertCocktailData(String name, String price, String description, String type, byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("price", price);
        values.put("description", description);
        values.put("type", type);
        values.put("image", image); // Assuming COLUMN_IMAGE is a BLOB column
        long result = db.insert("Products", null, values);

        return result != -1;
    }

    //getting the alcoholic drinks
    public Cursor getAlcoholicCocktails(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from Products ",null);
    }
}

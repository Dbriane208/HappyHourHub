package daniel.brian.happyhourhub.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Product_Database";
    private static final String TABLE_COCKTAIL = "Cocktail";
    private static final String KEY_ID = "id";
    private static final String PRODUCT_NAME = "Name";
    private static final String PRODUCT_PRICE="Price";
    private static final String PRODUCT_TYPE="Type";
    private static  final String PRODUCT_DESCRIPTION ="Product_description";

    public AdminDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COCKTAIL_TABLE="CREATE TABLE cocktail_table("+KEY_ID+"INTEGER PRIMARY KEY,"+PRODUCT_NAME+"TEXT,"+PRODUCT_PRICE+"NUMBER"+PRODUCT_DESCRIPTION+"TEXT"+PRODUCT_TYPE+"TEXT"+")";
        db.execSQL(CREATE_COCKTAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // drop table if table exists
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_COCKTAIL);
        // create tables again
        onCreate(db);
    }

    // code to add new product
    public boolean insertCocktailData(String PRODUCT_NAME,String PRODUCT_PRICE, String PRODUCT_DESCRIPTION, String PRODUCT_TYPE){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues values= new ContentValues();
        values.put("Name",PRODUCT_NAME);
        values.put("Price",PRODUCT_PRICE);
        values.put("Description", PRODUCT_DESCRIPTION);
        values.put("Type", PRODUCT_TYPE);
        long result = db.insert("TABLE_COCKTAIL",null,values);
        return result != -1;
    }
}
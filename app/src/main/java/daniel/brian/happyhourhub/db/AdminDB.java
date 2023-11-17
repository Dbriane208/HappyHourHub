package daniel.brian.happyhourhub.db;

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
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_COCKTAIL);
        // create tables again
        onCreate(db);
    }
}
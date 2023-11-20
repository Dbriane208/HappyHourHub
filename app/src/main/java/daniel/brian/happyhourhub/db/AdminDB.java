package daniel.brian.happyhourhub.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminDB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Product_Database";
    private static final String TABLE_COCKTAIL = "cocktail_table"; // Consistent naming
    private static final String KEY_ID = "id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_PRICE = "Price";
    private static final String COLUMN_DESCRIPTION = "Product_description"; // Correct column name
    private static final String COLUMN_TYPE = "Type";
    private static final String COLUMN_IMAGE = "Image";

    public AdminDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COCKTAIL_TABLE = "CREATE TABLE " + TABLE_COCKTAIL + " (" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PRICE + " REAL, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_TYPE + " TEXT, " +
                COLUMN_IMAGE + " BLOB)";
        db.execSQL(CREATE_COCKTAIL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop table if the table exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COCKTAIL);
        // Create tables again
        onCreate(db);
    }

    // Code to add new product
    public boolean insertCocktailData(String name, double price, String description, String type, byte[] imageData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PRICE, price);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_IMAGE, imageData); // Assuming COLUMN_IMAGE is a BLOB column

        long result = db.insert(TABLE_COCKTAIL, null, values);
        db.close(); // Close the database connection

        return result != -1;
    }
}

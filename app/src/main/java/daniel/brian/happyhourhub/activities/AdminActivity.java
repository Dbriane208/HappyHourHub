package daniel.brian.happyhourhub.activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import daniel.brian.happyhourhub.R;
import daniel.brian.happyhourhub.db.AdminDB;

public class AdminActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri; // Declare selectedImageUri as a class-level variable
    private Bitmap selectedBitmap; // Declare selectedBitmap as a class-level variable

    private AdminDB adminDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        adminDB = new AdminDB(this); // Create an instance of AdminDB

        EditText name = findViewById(R.id.productName);
        EditText price = findViewById(R.id.productPrice);
        EditText description = findViewById(R.id.productDescription);
        EditText type = findViewById(R.id.productType);
        Button addImageButton = findViewById(R.id.add_image_button);
        Button post = findViewById(R.id.buttonSend);

        // Create an ActivityResultLauncher for galleryIntent
        ActivityResultLauncher<Intent> galleryLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK) {
                    Intent data = result.getData();
                    if (data != null) {
                        // Get the URI of the selected image
                        selectedImageUri = data.getData();

                        // Convert the selected image to a Bitmap
                        try {
                            selectedBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        // Handle the selected image
                        Toast.makeText(AdminActivity.this, "Image selected!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        addImageButton.setOnClickListener(view -> {
            openGallery(galleryLauncher);
        });

        post.setOnClickListener(view -> {
            // Validate input fields
            String productName = name.getText().toString().trim();
            String productPrice = price.getText().toString().trim();
            String productDescription = description.getText().toString().trim();
            String productType = type.getText().toString().trim();

            if (productName.isEmpty() || productPrice.isEmpty() || productDescription.isEmpty() || productType.isEmpty()) {
                Toast.makeText(this, "Please Enter all Fields", Toast.LENGTH_LONG).show();
            } else {
                // Insert product data into the database
                try {
                    String priceValue = String.valueOf(Double.parseDouble(productPrice));

                    // Check if an image is selected
                    if (selectedBitmap != null) {
                        // Convert the selected image to a byte array
                        byte[] imageData = convertImageToByteArray(selectedImageUri);

                        boolean insert = adminDB.insertCocktailData(productName, priceValue, productDescription, productType, imageData);

                        if (insert) {
                            // Product inserted successfully
                            Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_LONG).show();
                        } else {
                            // Failed to insert product
                            Toast.makeText(this, "Failed to Add Product", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        // Show a message if no image is selected
                        Toast.makeText(this, "Please select an image", Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException e) {
                    // Handle the case where the entered price is not a valid number
                    Toast.makeText(this, "Invalid Price Format", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openGallery(ActivityResultLauncher<Intent> launcher) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        launcher.launch(galleryIntent);
    }

    private byte[] convertImageToByteArray(Uri uri) {
        try {
            ContentResolver contentResolver = getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);

            // Convert InputStream to byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            byteArrayOutputStream.close();

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        // Close the database connection when the activity is destroyed
        adminDB.close();
        super.onDestroy();
    }
}

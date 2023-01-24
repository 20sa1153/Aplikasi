package com.example.tesmagang;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView imageView;
    private EditText Nama, Polidrome;
    private Button Check, Next;

    Uri imageUri;
    private final int PICK_IMAGE_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_1);

        Nama = findViewById(R.id.editName);
        Polidrome = findViewById(R.id.editPoindrome);
        imageView = findViewById(R.id.profile_image);

        Next = findViewById(R.id.btn_next);
        Check = findViewById(R.id.btn_check);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImage();
            }
        });

        Check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kalimat = Polidrome.getText().toString();
                if (isPalindrome(kalimat)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pengecekan Polindrome");
                    builder.setMessage("Kalimat " + kalimat + " adalah polindrome");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                        }
                    });
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Pengecekan Polindrome");
                    builder.setMessage("Kalimat " + kalimat + " bukan polindrome");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                        }
                    });
                    builder.show();
                }
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = Nama.getText().toString();
                Intent intent = new Intent(MainActivity.this, ActivityScreen_2.class);
                intent.putExtra("nama", nama);
                startActivity(intent);
            }
        });
    }

    private boolean isPalindrome(String sentence) {
        sentence = sentence.replaceAll("\\s+", "").toLowerCase();
        int length = sentence.length();
        for (int i = 0; i < length / 2; i++) {
            if (sentence.charAt(i) != sentence.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    private void pickImage() {
        // Memunculkan pilihan untuk mengambil gambar dari kamera atau galeri
        final CharSequence[] items = {"Kamera", "Galeri", "Batal"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Tambahkan Gambar");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals("Kamera")) {
                    // Jika pilihan "Kamera" dipilih, maka akan membuka kamera untuk mengambil gambar
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                } else if (items[i].equals("Galeri")) {
                    // Jika pilihan "Galeri" dipilih, maka akan membuka galeri untuk memilih gambar
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult(intent, PICK_IMAGE_REQUEST);
                } else if (items[i].equals("Batal")) {
                    // Jika pilihan "Batal" dipilih, maka akan menutup dialog
                    dialogInterface.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            // Jika gambar diambil dari kamera
            if (requestCode == 0) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);
            }
            // Jika gambar dipilih dari galeri
            else if (requestCode == PICK_IMAGE_REQUEST) {
                imageUri = data.getData();
                imageView.setImageURI(imageUri);
            }
        }
    }
}
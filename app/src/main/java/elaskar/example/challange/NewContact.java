package elaskar.example.challange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class NewContact extends AppCompatActivity {

    EditText etNick, etPhone, etPage, etCity, etAddress;
    RadioButton rbRed, rbGreen, rbBlue;
    Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNick = findViewById(R.id.etNick);
        etPhone = findViewById(R.id.etPhone);
        etPage = findViewById(R.id.etPage);
        etCity = findViewById(R.id.etCity);
        etAddress = findViewById(R.id.etAddress);

        rbRed = findViewById(R.id.rbRed);
        rbGreen = findViewById(R.id.rbGreen);
        rbBlue = findViewById(R.id.rbBlue);

        btnOK = findViewById(R.id.btnOK);



        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(etNick.getText().toString().isEmpty() ||
                        etPhone.getText().toString().isEmpty() ||
                        etPage.getText().toString().isEmpty() ||
                        etCity.getText().toString().isEmpty() ||
                        etAddress.getText().toString().isEmpty() ||
                        (!rbRed.isChecked() && !rbGreen.isChecked() && !rbBlue.isChecked()))
                {
                    Toast.makeText(NewContact.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String nick = etNick.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();
                    String page = etPage.getText().toString().trim();
                    String city = etCity.getText().toString().trim();
                    String address = etAddress.getText().toString().trim();
                    String color = "";

                    if(rbRed.isChecked())
                        color = "r";
                    if(rbGreen.isChecked())
                        color = "g";
                    if(rbBlue.isChecked())
                        color = "b";

                    String number = getIntent().getStringExtra("number");

                    //Person person = new Person(nick, phone, page, city, address, color);

                    Intent intent = new Intent();
                    intent.putExtra("nick", nick);
                    intent.putExtra("phone", phone);
                    intent.putExtra("page", page);
                    intent.putExtra("city", city);
                    intent.putExtra("address", address);
                    intent.putExtra("color", color);
                    intent.putExtra("number", number);


                    setResult(RESULT_OK, intent);
                    NewContact.this.finish();
                }

            }
        });
    }
}

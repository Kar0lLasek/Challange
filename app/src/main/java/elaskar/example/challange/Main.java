package elaskar.example.challange;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    RadioButton rb1, rb2, rb3;
    Button btnCreate, btnReset;
    ImageView ivPhone, ivPage, ivMap;

    ArrayList<Person> people = new ArrayList<>();

    final int CONTACTID = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ok);

        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);

        ivMap = findViewById(R.id.ivMap);
        ivPage = findViewById(R.id.ivPage);
        ivPhone = findViewById(R.id.ivPhone);

        btnCreate = findViewById(R.id.btnCreate);
        btnReset = findViewById(R.id.btnReset);

        people.add(null);
        people.add(null);
        people.add(null);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                builder.setCancelable(true);
                builder.setTitle("Warning");
                builder.setMessage("Are you sure you want delete all contacts?");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                rb1.setText("New contact");
                                rb1.setTextColor(Color.parseColor("#000000"));
                                rb2.setText("New contact");
                                rb2.setTextColor(Color.parseColor("#000000"));
                                rb3.setText("New contact");
                                rb3.setTextColor(Color.parseColor("#000000"));
                                for(int i = 0; i < people.size(); i++){
                                    people.set(i, null);
                                }
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Main.this, NewContact.class);

                if(rb1.isChecked()) {
                    intent.putExtra("number", "0");
                    startActivityForResult(intent, CONTACTID);
                }
                else if(rb2.isChecked()) {
                    intent.putExtra("number", "1");
                    startActivityForResult(intent, CONTACTID);
                }
                else if(rb3.isChecked()) {
                    intent.putExtra("number", "2");
                    startActivityForResult(intent, CONTACTID);
                }
                else
                {
                    Toast.makeText(Main.this, "Please choose place to save a new contact", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(rb1.isChecked() && people.get(0) != null) { //!!!!!!!!!!!
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + people.get(0).getAddress() + ", " + people.get(0).getCity()));
                        startActivity(intent);
                    }
                    else if(rb2.isChecked() && people.get(1) != null) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("geo:0,0?q=" + people.get(1).getAddress() + ", "  + people.get(1).getCity()));
                        startActivity(intent);
                    }
                    else if(rb3.isChecked() && people.get(2) != null) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("geo:0,0?q=" + people.get(2).getAddress() + ", "  + people.get(2).getCity()));
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Main.this, "Please choose contact", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(Main.this, "Error with maps", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ivPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(rb1.isChecked() && people.get(0) != null) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + people.get(0).getPage()));
                        startActivity(intent);
                    }
                    else if(rb2.isChecked() && people.get(1) != null) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("http://" + people.get(1).getPage()));
                        startActivity(intent);
                    }
                    else if(rb3.isChecked() && people.get(2) != null) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("http://" + people.get(2).getPage()));
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Main.this, "Please choose contact", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception ex){
                    Toast.makeText(Main.this, "404 NOT FOUND", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ivPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(rb1.isChecked() && people.get(0) != null) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + people.get(0).getNumber()));
                        startActivity(intent);
                    }
                    else if(rb2.isChecked() && people.get(1) != null) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + people.get(1).getNumber()));
                        startActivity(intent);
                    }
                    else if(rb3.isChecked() && people.get(2) != null) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + people.get(2).getNumber()));
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Main.this, "Please choose contact", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Toast.makeText(Main.this, "Error with call", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CONTACTID) {
            if(resultCode == RESULT_OK) {

                String nick = data.getStringExtra("nick");
                String phone = data.getStringExtra("phone");
                String page = data.getStringExtra("page");
                String city = data.getStringExtra("city");
                String address = data.getStringExtra("address");
                String color = data.getStringExtra("color");
                String number = data.getStringExtra("number");
                int num = Integer.parseInt(number);


                Person person = new Person(nick, phone, page, city, address, color);
                people.set(num, person);

                if(rb1.isChecked()) {
                    rb1.setText(nick + ": " + phone);
                    if(color.equals("r"))
                        rb1.setTextColor(Color.parseColor("#ff0000"));
                    else if(color.equals("g"))
                        rb1.setTextColor(Color.parseColor("#00ff00"));
                    else if(color.equals("b"))
                        rb1.setTextColor(Color.parseColor("#0000ff"));
                }
                else if(rb2.isChecked()) {
                    rb2.setText(nick + ": " + phone);
                    if(color.equals("r"))
                        rb2.setTextColor(Color.parseColor("#ff0000"));
                    else if(color.equals("g"))
                        rb2.setTextColor(Color.parseColor("#00ff00"));
                    else if(color.equals("b"))
                        rb2.setTextColor(Color.parseColor("#0000ff"));
                }
                else if(rb3.isChecked()) {
                    rb3.setText(nick + ": " + phone);
                    if(color.equals("r"))
                        rb3.setTextColor(Color.parseColor("#ff0000"));
                    else if(color.equals("g"))
                        rb3.setTextColor(Color.parseColor("#00ff00"));
                    else if(color.equals("b"))
                        rb3.setTextColor(Color.parseColor("#0000ff"));
                }

            }
            if(resultCode == RESULT_CANCELED) {

            }
        }

    }
}

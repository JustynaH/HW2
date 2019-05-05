package justyna.hekert.hw2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import justyna.hekert.hw2.items.ItemListContent;

public class AddItemActivity extends AppCompatActivity {

    private String itemPicPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        itemPicPath = "";
    }

    public void setImgPicPath (String val){
        itemPicPath = val;
    }

    public void PicTakenCancelled (){
        Intent data = new Intent();
        setResult(RESULT_CANCELED, data);
        finish();
    }

    public void addClick(View view) {
        Intent data = new Intent();

        EditText itemTitleEditTxt = findViewById(R.id.ItemAddTitle);
        EditText itemAuthorEditTxt = findViewById(R.id.ItemAddAuthor);
        EditText itemDateEditTxt = findViewById(R.id.ItemAddDate);
        String itemTitle = itemTitleEditTxt.getText().toString();
        String itemAuthor = itemAuthorEditTxt.getText().toString();
        String itemDate = itemDateEditTxt.getText().toString();

        //default values
        if(itemTitle.isEmpty())
            itemTitle =getString(R.string.title);
        if(itemAuthor.isEmpty())
            itemAuthor = getString(R.string.author);
        if(itemDate.isEmpty())
            itemDate = getString(R.string.date);


        ItemListContent.addItem(new ItemListContent.Item("Item." + ItemListContent.ITEMS.size() +1,
                itemTitle,
                itemAuthor,
                itemDate,
                itemPicPath));

        itemTitleEditTxt.setText("");
        itemAuthorEditTxt.setText("");
        itemDateEditTxt.setText("");

        //hide keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        setResult(RESULT_OK, data);
        finish();
    }
}

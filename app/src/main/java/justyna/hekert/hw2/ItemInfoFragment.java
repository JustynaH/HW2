package justyna.hekert.hw2;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import justyna.hekert.hw2.items.ItemListContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class ItemInfoFragment extends Fragment {

    private ItemListContent.Item mDisplayedItem;

    public ItemInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        FragmentActivity activity = getActivity();
        Intent intent = activity.getIntent();

        if(intent !=null) {
            ItemListContent.Item recivedItem = intent.getParcelableExtra(MainActivity.itemExtra);
            if(recivedItem != null) {
                displayItem(recivedItem);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item_info, container, false);
    }


    public void displayItem(ItemListContent.Item item){
        FragmentActivity activity = getActivity();

        TextView itemInfoTitle = activity.findViewById(R.id.ItemInfoTitle);
        TextView itemInfoAuthor = activity.findViewById(R.id.ItemInfoAuthor);
        TextView itemInfoDate = activity.findViewById(R.id.ItemInfoDate);
        final ImageView itemInfoImage = activity.findViewById(R.id.ItemInfoImage);

        itemInfoTitle.setText(item.title);
        itemInfoAuthor.setText(item.author);
        itemInfoDate.setText(item.date);
        if(item.picPath != null && !item.picPath.isEmpty()){
            if(item.picPath.contains("book")) {
                Drawable itemDrawable;
                switch(item.picPath){
                    case "book1":
                        itemDrawable = activity.getResources().getDrawable(R.drawable.book1);
                        break;
                    case "book2":
                        itemDrawable = activity.getResources().getDrawable(R.drawable.book2);
                        break;
                    case "book3":
                        itemDrawable = activity.getResources().getDrawable(R.drawable.book3);
                        break;
                    case "book4":
                        itemDrawable = activity.getResources().getDrawable(R.drawable.book4);
                        break;
                    case "book5":
                        itemDrawable =activity.getResources().getDrawable(R.drawable.book5);
                        break;
                    case "book6":
                        itemDrawable = activity.getResources().getDrawable(R.drawable.book6);
                        break;
                    case "book7":
                        itemDrawable = activity.getResources().getDrawable(R.drawable.book7);
                        break;
                    default:
                        itemDrawable = activity.getResources().getDrawable(R.drawable.book);

                }
                itemInfoImage.setImageDrawable(itemDrawable);
            } else {
                Handler handler = new Handler();
                itemInfoImage.setVisibility(View.INVISIBLE);
                handler. postDelayed(new Runnable() {
                    @Override
                    public void run(){
                        itemInfoImage.setVisibility(View.VISIBLE);
                        Bitmap cameraImage = PicUtils.decodePic(mDisplayedItem.picPath,
                                itemInfoImage.getWidth(),
                                itemInfoImage.getHeight());
                        itemInfoImage.setImageBitmap(cameraImage);
                    }
                }, 200);
            }
        } else{
            itemInfoImage.setImageDrawable(activity.getResources().getDrawable(R.drawable.book));
        }
        mDisplayedItem = item;
    }
}

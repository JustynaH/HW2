package justyna.hekert.hw2.items;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import justyna.hekert.hw2.R;

public class ItemListContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Item> ITEMS = new ArrayList<Item>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Item> ITEM_MAP = new HashMap<String, Item>();

    private static final int COUNT = 7;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    public static void addItem(Item item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static void removeItem(int position) {
        String itemId = ITEMS.get(position).id;
        ITEMS.remove(position);
        ITEM_MAP.remove(itemId);
    }

    public static void clearList() {
        ITEMS.clear();
        ITEM_MAP.clear();
    }

    private static Item createDummyItem(int position) {

        switch(position){
            case 1:
                return new Item(String.valueOf(position), "Pan Tadeusz", "Adam Mickiewicz", "1834", "book1");
            case 2:
                return new Item(String.valueOf(position), "Balladyna", "Juliusz Słowacki", "1839", "book2");
            case 3:
                return new Item(String.valueOf(position), "Przedwiośnie", "Stefan Żeromski", "1924", "book3");
            case 4:
                return new Item(String.valueOf(position), "Krzyżacy", "Henryk Sienkiewicz", "1900", "book4");
            case 5:
                return new Item(String.valueOf(position), "Lalka", "Bolesław Prus", "1890", "book5");
            case 6:
                return new Item(String.valueOf(position), "Chłopi", "Władysław Reymont", "1904", "book6");
             case 7:
                return new Item(String.valueOf(position), "Wesele", "Stanisław Wyspiański", "1901", "book7");
            default:
                return new Item(String.valueOf(position), "Title", "Author", "Release date","book");
        }

    }


    /**
     * A dummy item representing a piece of title.
     */
    public static class Item implements Parcelable {
        public final String id;
        public final String title;
        public final String author;
        public final String date;
        public String picPath;

        //Redundant
        public Item(String id, String title, String author, String date) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.date = date;
            this.picPath = "";
        }

        public Item(String id, String title, String author, String date, String picPath) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.date = date;
            this.picPath = picPath;
        }

        protected Item(Parcel in) {
            id = in.readString();
            title = in.readString();
            author = in.readString();
            date = in.readString();
            picPath = in.readString();
        }

        public static final Creator<Item> CREATOR = new Creator<Item>() {
            @Override
            public Item createFromParcel(Parcel in) {
                return new Item(in);
            }

            @Override
            public Item[] newArray(int size) {
                return new Item[size];
            }
        };

        @Override
        public String toString() {
            return title;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(title);
            dest.writeString(author);
            dest.writeString(date);
            dest.writeString(picPath);
        }
    }
}

package InteractionHelper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mboyaa.datacollect.R;

import java.util.ArrayList;
import java.util.HashMap;

import DatabaseHelper.DatabaseHandler;

/**
 * Created by mboyaa on 1/6/2016.
 */
public class InteractionAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;


    public InteractionAdapter(Activity a, ArrayList<HashMap<String, String>> d){
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.interaction_row, null);

        TextView interactionType = (TextView)vi.findViewById(R.id.interaction_type); // title
        //TextView clientID = (TextView)vi.findViewById(R.id.client_id); // title

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        // Setting all values in listview
        interactionType.setText(song.get(DatabaseHandler.INTERACTION_TYPE));
      //  clientID.setText(song.get(DatabaseHandler.KEY_ID));
        return vi;
    }
}

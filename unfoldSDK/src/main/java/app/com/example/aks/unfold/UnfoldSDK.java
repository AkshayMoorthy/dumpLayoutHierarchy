package app.com.example.aks.unfold;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Akshay on 09-06-2016.
 */
public class UnfoldSDK {
    private View myTopView;
    ViewGroup viewGroup;
    int k=0,i=0;
    static String s="";
    static JSONArray jarray=new JSONArray();
    JSONObject root = new JSONObject();
    public static void init(AppCompatActivity MainActivity,String str)
    {

        ViewGroup parent=(ViewGroup)MainActivity.findViewById(android.R.id.content);
        dumpHierarchy(parent);
        JSONObject full=new JSONObject();
        try {
            full.put("node", jarray);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        String jstr=full.toString();
        System.out.println("$" + jstr);
        System.out.println(s);
    }
    public static void dumpHierarchy(ViewGroup parent) {
        JSONObject root = new JSONObject();
        JSONObject cld = new JSONObject();
        try {
            root.put("class", parent.getClass().getName());
            root.put("clickable", parent.isClickable());
            root.put("scrollable", parent.isScrollContainer());
            int[] l = new int[2];
            parent.getLocationOnScreen(l);
            int left = l[0];
            int top = l[1];
            int width = l[0]+parent.getWidth();
            int height =l[1]+ parent.getHeight();
            String bds = "[" + left + "," + top + "," + width + "," + height + "]";
            root.put("bounds", bds);

            jarray.put(root);




        } catch (JSONException e) {
            e.printStackTrace();
        }

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            if (child instanceof ViewGroup) {
                dumpHierarchy((ViewGroup) child);
            } else {
                try {

                    cld.put("class", child.getClass().getName());
                    cld.put("clickable", child.isClickable());
                    cld.put("scrollable", child.isScrollContainer());
                    int[] l = new int[2];
                    child.getLocationOnScreen(l);
                    int left = l[0];
                    int top = l[1];
                    int width = l[0]+child.getWidth();
                    int height =l[1]+child.getHeight();
                    String bds = "[" + left + "," + top + "," + width + "," + height + "]";
                    cld.put("bounds", bds);
                    jarray.put(cld);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }





    }

}

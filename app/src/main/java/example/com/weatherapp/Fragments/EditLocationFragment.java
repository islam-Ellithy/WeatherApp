package example.com.weatherapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import example.com.weatherapp.Models.BaseInfo;
import example.com.weatherapp.R;


public class EditLocationFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public EditLocationFragment() {
        // Required empty public constructor
    }

    public static EditLocationFragment newInstance() {
        EditLocationFragment fragment = new EditLocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_edit_location, container, false);

        Button ok = (Button) view.findViewById(R.id.ok);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getAndSendCity(view);
            }
        });


        return view;
    }

    public void getAndSendCity(View view) {
        EditText city = (EditText) view.findViewById(R.id.city);

        String cityName = city.getText().toString();

        if (cityName.length() > 0) {
            BaseInfo.setCity(cityName, getContext());

            mListener.onEditFragmentInteraction();
        } else {
            Toast.makeText(getContext(), "Please enter a right location", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnArticleSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onEditFragmentInteraction();
    }
}

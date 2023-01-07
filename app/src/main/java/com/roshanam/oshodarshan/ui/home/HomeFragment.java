package com.roshanam.oshodarshan.ui.home;


import static com.roshanam.oshodarshan.R.id;
import static com.roshanam.oshodarshan.R.string;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roshanam.oshodarshan.MainActivity;
import com.roshanam.oshodarshan.databinding.FragmentHomeBinding;
import com.roshanam.oshodarshan.ui.utils.Album;
import com.roshanam.oshodarshan.ui.utils.AlbumsAdapter;
import com.roshanam.oshodarshan.ui.utils.NetworkCall;
import com.roshanam.oshodarshan.ui.utils.Result;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private final String oshoWorldHindiURL = "https://www.oshoworld.com/?s=%s&id=14133";
    private final String oshoWorldEnglishURL = "https://www.oshoworld.com/?s=%s&id=14289";
    private AlbumsAdapter albumsAdapter;
    private Context context;
    private RecyclerView recyclerView;
    private MainActivity mainActivity;


    @SuppressLint("ResourceType")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        context = getContext();
        WebView webView = binding.webView;
        mainActivity = (MainActivity) requireActivity();

        Button searchBtn = binding.searchBtn;
        searchBtn.setOnClickListener((view -> {

            // Check if button is set to "Clear"
            if(searchBtn.getText() == getResources().getText(string.title_clear)){
                searchBtn.setText(string.title_search);
                // Clear the list as well
                clearList();
                showToast("List has been cleared!!");
                return;
            }
            String data = binding.searchItem.getText().toString();
            if (data.length() == 0) {
                showToast("Please enter some search text!");
//                Toast.makeText(getActivity(), "Please enter some search text!", Toast.LENGTH_SHORT).show();
                return;
            }
            String url = (binding.englishRadioBtn.isChecked()) ? String.format(oshoWorldEnglishURL, data) : String.format(oshoWorldHindiURL, data);
            Log.i("INFO", "Searching about: " + url);
            ExecutorService executor = Executors.newFixedThreadPool(2);

            Thread workThread = new Thread() {
                @Override
                public void run() {
                    Log.i("INFO","URL = " + url);
                    //Create callable instance
//                    Callable<Result> callable = new NetworkCall(url);
//                    Future<Result> foo = executor.submit(callable);
                    NetworkCall networkCall = new NetworkCall(url);
                    Result result = networkCall.searchFromOshoWorld(url);


                    assert result != null;
                    if (result.isThereException()) {
                        showToast("Something went wrong!!");
                        //  Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (!result.isHasResults()) {
                        showToast("No search results found!!");
//                        Toast.makeText(getActivity(), "No search results found!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        showToast("Results found!!");
                    }

                    // now run functions to extract album titles and link
                    ArrayList<Album> albums = result.extractAlbums();
                    updateList(albums);
                    // Set button text to clear
                    searchBtn.setText(string.title_clear);

                }
            };
            executor.submit(workThread);
            executor.shutdown();
        }));

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void showToast(final String toast) {
        getActivity().runOnUiThread(() -> Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show());

    }

    public void updateList(ArrayList<Album> albums) {

        getActivity().runOnUiThread(() -> {
            // Log.i("INFO","Over here!!"); // TODO: remove later
            recyclerView = getActivity().findViewById(id.recyclerList);

            albumsAdapter = new AlbumsAdapter(albums,mainActivity);


            RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(context.getApplicationContext());

            recyclerView.setLayoutManager(linearLayout);

            recyclerView.setItemAnimator(new DefaultItemAnimator());
//         /   recyclerView.addItemDecoration(new DividerDecoration(this))
            recyclerView.setAdapter(albumsAdapter);
        });
    }


    public void clearList() {

        getActivity().runOnUiThread(() -> {
            // Log.i("INFO","Over here!!"); // TODO: remove later
            recyclerView = getActivity().findViewById(id.recyclerList);

            recyclerView.setAdapter(new AlbumsAdapter(new ArrayList<Album>(),mainActivity));

        });
    }



}

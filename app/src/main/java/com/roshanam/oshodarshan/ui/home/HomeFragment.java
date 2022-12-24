package com.roshanam.oshodarshan.ui.home;

import android.content.Context;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.roshanam.oshodarshan.databinding.FragmentHomeBinding;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private final String oshoWorldHindiURL = "https://www.oshoworld.com/?s=%s&id=14133";
    private final String oshoWorldEnglishURL = "https://www.oshoworld.com/?s=%s&id=14289";






    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Button searchBtn = binding.searchBtn;
        searchBtn.setOnClickListener((view -> {
            String data = binding.searchItem.getText().toString();
            String url  =  (binding.englishRadioBtn.isChecked())?String.format(oshoWorldEnglishURL,data):String.format(oshoWorldHindiURL,data);
            Log.i("INFO", "Searching about: "+url);
            // Use fetcher library for download


//            ExecutorService executor = Executors.newSingleThreadExecutor();
//            Future<Object> foo = (Future<Object>) executor.submit(() -> {
//                 searchFromOshoWorld(url);
//
//            });
//           executor.shutdown();
        }));

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    void searchFromOshoWorld(String url){
        try {


            Document
                    doc = Jsoup.connect(url)
                    .timeout(10000)
                    .get();

            System.out.println(doc.getAllElements());

        } catch (Exception e){
            Toast.makeText(getActivity(),"Some error occurred!!",Toast.LENGTH_SHORT).show();

            e.printStackTrace();
        }
    }
}
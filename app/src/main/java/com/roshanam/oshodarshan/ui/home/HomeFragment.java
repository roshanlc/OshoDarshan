package com.roshanam.oshodarshan.ui.home;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.roshanam.oshodarshan.databinding.FragmentHomeBinding;
import com.roshanam.oshodarshan.ui.utils.NetworkCall;
import com.roshanam.oshodarshan.ui.utils.Result;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
            if (data.length() == 0) {
                Toast.makeText(getActivity(), "Please enter some search text!", Toast.LENGTH_SHORT).show();
                return;
            }
            String url = (binding.englishRadioBtn.isChecked()) ? String.format(oshoWorldEnglishURL, data) : String.format(oshoWorldHindiURL, data);
            Log.i("INFO", "Searching about: " + url);

            ExecutorService executor = Executors.newFixedThreadPool(2);
            //Create callable instance
            Callable<Result> callable = new NetworkCall(url);
            Future<Result> foo = executor.submit(callable);
            Result result = new Result();

            try {
                result = foo.get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (result.isThereException()) {
                Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean r = result.isThereNoResults();
            Log.d("DEBUG", "onCreateView: Result of error message = " + r);


            executor.shutdown();
        }));

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}

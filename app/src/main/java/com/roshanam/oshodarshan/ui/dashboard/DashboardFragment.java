package com.roshanam.oshodarshan.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roshanam.oshodarshan.R;
import com.roshanam.oshodarshan.databinding.FragmentDashboardBinding;
import com.roshanam.oshodarshan.ui.utils.AlbumsAdapter;
import com.roshanam.oshodarshan.ui.utils.QuotesAdapter;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final RecyclerView recyclerView = binding.quotesRecycler;

        QuotesAdapter quotesAdapater = new QuotesAdapter(getQuotes());

        RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(getContext().getApplicationContext());
        recyclerView.setLayoutManager(linearLayout);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
//         /   recyclerView.addItemDecoration(new DividerDecoration(this))
        recyclerView.setAdapter(quotesAdapater);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public ArrayList<String> getQuotes() {

        ArrayList<String> quotes = new ArrayList<>();
        quotes.add("If you love a flower, don’t pick it up.\n" +
                "Because if you pick it up it dies and" +
                "it ceases to be what you love.\n" +
                "So if you love a flower, let it be.\n" +
                "Love is not about possession.\n" +
                "Love is about appreciation.");

        quotes.add("Experience life in all possible ways \n" +
                "good-bad, bitter-sweet, dark-light,\n" +
                "summer-winter. Experience all the dualities.\n" +
                "Don't be afraid of experience, because\n" +
                "the more experience you have, the more\n" +
                "mature you become.");
        quotes.add("Sadness gives depth. Happiness gives height.\n" +
                " Sadness gives roots. Happiness gives branches.\n" +
                " Happiness is like a tree going into the sky,\n and sadness is like the roots going down into the womb of the earth.\n" +
                " Both are needed, and the higher a tree goes, the deeper it goes, simultaneously." +
                " The bigger the tree, the bigger will be its roots.\n" +
                " In fact, it is always in proportion. That's its balance.");

        quotes.add("To be creative means to be in love with life.\n" +
                " You can be creative only if you love life enough that you want to enhance its beauty," +
                " you want to bring a little more music to it," +
                " a little more poetry to it, a little more dance to it.");

        quotes.add("Be — don't try to become");

        quotes.add("Friendship is the purest love. It is the highest form of Love where nothing is asked for, no condition, where one simply enjoys giving.");

        quotes.add(" I'm simply saying that there is a way to be sane. I'm saying that you can get rid of all this insanity created by the past in you. Just by being a simple witness of your thought processes.");

        quotes.add("It is simply sitting silently, witnessing the thoughts, passing before you. Just witnessing, not interfering not even judging, because the moment you judge you have lost the pure witness. The moment you say “this is good, this is bad,” you have already jumped onto the thought process.");

        quotes.add("It takes a little time to create a gap between the witness and the mind. Once the gap is there, you are in for a great surprise, that you are not the mind, that you are the witness, a watcher.\n" +
                "\n" +
                "And this process of watching is the very alchemy of real religion. Because as you become more and more deeply rooted in witnessing, thoughts start disappearing. You are, but the mind is utterly empty.\n" +
                "\n" +
                "That’s the moment of enlightenment. That is the moment that you become for the first time an unconditioned, sane, really free human being.\n");
        quotes.add("One thing: you have to walk, and create the way by your walking; you will not find a ready-made path. It is not so cheap, to reach to the ultimate realization of truth. You will have to create the path by walking yourself; the path is not ready-made, lying there and waiting for you. It is just like the sky: the birds fly, but they don't leave any footprints. You cannot follow them; there are no footprints left behind.");

        quotes.add("Life begins where fear ends.");

        quotes.add("Listen to your being. It is continuously giving you hints; it is a still, small voice. It does not shout at you, that is true. And if you are a little silent you will start feeling your way. Be the person you are. Never try to be another, and you will become mature. Maturity is accepting the responsibility of being oneself, whatsoever the cost. Risking all to be oneself, that's what maturity is all about.");

        quotes.add("Intelligence is dangerous. Intelligence means you will start thinking on your own; you will start looking around on your own. You will not believe in the scriptures; you will believe only in your own experience.");

        quotes.add("Creativity is the greatest rebellion in existence.");

        quotes.add("Take hold of your own life.\n" +
                "See that the whole existence is celebrating.\n" +
                "These trees are not serious, these birds are not serious.\n" +
                "The rivers and the oceans are wild,\n" +
                "and everywhere there is fun,\n" +
                "everywhere there is joy and delight.\n" +
                "Watch existence,\n" +
                "listen to the existence and become part of it.");

        quotes.add("Millions of people are suffering: they want to be loved but they don't know how to love. And love cannot exist as a monologue; it is a dialogue, a very harmonious dialogue.");
    return quotes;

    }

}
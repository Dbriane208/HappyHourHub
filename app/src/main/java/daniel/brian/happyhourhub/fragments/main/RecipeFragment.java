package daniel.brian.happyhourhub.fragments.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import daniel.brian.happyhourhub.adapters.AlcoholicCocktailAdapter;
import daniel.brian.happyhourhub.adapters.NonAlcoholicCocktailAdapter;
import daniel.brian.happyhourhub.databinding.FragmentRecipeBinding;
import daniel.brian.happyhourhub.dtos.Alcoholic;
import daniel.brian.happyhourhub.dtos.NonAlcoholic;
import daniel.brian.happyhourhub.repository.GetAlcoholicCocktailRepository;
import daniel.brian.happyhourhub.repository.GetNonAlcoholicCocktailRepository;
import daniel.brian.happyhourhub.util.Result;
import daniel.brian.happyhourhub.viewmodel.AlcoholicCocktailViewModel;
import daniel.brian.happyhourhub.viewmodel.AlcoholicCocktailViewModelFactory;
import daniel.brian.happyhourhub.viewmodel.NonAlcoholicCocktailViewModel;
import daniel.brian.happyhourhub.viewmodel.NonAlcoholicCocktailViewModelFactory;

public class RecipeFragment extends Fragment {
    FragmentRecipeBinding binding;
    AlcoholicCocktailAdapter alcoholicCocktailAdapter;
    AlcoholicCocktailViewModel alcoholicCocktailViewModel;
    GetAlcoholicCocktailRepository getAlcoholicCocktailRepository;
    GetNonAlcoholicCocktailRepository getNonAlcoholicCocktailRepository;
    AlcoholicCocktailViewModelFactory alcoholicCocktailViewModelFactory;
    NonAlcoholicCocktailAdapter nonAlcoholicCocktailAdapter;
    NonAlcoholicCocktailViewModel nonAlcoholicCocktailViewModel;
    NonAlcoholicCocktailViewModelFactory nonAlcoholicCocktailViewModelFactory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(getLayoutInflater());

        // Setting up the RecyclerView for Alcoholic cocktails
        RecyclerView recyclerView = binding.alcoholicCocktails;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3, LinearLayoutManager.VERTICAL,false));
        alcoholicCocktailAdapter = new AlcoholicCocktailAdapter();
        recyclerView.setAdapter(alcoholicCocktailAdapter);

        // Setting up the ViewModel and Factory for the Recyclerview
        getAlcoholicCocktailRepository = new GetAlcoholicCocktailRepository();
        alcoholicCocktailViewModelFactory = new AlcoholicCocktailViewModelFactory(getAlcoholicCocktailRepository);
        alcoholicCocktailViewModel = new ViewModelProvider(this, alcoholicCocktailViewModelFactory).get(AlcoholicCocktailViewModel.class);
        getAllAlcoholicCocktails();

        // Setting the Recyclerview for nonAlcoholic cocktails
        RecyclerView nonAlcoholicRecyclerView = binding.nonalcoholicCocktails;
        nonAlcoholicRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3,LinearLayoutManager.VERTICAL,false));
        nonAlcoholicCocktailAdapter = new NonAlcoholicCocktailAdapter();
        nonAlcoholicRecyclerView.setAdapter(nonAlcoholicCocktailAdapter);

        // Setting the ViewModel and Factory for nonAlcoholic cocktails
        getNonAlcoholicCocktailRepository = new GetNonAlcoholicCocktailRepository();
        nonAlcoholicCocktailViewModelFactory = new NonAlcoholicCocktailViewModelFactory(getNonAlcoholicCocktailRepository);
        nonAlcoholicCocktailViewModel = new ViewModelProvider(this,nonAlcoholicCocktailViewModelFactory).get(NonAlcoholicCocktailViewModel.class);
        getAllNonAlcoholicCocktails();



        return binding.getRoot();
    }

    private void getAllNonAlcoholicCocktails() {
        nonAlcoholicCocktailViewModel.getNonAlcoholicCocktails().observe(getViewLifecycleOwner(),nonalcoholic -> {
            if(nonalcoholic instanceof Result.Loading<List<NonAlcoholic>>){
                binding.progressBar1.setVisibility(View.VISIBLE);
            } else if (nonalcoholic instanceof Result.Success<List<NonAlcoholic>>) {
                binding.progressBar1.setVisibility(View.INVISIBLE);
                List<NonAlcoholic> nonAlcoholicList = ((Result.Success<List<NonAlcoholic>>)nonalcoholic).getData();
                nonAlcoholicCocktailAdapter.setNonAlcoholicCocktail((ArrayList<NonAlcoholic>) nonAlcoholicList);
            } else if (nonalcoholic instanceof Result.Error<List<NonAlcoholic>>) {
                Log.d("Recipe Fragment","Non Alcoholic Error");
            }
        });
    }

    private void getAllAlcoholicCocktails() {
        alcoholicCocktailViewModel.getAlcoholicCocktails().observe(getViewLifecycleOwner(), listResult -> {
            if(listResult instanceof Result.Loading<List<Alcoholic>>){
                binding.progressBar.setVisibility(View.VISIBLE);
            }else if(listResult instanceof Result.Success<List<Alcoholic>>){
                binding.progressBar.setVisibility(View.INVISIBLE);
                List<Alcoholic> alcoholicList = ((Result.Success<List<Alcoholic>>)listResult).getData();
                alcoholicCocktailAdapter.setAlcoholicCocktail((ArrayList<Alcoholic>) alcoholicList);
            } else if (listResult instanceof Result.Error<List<Alcoholic>>) {
                Log.d("Recipe Fragment","Alcoholic Error");
            }
        });
    }
}
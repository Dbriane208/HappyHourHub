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
import daniel.brian.happyhourhub.databinding.FragmentRecipeBinding;
import daniel.brian.happyhourhub.dtos.Alcoholic;
import daniel.brian.happyhourhub.repository.GetAlcoholicCocktailRepository;
import daniel.brian.happyhourhub.util.Result;
import daniel.brian.happyhourhub.viewmodel.AlcoholicCocktailViewModel;
import daniel.brian.happyhourhub.viewmodel.AlcoholicCocktailViewModelFactory;

public class RecipeFragment extends Fragment {
    FragmentRecipeBinding binding;
    AlcoholicCocktailAdapter alcoholicCocktailAdapter;
    AlcoholicCocktailViewModel alcoholicCocktailViewModel;
    GetAlcoholicCocktailRepository getAlcoholicCocktailRepository;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(getLayoutInflater());

        RecyclerView recyclerView = binding.alcoholicCocktails;
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(),3, LinearLayoutManager.VERTICAL,false));
        alcoholicCocktailAdapter = new AlcoholicCocktailAdapter();
        recyclerView.setAdapter(alcoholicCocktailAdapter);

        getAlcoholicCocktailRepository = new GetAlcoholicCocktailRepository();
        AlcoholicCocktailViewModelFactory alcoholicCocktailViewModelFactory = new AlcoholicCocktailViewModelFactory(getAlcoholicCocktailRepository);
        alcoholicCocktailViewModel = new ViewModelProvider(this, alcoholicCocktailViewModelFactory).get(AlcoholicCocktailViewModel.class);
        getAllAlcoholic();

        return binding.getRoot();
    }

    private void getAllAlcoholic() {
        alcoholicCocktailViewModel.getAlcoholicCocktails().observe(getViewLifecycleOwner(), listResult -> {
            if(listResult instanceof Result.Loading<List<Alcoholic>>){
                binding.progressBar.setVisibility(View.VISIBLE);
            }else if(listResult instanceof Result.Success<List<Alcoholic>>){
                binding.progressBar.setVisibility(View.INVISIBLE);
                List<Alcoholic> alcoholicList = ((Result.Success<List<Alcoholic>>)listResult).getData();
                alcoholicCocktailAdapter.setAlcoholicCocktail((ArrayList<Alcoholic>) alcoholicList);
            } else if (listResult instanceof Result.Error<List<Alcoholic>>) {
                Log.d("Recipe Fragment","Error");
            }
        });
    }
}
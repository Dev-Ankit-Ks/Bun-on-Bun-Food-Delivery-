package com.example.bunonbun.Fragments

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.bunonbun.Adapters.MainFoodAdapter
import com.example.bunonbun.Models.MainMenuItem
import com.example.bunonbun.Models.MenuItem
import com.example.bunonbun.R
import com.example.bunonbun.databinding.FragmentMenuBinding
import kotlin.collections.ArrayList
import kotlin.math.abs

class MenuFragment : Fragment(){

    private var tts : TextToSpeech? = null
    private lateinit var binding : FragmentMenuBinding
    private lateinit var list : ArrayList<MenuItem>
    private lateinit var foodList : ArrayList<MainMenuItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentMenuBinding.inflate(layoutInflater)
     AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)


        val imageList = ArrayList<SlideModel>()

        imageList.add(SlideModel("https://lh3.googleusercontent.com/p/AF1QipP8VoRRnS9-p3Gts0gyDQD2rKx8BcK4cbPPGuY=s1360-w1360-h1020"))
        imageList.add(SlideModel(R.drawable.burger_bg))
        imageList.add(SlideModel(R.drawable.wraps_bg))
        imageList.add(SlideModel(R.drawable.bvbv))
        imageList.add(SlideModel("https://lh3.googleusercontent.com/p/AF1QipMxJ9awni1U_ULsIw9BPA44EtORwOqUqCeYzFFL=s1360-w1360-h1020"))
        imageList.add(SlideModel("https://lh3.googleusercontent.com/p/AF1QipMqa6-DnIrBIy6SVsqEImHY7Se_kDKTtxLbmdFb=s1360-w1360-h1020"))
        imageList.add(SlideModel(R.drawable.wraps_bg))
        imageList.add(SlideModel(R.drawable.burger_bg))
        imageList.add(SlideModel("https://lh3.googleusercontent.com/p/AF1QipNkz7kTbfSfilULgDPBg5Yj9aP1Vztpm4RZh55w=s1360-w1360-h1020"))
        imageList.add(SlideModel("https://lh3.googleusercontent.com/p/AF1QipPipIrg0AzJa3i8pl-oV-LIKVkvyVJ8tidlHpcl=s1360-w1360-h1020"))
       imageList.add(SlideModel(R.drawable.bvbv))
        binding.imageSlider.setImageList(imageList, ScaleTypes.FIT)
        list = ArrayList()
        foodList = ArrayList()

//        list.add(MenuItem(R.drawable.burger , "Burger"))
//        list.add(MenuItem(R.drawable.pasta , "Pasta"))
//        list.add(MenuItem(R.drawable.wraps , "Wraps"))
//        list.add(MenuItem(R.drawable.sandwich , "Sandwich"))
//        list.add(MenuItem(R.drawable.frenchfries , "Fries"))
//        list.add(MenuItem(R.drawable.burger , "Burger"))
//        list.add(MenuItem(R.drawable.pasta , "Pasta"))
//        list.add(MenuItem(R.drawable.wraps , "Wraps"))
//        list.add(MenuItem(R.drawable.sandwich , "Sandwich"))
//        list.add(MenuItem(R.drawable.frenchfries , "Fries"))
//
//        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//        binding.myRecyCate.setHasFixedSize(true)
//        binding.myRecyCate.layoutManager = layoutManager
//        val adapter = MenuCategoryAdapter(list)
//        binding.myRecyCate.adapter = adapter

        foodList.add(MainMenuItem(1, "" , "Chezzy Pizza" , R.drawable.burger_bg, 39 , 1))
        foodList.add(MainMenuItem( 2, "" , "Bob's allo tikki" , R.drawable.burger_bg, 78 , 1))
        foodList.add(MainMenuItem( 3, "" , "Chezzy Pizza" , R.drawable.burger_bg, 39 , 1))
        foodList.add(MainMenuItem( 4,  "" , "Bob's allo tikki" , R.drawable.burger_bg, 78 , 1))
        foodList.add(MainMenuItem( 5, "" , "Chezzy Pizza" , R.drawable.burger_bg, 39 , 1))
//        foodList.add(MainMenuItem("Fires" , "Bob's allo fries" ,"https://img.freepik.com/free-photo/top-view-delicious-fries-sauce_23-2149235944.jpg?w=740&t=st=1683135691~exp=1683136291~hmac=d26a3cf9479e756abb46c453c2caea99e1d8e7e3cf62e9a20286751b5bd5cb31", 49 , 1))
//        foodList.add(MainMenuItem("Fires" , "Bob's allo fries" ,"https://img.freepik.com/free-photo/top-view-delicious-fries-sauce_23-2149235944.jpg?w=740&t=st=1683135691~exp=1683136291~hmac=d26a3cf9479e756abb46c453c2caea99e1d8e7e3cf62e9a20286751b5bd5cb31", 49 , 1))
//        foodList.add(MainMenuItem("Sandwich" , "allo masala" , "https://img.freepik.com/free-photo/side-view-club-sandwich-with-salted-cucumbers-lemon-olives-round-white-plate_176474-3049.jpg?w=826&t=st=1683135542~exp=1683136142~hmac=c887acd2ab4fd4f4b4cc9515203fea5d8506c1e9777a81defd3fdb360377d296" , 29 , 1))
//        foodList.add(MainMenuItem("Wraps" , "onion wraps" , "https://img.freepik.com/free-photo/side-view-chicken-roll-grilled-chicken-lettuce-cucumber-tomato-mayo-pita_141793-4849.jpg?w=1380&t=st=1683135617~exp=1683136217~hmac=1e3d04cae4579df80451615201d60656c3e9e872b96fc18a76e292ec77dfaa25", 69 , 1))
//        foodList.add(MainMenuItem("Pasta" , "white soucs pasta" ,"https://img.freepik.com/free-photo/side-view-penne-pasta-with-tomato-sauce-greens-plate_141793-5043.jpg?w=1380&t=st=1683135508~exp=1683136108~hmac=f9c8a65582c4cdfcd822a2e7d4462fa0b72add07fb75e8207e0ae6cd5817bbfa" , 129 , 1))
//        foodList.add(MainMenuItem("Burger" , "Bob's allo tikki" , "https://img.freepik.com/free-photo/front-view-tasty-meat-burger-with-cheese-salad-dark-background_140725-89597.jpg?w=1380&t=st=1683135475~exp=1683136075~hmac=4279c429a70c8b61b0172e7e3b3cedd3ffd1065b6a930c7aa417cabc5a0abda7" , 39 , 1))
//        foodList.add(MainMenuItem("Fires" , "Bob's allo fries" ,"https://img.freepik.com/free-photo/top-view-delicious-fries-sauce_23-2149235944.jpg?w=740&t=st=1683135691~exp=1683136291~hmac=d26a3cf9479e756abb46c453c2caea99e1d8e7e3cf62e9a20286751b5bd5cb31", 49 , 1))
//        foodList.add(MainMenuItem("Sandwich" , "allo masala" , "https://img.freepik.com/free-photo/side-view-club-sandwich-with-salted-cucumbers-lemon-olives-round-white-plate_176474-3049.jpg?w=826&t=st=1683135542~exp=1683136142~hmac=c887acd2ab4fd4f4b4cc9515203fea5d8506c1e9777a81defd3fdb360377d296" , 29 , 1))
//        foodList.add(MainMenuItem("Wraps" , "onion wraps" , "https://img.freepik.com/free-photo/side-view-chicken-roll-grilled-chicken-lettuce-cucumber-tomato-mayo-pita_141793-4849.jpg?w=1380&t=st=1683135617~exp=1683136217~hmac=1e3d04cae4579df80451615201d60656c3e9e872b96fc18a76e292ec77dfaa25", 69 , 1))
//        foodList.add(MainMenuItem("Pasta" , "white soucs pasta" ,"https://img.freepik.com/free-photo/side-view-penne-pasta-with-tomato-sauce-greens-plate_141793-5043.jpg?w=1380&t=st=1683135508~exp=1683136108~hmac=f9c8a65582c4cdfcd822a2e7d4462fa0b72add07fb75e8207e0ae6cd5817bbfa" , 129 , 1))

        val adapter2 = MainFoodAdapter(requireContext() , foodList)
        binding.MenuRec.adapter = adapter2
        binding.MenuRec.offscreenPageLimit = 3
        binding.MenuRec.clipToPadding = false
        binding.MenuRec.clipChildren = false
        binding.MenuRec.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer{page , position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }
        binding.MenuRec.setPageTransformer(transformer)


//        val layoutManager2 = LinearLayoutManager(context)
//        binding.MenuRec.setHasFixedSize(true)
//        binding.MenuRec.layoutManager = layoutManager2
//        val adapter2 = MainFoodAdapter(requireContext() , foodList)
//        binding.MenuRec.adapter = adapter2
        return binding.root
    }


}
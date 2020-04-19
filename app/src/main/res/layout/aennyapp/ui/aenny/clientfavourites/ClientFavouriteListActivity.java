package design.swira.aennyapp.ui.aenny.clientfavourites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityClientFavouriteListBinding;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.ui.aenny.adapters.ClientFavouriteListAdapter;
import design.swira.aennyapp.ui.aenny.customdialog.MyCustomDialogActivity;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.SwipeHelper;

public class ClientFavouriteListActivity extends AppCompatActivity implements View.OnClickListener  {

    ActivityClientFavouriteListBinding binding;
    ClientFavouriteViewModel viewModel;
    ClientFavouriteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_client_favourite_list);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_client_favourite_list);

        viewModel= ViewModelProviders.of(this).get(ClientFavouriteViewModel.class);

        binding.add.setOnClickListener(this);

        adapter=new ClientFavouriteListAdapter(this);

        bindData();



    }

    private void bindData() {
        int clientid= Constants.getClientId(ClientFavouriteListActivity.this);
        viewModel.getClientFavouriteById(clientid);

        viewModel.clientFavouritelistMutableLiveData.observe(ClientFavouriteListActivity.this, new Observer<List<ClientFavouriteResponse>>() {
            @Override
            public void onChanged(List<ClientFavouriteResponse> clientFavouriteResponses) {
                if(clientFavouriteResponses != null){
                    adapter.setList(clientFavouriteResponses);
                    binding.recycler.setAdapter(adapter);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(ClientFavouriteListActivity.this));
                    binding.pbar.setVisibility(View.GONE);
                    binding.recycler.setVisibility(View.VISIBLE);


                    SwipeHelper swipeHelper = new SwipeHelper(ClientFavouriteListActivity.this, binding.recycler) {
                        @Override
                        public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                            underlayButtons.add(new SwipeHelper.UnderlayButton(
                                    "Delete",
                                    0,
                                    Color.parseColor("#ea0000"),
                                    new SwipeHelper.UnderlayButtonClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            // TODO: onDelete

                                            int id = clientFavouriteResponses.get(pos).getClient_Favourite_Id();
                                            //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                                            Intent i=new Intent(ClientFavouriteListActivity.this, MyCustomDialogActivity.class);
                                            i.putExtra("id",id);
                                            i.putExtra("title","Confirm delete");
                                            i.putExtra("msg","Are you sure you want to delete this client favourite ?");
                                            i.putExtra("flag","fav");
                                            startActivity(i);
                                        }
                                    }
                            ));

                            /*underlayButtons.add(new SwipeHelper.UnderlayButton(
                                    "Transfer",
                                    0,
                                    Color.parseColor("#FF9502"),
                                    new SwipeHelper.UnderlayButtonClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            // TODO: OnTransfer
                                        }
                                    }
                            ));*/
                            underlayButtons.add(new SwipeHelper.UnderlayButton(
                                    "Edit",
                                    0,
                                    Color.parseColor("#0c66f0"),
                                    new SwipeHelper.UnderlayButtonClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            // TODO: OnUnshare

                                            int id = clientFavouriteResponses.get(pos).getClient_Favourite_Id();
                                            //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                                            Intent i=new Intent(ClientFavouriteListActivity.this, UpdateClientFavouriteActivity.class);
                                            i.putExtra("id",id);
                                            startActivity(i);

                                        }
                                    }
                            ));
                        }
                    };


                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add){
            addFavourite();
        }

    }

    private void addFavourite() {
        Intent i=new Intent(ClientFavouriteListActivity.this,AddClientFavouritesActivity.class);
        startActivity(i);
    }


}

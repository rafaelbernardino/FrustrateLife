package com.bernardino.frustratelife.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bernardino.frustratelife.Constants;
import com.bernardino.frustratelife.FrustrateActivity;
import com.bernardino.frustratelife.R;
import com.bernardino.frustratelife.adapter.FrustrationAdapter;
import com.bernardino.frustratelife.listener.OnClickListener;
import com.bernardino.frustratelife.persistence.bean.Frustration;
import com.bernardino.frustratelife.persistence.dao.DaoFrustration;

import java.util.List;

public class FrustrationFragment extends Fragment {
    private String tipo;
    private RecyclerView rvFrustration;
    private FrustrationAdapter adapter;
    private List<Frustration> listFrustration;

    public FrustrationFragment() {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frustration, container, false);
        rvFrustration = (RecyclerView) v.findViewById(R.id.rvFrustration);
        rvFrustration.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFrustration.setItemAnimator(new DefaultItemAnimator());
        rvFrustration.setHasFixedSize(true);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        loadData();
//    }
    private void loadData() {
        try {
            listFrustration = DaoFrustration.getListFrustration(getActivity());
        } catch (Exception e) {
            Log.e(Constants.TAG_LOG, getString(R.string.ERROR_DATA_LOAD));
            Toast.makeText(getActivity(), getString(R.string.ERROR_DATA_LOAD), Toast.LENGTH_LONG).show();
        }

        adapter = new FrustrationAdapter(getActivity(), listFrustration, onClickLister());
        rvFrustration.setAdapter(adapter);
    }

    private OnClickListener onClickLister() {
        return new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                final int listPosition = position;
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle(R.string.action)
                .setItems(R.array.action_array, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        if (position == 0) {
                            try {
//                                DaoFrustration.updateFrustration(getActivity(), listFrustration.get(listPosition));
//                                Toast.makeText(getActivity(), getString(R.string.data_updated), Toast.LENGTH_LONG).show();
//                                dialogInterface.dismiss();
//                                loadData();
                                Intent i = new Intent(getActivity(), FrustrateActivity.class);
                                i.putExtra(Constants.ACTION, "edit");
                                i.putExtra(Constants.OBJ_FRUSTRATION, listFrustration.get(listPosition));
                                dialogInterface.dismiss();
                                startActivity(i);
                            } catch (Exception e) {
                                Log.e(Constants.TAG_LOG, getString(R.string.error_update_data));
                                Toast.makeText(getActivity(), getString(R.string.error_delete_data), Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(getActivity(), "Clicou no item editar", Toast.LENGTH_LONG).show();
                        } else {
                            try {
                                DaoFrustration.deleteFrustration(getActivity(), listFrustration.get(listPosition).getIdFrustration());
                                Toast.makeText(getActivity(), getString(R.string.data_deleted), Toast.LENGTH_LONG).show();
                                dialogInterface.dismiss();
                                loadData();
                            } catch (Exception e) {
                                Log.e(Constants.TAG_LOG, getString(R.string.error_delete_data));
                                Toast.makeText(getActivity(), getString(R.string.error_delete_data), Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
    }
}

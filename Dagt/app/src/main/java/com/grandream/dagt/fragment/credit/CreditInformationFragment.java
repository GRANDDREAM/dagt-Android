package com.grandream.dagt.fragment.credit;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.grandream.dagt.R;
import com.grandream.dagt.base.GenericFragmentActivity;
import com.grandream.dagt.bean.credit.CreditInfo;
import com.grandream.dagt.bean.credit.CreditInfoReslut;
import com.grandream.dagt.databinding.CreditinformationLayoutBinding;
import com.grandream.dagt.fragment.BaseFragment;
import com.grandream.dagt.fragment.adapter.OnItemClickListener;
import com.grandream.dagt.fragment.adapter.credit.OrgInfoRcleryAdapter;
import com.grandream.dagt.fragment.my.CommonProblemFragment;
import com.grandream.dagt.http.api.ApiService;
import com.grandream.dagt.http.interfaces.IHttpCallback;
import com.grandream.dagt.http.model.HttpResult;
import com.grandream.dagt.requestbody.credit.SendCreatCreditApplys;
import com.grandream.dagt.utils.CommonUtil;
import com.grandream.dagt.utils.Utils;

/**
 * 授信信息 第二步
 * Created by chenjing on 2018/4/17.
 */

public class CreditInformationFragment extends BaseFragment implements SeekBar.OnSeekBarChangeListener {
    private CreditinformationLayoutBinding binding;
    private CreditInfo creditInfo;
    protected OrgInfoRcleryAdapter adapter;
    private String id;
    private int p;
    private int step;
    private int max;
    Double server_fee;
    private int rule_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        savedInstanceState = getArguments();
        creditInfo = (CreditInfo) savedInstanceState.getSerializable("CREDITINFO");
        rule_id = savedInstanceState.getInt("RULE_ID");
        binding = DataBindingUtil.inflate(inflater, R.layout.creditinformation_layout, container, false);
        binding.setClick(this);
        initView();
        return binding.getRoot();
    }



    private void initView() {
        binding.setBean(creditInfo.getData());//初始化视图
        //初始服务费
        server_fee = Utils.toDouble(binding.needAmount.getText().toString()) * Utils.toDouble(creditInfo.getData().getCredit_apply_server_base_fee());
        binding.fee.setText(server_fee + creditInfo.getData().getCredit_apply_server_fee_unit());

        binding.navigation.setLeftClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendKeyBackEvent();
            }
        });
        binding.navigation.setRightText("授信规则");
        binding.navigation.setRightClick(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("RULE_ID", rule_id);
                GenericFragmentActivity.start(getActivity(), CommonProblemFragment.class, bundle);
            }
        });

        //滑动区间
        step = Utils.toInteger(creditInfo.getData().getStep_amount_limit());
        //默认选中的银行id
        id = creditInfo.getData().getLoan_organization().get(0).getId();
        //最大滑动区间数
        max = (int) (Utils.toDouble(creditInfo.getData().getLoan_organization().get(0).getLoan_limit_max()) / Utils.toDouble(creditInfo.getData().getStep_amount_limit()));
        binding.needAmountSeek.setMax(max);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.orgRecy.setLayoutManager(manager);
        adapter = new OrgInfoRcleryAdapter(getActivity(), creditInfo.getData().getLoan_organization());
        binding.orgRecy.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                p = position;
                id = creditInfo.getData().getLoan_organization().get(position).getId();
                binding.canAmountLimit.setText("￥" + creditInfo.getData().getLoan_organization().get(position).getCan_amount_limit());
                max = (int) (Utils.toDouble(creditInfo.getData().getLoan_organization().get(position).getLoan_limit_max()) / Utils.toDouble(creditInfo.getData().getStep_amount_limit()));
                binding.needAmountSeek.setMax(max);
            }
        });
        binding.needAmountSeek.setOnSeekBarChangeListener(this);

    }

    public void NextClick(View view) {
        if (CheckValue()) {

        }
        SendCreatCreditApplys sendCreatCreditApplys = new SendCreatCreditApplys();
        sendCreatCreditApplys.setUser_id(Utils.getUser_id(getActivity()));
        sendCreatCreditApplys.setUser_wallet_type(creditInfo.getData().getWallet_type());
        sendCreatCreditApplys.setUser_wallet_address(creditInfo.getData().getWallet_address());
        sendCreatCreditApplys.setNeed_amount_limit(binding.needAmount.getText().toString());
        Double server_fee = Utils.toDouble(binding.needAmount.getText().toString()) * Utils.toDouble(creditInfo.getData().getCredit_apply_server_base_fee());
        sendCreatCreditApplys.setCredit_apply_server_fee(server_fee.toString());
        sendCreatCreditApplys.setCredit_apply_server_fee_unit(creditInfo.getData().getCredit_apply_server_fee_unit());
        sendCreatCreditApplys.setLoan_coin_id(creditInfo.getData().getLoan_coin_id());
        sendCreatCreditApplys.setLoan_organization_years_ratio(creditInfo.getData().getLoan_organization().get(p).getOrg_years_ratio());
        sendCreatCreditApplys.setLoan_organization_id(id);
        sendCreatCreditApplys.setCan_amount_limit(creditInfo.getData().getCan_amount_limit());

        ApiService.CreditApplys(getActivity(), new IHttpCallback<CreditInfoReslut>() {
            @Override
            public void onNext(HttpResult<CreditInfoReslut> t) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("CREDITINFORESLUT", t.getResponse_data());
                bundle.putInt("RULE_ID", rule_id);
                GenericFragmentActivity.start(getActivity(), LockContractsFragment.class, bundle);
                getActivity().finish();
            }

            @Override
            public void onError(int code, String message) {

            }
        }, Utils.getRequestBody(sendCreatCreditApplys));
    }

    private boolean CheckValue() {
        return true;
    }


    /**
     * 进度条监听
     *
     * @param seekBar
     * @param i
     * @param b
     */

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int amout = i * Utils.toInteger(creditInfo.getData().getStep_amount_limit());
        binding.needAmount.setText(" " + amout);
        binding.fee.setText(amout * Utils.toDouble(creditInfo.getData().getCredit_apply_server_base_fee()) + creditInfo.getData().getCredit_apply_server_fee_unit());
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}

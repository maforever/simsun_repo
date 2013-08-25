package com.zhaori.simsun;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhaori.simsun.model.Nrl;
import com.zhaori.simsun.model.Payee;
import com.zhaori.simsun.service.LoginSharedPreferenceService;
import com.zhaori.simsun.service.NRLService;
import com.zhaori.simsun.service.PayeeService;
import com.zhaori.simsun.utils.StreamTool;

public class QianFaShenQingActivity extends Activity {
	private Intent intent = null;
	private LoginSharedPreferenceService loginSp = null;
	private int payee_id = 0;
	private PayeeService ps = null;
	private Payee p = null;
	private TextView skgsEt, skzhEt, fkzhEt, jyrqEt, jyxhEt = null;
	private String skgs, skzh, fkzh, jyrq, fkje = null;
	private EditText fkjeEt = null;
	private Dialog dialog = null;
	private DatePicker dp = null;
	private boolean isTiXing = true;
	private CheckBox cb = null;
	private Nrl nrl = null;
	private String time;
	private NRLService ns = null;
//	private int business_type = 0;
	private String jyxh = null; //交易序号
	private RelativeLayout rl = null;
	private String remark = "-1";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.qianfashenqing);
		loginSp = new LoginSharedPreferenceService(this);
		ps = new PayeeService(this);
		ns = new NRLService(this);
		
		rl = (RelativeLayout) this.findViewById(R.id.skzh_layout);
		
//		business_type = this.getIntent().getIntExtra("business_type", 0);
//		if(business_type == 1) {
//			rl.setVisibility(ViewGroup.GONE);
//			remark = "yhpj";
//		}
		
		remark = "电子结算卡";
		
		skgsEt = (TextView) this.findViewById(R.id.skgs);
		skzhEt = (TextView) this.findViewById(R.id.skzh);
		fkzhEt = (TextView) this.findViewById(R.id.fkzh);
		jyrqEt = (TextView) this.findViewById(R.id.jyrq);
		fkjeEt = (EditText) this.findViewById(R.id.fkje);
		jyxhEt = (TextView) this.findViewById(R.id.jyxh);
		
		jyxh = StreamTool.getJYXH();
		jyxhEt.setText(jyxh);
		
		cb = (CheckBox) this.findViewById(R.id.tixing);
		cb.setChecked(true);
		dp = new DatePicker(this);
		initDateDialog();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		payee_id = loginSp.getSelectedPayeeId();
		loginSp.clearSelectedPayeeId();
		String new_payee_account = loginSp.getNewPayeeAccount();
		loginSp.clearNewPayeeAccount();
		String pay_account = loginSp.getPayAccount();
		loginSp.clearPayAccount();
//		String date = loginSp.getDealDate();
//		loginSp.clearDealDate();
		if(payee_id > 0) {
			p = ps.getPayeeById(payee_id);
			skgsEt.setText(p.getPayee_name());
			skzhEt.setText(p.getPayee_account());
		}
		if(new_payee_account != null &&  !"".equals(new_payee_account)) {
			skzhEt.setText(new_payee_account);
		}
		if(pay_account != null && !"".equals(pay_account)) {
			fkzhEt.setText(pay_account);
		}
//		if(date != null && !"".equals(date)) {
//			jyrqEt.setText(date);
//		}
		
	}
	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			QianFaShenQingActivity.this.finish();
			intent = new Intent(QianFaShenQingActivity.this, DianZiJieSuanKaActivity.class);
//			intent.putExtra("business_type", business_type);
			startActivity(intent);
			break;
		case R.id.skgs_btn:
//			QianFaShenQingActivity.this.finish();
//			intent = new Intent(QianFaShenQingActivity.this, XuanZheShouKuanRenMingChengActivity.class);
//			startActivity(intent);
			
			XuanZheShouKuanRenMingChengActivity.prepare(QianFaShenQingActivity.this, R.id.root);
			intent = new Intent(QianFaShenQingActivity.this, XuanZheShouKuanRenMingChengActivity.class);
			intent.putExtra("position", loginSp.getSelectedPayeePosition());
			loginSp.clearSelectedPayeePostion();
			startActivity(intent);
			overridePendingTransition(0, 0);
			break;
			
		case R.id.skzh_btn:
//			QianFaShenQingActivity.this.finish();
//			intent = new Intent(QianFaShenQingActivity.this, SheZhiShouKuanRenZhangHaoAcitivity.class);
//			startActivity(intent);
			
			SheZhiShouKuanRenZhangHaoAcitivity.prepare(QianFaShenQingActivity.this, R.id.root);
			intent = new Intent(QianFaShenQingActivity.this, SheZhiShouKuanRenZhangHaoAcitivity.class);
			startActivity(intent);
			overridePendingTransition(0, 0);
			break;
			
		case R.id.fkzh_btn:
			SheZhiFuKuanRenZhangHaoActivity.prepare(QianFaShenQingActivity.this, R.id.root);
			intent = new Intent(QianFaShenQingActivity.this, SheZhiFuKuanRenZhangHaoActivity.class);
			startActivity(intent);
			overridePendingTransition(0, 0);
			break;
			
		case R.id.jyrq_btn:
//			SheZhiJiaoYiRiQiActivity.prepare(QianFaShenQingActivity.this, R.id.root);
//			intent = new Intent(QianFaShenQingActivity.this, SheZhiJiaoYiRiQiActivity.class);
//			startActivity(intent);
//			overridePendingTransition(0, 0);
			dialog.show();
			break;
			
		case R.id.cz:
			skgsEt.setText(null);
			fkzhEt.setText(null);
			skzhEt.setText(null);
			jyrqEt.setText(null);
			fkjeEt.setText(null);
			break;
			
		case R.id.bc_btn:
			getInfo();
			if(checkInfo()) {
				nrl = new Nrl();
				nrl.setTime(time);
				nrl.setTo_name(skgs);
				nrl.setTo_account(skzh);
				nrl.setFrom_account(fkzh);
				nrl.setQuantity(Float.valueOf(fkje));
				nrl.setRequest_date(jyrq);
				nrl.setExamine_email(null);
				nrl.setExamine_phone(null);
				nrl.setExamine_userid(null);
				nrl.setRequest_userid(Integer.parseInt(loginSp.getLoginUserId()));
				nrl.setBusiness_type(0);
				nrl.setBusiness_serial_num(jyxh);
				nrl.setRemark(remark);
				Log.i("a", nrl.toString());
				
				if(isTiXing) {
					nrl.setNotify_email(1);
				}else {
					nrl.setNotify_email(0);
				}
				ns.addNRL(nrl);
				
				Toast.makeText(this, "保存成功!", 0).show();
				
			}else {
				Toast.makeText(this, "请填写完整的签发申请信息!", 0).show();
			}
			break;
			
		case R.id.fs_btn:
			getInfo();
			if(checkInfo()) {
				QianFaShenQingActivity.this.finish();
				intent = new Intent(QianFaShenQingActivity.this, XuanZeShouQuanRenActivity.class);
				intent.putExtra("skgs", skgs);
				intent.putExtra("skzh", skzh);
				intent.putExtra("fkzh", fkzh);
				intent.putExtra("fkje", fkje);
				intent.putExtra("jyrq", jyrq);
				intent.putExtra("isTixing", isTiXing);
				intent.putExtra("time", time);
				intent.putExtra("request_userid", loginSp.getLoginUserId());
				intent.putExtra("business_type", 0);
				intent.putExtra("business_serial_num", jyxh);
				intent.putExtra("remark", remark);
				intent.putExtra("flag", "dzjsk");
				startActivity(intent);
			}else {
				Toast.makeText(this, "请填写完整的签发申请信息!", 0).show();
			}
			break;
		}
		
	}
	
	public void getInfo() {
		skgs = skgsEt.getText().toString();
		skzh = skzhEt.getText().toString();
		fkzh = fkzhEt.getText().toString();
		jyrq = jyrqEt.getText().toString(); 
		fkje = fkjeEt.getText().toString();
		isTiXing = cb.isChecked();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm HH:mm:ss");
		time = sdf.format(new Date());
		
	}
	
	public boolean checkInfo() {
		if(skgs != null && !"".equals(skgs) && skzh != null && !"".equals(skzh) && fkzh != null && !"".equals(fkzh) && jyrq != null && !"".equals(jyrq) && fkje != null && !"".equals(fkje)) {
			return true;
		}
		return false;
	}
	
	
	public void initDateDialog() {
		dialog = new AlertDialog.Builder(this)
		.setTitle("设置交易日期")
		.setView(dp)
		.setPositiveButton("设置", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String year = String.valueOf(dp.getYear());
				String month = String.valueOf(dp.getMonth() + 1);
				String day = String.valueOf(dp.getDayOfMonth());
				String date = year + "-" + month + "-" + day;
				jyrqEt.setText(date);
			}
		})
		.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		})
		.create();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ps.closeDB();
		ns.clossDB();
	}
}














































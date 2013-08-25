package com.zhaori.simsun;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zhaori.simsun.model.Nrl;
import com.zhaori.simsun.model.Payee;
import com.zhaori.simsun.service.LoginSharedPreferenceService;
import com.zhaori.simsun.service.NRLService;
import com.zhaori.simsun.service.PayeeService;
import com.zhaori.simsun.utils.StreamTool;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Qianfashenqing_yhpj extends Activity {
	private Intent intent = null;
	private LoginSharedPreferenceService loginSp = null;
	private int payee_id = 0;
	private PayeeService ps = null;
	private Payee p = null;
	private TextView skgsEt, ywlxEt, fkzhEt, jyrqEt, jyxhEt  = null;
	private String skgs, skzh, fkzh, jyrq, fkje = null;
	private EditText fkjeEt, remarkEt = null;
	private Dialog dialog = null;
	private DatePicker dp = null;
	private boolean isTiXing = true;
	private CheckBox cb = null;
	private Nrl nrl = null;
	private String time;
	private NRLService ns = null;
	private int business_type = 0;
	private String jyxh = null; //交易序号
	private RelativeLayout rl = null;
	private String remark = "-1";
	private String ywlx = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qianfashenqing_yhpj);
		loginSp = new LoginSharedPreferenceService(this);
		
		ps = new PayeeService(this);
		ns = new NRLService(this);
		
		skgsEt = (TextView) this.findViewById(R.id.skgs);
		fkzhEt = (TextView) this.findViewById(R.id.fkzh);
		ywlxEt = (TextView) this.findViewById(R.id.ywlx);
		jyrqEt = (TextView) this.findViewById(R.id.jyrq);
		fkjeEt = (EditText) this.findViewById(R.id.fkje);
		jyxhEt = (TextView) this.findViewById(R.id.jyxh);
		remarkEt = (EditText) this.findViewById(R.id.ytbz);
		
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
		String pay_account = loginSp.getPayAccount();
		loginSp.clearPayAccount();
		String date = loginSp.getDealDate();
		loginSp.clearDealDate();
		ywlx = loginSp.getYWLX();
		Log.i("a", "业务类型 = " + ywlx);
		loginSp.clearYWLX();
		if(payee_id > 0) {
			p = ps.getPayeeById(payee_id);
			skgsEt.setText(p.getPayee_name());
		}
		if(pay_account != null && !"".equals(pay_account)) {
			fkzhEt.setText(pay_account);
		}
		if(date != null && !"".equals(date)) {
			jyrqEt.setText(date);
		}
		if(ywlx != null && !"".equals(ywlx)) {
			ywlxEt.setText(ywlx);
		}
		
	}
	
	

	
	public void btnClick(View view) {
		switch (view.getId()) {
		case R.id.back:
			Qianfashenqing_yhpj.this.finish();
			intent = new Intent(Qianfashenqing_yhpj.this, YinHangPiaoJuActivity.class);
			startActivity(intent);
			break;
			
		case R.id.skgs_btn:
			XuanZheShouKuanRenMingChengActivity.prepare(Qianfashenqing_yhpj.this, R.id.root);
			intent = new Intent(Qianfashenqing_yhpj.this, XuanZheShouKuanRenMingChengActivity.class);
			intent.putExtra("position", loginSp.getSelectedPayeePosition());
			loginSp.clearSelectedPayeePostion();
			startActivity(intent);
			overridePendingTransition(0, 0);
			break;

		case R.id.fkzh_btn:
			SheZhiFuKuanRenZhangHaoActivity.prepare(Qianfashenqing_yhpj.this, R.id.root);
			intent = new Intent(Qianfashenqing_yhpj.this, SheZhiFuKuanRenZhangHaoActivity.class);
			startActivity(intent);
			overridePendingTransition(0, 0);
			break;
			
			
		case R.id.jyrq_btn:
			dialog.show();
			break;
			
		case R.id.ywlx_btn:
			XuanzeyewuzhongleiActivity.prepare(Qianfashenqing_yhpj.this, R.id.root);
			intent = new Intent(Qianfashenqing_yhpj.this, XuanzeyewuzhongleiActivity.class);
			startActivity(intent);
			overridePendingTransition(0, 0);
			break;
			
		case R.id.fs_btn:
			getInfo();
			if(checkInfo()) {
				Qianfashenqing_yhpj.this.finish();
				intent = new Intent(Qianfashenqing_yhpj.this, XuanZeShouQuanRenActivity.class);
				intent.putExtra("skgs", skgs);
				intent.putExtra("skzh", skzh);
				intent.putExtra("fkzh", fkzh);
				intent.putExtra("fkje", fkje);
				intent.putExtra("jyrq", jyrq);
				intent.putExtra("isTixing", isTiXing);
				intent.putExtra("time", time);
				intent.putExtra("request_userid", loginSp.getLoginUserId());
				intent.putExtra("business_type", getBusiness_type());
				intent.putExtra("business_serial_num", jyxh);
				intent.putExtra("remark", remark);
				intent.putExtra("flag", "yhpj");
				startActivity(intent);
			}else {
				Toast.makeText(this, "请填写完整的签发申请信息!", 0).show();
			}
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
				nrl.setBusiness_type(getBusiness_type());
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
		}
		
		
	}
	
	public int getBusiness_type() {
		if(ywlx != null && "支票".equals(ywlx)) {
			return 1;
		}else if(ywlx != null && "银行汇票申请书".equals(ywlx)) {
			return 2;
		}else if(ywlx != null && "银行本票申请书".equals(ywlx)) {
			return 3;
		}else if(ywlx != null && "电汇凭证".equals(ywlx)) {
			return 4;
		}
		return 5;
	}
	
	public void getInfo() {
		skgs = skgsEt.getText().toString();
		fkzh = fkzhEt.getText().toString();
		jyrq = jyrqEt.getText().toString(); 
		fkje = fkjeEt.getText().toString();
		ywlx = ywlxEt.getText().toString();
		isTiXing = cb.isChecked();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm HH:mm:ss");
		time = sdf.format(new Date());
		skzh = "-1";
		remark = remarkEt.getText().toString();
		if(remark == null || "".equals(remark)) {
			remark = "无";
		}
	}
	
	public boolean checkInfo() {
		Log.i("a", "skgs = " + skgs + " fkzh = " + fkzh + " jyrq = " + jyrq + " fkje = " + fkje + " ywlx = " + ywlx);
		if(skgs != null && !"".equals(skgs)  && fkzh != null && !"".equals(fkzh) && jyrq != null && !"".equals(jyrq) && fkje != null && !"".equals(fkje) && ywlx != null && !"".equals(ywlx)) {
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
		ns.clossDB();
		ps.closeDB();
	}
}




























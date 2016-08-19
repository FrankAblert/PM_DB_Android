package com.mzba.pokemon.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mzba.pokemon.R;
import com.mzba.pokemon.util.StringUtil;


/**
 * 
 * @author 06peng
 * 
 */
public class CustomAnimationView extends RelativeLayout {
	
    private ProgressBarCircularIndeterminate progressBar;
	private TextView tv;

	public CustomAnimationView(Context context) {
		super(context);
		initView();
	}

	public CustomAnimationView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
	}

	public CustomAnimationView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}
	
	private void initView() {
		LayoutInflater.from(getContext()).inflate(R.layout.refreshable_list_footer, this);
        progressBar = (ProgressBarCircularIndeterminate) findViewById(R.id.load_more_progressbar);
		tv = (TextView) findViewById(R.id.load_more_textview);
	}

	public void loadComplete(String text) {
        progressBar.setVisibility(View.GONE);
        tv.setVisibility(View.VISIBLE);
		if (StringUtil.isNotBlank(text)) {
			tv.setText(text);
		} else {
			tv.setText(getContext().getString(R.string.load_complete));
		}
	}
	
	public void startLoad() {
		setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        tv.setVisibility(View.GONE);
	}
}

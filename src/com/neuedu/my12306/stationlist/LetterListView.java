package com.neuedu.my12306.stationlist;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class LetterListView extends View {
	
	int choose = -1;// 选中
	String[] letter = {"#","A","B","C","D","E","F","G","H",
					   "I","J","K","L","M","N","O","P","Q","R","S",
					   "T","U","V","W","X","Y","Z"};
	Paint paint = new Paint();
	OnTouchingLetterChangedListener onTouchingLetterChangedListener;
	
	public LetterListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}
	public LetterListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	public LetterListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//获取画布大小
		int width =  getWidth();
		int height = getHeight();
		//每一个字符的高度
		int singleHeight = height/letter.length;
		//canvas.drawColor(Color.parseColor("#66ff33"));//设置背景颜色
		for(int i=0; i<letter.length;i++)
		{
			//设置画布属性
			paint.setColor(Color.LTGRAY);//设置颜色
			paint.setTypeface(Typeface.DEFAULT_BOLD);//设置画笔粗细
			paint.setAntiAlias(true);//设置抗锯齿
			//计算字符画在画布的位置x|y
			float x= width/2 - paint.measureText(letter[i])/2;
			float y = i*singleHeight + singleHeight;
			if(i == choose)
			{
				paint.setColor(Color.GREEN);
				paint.setFakeBoldText(true);
			}
			canvas.drawText(letter[i], x, y, paint);
			paint.reset();
		}
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		final int action = event.getAction();
		final float y = event.getY();
		OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
		final int c = (int) (y/getHeight()*letter.length);//获取对应的字母letter[c]
		final int oldchoose = choose;
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if(oldchoose != c && listener != null)
			{
				if(c>=0 && c<letter.length)
				{
					choose = c;
					listener.OnTouchingLetterChanged(letter[c]);
					invalidate();
				}
			}
			break;
		case MotionEvent.ACTION_UP:	
			choose = -1;
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			if(oldchoose != c && listener != null)
			{
				if(c>=0 && c<letter.length)
				{
					choose = c;
					listener.OnTouchingLetterChanged(letter[c]);
					invalidate();
				}
			}
			break;
		default:
			break;
		}
		return true;
	}
	
	public void setOnTouchingLetterChangedListener(
			OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
		this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
	}

	//定义内部监听器
	interface OnTouchingLetterChangedListener{
		public void OnTouchingLetterChanged(String c);
	}
	
}

package lj.com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {

	private LinearLayout mWrapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int  mScreenWidth;
	private int mMenuWidth;
	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		
//		TypedArray a=context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu, defStyle, 0);
//		a.recycle();
//		int n=a.getIndexCount();
		
		
		WindowManager wm=(WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        DisplayMetrics outMetrics=new DisplayMetrics ();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth=outMetrics.widthPixels;
        mMenueRightPadding=  (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
	

	}

	public SlidingMenu(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	private boolean once;
	private boolean isopen;
	private int mMenueRightPadding=50;
	

	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs,0);
			}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		if(!once)
		{
		mWrapper=(LinearLayout) getChildAt(0);
		mMenu=(ViewGroup) mWrapper.getChildAt(0);
		mContent=(ViewGroup) mWrapper.getChildAt(1);
		mMenuWidth=mMenu.getLayoutParams().width= mScreenWidth- mMenueRightPadding;
		mContent.getLayoutParams().width= mScreenWidth;
		once=true;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
	}
//
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
	
		
		super.onLayout(changed, l, t, r, b);
		if(changed)
		    this.scrollTo(mMenuWidth, 0);
		
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int action=ev.getAction();
		switch(action)
		{
		case MotionEvent.ACTION_UP:
			int scrollx=getScrollX();
			if(scrollx>=mMenuWidth/2)
			{
				this.smoothScrollTo(mMenuWidth, 0);
				isopen=false;
			}
			else
			{
				this.smoothScrollTo(0, 0);
				isopen=true;
			}
			return true;
		}
		
		
		
		return super.onTouchEvent(ev);
	}
	public void openMenue()
	{
		if(isopen)return;
		this.smoothScrollTo(0,0);
		isopen=true;
	}
	
	public void closeMenue()
	{
		if(!isopen)return;
		this.smoothScrollTo(mMenuWidth,0);
		isopen=false;
	}
	public void toggle()
	{
		if(isopen)
		{
			closeMenue();
		}
		else
			{openMenue();
			}
			}
	}
	
	



/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.library.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

import com.dryseed.dryseedapp.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;

@SuppressLint("ViewConstructor")
public class FlipLoadingLayout extends LoadingLayout {

	static final int FLIP_ANIMATION_DURATION = 150;

	private final Animation mRotateAnimation, mResetRotateAnimation;

	public FlipLoadingLayout(Context context, final Mode mode, final Orientation scrollDirection, TypedArray attrs) {
		super(context, mode, scrollDirection, attrs);

		final int rotateAngle = mode == Mode.PULL_FROM_START ? -180 : 180;

		mRotateAnimation = new RotateAnimation(0, rotateAngle, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		mRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
		mRotateAnimation.setDuration(FLIP_ANIMATION_DURATION);
		mRotateAnimation.setFillAfter(true);

		mResetRotateAnimation = new RotateAnimation(rotateAngle, 0, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		mResetRotateAnimation.setInterpolator(ANIMATION_INTERPOLATOR);
		mResetRotateAnimation.setDuration(FLIP_ANIMATION_DURATION);
		mResetRotateAnimation.setFillAfter(true);
		
		mHeaderImage.setVisibility(View.INVISIBLE);
	}

	@Override
	protected void onLoadingDrawableSet(Drawable imageDrawable) {
//		if (null != imageDrawable) {
//			final int dHeight = imageDrawable.getIntrinsicHeight();
//			final int dWidth = imageDrawable.getIntrinsicWidth();

			/**
			 * We need to set the width/height of the ImageView so that it is
			 * square with each side the size of the largest drawable dimension.
			 * This is so that it doesn't clip when rotated.
			 */
//			ViewGroup.LayoutParams lp = mHeaderImage.getLayoutParams();
//			lp.width = lp.height = Math.max(dHeight, dWidth);
//			mHeaderImage.requestLayout();

			/**
			 * We now rotate the Drawable so that is at the correct rotation,
			 * and is centered.
			 */
//			mHeaderImage.setScaleType(ScaleType.MATRIX);
//			Matrix matrix = new Matrix();
//			matrix.postTranslate((lp.width - dWidth) / 2f, (lp.height - dHeight) / 2f);
//			matrix.postRotate(getDrawableRotationAngle(), lp.width / 2f, lp.height / 2f);
//			mHeaderImage.setImageMatrix(matrix);
//		}
	}

	@Override
	protected void onPullImpl(float scaleOfLayout) {
		// NO-OP
	}

	@Override
	protected void pullToRefreshImpl() {
		// Only start reset Animation, we've previously show the rotate anim
		if (mRotateAnimation == mHeaderImage.getAnimation()) {
//			mHeaderImage.startAnimation(mResetRotateAnimation);
		}
	}

	@Override
	protected void refreshingImpl() {
//		mHeaderImage.clearAnimation();
//		mHeaderImage.setVisibility(View.INVISIBLE);
		mHeaderProgress.setVisibility(View.VISIBLE);
	}

	@Override
	protected void releaseToRefreshImpl() {
//		mHeaderImage.startAnimation(mRotateAnimation);
	}

	@Override
	protected void resetImpl() {
//		mHeaderImage.clearAnimation();
		mHeaderProgress.setVisibility(View.GONE);
		mHeaderImage.setVisibility(View.INVISIBLE);
	}

	@Override
	protected int getDefaultDrawableResId() {
		return R.drawable.default_ptr_flip;
	}


	@Override
	public void onScroll(int x, int y) {
		
	}

	@Override
	public void addHeaderView(View header, android.view.ViewGroup.LayoutParams params) {
		
	}

}

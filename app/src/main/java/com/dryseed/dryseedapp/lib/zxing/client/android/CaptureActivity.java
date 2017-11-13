package com.dryseed.dryseedapp.lib.zxing.client.android;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.dryseed.dryseedapp.R;
import com.dryseed.dryseedapp.lib.zxing.client.android.camera.CameraManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

import java.io.IOException;
import java.util.List;
import java.util.Vector;


/**
 * Initial the camera
 * 
 * 
 */
public class CaptureActivity extends Activity implements Callback {
	private static String TAG = "Barcode_CaptureActivity";
	private static final float BEEP_VOLUME = 0.10f;
	private static final int LONG_LIMIT = 800;
	private static final int SHORT_LIMIT = 128;

	private Handler mHandler = new Handler();
	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private boolean vibrate;
	private boolean isLightOn = false;
	private boolean isCameraReady = false;
	private ProgressDialog mProgressDialog;
	private int cameraPosition = 1;//0代表前置摄像头，1代表后置摄像头
	private int tabPosition = 0;
	private SurfaceHolder surfaceHolder;
	private Camera mCamera = null;
	private Camera.Parameters parameters;

	private enum State {
		PREVIEW, SUCCESS, DONE
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capture);

		Log.d(TAG, "MMM");
		Intent intent = getIntent();

		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);

		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}

	private void setTabPhotoBuy() {
		tabPosition = 1;
		//viewfinderView.setPosition(tabPosition);
//						handler.clear();
		if (handler != null) {
			Message quitMsg = Message.obtain(handler.decodeThread.getHandler(), R.id.quit);
			quitMsg.sendToTarget();
		}else {
			isCameraReady = false;
		}

//		addPictureSizeParameters(mCamera.getParameters());

		//viewfinderView.setMiddleBmp(null);
		//viewfinderView.postInvalidate();
	}




	@Override
	protected void onResume() {
		super.onResume();

		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		viewfinderView.setVisibility(View.VISIBLE);
		surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					initCamera(surfaceHolder);
				}
			});
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}


		vibrate = true;

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	/*
	 * （非 Javadoc）
	 *
	 * @see android.app.Activity#onStop()
	 */
	@Override
	protected void onStop() {
		// TODO 自动生成的方法存根
		super.onStop();

		//onstop 来进行关闭。 618 活动web页取照片解决问题 yaoxing6
		inactivityTimer.shutdown();

		if (handler != null) {
			handler.quitSynchronously();
			handler.clear();
			handler = null;
		}

		try{
			CameraManager.get().closeDriver();

			/*mCamera.stopPreview();//停掉原来摄像头的预览
			mCamera.release();//释放资源
			mCamera = null;//取消原来摄像头*/
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		//onstop 来进行关闭。 618 活动web页取照片解决问题  yaoxing6
//        inactivityTimer.shutdown();

		viewfinderView = null;
		if(null!=mediaPlayer)
			mediaPlayer.release();
		mediaPlayer = null;
		if(null!=mProgressDialog && mProgressDialog.isShowing())
			mProgressDialog.dismiss();
		mProgressDialog = null;
//		mCaptureActivity = null;
	}

	/**
	 * 处理扫描结果
	 *
	 * @param result
	 */
	public void handleDecode(Result result) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		String resultString = result.getText();
		String formatString = result.getBarcodeFormat().name();
		Toast.makeText(this, resultString, Toast.LENGTH_SHORT).show();
		if (resultString.equals("")) {
		} else {
			/*Intent resultIntent = new Intent(CaptureActivity.this, BarcodeActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString(Intents.Scan.RESULT, resultString);
			bundle.putString(Intents.Scan.RESULT_FORMAT, formatString);
			resultIntent.putExtras(bundle);
			resultIntent.putExtra(Intents.Scan.ACTION, 1);
			startActivity(resultIntent);
//			JDMtaUtils.sendCommonData(getBaseContext(),getClass(), "Scan_Scan_Scan",BarcodeActivity.class,resultString);
			JDMtaUtils.sendCommonData(getBaseContext(), "Scan_Scan_Scan",resultString,"", CaptureActivity.this,"",BarcodeActivity.class ,"");
//			finish();*/


            //controller.processBarcodeScanned(resultString, formatString, BarcodeUtils.SCAN_TYPE);
		}

//		CaptureActivity.this.finish();
	}

	/**
	 * 重启扫码线程
	 *
	 * NOTE:
	 * 扫到文本或非白名单URL之后，在扫码页面弹出提示框，需要在提示框消失的时候重启扫码线程，在BarcodeUtils类中调用
	 *
	 */
	public void restartDecodeThread() {
		if (handler != null) {
			handler.quitSynchronously();
			handler.clear();
			handler = new CaptureActivityHandler(decodeFormats, characterSet);
		}
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			if (cameraPosition == 1) {//后置
				CameraManager.get().openDriver(surfaceHolder);
				mCamera = CameraManager.get().getCamera();
			}else if (cameraPosition == 0) {//前置
				if (mCamera != null) {
					CameraManager.get().stopPreview();
					mCamera.release();//释放资源
					mCamera = null;//取消原来摄像头
				}
				mCamera = Camera.open(1);
				CameraManager.get().setCamera(mCamera);
				try {
					mCamera.setPreviewDisplay(surfaceHolder);//通过surfaceview显示取景画面
					mCamera.setDisplayOrientation(CameraManager.getCameraDegrees(this,1,mCamera));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			parameters = addPictureSizeParameter(mCamera,cameraPosition);
			mCamera.setParameters(parameters);

			CameraManager.get().startPreview();

			isCameraReady = true;
		} catch (IOException ioe) {
			isCameraReady = false;
			return;
		} catch (RuntimeException e) {
			isCameraReady = false;
			return;
		}

		if (handler == null) {
			handler = new CaptureActivityHandler(decodeFormats, characterSet);
		}

		if (getIntent().getIntExtra("position",-1) == 1 || tabPosition == 1) {
			Message quitMsg = Message.obtain(handler.decodeThread.getHandler(), R.id.quit);
			quitMsg.sendToTarget();
		}
	}

	private Camera.Parameters addPictureSizeParameter(Camera camera,int cameraPosition) {
		Camera.Parameters param = camera.getParameters();
		Camera.Size targetPictureSize = null;
		List<Camera.Size> pictureSizes = param.getSupportedPictureSizes();
		targetPictureSize = pictureSizes.get(0);
		for (int i = 0; i < pictureSizes.size(); i++) {
			if (pictureSizes.get(i).width > targetPictureSize.width)
				targetPictureSize = pictureSizes.get(i);
		}
		param.setPictureSize(targetPictureSize.width, targetPictureSize.height);
		if (cameraPosition == 0) {
			/*List<Camera.Size> previewSizes = param.getSupportedPreviewSizes();
			Camera.Size targetPreviewSize = null;
			float previewRatio = (float) ((SurfaceView) findViewById(R.id.preview_view)).getHeight() / ((SurfaceView) findViewById(R.id.preview_view)).getWidth();
			float deltaRatio = Math.abs(previewRatio - (float) previewSizes.get(0).height / previewSizes.get(0).width);
			if (previewSizes != null && previewSizes.size() > 0) {
				targetPreviewSize = previewSizes.get(0);
				if (previewSizes.size() >= 1) {
					for (int i = 1; i < previewSizes.size(); i++) {
						if (Math.abs(previewRatio - (float) previewSizes.get(i).height / previewSizes.get(i).width) < deltaRatio) {
							deltaRatio = Math.abs(previewRatio - (float) previewSizes.get(i).height / previewSizes.get(i).width);
							targetPreviewSize = previewSizes.get(i);
						}
					}
				}
			}
			if (targetPreviewSize != null) {
				param.setPreviewSize(targetPreviewSize.width, targetPreviewSize.height);
			}*/
			List<Camera.Size> previewSizes = param.getSupportedPreviewSizes();
			Camera.Size targetPreviewSize = getOptimalPreviewSize(previewSizes,
					Math.max(getResources().getDisplayMetrics().widthPixels,getResources().getDisplayMetrics().heightPixels)
					,Math.min(getResources().getDisplayMetrics().widthPixels,getResources().getDisplayMetrics().heightPixels));
			/*Camera.Size targetPreviewSize = getOptimalPreviewSize(previewSizes,
					Math.max(((SurfaceView) findViewById(R.id.preview_view)).getWidth(),((SurfaceView) findViewById(R.id.preview_view)).getHeight())
					,Math.min(((SurfaceView) findViewById(R.id.preview_view)).getWidth(),((SurfaceView) findViewById(R.id.preview_view)).getHeight()));*/
			param.setPreviewSize(targetPreviewSize.width, targetPreviewSize.height);
			/*((SurfaceView) findViewById(R.id.preview_view)).setLayoutParams(new RelativeLayout.LayoutParams(
					targetPreviewSize.width > targetPreviewSize.height ? targetPreviewSize.height : targetPreviewSize.width
					, targetPreviewSize.width > targetPreviewSize.height ? targetPreviewSize.width : targetPreviewSize.height));*/
		}
		return param;
	}


	private Camera.Size getOptimalPreviewSize(List<Camera.Size> sizes, int w, int h) {
		final double ASPECT_TOLERANCE = 0.1;
		double targetRatio = (double) w / h;
		if (sizes == null) return null;

		Camera.Size optimalSize = null;
		double minDiff = Double.MAX_VALUE;

		int targetHeight = h;

		// Try to find an size match aspect ratio and size
		for (Camera.Size size : sizes) {
			double ratio = (double) size.width / size.height;
			if (Math.abs(ratio - targetRatio) > ASPECT_TOLERANCE) continue;
			if (Math.abs(size.height - targetHeight) < minDiff) {
				optimalSize = size;
				minDiff = Math.abs(size.height - targetHeight);
			}
		}

		// Cannot find the one match the aspect ratio, ignore the requirement
		if (optimalSize == null) {
			minDiff = Double.MAX_VALUE;
			for (Camera.Size size : sizes) {
				if (Math.abs(size.height - targetHeight) < minDiff) {
					optimalSize = size;
					minDiff = Math.abs(size.height - targetHeight);
				}
			}
		}
		return optimalSize;
	}



	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		/*try {
			if (mCamera == null) {
				mCamera = CameraManager.get().getCamera();
			}
			Camera.Parameters parameters = mCamera.getParameters();
			parameters.setPictureFormat(PixelFormat.JPEG);
			*//*parameters.setPreviewSize(width, height); // 设置预览大小
			parameters.setPictureSize(width, height); // 设置保存的图片尺寸*//*
			if (CommonUtil.getSDKInt() >= 8) {
				mCamera.setDisplayOrientation(90);
			} else {
				if (getResources().getConfiguration().orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT) {
					parameters.set("orientation", "portrait");
					parameters.set("rotation", 90);
				}
			}
			mCamera.setParameters(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	@Override
	public void surfaceCreated(final SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					initCamera(holder);
				}
			});
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;
	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}


	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
//		if (playBeep && mediaPlayer != null) {
//			mediaPlayer.start();
//		}
//		if (vibrate) {
//			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
//			vibrator.vibrate(VIBRATE_DURATION);
//		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		@Override
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};




	/**
	 * This class handles all the messaging which comprises the state machine for
	 * capture.
	 */
	public final class CaptureActivityHandler extends Handler {

		private DecodeThread decodeThread;
		private State state;

		public CaptureActivityHandler(Vector<BarcodeFormat> decodeFormats, String characterSet) {
			decodeThread = new DecodeThread(CaptureActivity.this, decodeFormats, characterSet,
					new ViewfinderResultPointCallback(CaptureActivity.this.getViewfinderView()));
			decodeThread.start();
			state = State.SUCCESS;
			// Start ourselves capturing previews and decoding.
			try{
				/*parameters = updateParameter(mCamera);
				mCamera.setParameters(parameters);*/
				CameraManager.get().startPreview();
			}catch (Exception e){

			}
			restartPreviewAndDecode();
		}

		@Override
		public void handleMessage(Message message) {
			Log.d(TAG, "handleMessage");
			switch (message.what) {
				case R.id.auto_focus:
					// Log.d(TAG, "Got auto-focus message");
					// When one auto focus pass finishes, start another. This is the
					// closest thing to
					// continuous AF. It does seem to hunt a bit, but I'm not sure what
					// else to do.
					if (state == State.PREVIEW) {
						try{
							CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
						}catch (Exception e){
							e.printStackTrace();
						}
					}
					break;
				case R.id.restart_preview:
					Log.d(TAG, "Got restart preview message");
					restartPreviewAndDecode();
					break;
				case R.id.decode_succeeded:
					Log.d(TAG, "Got decode succeeded message");
					state = State.SUCCESS;
					Bundle bundle = message.getData();

					/***********************************************************************/
					// Bitmap barcode = bundle == null ? null : (Bitmap) bundle
					// .getParcelable(DecodeThread.BARCODE_BITMAP);

                    CaptureActivity.this.handleDecode((Result) message.obj);
                    break;
                case R.id.decode_failed:
                    // We're decoding as fast as possible, so when one decode fails,
                    // start another.
                    state = State.PREVIEW;
                    try{
                        CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
                    }catch (Exception e){
                        e.printStackTrace();
                        return;
                    }
                    break;
                case R.id.return_scan_result:
                    CaptureActivity.this.setResult(Activity.RESULT_OK, (Intent) message.obj);
                    CaptureActivity.this.finish();
                    break;
                case R.id.launch_product_query:
                    String url = (String) message.obj;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    CaptureActivity.this.startActivity(intent);
                    break;
            }
        }

		public void quitSynchronously() {
			state = State.DONE;
			try{
				CameraManager.get().stopPreview();
			}catch (Exception e){
				e.printStackTrace();
			}
			Message quit = Message.obtain(decodeThread.getHandler(), R.id.quit);
			quit.sendToTarget();
			try {
				decodeThread.join();
			} catch (InterruptedException e) {
				// continue
			}

			// Be absolutely sure we don't send any queued up messages
			removeMessages(R.id.decode_succeeded);
			removeMessages(R.id.decode_failed);
		}

		private void restartPreviewAndDecode() {
			if (state == State.SUCCESS) {
				state = State.PREVIEW;
				try{
					CameraManager.get().requestPreviewFrame(decodeThread.getHandler(), R.id.decode);
					CameraManager.get().requestAutoFocus(this, R.id.auto_focus);
				}catch (Exception e){
					e.printStackTrace();
				}
				CaptureActivity.this.drawViewfinder();
			}
		}


		public void clear()
		{
			if(null!=decodeThread)
				decodeThread.interrupt();
			decodeThread = null;
		}
	}

}